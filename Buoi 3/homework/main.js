// Câu 1 : Highlight tất cả các từ có độ dài lớn hơn hoặc bằng 5 ký tự trong đoạn văn (background = “yellow”)

    const paragraph = document.getElementById("content");
    const words = paragraph.innerText.split(/\s+/);
    const hightLightWord = words.map(word => {
        if(word.length >= 5){
            return `<span class = "hightLight">${word}</span>`;
        }
        return word;
    });
    paragraph.innerHTML = hightLightWord.join(" ");

// Câu 2 : Thêm link hiển thị text “facebook” link đến trang facebook.com ở sau thẻ p

    const link = document.createElement("a");
    link.href = "facebook.com";
    link.innerHTML = "<span>Facebook</span>";
    paragraph.insertAdjacentElement("afterend" , link);

// Câu 3 : Đếm số từ có trong đoạn văn. Tạo 1 thẻ div để hiển thị số từ

const wordCount = words.length;

const wordCountDiv = document.createElement("div");
wordCountDiv.textContent = `Số từ trong đoạn văn là ${wordCount}` ;
link.insertAdjacentElement("afterend" , wordCountDiv);

// Câu 4 : Thay thế ký hiệu (, - dấu phẩy) => 🤔 và (. - dấu chấm) => 😲

text = hightLightWord.join(" ");
text = text.replace(/,/g , "🤔").replace(/\./g , "😲");
paragraph.innerHTML = text;
