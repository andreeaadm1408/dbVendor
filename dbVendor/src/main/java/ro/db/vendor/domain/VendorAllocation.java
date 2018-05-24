package ro.db.vendor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "vendor_allocation", schema = "public", catalog = "postgres")
public class VendorAllocation {
    private Timestamp startDate;
    private Timestamp endDate;
    private int idVendorAllocation;
    private Vendors vendorsByIdVendor;
    private Projects projectsByIdProiect;

    @Basic
    @Column(name = "start_date", nullable = true)
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "EET")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "EET")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Id
    @Column(name = "id_vendor_allocation", nullable = false)
    public int getIdVendorAllocation() {
        return idVendorAllocation;
    }

    public void setIdVendorAllocation(int idVendorAllocation) {
        this.idVendorAllocation = idVendorAllocation;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorAllocation that = (VendorAllocation) o;
        return idVendorAllocation == that.idVendorAllocation &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(startDate, endDate, idVendorAllocation);
    }

    @ManyToOne
    @JoinColumn(name = "id_vendor", referencedColumnName = "id_vendor", nullable = false)
    public Vendors getVendorsByIdVendor() {
        return vendorsByIdVendor;
    }

    public void setVendorsByIdVendor(Vendors vendorsByIdVendor) {
        this.vendorsByIdVendor = vendorsByIdVendor;
    }

    @ManyToOne
    @JoinColumn(name = "id_proiect", referencedColumnName = "id_project")
    public Projects getProjectsByIdProiect() {
        return projectsByIdProiect;
    }

    public void setProjectsByIdProiect(Projects projectsByIdProiect) {
        this.projectsByIdProiect = projectsByIdProiect;
    }
}
