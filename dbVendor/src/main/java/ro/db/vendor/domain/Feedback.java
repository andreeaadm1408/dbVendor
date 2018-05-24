package ro.db.vendor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Feedback {
    private String feedbackDet;
    private Timestamp fbDate;
    private int idFeedback;
    private Vendors vendorsByIdVendor;
    private Employees employeesByIdEmp;

    @Basic
    @Column(name = "feedback_det", nullable = false, length = -1)
    public String getFeedbackDet() {
        return feedbackDet;
    }

    public void setFeedbackDet(String feedbackDet) {
        this.feedbackDet = feedbackDet;
    }

    @Basic
    @Column(name = "fb_date", nullable = true)
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "EET")
    public Timestamp getFbDate() {
        return fbDate;
    }

    public void setFbDate(Timestamp fbDate) {
        this.fbDate = fbDate;
    }

    @Id
    @Column(name = "id_feedback", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(int idFeedback) {
        this.idFeedback = idFeedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return idFeedback == feedback.idFeedback &&
                Objects.equals(feedbackDet, feedback.feedbackDet) &&
                Objects.equals(fbDate, feedback.fbDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(feedbackDet, fbDate, idFeedback);
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
    @JoinColumn(name = "id_emp", referencedColumnName = "id_emp", nullable = false)
    public Employees getEmployeesByIdEmp() {
        return employeesByIdEmp;
    }

    public void setEmployeesByIdEmp(Employees employeesByIdEmp) {
        this.employeesByIdEmp = employeesByIdEmp;
    }
}
