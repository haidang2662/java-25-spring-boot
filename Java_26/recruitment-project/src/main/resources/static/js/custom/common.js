function showToast(message, type) {
    let color;
    if (type === SUCCESS_TOAST) {
        color = "#198754";
    } else if (type === ERROR_TOAST) {
        color = "#dc3545";
    } else {
        color = "#fd7e14";
    }

    Toastify({
        text: message,
        duration: 3000,
        // destination: "https://github.com/apvarun/toastify-js",
        newWindow: true,
        close: false,
        gravity: "top", // `top` or `bottom`
        position: "right", // `left`, `center` or `right`
        stopOnFocus: true, // Prevents dismissing of toast on hover
        style: {
            background: color,
        },
        onClick: function () {
        } // Callback after click
    }).showToast();
}

function handleResponseError(err, customMessage) {
    // console.log(err);
    if (!err) {
        return;
    }
    // const code = err?.responseJSON?.code;
    const message = err?.responseJSON?.message || customMessage;

    if (!message) {
        return;
    }

    showToast(message, ERROR_TOAST);
}

function handleResponseErrorVer2(jqXHR, textStatus, errorThrown) {
    const url = jqXHR.url;
    console.log(url);
    if (url === "") {
        ///....
    }
}

const ajaxSetupObj = {
    error: function (jqXHR, textStatus, errorThrown) {
        handleResponseErrorVer2(jqXHR, textStatus, errorThrown);
    }
};
const accessToken = localStorage.getItem('accessToken');
if (accessToken) {
    ajaxSetupObj['headers'] = {
        "Authorization": "Bearer " + accessToken
    }
}
$.ajaxSetup(ajaxSetupObj);

function fillSidebarLinks() {
    const account = localStorage.getItem('account');
    const currentRole = account ? JSON.parse(account)?.role : '';
    for (let i = 0; i < SIDEBAR_APPLICATION_PATHS.length; i++) {
        const element = SIDEBAR_APPLICATION_PATHS[i];
        if (currentRole !== element.role) {
            continue;
        }
        $('.user-sidebar .' + element.sidebarClass + ' a').attr("href", element.path);
    }
}

function activateCurrentSidebarMenu() {
    const currentPath = window.location.pathname;
    for (let i = 0; i < SIDEBAR_APPLICATION_PATHS.length; i++) {
        const element = SIDEBAR_APPLICATION_PATHS[i];
        if (element.path === currentPath) {
            $('.user-sidebar .' + element.sidebarClass).toggleClass('active');
        }
    }
}

$(document).ready(function () {
    fillSidebarLinks();
    activateCurrentSidebarMenu();
});
