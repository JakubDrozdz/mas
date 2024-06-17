package pl.jakubdrozdz.divingschool.model.certificate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jakubdrozdz.divingschool.model.diving.course.CourseType;

import java.util.HashSet;
import java.util.Set;

@Getter

@Entity(name = "Certificate")
public class Certificate {
    @Id
    @GeneratedValue
    private Long certificateId;
    private String certificateName;
    private String organizationName;
    private String qualificationsDescription;
    @OneToMany(mappedBy = "certificate")
    private Set<CertificateOwnership> certificateOwnershipSet;
    @OneToMany(mappedBy = "grantedCertificate")
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
