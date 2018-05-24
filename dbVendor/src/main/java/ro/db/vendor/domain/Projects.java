package ro.db.vendor.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Projects {
    private int idProject;
    private String projectName;
    private Employees employeesByIdMng;

    @Id
    @Column(name = "id_project", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    @Basic
    @Column(name = "project_name", nullable = false, length = -1)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projects projects = (Projects) o;
        return idProject == projects.idProject &&
                Objects.equals(projectName, projects.projectName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProject, projectName);
    }

    @ManyToOne
    @JoinColumn(name = "id_mng", referencedColumnName = "id_emp", nullable = false)
    public Employees getEmployeesByIdMng() {
        return employeesByIdMng;
    }

    public void setEmployeesByIdMng(Employees employeesByIdMng) {
        this.employeesByIdMng = employeesByIdMng;
    }
}
