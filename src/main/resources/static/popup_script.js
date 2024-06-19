function showPrompt() {
    if(selectedCourseTypeId == null){
        alert('You need to select a certificate');
    } else {
        fetchOptions();
        document.getElementById('myPopUp').style.display = 'block';
    }
}

function fetchOptions() {
    fetch('http://localhost:8080/api/v1/courseType/'+selectedCourseTypeId+'/courses')
        .then(response => response.json())
        .then(data => {
            populateSelectList(data);
        })
        .catch(error => {
            alert('Error fetching diving courses: '+ error);
        });
}

function populateSelectList(options) {
    const select = document.getElementById('optionsSelect');
    const btn = document.getElementById('otherTrainingBtn');
    select.style.display = 'inline-block';
    select.innerHTML = '';

    if (options.length > 0) {
        options.forEach(option => {
            const opt = document.createElement('option');
            opt.value = option.courseId;
            //TODO: format display value
            opt.textContent = "courseId:" + option.courseId + ";additionalCost:" + option.additionalCost;
            select.appendChild(opt);
        });
        btn.disabled = false;
    } else {
        btn.disabled = true;
        select.style.display = 'none';
    }
}

function closePopUp() {
    document.getElementById('myPopUp').style.display = 'none';
}

window.onclick = function(event) {
    const popUp = document.getElementById('myPopUp');
    if (event.target === popUp) {
        closePopUp();
    }
}