$(document).ready(async function () {

    let totalPage;
    let totalRecord;
    let paging;
    let pageIndex = 0;
    let pageSize = 10;
    await getInterviewsData({});

    $(document).on("click", ".btn-delete", function (event) {
        const jobId = $(event.currentTarget).attr("data-id"); // Lấy job ID từ thuộc tính id

        const confirmResult = confirm("Do you want to delete this job?");
        if (!confirmResult) {
            return;
        }

        $.ajax({
            url: '/api/v1/jobs/' + jobId,
            type: 'DELETE',
            contentType: "application/json; charset=utf-8",
            success: async function () {
                showToast("Delete job successfully", SUCCESS_TOAST);
                await getInterviewsData({});
            },
            error: function (err) {
                showToast(err.responseJSON.message, ERROR_TOAST);
            }
        });
    });

    $(document).on("click", ".btn-update", function (event) {
        const jobId = $(event.currentTarget).attr("data-id");
        window.location.href = `/companies/jobs/job-updating/${jobId}`;
    });

    async function getInterviewsData(request) {
        // Disable nút search và hiển thị spinner
        $("#search-interview-btn").prop("disabled", true);
        $("#spinner-search").removeClass('d-none');
        $(".page-item .page-link").addClass('disabled');

        // Hiển thị spinner ở bảng
        $("#interview-table tbody").html('<tr><td colspan="9" class="text-center"><div class="spinner-border" role="status"><span class="visually-hidden">Loading...</span></div></td></tr>');

        await $.ajax({
            url: "/api/v1/interviews",
            type: "GET",
            data: {
                pageIndex: pageIndex,
                pageSize: pageSize,
                ...request
            },
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                renderInterviewTable(data);

            },
            error: function () {
                showToast("Get job failed", ERROR_TOAST);
            }
        });

        // Ẩn spinner và kích hoạt lại nút search
        $("#spinner-search").addClass('d-none');
        $("#search-interview-btn").prop("disabled", false);
        $(".page-item .page-link").removeClass('disabled');
    }

    function renderInterviewTable(data) {
        const paginationHtml = $("#interview-paging .pagination");
        const tableContent = $("#interview-table tbody");
        const totalRecordHtml = $(".total-record");

        tableContent.empty();
        paginationHtml.empty();
        totalRecordHtml.empty();
        if (!data) {
            return;
        }

        const interviews = data.data;
        totalPage = data.totalPage;
        totalRecord = data.totalRecord;
        paging = data.pageInfo;
        pageIndex = paging.pageNumber;

        if (!interviews || interviews.length === 0) {
            return;
        }

        for (let i = 0; i < interviews.length; i++) {
            interviews[i]['stt'] = pageIndex * pageSize + i + 1;
        }


        for (let i = 0; i < interviews.length; i++) {
            const interview = interviews[i];
            let tr = "<tr>" +
                "<td>" + interview.stt + "</td>" +
                "<td>" + interview.candidateName + "</td>" +
                "<td>" + interview.jobTitle + "</td>" +
                "<td>" + interview.interviewEmailSentAt + "</td>" +
                "<td>" + interview.interviewAt + "</td>" +
                "<td>" + interview.type + "</td>" +
                "<td>" + interview.status + "</td>" +
                "<td>" +
                "<div class='action-icons d-flex align-items-center'>" + // Thêm container Flexbox
                getInterviewActionButtons(interview) +
                "</div>" +
                "</td>" +
                "</tr>";

            tableContent.append(tr);

            //  EXPIRED
            $(".btn-expire").off("click").click(async function (event) {
                const toggleInput = $(event.currentTarget);
                const jobId = toggleInput.attr("data-id");
                try {
                    await $.ajax({
                        url: '/api/v1/jobs/' + jobId + '/status',
                        type: 'PATCH',
                        data: JSON.stringify({ status: JOB_STATUS.EXPIRED }),
                        contentType: 'application/json; charset=utf-8',
                    });
                    showToast("Job marked as expired successfully", SUCCESS_TOAST);
                    await getInterviewsData({});
                } catch (err) {
                    showToast(err.responseJSON.message, ERROR_TOAST);
                }
            });

            //  PUBLISH
            $(".btn-publish").off("click").click(async function (event) {
                const toggleInput = $(event.currentTarget);
                const jobId = toggleInput.attr("data-id");
                try {
                    await $.ajax({
                        url: '/api/v1/jobs/' + jobId + '/status',
                        type: 'PATCH',
                        data: JSON.stringify({ status: JOB_STATUS.PUBLISH }),
                        contentType: 'application/json; charset=utf-8',
                    });
                    showToast("Job marked as publish successfully", SUCCESS_TOAST);
                    await getInterviewsData({});
                } catch (err) {
                    showToast(err.responseJSON.message, ERROR_TOAST);
                }
            });

            // UNPUBLISHED
            $(".btn-unpublish").off("click").click(async function (event) {
                const toggleInput = $(event.currentTarget);
                const jobId = toggleInput.attr("data-id");

                try {
                    await $.ajax({
                        url: '/api/v1/jobs/' + jobId + '/status',
                        type: 'PATCH',
                        data: JSON.stringify({ status: JOB_STATUS.UNPUBLISHED }),
                        contentType: 'application/json; charset=utf-8',
                    });
                    showToast("Job marked as unpublisged successfully", SUCCESS_TOAST);
                    await getInterviewsData({});
                } catch (err) {
                    showToast(err.responseJSON.message, ERROR_TOAST);
                }
            });

        }

        paginationHtml.append("<li class=\"page-item go-to-first-page\"><a class=\"page-link\" href=\"#\"><i class=\"fa-solid fa-angles-left\"></i></a></li>");
        paginationHtml.append("<li class=\"page-item previous-page\"><a class=\"page-link\" href=\"#\"><i class=\"fa-solid fa-chevron-left\"></i></a></li>");
        for (let i = 1; i <= totalPage; i++) {
            const page = "<li class='page-item " + (i === paging.pageNumber + 1 ? "active" : '') + "' page='" + (i - 1) + "'><a class='page-link' href='#'>" + i + "</a></li>";
            paginationHtml.append(page);
        }

        paginationHtml.append("<li class=\"page-item next-page\"><a class=\"page-link\" href=\"#\"><i class=\"fa-solid fa-chevron-right\"></i></a></li>");
        paginationHtml.append("<li class=\"page-item go-to-last-page\"><a class=\"page-link\" href=\"#\"><i class=\"fa-solid fa-angles-right\"></i></a></li>");


        totalRecordHtml.append("<span><span class='fw-bold'>Total records</span>: " + totalRecord + "</span>")

        // Xóa sự kiện cũ trước khi thêm sự kiện mới
        $(".page-item").off("click").click(async function (event) {
            const newPageIndex = $(event.currentTarget).attr("page");
            if (!newPageIndex || isNaN(newPageIndex)) {
                return;
            }
            pageIndex = parseInt(newPageIndex);
            await getInterviewsData({});
        });

        $(".go-to-first-page").click(async function () {
            pageIndex = 0;
            await getInterviewsData({});
        });

        $(".go-to-last-page").click(async function () {
            pageIndex = totalPage - 1;
            await getInterviewsData({});
        });

        $(".previous-page").click(async function () {
            if (pageIndex === 0) {
                return;
            }
            pageIndex = pageIndex - 1;
            await getInterviewsData({});
        });

        $(".next-page").click(async function () {
            if (pageIndex === totalPage - 1) {
                return;
            }
            pageIndex = pageIndex + 1;
            await getInterviewsData({});
        });
    }

    function getInterviewActionButtons(job) {
        const UPDATE_BUTTON =
            "<span role='button' class='text-primary btn-update me-2 btn-update' data-bs-toggle='tooltip' title='Update' data-id='" + job.id + "'>" +
            "    <i class='fa-solid fa-pencil'></i>" +
            "</span>";
        const DELETE_BUTTON =
            "<span role='button' class='text-danger btn-delete me-2 btn-delete' data-bs-toggle='tooltip' title='Delete' data-id='" + job.id + "'>" +
            "    <i class='fa-solid fa-trash'></i>" +
            "</span>";
        const PUBLISH_BUTTON =
            "<span role='button' class='text-success me-2 btn-publish' data-id='" + job.id + "' data-bs-toggle='tooltip' title='Publish'>" +
            "    <i class='fa-solid fa-check'></i> " +
            "</span>";
        const UNPUBLISHED_BUTTON =
            "<span role='button' class='text-warning me-2 btn-unpublish' data-id='" + job.id + "' data-bs-toggle='tooltip' title='Unpublished'>" +
            "    <i class='fa-solid fa-x'></i> " +
            "</span>";
        const EXPIRE_BUTTON =
            "<span role='button' class='text-secondary me-2 btn-expire' data-id='" + job.id + "' data-bs-toggle='tooltip' title='Expire'>" +
            "    <i class='fa-solid fa-clock'></i> " +
            "</span>";

        let buttons = "";
        switch (job.status) {
            case "DRAFT":
                buttons = UPDATE_BUTTON + DELETE_BUTTON + PUBLISH_BUTTON;
                break;
            case "PUBLISH":
                buttons = UNPUBLISHED_BUTTON + EXPIRE_BUTTON;
                break;
            case "UNPUBLISHED":
                buttons = UPDATE_BUTTON + PUBLISH_BUTTON;
                break;
            case "EXPIRED":
                buttons = UPDATE_BUTTON + PUBLISH_BUTTON;
                break;
        }

        return buttons;
    }

    $("#search-interview-btn").click(async function () {

        // Lấy dữ liệu từ form
        const formData = $("#search-interview-form").serializeArray();
        let searchInterview = {};
        for (let i = 0; i < formData.length; i++) {
            searchInterview[formData[i].name] = formData[i].value;
        }

        await getInterviewsData(searchInterview);

    });

    $("#reset-search-interview-btn").click(async function () {
        // Reset form
        $("#search-interview-form").trigger("reset");

        // Lấy lại dữ liệu
        await getInterviewsData({});
    });

})
;