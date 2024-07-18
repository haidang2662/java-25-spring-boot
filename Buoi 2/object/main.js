let user = {
    name: "Nguyễn Văn A",
    age: 23,
    email: "abc@gmail.com",
    address : {
        province : 'Hà Nội' ,
        district : 'Cầu Giấy' ,
        ward : 'Dịch Vọng',
        detail : 'Số 1 , ngõ 1 , phố 1'
    },
    languages : ['Tiếng Anh' , 'Tiếng Nhật'],
    eat : function(food){
        console.log(`eating ${food}`)
    } ,

    showInfo : function(){
        console.log(`Xin chào , tôi tên là ${this.name} , năm nay tôi ${this.age} tuổi , email của tôi là ${this.email}`)
    }
}

console.log(user.address.ward);
console.log(user.languages[1]);
user.eat('Phở');
user.showInfo();