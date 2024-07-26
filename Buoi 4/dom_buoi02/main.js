const sayHello = () => {
    alert("Xin chào các bạn 1")
};

const btn2 = document.getElementById("btn2");
btn2.onclick = () => {
    alert("Xin chào các bạn 2")
};

const btn3 = document.getElementById("btn3");
btn3.addEventListener("click" , () => {
    alert("Xin chào các bạn 3");
});

const btnPlay = document.getElementById("play");
const btnPause = document.getElementById("pause");
const timeEl = document.getElementById("time");
const messageEl = document.getElementById("message");

let time = 10 ;

let interval 
// 1000 ms = 1s

btnPlay.addEventListener("click" , () => {
    interval = setInterval(() => {
        time--;
        timeEl.innerText = `${time} s`;
    
        if(time === 0) {
            messageEl.innerText = "Hết giờ" ;
            clearInterval(interval);
        }
    } , 1000) 
});

btnPause.addEventListener("click" , () => {
    clearInterval(interval);
});
// Cách 1 :
// document.addEventListener("click" , (e) => {
// const currentEl = document.querySelector(".circle");
// if(currentEl) {
//     currentEl.parentElement.removeChild(currentEl);
// }

//     const circleEl = document.createElement("div");
//     circleEl.classList.add("circle");

//     // set position for circle
//     circleEl.style.left = `${e.offsetX -50}px`;
//     circleEl.style.top = `${e.offsetY -50}px`

//     document.body.appendChild(circleEl);
// })

// Cách 2 : 
document.addEventListener("click" , (e) => {
    const currentEl = document.querySelector(".circle");
    if(currentEl) {
    currentEl.style.left = `${e.offsetX -50}px`;
    currentEl.style.top = `${e.offsetY -50}px`;
    return;
    }

    const circleEl = document.createElement("div");
    circleEl.classList.add("circle");

    // set position for circle
    circleEl.style.left = `${e.offsetX -50}px`;
    circleEl.style.top = `${e.offsetY -50}px`

    document.body.appendChild(circleEl);
})

// tìm kiếm user :
const users = [
    {id: 1 , name : "Lê Ánh" } ,
    {id: 2 , name : "Nguyễn Thị Ngọc" } ,
    {id: 3 , name : "Trần Tuấn" } ,
    {id: 4 , name : "Lê Bảo" } ,
    {id: 5 , name : "Trịnh Hạnh" } ,
    {id: 6 , name : "Hồ Xuân Hương" } ,
    {id: 7 , name : "Nguyễn Hằng" } ,

]; 

const inputEl = document.getElementById("input-name");
const btnShowAll = document.getElementById("btn-show-all");
const ListUserEl = document.getElementById("list");

const renderUsers = (users) => {
    let html = "";
    users.forEach(user => {
        html += `<li>${user.id} - ${user.name}</li>`
    });
    ListUserEl.innerHTML = html;
};

inputEl.addEventListener("keydown" , (e) => {
    if(e.key === "Enter") {
        // Lấy keyword từ input
        const keyword = e.target.value;
        // Lọc ra những user có tên chứa trong keyword
        const result = users.filter(user => user.name.toLocaleLowerCase().includes(keyword.toLocaleLowerCase()));

        // HIển thị kết quả
        renderUsers(result);
    }
});

btnShowAll.addEventListener("click" , () => {
    renderUsers(users);
})

renderUsers(users);

const btnTop = document.getElementById("back-to-top")
// scroll back to top :
window.addEventListener("scroll" , () => {
    if(document.documentElement.scrollTop > 300) {
        // Hiển thị nút back to top
        btnTop.classList.remove("hide");
    } else {
        // Ẩn nút back to top
        btnTop.classList.add("hide");
    }
});

btnTop.addEventListener("click" , () => {
    window.scrollTo({
        top : 0 ,
        behavior : "smooth"
    })
});
