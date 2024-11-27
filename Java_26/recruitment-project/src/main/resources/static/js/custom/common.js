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
