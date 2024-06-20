document.addEventListener('DOMContentLoaded', () => {
    const choosenDivingCourse = localStorage.getItem("divingCourse")
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
        }
    })
}