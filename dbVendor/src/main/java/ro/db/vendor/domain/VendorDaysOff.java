package ro.db.vendor.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vendor_days_off", schema = "public", catalog = "postgres")
public class VendorDaysOff {
    private int initialNumbersOfDaysOff;
    private int curentDaysOff;
    private int idVendorDoff;
    private Vendors vendorsByIdVendor;

    @Basic
    @Column(name = "initial_numbers_of_days_off", nullable = false)
    public int getInitialNumbersOfDaysOff() {
        return initialNumbersOfDaysOff;
    }

    public void setInitialNumbersOfDaysOff(int initialNumbersOfDaysOff) {
        this.initialNumbersOfDaysOff = initialNumbersOfDaysOff;
    }

    @Basic
    @Column(name = "curent_days_off", nullable = false)
    public int getCurentDaysOff() {
        return curentDaysOff;
    }

    public void setCurentDaysOff(int curentDaysOff) {
        this.curentDaysOff = curentDaysOff;
    }

    @Id
    @Column(name = "id_vendor_doff", nullable = false)
    public int getIdVendorDoff() {
        return idVendorDoff;
    }

    public void setIdVendorDoff(int idVendorDoff) {
        this.idVendorDoff = idVendorDoff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorDaysOff that = (VendorDaysOff) o;
        return initialNumbersOfDaysOff == that.initialNumbersOfDaysOff &&
                curentDaysOff == that.curentDaysOff &&
                idVendorDoff == that.idVendorDoff;
    }

    @Override
    public int hashCode() {

        return Objects.hash(initialNumbersOfDaysOff, curentDaysOff, idVendorDoff);
    }

    @ManyToOne
    @JoinColumn(name = "id_vendor", referencedColumnName = "id_vendor")
    public Vendors getVendorsByIdVendor() {
        return vendorsByIdVendor;
    }

    public void setVendorsByIdVendor(Vendors vendorsByIdVendor) {
        this.vendorsByIdVendor = vendorsByIdVendor;
    }
}
