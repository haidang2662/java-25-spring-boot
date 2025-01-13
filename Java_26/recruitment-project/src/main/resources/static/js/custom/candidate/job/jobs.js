$(document).ready(async function () {

    const candidateJobSearchObjStr = localStorage.getItem("candidateJobSearchObj");
    const candidateJobSearchObj = candidateJobSearchObjStr ? JSON.parse(candidateJobSearchObjStr) : {};
    console.log(candidateJobSearchObj);

    // call ajax search job va render du lieu (Co phan trang)
    // sẽ call đúng vào API search job của company luôn (thêm điều kiện để phân biệt company và candidate)
    let totalPage;
    let totalRecord;
    let paging;
    let pageIndex = 0;
    let pageSize = 20;
    await getJobs(candidateJobSearchObj);

    async function getJobs(request) {
        // Disable nút search và hiển thị spinner
        // $("#search-job-btn").prop("disabled", true);
        // $("#spinner-search").removeClass('d-none');
        // $(".page-item .page-link").addClass('disabled');

        // Hiển thị spinner ở khối div bên phải
        // $("#job-table tbody").html('<tr><td colspan="9" class="text-center"><div class="spinner-border" role="status"><span class="visually-hidden">Loading...</span></div></td></tr>');

        await $.ajax({
            url: "/api/v1/jobs",
            type: "GET",
            data: {
                pageIndex: pageIndex,
                pageSize: pageSize,
                ...request
            },
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                renderJobs(data);
            },
            error: function () {
                showToast("Get job failed", ERROR_TOAST);
            }
        });

        // Ẩn spinner và kích hoạt lại nút search
        // $("#spinner-search").addClass('d-none');
        // $("#search-job-btn").prop("disabled", false);
        // $(".page-item .page-link").removeClass('disabled');
    }

    function renderJobs(data) {
        const paginationHtml = $("#job-paging .pagination");
        const tableContent = $("#candidate-jobs");
        const totalRecordHtml = $(".total-record");

        tableContent.empty();
        paginationHtml.empty();
        totalRecordHtml.empty();
        if (!data) {
            return;
        }

        const jobs = data.data;
        totalPage = data.totalPage;
        totalRecord = data.totalRecord;
        paging = data.pageInfo;
        pageIndex = paging.pageNumber;

        if (!jobs || jobs.length === 0) {
            return;
        }

        for (let i = 0; i < jobs.length; i++) {
            jobs[i]['stt'] = pageIndex * pageSize + i + 1;
        }


        for (let i = 0; i < jobs.length; i++) {
            const job = jobs[i];
            let jobBlock = `<div class="job-block col-lg-6 col-md-12 col-sm-12">
                            <div class="inner-box">
                                <div class="content">
                                    <span class="company-logo"><img src="images/resource/company-logo/1-1.png" alt=""></span>
                                    <h4><a href="#">${job.name}</a></h4>
                                    <ul class="job-info">
                                        <li><span class="icon flaticon-briefcase"></span> ${job.position}</li>
                                        <li><span class="icon flaticon-map-locator"></span> ${job.workingAddress}</li>
                                        <li><span class="icon flaticon-clock-3"></span> ${getTimeDifferenceInDays(new Date(job.createdAt), new Date())}</li>
                                        <li><span class="icon flaticon-money"></span> ${job.salaryTo ? `${job.salaryFrom} - ${job.salaryTo}` : job.salaryFrom}</li>
                                    </ul>
                                    <ul class="job-other-info">
                                        <li class="time">Full Time</li>
                                        <li class="privacy">Private</li>
                                        <li class="required">Urgent</li>
                                    </ul>
                                    <button class="bookmark-btn"><span class="flaticon-bookmark"></span></button>
                                </div>
                            </div>
                        </div>`;

            tableContent.append(jobBlock);

            //  favorite
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
                    await getJobs({});
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
            await getJobs({});
        });

        $(".go-to-first-page").click(async function () {
            pageIndex = 0;
            await getJobs({});
        });

        $(".go-to-last-page").click(async function () {
            pageIndex = totalPage - 1;
            await getJobs({});
        });

        $(".previous-page").click(async function () {
            if (pageIndex === 0) {
                return;
            }
            pageIndex = pageIndex - 1;
            await getJobs({});
        });

        $(".next-page").click(async function () {
            if (pageIndex === totalPage - 1) {
                return;
            }
            pageIndex = pageIndex + 1;
            await getJobs({});
        });
    }

});
