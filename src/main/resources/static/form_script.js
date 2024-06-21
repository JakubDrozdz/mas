let selectedCourseType;
let jsonObject
document.addEventListener('DOMContentLoaded', () => {
    const choosenDivingCourse = localStorage.getItem('divingCourse');
    selectedCourseType = localStorage.getItem('courseType');
    localStorage.clear();
    if(choosenDivingCourse != null && choosenDivingCourse >= 0){
        fetch('http://localhost:8080/api/v1/divingCourse/'+choosenDivingCourse)
            .then(response => response.json())
            .then(data => {
                console.log(data)
                document.getElementById('description').textContent = data.detailedDescription;
                document.getElementById('additional-cost').value = data.additionalCost;
                enableRadioOption(data.divingCourseType)
            })
            .catch(error => {
                alert('Error fetching diving courses details: '+ error);
            });
    }
});

function enableRadioOption(divingCourseType) {
    let radios = document.getElementsByName('diving-course-type');

    radios.forEach(radio => {
        if (radio.value === divingCourseType) {
            radio.checked = true;
        } else{
            radio.disabled = true;
        }
    })
}

function validateForm() {
    const detailedDescription = document.getElementById('description').value;
    const statusOrdinal = 0;
    const startDate = document.getElementById('start-date').value;
    const endDate = document.getElementById('end-date').value;
    const additionalCost = document.getElementById('additional-cost').value;
    const divingCourseType = document.querySelector('input[name="diving-course-type"]:checked').value;

    //validation
    let errorMessage = '';

    if (!detailedDescription.trim()) {
        errorMessage += 'Opis nie może być pusty.\n';
    }

    const startDateObj = new Date(startDate);
    const currentDate = new Date();
    currentDate.setHours(23, 59, 59, 99);
    if (startDateObj <= currentDate) {
        errorMessage += 'Data rozpoczęcia niepoprawna.\n';
    }
    const endDateObj = new Date(endDate);
    if (endDateObj <= currentDate || endDateObj.getTime() <= startDateObj.getTime()) {
        errorMessage += 'Data zakończenia niepoprawna\n';
    }

    if (parseInt(additionalCost) < 0) {
        errorMessage += 'Dodatkowy koszt nie może być ujemny.\n';
    }

    if (errorMessage) {
        alert(errorMessage);
    } else{
        jsonObject = {
            detailedDescription: detailedDescription,
            courseStatus: statusOrdinal,
            startDate: startDate,
            endDate: endDate,
            additionalCost: additionalCost,
            courseType: selectedCourseType,
            divingCourseType: divingCourseType
        };
        disableInputFields();
        updateButtonContainer();
    }
}

function disableInputFields() {
    const formContainer = document.getElementById('form');
    const inputs = formContainer.getElementsByTagName('input');
    for (let i = 0; i < inputs.length; i++) {
        console.log(inputs[i]);
        inputs[i].setAttribute('readonly', true);
    }
    const textAreas = formContainer.getElementsByTagName('textarea');
    for (let i = 0; i < textAreas.length; i++) {
        console.log(textAreas[i]);
        textAreas[i].setAttribute('readonly', true);
    }
}

function updateButtonContainer(){
    const btnContainer = document.getElementById('button-container');
    const validateBtn = document.getElementById('validate-button');
    btnContainer.removeChild(validateBtn);
    const submitBtn = document.createElement('button');
    submitBtn.type = 'button';
    submitBtn.textContent = 'Zatwierdź';
    submitBtn.classList.add('action-button');
    submitBtn.onclick = () => postData();
    const cancelBtn = document.createElement('button');
    cancelBtn.type = 'button';
    cancelBtn.textContent = 'Odrzuć';
    cancelBtn.classList.add('action-button');
    cancelBtn.onclick = () => window.location.href = '/';
    btnContainer.appendChild(cancelBtn);
    btnContainer.appendChild(submitBtn);
}

async function postData() {
    await fetch('http://localhost:8080/api/v1/divingCourse', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        redirect: 'follow',
        body: JSON.stringify(jsonObject)
    }).then(response => {
        console.log(response);
        if(response.ok){
            alert('Dane zapisane. Naciśnij OK aby wrócić na stronę główną');
        }else {
            alert('Dane niezapisane - Błąd serwera. Naciśnij OK aby wrócić na stronę główną');
        }

    }).catch(error => alert('Error fetching diving courses details: '+ error));
    window.location.href = '/';
}