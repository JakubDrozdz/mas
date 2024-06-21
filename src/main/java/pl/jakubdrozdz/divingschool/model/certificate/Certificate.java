package pl.jakubdrozdz.divingschool.model.certificate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import pl.jakubdrozdz.divingschool.model.diving.course.CourseType;

import java.util.HashSet;
import java.util.Set;

/**
 * Model class for Certificate entity
 *
 * @author Jakub Drozdz
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Certificate")
@ToString
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certificateId;
    @Column(unique = true)
    private String certificateName;
    private String organizationName;
    private String qualificationsDescription;
    @OneToMany(mappedBy = "certificate")
    private Set<CertificateOwnership> certificateOwnershipSet;
    @OneToMany(mappedBy = "grantedCertificate", cascade = CascadeType.REMOVE)
    private Set<CourseType> courseTypes;

    public Certificate(String certificateName, String organizationName, String qualificationsDescription) {
        certificateOwnershipSet = new HashSet<>();
        courseTypes = new HashSet<>();
        setCertificateName(certificateName);
        setOrganizationName(organizationName);
        setQualificationsDescription(qualificationsDescription);
    }

    public void setCertificateName(String certificateName) {
        if(StringUtils.isBlank(certificateName)){
            throw new IllegalArgumentException("Certificate name cannot be blank");
        }
        this.certificateName = certificateName;
    }

    public void setOrganizationName(String organizationName) {
        if(StringUtils.isBlank(organizationName)) {
            throw new IllegalArgumentException("Organization name cannot be blank");
        }
        this.organizationName = organizationName;
    }

    public void setQualificationsDescription(String qualificationsDescription) {
        if(StringUtils.isBlank(qualificationsDescription) || qualificationsDescription.length() > 300) {
            throw new IllegalArgumentException("Qualifications description cannot be blank and max length is 300");
        }
        this.qualificationsDescription = qualificationsDescription;
    }

    /**
     * Method used to create association with CertificateOwnership class
     *
     * @param certificateOwnership instance of CertificateOwnership class
     */
    public void addCertificateOwnership(CertificateOwnership certificateOwnership){
        if(certificateOwnership == null){
            throw new IllegalArgumentException("Certificate ownership cannot be null");
        }
        if(this.certificateOwnershipSet.contains(certificateOwnership)){
            throw new IllegalArgumentException("Cannot have duplicated certificates");
        }
        this.certificateOwnershipSet.add(certificateOwnership);
    }

    /**
     * Method used to create association with CourseType class
     *
     * @param courseType instance of CourseType class
     */
    public void addCourseType(CourseType courseType) {
        if(!this.courseTypes.contains(courseType)){
            this.courseTypes.add(courseType);
            courseType.addGrantedCertificate(this);
        }
    }

    //TODO: move to service?
    public static Certificate addCertificateOwnership(){
        return null;
    }
}
