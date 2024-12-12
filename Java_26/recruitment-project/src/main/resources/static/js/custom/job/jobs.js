$(document).ready(function () {

    $(".delete-job-btn").click(function (event) {
        const confirmResult = confirm("Do you want to delete this job?");
        if (!confirmResult) {
            return;
        }

        const jobId = $(event.currentTarget).attr("job-id");
        console.log(jobId);

        $.ajax({
            url: '/api/v1/jobs/' + jobId,
            type: 'DELETE',
            contentType: "application/json; charset=utf-8",
            success: function () {
                showToast("Delete job successfully" , SUCCESS_TOAST)
                setTimeout(() => {
                    location.reload();// refresh lại trang web
                }, 1000);
            },
            error: function (err) {
                showToast("Delete job failed" , ERROR_TOAST)
            }
        });

    });

    // Lay thong tin chi tiet cua 1 dau sach
    async function getJobDetails(id) {
        let job = null;
        await $.ajax({
            url: '/api/v1/jobs/' + id,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                job = data;
            },
            error: function (err) {
                alert(err.responseJSON.error);
            }
        });
        return job;
    }

});