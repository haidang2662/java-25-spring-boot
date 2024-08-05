const quizes = [
    {
        id: 1,
        question: "1 + 1 = ?",
        answers: [1, 2, 3, 4],
    },
    {
        id: 2,
        question: "2 + 2 = ?",
        answers: [2, 3, 4, 5],
    },
    {
        id: 3,
        question: "3 + 3 = ?",
        answers: [3, 4, 5, 6],
    },
];

document.getElementById('btn').addEventListener('click', function() {
    // Lấy tất cả các phần tử câu hỏi
    const quizItems = document.querySelectorAll('.quiz-item');
    
    quizItems.forEach(quizItem => {
        // Lấy tất cả các đáp án của câu hỏi hiện tại
        const answers = quizItem.querySelectorAll('.quiz-answer-item input');
        
        // Tạo chỉ số ngẫu nhiên để chọn đáp án
        const randomIndex = Math.floor(Math.random() * answers.length);
        
        // Chọn đáp án ngẫu nhiên
        answers[randomIndex].checked = true;
    });
});
