package pl.jakubdrozdz.divingschool.model;

import lombok.Getter;

@Getter
public class Certificate {
    private String certificateName;
    private String organizationName;
    private String qualificationsDescription;

    public Certificate(String certificateName, String organizationName, String qualificationsDescription) {
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
}
