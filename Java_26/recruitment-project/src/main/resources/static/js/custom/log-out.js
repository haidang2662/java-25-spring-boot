$(document).ready(function () {

    $("#log-out-btn").click(async function () {

        if (!confirm("Do you want to log out ? ")) {
            return;
        };

        await $.ajax({
            url: "/api/v1/authentications/logout",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            headers: {"Authorization": "Bearer " + localStorage.getItem('accessToken')},
            success: function () {},
            error: function () {}
        });

        localStorage.removeItem('accessToken');
        localStorage.removeItem('refreshToken');
        localStorage.removeItem('account');
        localStorage.removeItem('account-info');
        location.href = "/";

    });

});