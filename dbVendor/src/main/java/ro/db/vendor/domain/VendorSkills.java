package ro.db.vendor.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vendor_skills", schema = "public", catalog = "postgres")
public class VendorSkills {
    private int level;
    private int idVendorSkills;
    private Vendors vendorsByIdVendor;
    private Skills skillsByIdSkill;

    @Basic
    @Column(name = "level", nullable = false)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Id
    @Column(name = "id_vendor_skills", nullable = false)
    public int getIdVendorSkills() {
        return idVendorSkills;
    }

    public void setIdVendorSkills(int idVendorSkills) {
        this.idVendorSkills = idVendorSkills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorSkills that = (VendorSkills) o;
        return level == that.level &&
                idVendorSkills == that.idVendorSkills;
    }

    @Override
    public int hashCode() {

        return Objects.hash(level, idVendorSkills);
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
    @JoinColumn(name = "id_skill", referencedColumnName = "id_skill", nullable = false)
    public Skills getSkillsByIdSkill() {
        return skillsByIdSkill;
    }

    public void setSkillsByIdSkill(Skills skillsByIdSkill) {
        this.skillsByIdSkill = skillsByIdSkill;
    }
}
