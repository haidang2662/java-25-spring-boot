$(document).ready(async function () {

    const account = JSON.parse(localStorage.getItem("account"));
    if (!account) {
        window.location = "/login";
    }
    if (account.role === CANDIDATE_ROLE) {
        $("#company-profile-block").hide();
        $(".user-sidebar").toggleClass("d-none");
        $(".page-wrapper").toggleClass("p-0");
        $(".user-dashboard").toggleClass("px-5 mx-5");
    }
    let chosenFile = null;

    const accountInfo = await getAccountDetail(account.id);
    localStorage.setItem("account", JSON.stringify(accountInfo));

    if (account.role === CANDIDATE_ROLE) {
        const candidateInfo = accountInfo.candidateModel;

        for (let prop in candidateInfo) {
            $("#candidate-info-form [name='" + prop + "']").val(candidateInfo[prop]);
        }
        if (candidateInfo.avatarUrl) {
            $(".img-avatar").attr("src", "/api/v1/files/account/" + candidateInfo.avatarUrl);
        }
    } else if (account.role === COMPANY_ROLE) {
        $("#candidate-profile-block").hide();

        //..... do data vao form
    }


    // $(".change-avatar-btn").click((event) => {
    //     event.preventDefault();
    //     $("#avatar-input").click();
    //     $("#avatar-error").text("");
    // });
    //
    $("#avatar-input").change(event => {
        const file = event.target.files[0];
        if (!file) {
            return;
        }
        if (file.size > MAX_AVATAR_FILE_SIZE) {
            $("#avatar-error").text("Avatar must be less than 5MB.")
            return;
        }
        chosenFile = file;
        const imageBlob = new Blob([chosenFile], {type: chosenFile.type});
        const imageUrl = URL.createObjectURL(imageBlob);
        $(".img-avatar").attr("src", imageUrl);
    });

    $(".account-info-saving-btn").click(() => {
        const formHtml = account.role === CANDIDATE_ROLE ? $("#candidate-info-form") : $("#company-info-form");
        const isValidForm = formHtml.valid();
        if (!isValidForm) {
            return;
        }
        const formData = new FormData();
        formData.append('accountRequest', JSON.stringify(getDataForm()));
        if (chosenFile) {
            formData.append('avatar', chosenFile, chosenFile.name);
        }
        $.ajax({
            url: `/api/v1/accounts/${account.id}`,
            type: 'PUT',
            processData: false,
            contentType: false,
            data: formData,
            success: function (response) {
                showToast("Save successfully", SUCCESS_TOAST);
                localStorage.setItem("account", JSON.stringify(response));
            }
        });
    })

    function getDataForm() {
        let formValues;
        if (account.role === CANDIDATE_ROLE) {
            formValues = $("#candidate-info-form").serializeArray();
        } else if (account.role === COMPANY_ROLE) {
            formValues = $("#company-info-form").serializeArray();
        }

        const accountObj = {};
        formValues.forEach(input => {
            accountObj[input.name] = input.value;
        });
        return accountObj;
    }

});