// Câu 1. Tạo 1 thẻ p có id=“text”, có nội dung bất kỳ (có thể tạo bằng HTML hay Javascript - tùy chọn). Yêu cầu
// Đặt màu văn bản thành #777
// Đặt kích thước phông chữ thành 2rem
// Đặt nội dung HTML thành Tôi có thể làm <em> bất cứ điều gì </em> tôi muốn với JavaScript.

const text = document.getElementById('text');
console.log(text);
text.style.color = "#777";
text.style.fontSize = "2rem";
text.innerHTML = "Tôi có thể làm <em> bất cứ điều gì </em> tôi muốn với JavaScript" ;

// Câu 2. Sử dụng vòng lặp để đặt màu chữ cho tất cả thẻ li thành màu blue (tạo thẻ ul-li bằng html)

const myList = document.querySelectorAll("#myList li"); // li nó là con nên cách ra
myList.forEach(item => {
    item.style.color = 'blue';
});

// Câu 3. Cho mã HTML có nội dung như sau (tạo thẻ ul-li bằng html):

// <ul id="list">
//    <li>Item 1</li>
//    <li>Item 2</li>
//    <li>Item 3</li>
//    <li>Item 4</li>
//    <li>Item 5</li>
//    <li>Item 6</li>
//    <li>Item 7</li>
// </ul>

// Sử dụng javascript để thực hiện những công việc sau

// Thêm 3 thẻ <li> có nội dung Item 8, Item 9, Item 10 vào cuối danh sách

// Sửa nội dung cho thẻ <li> 1 thành màu đỏ (color)

// Sửa background cho thẻ <li> 3 thành màu xanh (background-color)

// Remove thẻ <li> 4

// Thêm thẻ <li> mới thay thế cho thẻ <li> 4 bị xóa ở bước trước, thẻ <li> mới có nội dung bất kỳ

//cau3
// console.log("cau3");
// const newItem8 = document.createElement('li');
// newItem8.textContent = 'Item 8';
// list.appendChild(newItem8);

// const newItem9 = document.createElement('li');
// newItem9.textContent = 'Item 9';
// list.appendChild(newItem9);

// const newItem10 = document.createElement('li');
// newItem10.textContent = 'Item 10';
// list.appendChild(newItem10);

// C2 :
for (let i = 8 ; i < 11 ; i++) {
    const newItem = document.createElement('li');
    newItem.textContent = `Item ${i}` ;
    list.appendChild(newItem);
}
let ul1 = document.getElementById("list")
ul1.children[0].style.color = "red"
ul1.children[2].style.backgroundColor = "blue"

//câu 4

const listItemToRemove = list.querySelector("li:nth-child(4)");
listItemToRemove.remove();

//câu 5
// const newListItem = document.createElement("li");
// newListItem.textContent = "Nội dung mới";

// // list.children[2].insertAdjacentElement("afterend" , newListItem);
// list.children[3].insertAdjacentElement("beforebegin" , newListItem);

list.children[2].insertAdjacentHTML("afterend" , "<li>Nội dung mới</li>");

