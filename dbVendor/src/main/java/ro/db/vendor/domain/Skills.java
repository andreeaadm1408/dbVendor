package ro.db.vendor.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Skills {
    private int idSkill;
    private String skillName;

    @Id
    @Column(name = "id_skill", nullable = false)
    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idSkill) {
        this.idSkill = idSkill;
    }

    @Basic
    @Column(name = "skill_name", nullable = false, length = -1)
    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skills skills = (Skills) o;
        return idSkill == skills.idSkill &&
                Objects.equals(skillName, skills.skillName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idSkill, skillName);
    }
}
