package com.example;

import nl.altindag.ssl.SSLFactory;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.security.PublicKey;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.HexFormat;
import java.util.stream.Stream;

/*
1. HttpClient (Java 11)
2. RestTemplate (Spring)
3. WebClient (Spring)
 */
@Component
public class Server2Client {


    public String getHelloFromServer2() throws IOException, InterruptedException {
        System.setProperty("javax.net.debug", "ssl,handshake");

        var sslConfig = SSLFactory.builder()
                .withDefaultTrustMaterial() // cacerts
                .withTrustMaterial(Path.of("src/main/resources/s1_ts.p12"), "admin123".toCharArray())
                .withIdentityMaterial(Path.of("src/main/resources/s1_ks.p12"), "admin123".toCharArray())
                .build().getSslContext();

        var http = HttpClient.newBuilder()
                .sslContext(sslConfig)
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://server2:8443/hello"))
//                .setHeader("Content-Type", "application/json")
//                .setHeader("Accept", "application/json")
//                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());

        response.headers().map().forEach((k, v) -> System.out.println(k + ":" + v));

        response.sslSession().ifPresent(ssl -> {
            System.out.println("ssl.getCipherSuite(): " + ssl.getCipherSuite());
            System.out.println("ssl.getCipherSuite(): " + ssl.getProtocol());
            try {
                Stream.of(ssl.getPeerCertificates())
                        .map(cert -> (X509Certificate) cert)
                        .forEach(c -> {
                            try {
                                c.checkValidity();
                                PublicKey key = c.getPublicKey();
                                System.out.println(HexFormat.of().formatHex(key.getEncoded()));
                            } catch (CertificateExpiredException e) {
                                e.printStackTrace();
                            } catch (CertificateNotYetValidException e) {
                                e.printStackTrace();
                            }
                        });
            } catch (SSLPeerUnverifiedException e) {
                e.printStackTrace();
            }
        });
        return response.body();

    }
}
