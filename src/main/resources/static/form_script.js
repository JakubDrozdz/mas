let selectedCourseType;
document.addEventListener('DOMContentLoaded', () => {
    const choosenDivingCourse = localStorage.getItem('divingCourse');
    selectedCourseType = localStorage.getItem('courseType');
    localStorage.clear();
    console.log(choosenDivingCourse);
    if(choosenDivingCourse != null && choosenDivingCourse >= 0){
        console.log("inside function");
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

    const jsonObject = {
        detailedDescription: detailedDescription,
        courseStatus: statusOrdinal,
        startDate: startDate,
        endDate: endDate,
        additionalCost: additionalCost,
        courseType: selectedCourseType
    };

    console.log(JSON.stringify(jsonObject, null, 2));
}