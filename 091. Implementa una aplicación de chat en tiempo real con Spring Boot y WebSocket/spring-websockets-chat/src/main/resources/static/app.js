let stompClient = null;
let userName;
let textarea;
let userlist;

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

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function showMsg(response) {
    const input = response.body;
    console.log("Client received: " + input);

    let line = "";
    let message = JSON.parse(input);
    console.log("message: " + message);

    line = `${message.username} : ${message.body} \n`;
    textarea.value += "" + line;

}
function updateUserList(response) {
    const input = response.body;
    let line = "";
    let message = JSON.parse(input);
    console.log("message: " + message);

    line = "Users:\n";
    console.log(message.body)
    let users = message.body.split(":")[1];
    users.split(",").forEach(user => line += `- ${user}\n`)
    userlist.value = line;
}

const submitJoin = () => {
    let input = document.getElementById("input");
    let name = document.getElementById("name");
    let join = document.getElementById("join");
    if (name.value.length <= 0)
        return;

    let message = {};
    message.username = name.value;
    let messageJson = JSON.stringify(message);
    console.log("before send");
    stompClient.send("/app/join", {}, messageJson);
    console.log("after send");

    name.disabled = true;
    join.disabled = true;
    input.disabled = false;
    userName = name.value;

};
const sendMessage = () => {
    let input = document.getElementById("input");
    let message = {};
    message.username = userName;
    message.body = input.value;
    let messageJson = JSON.stringify(message);
    stompClient.send("/app/chat", {}, messageJson);
    input.value = "";
};


function connect() {
    const socket = new SockJS('/websocket-server');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);

        const subscription = stompClient.subscribe('/topic/responses', function (response) {
            showMsg(response, 'table-success');
        });
        const subscription2 = stompClient.subscribe('/topic/users', function (response) {
            updateUserList(response);
        });

        stompClient.subscribe('/topic/errors', function (response) {
            showMsg(response, 'table-danger');
            console.log('Client unsubscribes: ' + subscription);
            subscription.unsubscribe({});
        });
    });
}

$(function () {
    $("form").on('submit', (e) => { e.preventDefault(); });
    $( "#connect" ).click(() => { connect(); });
    $( "#disconnect" ).click(() => { disconnect(); });
    $( "#join" ).click(() => { submitJoin(); });
    $( "#sendChat" ).click(() => { sendMessage(); });
    $( "#name" ).focus();
    userlist = document.getElementById("userlist");
    textarea = document.getElementById("textarea");
});