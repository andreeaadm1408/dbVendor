package ro.db.vendor.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Departments {
    private int idDepartament;
    private String depName;
    private Double costCenter;

    @Id
    @Column(name = "id_departament", nullable = false)
    public int getIdDepartament() {
        return idDepartament;
    }

    public void setIdDepartament(int idDepartament) {
        this.idDepartament = idDepartament;
    }

    @Basic
    @Column(name = "dep_name", nullable = false, length = -1)
    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Basic
    @Column(name = "cost_center", nullable = true, precision = 0)
    public Double getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(Double costCenter) {
        this.costCenter = costCenter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departments that = (Departments) o;
        return idDepartament == that.idDepartament &&
                Objects.equals(depName, that.depName) &&
                Objects.equals(costCenter, that.costCenter);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idDepartament, depName, costCenter);
    }
}
