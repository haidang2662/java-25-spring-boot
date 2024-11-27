$(document).ready(function () {

    const accountInfo = JSON.parse(localStorage.getItem("account-info"));
    const account = JSON.parse(localStorage.getItem("account"));

    // chưa login
    if (!accountInfo) {
        return;
    }

    // login rồi
    $("#btn-login").hide();

    $("#notification-btn").toggleClass('d-none');
    $("#header-account-info").toggleClass('d-none');

    if (account?.role === COMPANY_ROLE) {
        $("#header-job-posting-btn").show();
    } else {
        $("#header-job-posting-btn").hide();
    }

    $("#header-account-info .dropdown-toggle .name").text(`${accountInfo.name || "My account"}`);

})