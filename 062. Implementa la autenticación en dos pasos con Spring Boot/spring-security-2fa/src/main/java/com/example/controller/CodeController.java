package com.example.controller;

import com.example.model.UserDetailsAdapter;
import com.example.utils.SecurityUtils;
import org.jboss.aerogear.security.otp.Totp;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/code")
public class CodeController {

    @GetMapping
    public String showForm(){
        return "code";
    }

    @PostMapping
    public String verifyCode(String code){
        try {
            UserDetailsAdapter userDetailsAdapter = (UserDetailsAdapter)
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Totp totp = new Totp(userDetailsAdapter.getAccount().getSecret());

            if (!isValidLong(code) || !totp.verify(code)) {
                throw new VerificationCodeException("invalid key");
            }

            SecurityUtils.setAuthentication();
            return "redirect:/home";
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (VerificationCodeException e) {
            e.printStackTrace();
        }
        return "redirect:/code?error";
    }

    private boolean isValidLong(String code) {
        try {
            Long.parseLong(code);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
