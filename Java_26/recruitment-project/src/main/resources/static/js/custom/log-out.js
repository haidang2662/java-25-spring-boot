$(document).ready(function () {

    $("#log-out-btn").click(function () {

        if (!confirm("Do you want to log out ? ")) {
            return;
        };

        $.ajax({
            url: "/api/v1/authentications/logout",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            success: function () {
                localStorage.clear();
                location.href = "/";
            },
            error: function (err) {
                handleResponseError(err, "Log out failed")
            }
        });

    });

});