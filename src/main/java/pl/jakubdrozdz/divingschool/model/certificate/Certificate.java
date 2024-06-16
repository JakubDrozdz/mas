package pl.jakubdrozdz.divingschool.model.certificate;

import lombok.Getter;
import pl.jakubdrozdz.divingschool.model.diving.course.CourseType;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Certificate {
    private String certificateName;
    private String organizationName;
    private String qualificationsDescription;
    private Set<CertificateOwnership> certificateOwnershipSet;
    private Set<CourseType> courseTypes;

    public Certificate(String certificateName, String organizationName, String qualificationsDescription) {
        certificateOwnershipSet = new HashSet<>();
        courseTypes = new HashSet<>();
        setCertificateName(certificateName);
        setOrganizationName(organizationName);
        setQualificationsDescription(qualificationsDescription);
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setQualificationsDescription(String qualificationsDescription) {
        this.qualificationsDescription = qualificationsDescription;
    }

    public static Certificate addCertificate(){
        return null;
    }

    public void addCertificate(CertificateOwnership certificateOwnership){
        if(this.certificateOwnershipSet.contains(certificateOwnership)){
            //TODO: throw exception?
            throw new IllegalArgumentException("Cannot have duplicated certificates");
        }
        this.certificateOwnershipSet.add(certificateOwnership);
    }

    public void addCourseType(CourseType courseType) {
        if(!this.courseTypes.contains(courseType)){
            this.courseTypes.add(courseType);
            courseType.addGrantedCertificate(this);
        }
    }
}
