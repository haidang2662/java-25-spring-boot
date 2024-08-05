// Câu 1 :

function getStringHasMaxLength(arr) {
    // Bước 1: Tìm độ dài lớn nhất
    let maxLength = 0;
    for (let i = 0; i < arr.length; i++) {
        if (arr[i].length > maxLength) {
            maxLength = arr[i].length;
        }
    }

    // Bước 2: Lọc các chuỗi có độ dài lớn nhất
    const result = [];
    for (let i = 0; i < arr.length; i++) {
        if (arr[i].length === maxLength) {
            result.push(arr[i]);
        }
    }

    return result;
}

const result = getStringHasMaxLength(['aba', 'aa', 'ad', 'c', 'vcd']);
console.log(result); 

// Câu 2.1 :

function filterUsers(users) {
    return users.filter(user => user.age > 25 && user.isStatus);
}

// Ví dụ sử dụng
const users = [
    { name: "Bùi Công Sơn", age: 30, isStatus: true },
    { name: "Nguyễn Thu Hằng", age: 27, isStatus: false },
    { name: "Phạm Văn Dũng", age: 20, isStatus: false }
];

const filteredUsers = filterUsers(users);
console.log(filteredUsers);

// Câu 2.2 :

function sortUsersByAge(users) {
    return users.slice().sort((a, b) => a.age - b.age);
}

// Ví dụ sử dụng
const sortedUsers = sortUsersByAge(users);
console.log(sortedUsers);

// Câu 3 :

function getCountElement(arr) {
    const countObject = {}; 

    arr.forEach(element => {
        if (countObject[element]) {
            // Nếu phần tử đã tồn tại trong đối tượng, tăng giá trị lên 1
            countObject[element]++;
        } else {
            // Nếu phần tử chưa tồn tại, khởi tạo giá trị bằng 1
            countObject[element] = 1;
        }
    });

    return countObject;
}

// Ví dụ sử dụng
const result1 = getCountElement(["one", "two", "three", "one", "one", "three"]);
console.log(result1);




