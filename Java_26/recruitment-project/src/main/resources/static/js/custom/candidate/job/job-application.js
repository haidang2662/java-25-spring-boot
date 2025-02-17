$(document).ready(function () {
    const account = JSON.parse(localStorage.getItem("account"));
    if (!account) {
        location.href = "/login";
    }


});
