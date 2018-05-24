package ro.db.vendor.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vendor_managers", schema = "public", catalog = "postgres")
public class VendorManagers {
    private int idVendorMng;
    private String nameMng;
    private String email;
    private String phone;
    private Companies companiesByIdCompany;

    @Id
    @Column(name = "id_vendor_mng", nullable = false)
    public int getIdVendorMng() {
        return idVendorMng;
    }

    public void setIdVendorMng(int idVendorMng) {
        this.idVendorMng = idVendorMng;
    }

    @Basic
    @Column(name = "name_mng", nullable = false, length = -1)
    public String getNameMng() {
        return nameMng;
    }

    public void setNameMng(String nameMng) {
        this.nameMng = nameMng;
    }

    @Basic
    @Column(name = "email", nullable = false, length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = -1)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorManagers that = (VendorManagers) o;
        return idVendorMng == that.idVendorMng &&
                Objects.equals(nameMng, that.nameMng) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idVendorMng, nameMng, email, phone);
    }

    @ManyToOne
    @JoinColumn(name = "id_company", referencedColumnName = "id_company")
    public Companies getCompaniesByIdCompany() {
        return companiesByIdCompany;
    }

    public void setCompaniesByIdCompany(Companies companiesByIdCompany) {
        this.companiesByIdCompany = companiesByIdCompany;
    }
}
