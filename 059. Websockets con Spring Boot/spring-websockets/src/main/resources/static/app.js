let stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#send").prop("disabled", !connected);

    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $('#output').val('');
    $("#responses").html("");
}

function connect() {
    const socket = new SockJS('/websocket-server');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);

        stompClient.subscribe('/app/subscribe', function (response) {
            showMsg(response, 'table-info');
        });

        const subscription = stompClient.subscribe('/queue/responses', function (response) {
            showMsg(response, 'table-success');
        });

        stompClient.subscribe('/queue/errors', function (response) {
            showMsg(response, 'table-danger');

            console.log('Client unsubscribes: ' + subscription);
            subscription.unsubscribe({});
        });

        stompClient.subscribe('/topic/periodic', function (response) {
            showMsg(response, 'table-secondary');
        });

    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMsg() {
    const output = $("#output").val();
    // stompClient.send("/app/request", {}, JSON.stringify({'name': output}));
    stompClient.send("/app/request", {}, output);
}

function showMsg(response, clazz) {
    const input = response.body;
    console.log("Client received: " + input);
    $("#responses").append("<tr class='" + clazz + "'><td>" + input + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => { e.preventDefault(); });
    $( "#connect" ).click(() => { connect(); });
    $( "#disconnect" ).click(() => { disconnect(); });
    $( "#send" ).click(() => { sendMsg(); });
});

