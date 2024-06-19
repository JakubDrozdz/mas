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
    select.innerHTML = '';

    if (options.length > 0) {
        options.forEach(option => {
            const opt = document.createElement('option');
            opt.value = option.courseId;
            //TODO: format display value
            opt.textContent = "courseId:" + option.courseId + ";additionalCost:" + option.additionalCost;
            select.appendChild(opt);
        });
    } else {
        const opt = document.createElement('option');
        opt.value = '';
        opt.textContent = 'Brak kursów nurkowych';
        select.appendChild(opt);
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