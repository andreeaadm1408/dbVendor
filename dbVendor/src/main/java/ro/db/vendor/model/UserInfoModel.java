package ro.db.vendor.model;


public class UserInfoModel {
    private int idEmp;
    private String empName;
    private String email;
    private String phone;
    private boolean isMng;
    private String depName;
    private int idMng;
    private int idRole;

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isMng() {
        return isMng;
    }

    public void setMng(boolean mng) {
        isMng = mng;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public int getIdMng() {
        return idMng;
    }

    public void setIdMng(int idMng) {
        this.idMng = idMng;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
