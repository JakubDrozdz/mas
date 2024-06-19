let globalCertificates =[];
let selectedCourseTypeId;

document.addEventListener('DOMContentLoaded', () => {
    const certificateTable = document.getElementById('certificates-table-body');

    // Replace with your API endpoint
    const apiEndpoint = 'http://localhost:8080/api/v1/certificate';

    fetch(apiEndpoint)
        .then(response => response.json())
        .then(certificates => {
            certificates.forEach(certificate => {
                globalCertificates.push(certificate);
                const rowData = document.createElement('td');
                rowData.setAttribute('certificate-id', certificate.certificateId);
                rowData.textContent = certificate.certificateName;
                rowData.addEventListener('click', function() {
                    //console.log(listItem);
                    const certificateId = parseInt(this.getAttribute('certificate-id'));
                    //console.log(certificateId)
                    displayCourseTypes(certificateId);
                });
                const row = document.createElement('tr');
                row.appendChild(rowData);
                certificateTable.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error fetching data:', error);
            const errorItem = document.createElement('td');
            errorItem.textContent = 'Error loading certificates';
            const row = document.createElement('tr');
            row.appendChild(errorItem);
            certificateTable.appendChild(row);
        });
});

function displayCourseTypes(certificateId) {
    //console.log(globalCertificates)
    const certificate = globalCertificates.find((cert) => cert.certificateId === certificateId);
    //console.log(certificate)
    if (certificate) {
        const courseTable = document.getElementById('course-table-body')
        courseTable.innerHTML = '';
        if(certificate.courseTypes.length === 0){
            alert('There is no course types assigned to certificate')
        }
        certificate.courseTypes.forEach(
            courseType => {
                const rowData = document.createElement('tr');
                const nameColumn = document.createElement('td');
                const minAgeColumn = document.createElement('td');
                const maxAgeColumn = document.createElement('td');
                const reqCertColumn = document.createElement('td');
                rowData.setAttribute('course-type-id', courseType.courseTypeId)
                //TEST
                rowData.addEventListener('click', function() {
                    let selected = courseTable.getElementsByClassName('selected');
                    if (selected.length > 0) {
                        selected[0].classList.remove('selected');
                    }
                    rowData.classList.add('selected');
                    selectedCourseTypeId = rowData.getAttribute('course-type-id');
                    console.log(selectedCourseTypeId);
                });
                //
                nameColumn.textContent = courseType.name;
                minAgeColumn.textContent = courseType.minParticipantAge;
                maxAgeColumn.textContent = courseType.maxParticipantAge;
                reqCertColumn.textContent = courseType.requiredCertificate == null ? '-' : courseType.requiredCertificate;
                rowData.appendChild(nameColumn);
                rowData.appendChild(minAgeColumn);
                rowData.appendChild(maxAgeColumn);
                rowData.appendChild(reqCertColumn);
                courseTable.appendChild(rowData);
            }
        )
        //const courseTypeNames = certificate.courseTypes.map(courseType => courseType.name).join('\n');
        //alert(`Available Course Types for ${certificate.certificateName}:\n${courseTypeNames}`);
    } else {
        alert('Certificate not found.');
    }
}

function addNewCourseType() {
    alert('Feature not implemented yet');
}
function redirect(){
    console.log("redirect function inside");
    if(selectedCourseTypeId == null){
        alert('You need to select a certificate');
    } else {
        window.location.href = 'empty_page.html';
    }
}

