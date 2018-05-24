package ro.db.vendor.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project_skills", schema = "public", catalog = "postgres")
public class ProjectSkills {
    private Integer level;
    private int idProjectSkill;
    private Projects projectsByIdProject;
    private Skills skillsByIdSkill;

    @Basic
    @Column(name = "level", nullable = true)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Id
    @Column(name = "id_project_skill", nullable = false)
    public int getIdProjectSkill() {
        return idProjectSkill;
    }

    public void setIdProjectSkill(int idProjectSkill) {
        this.idProjectSkill = idProjectSkill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectSkills that = (ProjectSkills) o;
        return idProjectSkill == that.idProjectSkill &&
                Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {

        return Objects.hash(level, idProjectSkill);
    }

    @ManyToOne
    @JoinColumn(name = "id_project", referencedColumnName = "id_project")
    public Projects getProjectsByIdProject() {
        return projectsByIdProject;
    }

    public void setProjectsByIdProject(Projects projectsByIdProject) {
        this.projectsByIdProject = projectsByIdProject;
    }

    @ManyToOne
    @JoinColumn(name = "id_skill", referencedColumnName = "id_skill")
    public Skills getSkillsByIdSkill() {
        return skillsByIdSkill;
    }

    public void setSkillsByIdSkill(Skills skillsByIdSkill) {
        this.skillsByIdSkill = skillsByIdSkill;
    }
}
