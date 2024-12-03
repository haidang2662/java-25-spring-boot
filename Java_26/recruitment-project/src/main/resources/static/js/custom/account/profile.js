$(document).ready(function () {

    const account = JSON.parse(localStorage.getItem("account"));
    if (!account) {
        window.location = "/login";
    }

    if (account.role === CANDIDATE_ROLE) {
        $("#company-profile-block").hide();
        $(".user-sidebar").toggleClass("d-none");
        $(".page-wrapper").toggleClass("p-0");
        $(".user-dashboard").toggleClass("px-5 mx-5");
    } else if (account.role === COMPANY_ROLE) {
        $("#candidate-profile-block").hide();
    } else {
    }


});