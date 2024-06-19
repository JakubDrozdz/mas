function showPrompt() {
    if(selectedCourseTypeId == null){
        alert('You need to select a certificate');
    } else {
        document.getElementById('myPopUp').style.display = 'block';
    }
}

function closePopUp() {
    document.getElementById('myPopUp').style.display = 'none';
}

window.onclick = function(event) {
    var popUp = document.getElementById('myPopUp');
    if (event.target == popUp) {
        closePopUp();
    }
}