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
        "url" : host + port + "/chat",
        "method" : "GET"
    };

    $.ajax(setting).done(function (response) {
        $("#msg").html(response.replace(/\n/g, "<br />", "<hr />"))
        $("#msg").scrollTop($("#msg")[0].scrollHeight);
    }).fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
    });
}*/

/*function say() {
    var msg = $('#messageForm').serialize();
    //var name = $('#nameForm').serialize();

    var setting = {
        "method" : "POST",
        "crossDomain" : true,
        "url" : host + port + "/chat",
        "data" : msg
    };

    //clearFormMsg();
    $.ajax(setting).done(function (response) {
        loadHistory();
        //online();
    }).fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
    });
}*/
/*function clearFormMsg() {
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

//clearFormMsg();
loadHistory();
//online();
//loadMessageHistory
setInterval(loadHistory, 10000);
//setInterval(online, 10000);
