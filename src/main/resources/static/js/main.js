
function online() {
    var setting = {
        "crossDomain" : true,
        "url" : "http://localhost:8080/chat/online",
        "method" : "GET"
    };

    $.ajax(setting).done(function (response) {
        $("#users").html(response.replace(/\n/g, "<br />"))
        $("#users").scrollTop($("#users")[0].scrollHeight);
    }).fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
    });
}

function loadHistory() {

    var setting = {
        "crossDomain" : true,
        "url" : "http://localhost:8080/chat/chat",
        "method" : "GET"
    };

    $.ajax(setting).done(function (response) {
        $("#msgArea").html(response.replace(/\n/g, "<br />", "<hr />"))
        $("#msgArea").scrollTop($("#msgArea")[0].scrollHeight);
    }).fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
    });
}

function say() {
    var msg = $('#messageForm').serialize();
    var name = $('#nameForm').serialize();

    var setting = {
        "method" : "POST",
        "crossDomain" : true,
        "url" : "http://localhost:8080/chat/say",
        "data" : name + "&" + msg
    };

    $.ajax(setting).done(function (response) {
        loadHistory();
        online();
    }).fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
    });
}

function logout() {
    var name = $('#nameForm').serialize();

        var setting = {
            "method": "POST",
            "crossDomain": true,
            "url": "http://localhost:8080/chat/logout",
            "data": name
        };
    $.ajax(setting).done(function (response) {
        loadHistory();
        online();
    }).fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
    });
}

function login() {

    var name = $('#nameForm').serialize();

        var setting = {
            "method": "POST",
            "crossDomain": true,
            "url": "http://localhost:8080/chat/login",
            "data": name
        };

    $.ajax(setting).done(function (response) {
        loadHistory();
        online();
    }).fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + " " + jqXHR.statusText + ". " + jqXHR.responseText);
    });
}

loadHistory();
online();
setInterval(loadHistory, 10000);
setInterval(online, 10000);