package ro.db.vendor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Overtime {
    private Timestamp date;
    private int nrOfHours;
    private String type;
    private int idOvertime;
    private Vendors vendorsByIdVendor;

    @Basic
    @Column(name = "date", nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "EET")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "nr_of_hours", nullable = false)
    public int getNrOfHours() {
        return nrOfHours;
    }

    public void setNrOfHours(int nrOfHours) {
        this.nrOfHours = nrOfHours;
    }

    @Basic
    @Column(name = "type", nullable = false, length = -1)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Id
    @Column(name = "id_overtime", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdOvertime() {
        return idOvertime;
    }

    public void setIdOvertime(int idOvertime) {
        this.idOvertime = idOvertime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Overtime overtime = (Overtime) o;
        return nrOfHours == overtime.nrOfHours &&
                idOvertime == overtime.idOvertime &&
                Objects.equals(date, overtime.date) &&
                Objects.equals(type, overtime.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(date, nrOfHours, type, idOvertime);
    }

    @ManyToOne
    @JoinColumn(name = "id_vendor", referencedColumnName = "id_vendor", nullable = false)
    public Vendors getVendorsByIdVendor() {
        return vendorsByIdVendor;
    }

    public void setVendorsByIdVendor(Vendors vendorsByIdVendor) {
        this.vendorsByIdVendor = vendorsByIdVendor;
    }
}
