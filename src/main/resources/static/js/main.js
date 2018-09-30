var host = "http://localhost:";
var port = 8080;
//var hostHeroku = "https://webchat-heroku.herokuapp.com";

/*function online() {
    var setting = {
        "crossDomain" : true,
        "url" : host + port + "/online",
        "method" : "GET"
    };

    $.ajax(setting).done(function (response) {
        $("#users").html(response.replace(/\n/g, "<br />"))
        $("#users").scrollTop($("#users")[0].scrollHeight);
    }).fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
    });
}*/

/*function loadHistory() {
    var setting = {
        "crossDomain" : true,
        "url" : host + port + "/allMessages",
        "method" : "GET"
    };

    $.ajax(setting).done(function (response) {
        $("#msgArea").html(response.replace(/\n/g, "<br />", "<hr />"))
        $("#msgArea").scrollTop($("#msgArea")[0].scrollHeight);
    }).fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
    });
}*/

/*function say() {
    var msg = $('#messageForm').serialize();
    var name = $('#nameForm').serialize();

    var setting = {
        "method" : "POST",
        "crossDomain" : true,
        "url" : host + port + "/say",
        "data" : name + "&" + msg
    };

    clearFormMsg();
    $.ajax(setting).done(function (response) {
        loadHistory();
        online();
    }).fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
    });
}*/
/*
function clearFormMsg() {
    $('#messageForm').submit(function () {
        var form = this;
        form.reset();
    });
}*/

/*function login() {

    var name = $('#nameForm').serialize();

        var setting = {
            "method": "POST",
            "crossDomain": true,
            "url":  host + port + "/login",
            "data": name
        };

    $.ajax(setting).done(function (response) {
        loadHistory();
        online();
    }).fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
    });
}*/

clearFormMsg();
loadHistory();
online();
setInterval(loadHistory, 10000);
setInterval(online, 10000);