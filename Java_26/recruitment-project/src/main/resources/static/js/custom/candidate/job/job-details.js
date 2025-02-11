$(document).ready(async function () {
    const pathParts = window.location.pathname.split('/'); // Tách URL thành các phần
    const jobId = pathParts[pathParts.length - 1]; // Lấy phần tử cuối cùng

    if (!jobId) {
        window.href = '/404';
    }
    await loadJobDetails(jobId);
});

// Hàm load dữ liệu chi tiết của job từ API
async function loadJobDetails(jobId) {
    try {
        const job = await $.ajax({
            url: `/api/v1/jobs/${jobId}`,
            type: "GET",
            contentType: "application/json; charset=utf-8",
        });
        renderJobDetails(job);
    } catch (error) {
        if (error.status === 404) {
            window.location.href = "/404";
            return;
        }
        showToast(error.responseJSON.message, ERROR_TOAST);
    }
}

// Render thông tin chi tiết của job vào HTML
function renderJobDetails(job) {
    // Xóa các nút cũ trong .job-buttons
    $(".job-buttons").empty();

    let buttons = "";
    buttons = buttons.replaceAll("job-id", job.id);
    $(".job-buttons").append($(buttons));

    job.description = job.description.replaceAll("\r\n", "<br>").replaceAll("\n", "<br>");
    job.requirement = job.requirement.replaceAll("\r\n", "<br>").replaceAll("\n", "<br>");
    job.benefit = job.benefit.replaceAll("\r\n", "<br>").replaceAll("\n", "<br>");
    job.skills = job.skills.replaceAll("\r\n", "<br>").replaceAll("\n", "<br>");

    for (let key in job) {
        $("#" + key).html(job[key]);
    }
    $("#yearOfExperience").text(job.yearOfExperienceTo ? `${job.yearOfExperienceFrom} - ${job.yearOfExperienceTo}` : job.yearOfExperienceFrom);
    $("#salary").text(job.salaryTo ? `${job.salaryFrom} - ${job.salaryTo}` : job.salaryFrom);
    $("#workingTimeType").text(decodeJobWorkingTimeType(job.workingTimeType));
    $("#workingType").text(decodeJobWorkingType(job.workingType));
    $("#literacy").text(decodeJobLiteracy(job.literacy));
    $("#level").text(decodeJobLevel(job.level));
    $("#job-urgent").toggleClass(job.urgent ? 'd-block' : 'd-none');

    // company info
    const company = job.company;
    const alias = company.alias ? " (" + company.alias + ")" : "";
    $(".company-widget .company-logo img").attr("src", company.avatarUrl ? "/api/v1/files/avatar/" + company.avatarUrl : DEFAULT_AVATAR_URL);
    $(".company-widget .company-name").text(company.name + alias);
    $(".company-widget .profile-link").attr("href", "/companies/" + company.id);
    $(".company-widget .company-info .company-size span").text(company.employeeQuantity);
    $(".company-widget .company-info .company-created-at span").text(new Date(company.createdAt).getFullYear());
    $(".company-widget .company-info .phone span").text(company.phone);
    $(".company-widget .company-info .email span").text(company.email);
    $(".company-widget .company-info .location span").text(company.headQuarterAddress);
    $(".company-widget .company-website a").attr("href", company.website);
    $(".company-widget .company-website a").text(company.website);

}

function decodeJobWorkingTimeType(workingTimeType) {
    switch (workingTimeType) {
        case "FULL_TIME":
            return "Full time";
        case "PART_TIME":
            return "Part time";
    }
}

function decodeJobWorkingType(workingType) {
    switch (workingType) {
        case "OFFLINE":
            return "Offline";
        case "ONLINE":
            return "Online";
    }
}

function decodeJobLiteracy(literacy) {
    switch (literacy) {
        case "PROFESSOR":
            return "Professor";
        case "DOCTOR":
            return "Doctor";
        case "MASTER":
            return "Master";
        case "UNIVERSITY":
            return "University";
        case "COLLEGE":
            return "College";
        case "HIGH_SCHOOL":
            return "High School";
    }
}

function decodeJobLevel(level) {
    switch (level) {
        case "INTERN":
            return "Intern";
        case "JUNIOR":
            return "Junior";
        case "FRESHER":
            return "Fresher";
        case "SENIOR":
            return "Senior";
        case "MASTER":
            return "Master";
    }
}