let counter = 0 ;
const counterDisplay = document.getElementById("counter");
const minus = document.querySelector(".prevBtn") ;
const plus = document.querySelector(".nextBtn") ;
function updateCounter() {
    counterDisplay.textContent = counter;
    if(counter > 0) {
        counterDisplay.style.color = "green" ;
    } else if (counter < 0 ) {
        counterDisplay.style.color = "red" ;
    } else {
        counterDisplay.style.color = "#333333";
    }
}
minus.addEventListener("click" , function() {
    counter--;
    updateCounter();
})
plus.addEventListener("click" , function() {
    counter++;
    updateCounter();
})
