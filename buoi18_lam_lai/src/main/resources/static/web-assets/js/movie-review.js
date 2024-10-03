const stars = document.querySelectorAll(".rating .star");
const ratingValue = document.getElementById("rating-value");

let currentRating = 0;

stars.forEach((star) => {
    star.addEventListener("mouseover", () => {
        resetStars();
        const rating = parseInt(star.getAttribute("data-rating"));
        highlightStars(rating);
    });

    star.addEventListener("mouseout", () => {
        resetStars();
        highlightStars(currentRating);
    });

    star.addEventListener("click", () => {
        currentRating = parseInt(star.getAttribute("data-rating"));
        ratingValue.textContent = `Bạn đã đánh giá ${currentRating} sao.`;
        highlightStars(currentRating);
    });
});

function resetStars() {
    stars.forEach((star) => {
        star.classList.remove("active");
    });
}

function highlightStars(rating) {
    stars.forEach((star) => {
        const starRating = parseInt(star.getAttribute("data-rating"));
        if (starRating <= rating) {
            star.classList.add("active");
        }
    });
}

// Hien thi danh sach review
const reviewListEl = document.querySelector(".review-list");
const renderReviews = reviews => {
    let html = "";
    reviews.forEach(review => {
        html += `
            <div class="rating-item d-flex align-items-center mb-3 pb-3">
                <div class="rating-avatar">
                    <img src="${review.user.avatar}" alt="${review.user.name}">
                </div>
                <div class="rating-info ms-3">
                    <div class="d-flex align-items-center">
                        <p class="rating-name mb-0"><strong>${review.user.name}</strong></p>
                        <p class="rating-time mb-0 ms-2">${formatDate(review.createdAt)}</p></div>
                    <div class="rating-star">
                        <p class="mb-0 fw-medium">
                            <span class="rating-icon me-1"><i class="fa fa-star"></i></span>
                            <span>${review.rating}/10</span>
                        </p>
                    </div>
                    <p class="rating-content mt-1 mb-0 text-muted">${review.content}</p></div>
            </div>
        `
    });
    reviewListEl.innerHTML = html;
}

const formatDate = dateString => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = ("0" + (date.getMonth() + 1)).slice(-2); // 09 -> 09 , 011 -> 11
    const day = ("0" + date.getDate()).slice(-2);
    return `${day}/${month}/${year}`;
}

const formReviewEl = document.getElementById("form-review");
formReviewEl.addEventListener("submit", () => {

})

// Tạo review
const createReview = () => {

}

// Cập nhật review
const updateReview = () => {}

// Xóa review
const deleteReview = () => {}