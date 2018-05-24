package ro.db.vendor.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Companies {
    private int idCompany;
    private String companyName;
    private String companyAddress;

    @Id
    @Column(name = "id_company", nullable = false)
    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    @Basic
    @Column(name = "company_name", nullable = false, length = -1)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "company_address", nullable = false, length = -1)
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Companies companies = (Companies) o;
        return idCompany == companies.idCompany &&
                Objects.equals(companyName, companies.companyName) &&
                Objects.equals(companyAddress, companies.companyAddress);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCompany, companyName, companyAddress);
    }
}
