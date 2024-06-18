let globalCertificates =[];

document.addEventListener('DOMContentLoaded', () => {
    const certificateList = document.getElementById('certificates-list');

    // Replace with your API endpoint
    const apiEndpoint = 'http://localhost:8080/api/v1/certificate';

    fetch(apiEndpoint)
        .then(response => response.json())
        .then(certificates => {
            certificates.forEach(certificate => {
                globalCertificates.push(certificate);
                const listItem = document.createElement('li');
                listItem.setAttribute('certificate-id', certificate.certificateId)
                listItem.textContent = certificate.certificateName;
                listItem.addEventListener('click', function() {
                    //console.log(listItem);
                    const certificateId = parseInt(this.getAttribute('certificate-id'));
                    //console.log(certificateId)
                    displayCourseTypes(certificateId);
                });
                certificateList.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error('Error fetching data:', error);
            const errorItem = document.createElement('li');
            errorItem.textContent = 'Error loading certificates';
            certificateList.appendChild(errorItem);
        });
});

function displayCourseTypes(certificateId) {
    //console.log(globalCertificates)
    const certificate = globalCertificates.find((cert) => cert.certificateId === certificateId);
    //console.log(certificate)
    if (certificate) {
        const courseList = document.getElementById('course-list')
        courseList.innerHTML = '';
        if(certificate.courseTypes.length === 0){
            alert('There is no course types assigned to certificate')
        }
        certificate.courseTypes.forEach(
            courseType => {
                const listItem = document.createElement('li');
                //listItem.setAttribute('certificate-id', certificate.certificateId)
                listItem.textContent = courseType.name;
                courseList.appendChild(listItem);
            }
        )
        //const courseTypeNames = certificate.courseTypes.map(courseType => courseType.name).join('\n');
        //alert(`Available Course Types for ${certificate.certificateName}:\n${courseTypeNames}`);
    } else {
        alert('Certificate not found.');
    }
}

