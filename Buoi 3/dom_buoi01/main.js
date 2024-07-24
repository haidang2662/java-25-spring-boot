// 1. Truy cập vào thẻ h1 có id=“heading” thay đổi màu chữ thành ‘red’, và in hoa nội dung của thẻ đó

const heading = document.getElementById('heading');
console.log(heading);

const heading1 = document.querySelector('#heading');
console.log(heading1);

heading.style.color = 'red' ;
heading.style.textTransform = 'uppercase';

// 2. Thay đổi màu chữ của tất cả thẻ có class “para” thành màu “blue” và có font-size = “20px”

const paras = document.querySelectorAll('.para');
console.log(paras);
// for (let i = 0; i < paras.length; i++) {
//     paras[i].style.color = 'blue';
//     paras[i].style.fontSize = '20px'; 
// }
paras.forEach(para => {
    para.style.color = 'blue';
    para.style.fontSize = '20px'; 
})

// 3. Thêm thẻ a link đến trang ‘facebook’ ở đằng sau thẻ có class “para-3”

const link = document.createElement('a');
console.log(link);
link.href = 'http://facebook.com';
link.innerHTML = "<span>Facebook</span>" ; // innerText , innerHTML
console.log(link);

const content = document.querySelector(".content");
document.body.insertBefore(link , content);
// 4. Thay đổi nội dung của thẻ có id=“title” thành “Đây là thẻ tiêu đề”

const title = document.getElementById("title");
title.innerText = "Đây là thẻ tiêu đề";

// 5. Thêm class “text-bold” vào thẻ có class=“description” (định nghĩa class “text-bold” có tác dụng in đậm chữ)

const description = document.querySelector(".description");
description.classList.add("text-bold");

// 6. Thay thế thẻ có class=“para-3” thành thẻ button có nội dung là “Click me”

const btn = document.createElement('button');
btn.innerText = "Click me";
console.log(btn);

const p3 = document.querySelector(".para-3");
document.body.replaceChild(btn,p3);

// 7. Copy thẻ có class=“para-2” và hiển thị ngay đằng sau thẻ đó

const p2 = document.querySelector('.para-2');
const p2Copy = p2.cloneNode(true);
document.body.insertBefore(p2Copy , btn);

// 8. Xóa thẻ có class=“para-1”

const p1 = document.querySelector('.para-1');
document.body.removeChild(p1);