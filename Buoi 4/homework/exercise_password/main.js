const passwordInput = document.getElementById('passwordInput') ;
const btn = document.getElementById('btn') ;
btn.addEventListener('click' , function() {
    if (passwordInput.type === 'password') {
        passwordInput.type = 'text' ;
        btn.textContent = 'Hide' ;
    } else{
        passwordInput.type = 'password' ; 
        btn.textContent = 'Show' ;
    }
})