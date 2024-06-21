function showPrompt() {
    if(selectedCourseTypeId == null){
        alert('You need to select a course type');
    } else {
        localStorage.setItem('courseType', selectedCourseTypeId);
        fetchOptions();
        document.getElementById('my-pop-up').style.display = 'block';
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
    const select = document.getElementById('options-select');
    const btn = document.getElementById('other-training-btn');
    select.style.display = 'inline-block';
    select.innerHTML = '';

    if (options.length > 0) {
        options.forEach(option => {
            const opt = document.createElement('option');
            opt.value = option.courseId;
            opt.textContent = 'ID: ' + option.courseId + ', szkolenie ' + calculateDivingCourseTypeName(option.divingCourseType) + ', dodatkowy koszt: ' + option.additionalCost + ' PLN';
            select.appendChild(opt);
        });
        btn.disabled = false;
    } else {
        btn.disabled = true;
        select.style.display = 'none';
    }
}

function calculateDivingCourseTypeName(divingCourseType) {
     switch (divingCourseType) {
         case 'single':
             return 'jednoosobowe';
         case 'pair':
             return 'w parach';
         case 'group':
             return 'grupowe'
         default:
             return 'nie zdefiniowane'
     }
}

function closePopUp() {
    document.getElementById('my-pop-up').style.display = 'none';
}

window.onclick = function(event) {
    const popUp = document.getElementById('my-pop-up');
    if (event.target === popUp) {
        closePopUp();
    }
}

function setUpChoosenCertificate(){
    localStorage.setItem('divingCourse', document.getElementById('options-select').selectedOptions[0].value);
}