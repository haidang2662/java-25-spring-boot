// Câu 1 : change content
function changeContent() {
    const quote = [
        "A",
        "B",
        "C",
        "D",
        "E",
        "F"
    ];
    const randomQuote = quote[Math.floor(Math.random()*quote.length)];
    document.getElementById('text').innerText = randomQuote;
}
// Câu 2 : change color
document.getElementById("btn-2").onclick = function() {
    document.getElementById("text").style.color = "blue";
}
// Câu 3 : change background
document.getElementById("btn-3").onclick = function() {
    document.getElementById("text").style.backgroundColor = "yellow";
}