var cta = document.querySelector(".cta");
var check = 0;

cta.addEventListener('click', function(e){
    const text = e.target.nextElementSibling;
    const loginText = e.target.parentElement;
    text.classList.toggle('show-hide');
    loginText.classList.toggle('expand');
    if(check === 0)
    {
        cta.innerHTML = "<i class =\"fa fa-chevron-up\"></i>";



        check++;
    }
    else
    {
        cta.innerHTML = "<i class =\"fa fa-chevron-down\"></i>";
        check = 0;
    }
})