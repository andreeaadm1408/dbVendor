package ro.db.vendor.model;

import ro.db.vendor.domain.Employees;
import ro.db.vendor.domain.Vendors;

import java.sql.Timestamp;

public class FeedbackModel {
    private String feedbackDet;
    private int idVendor;

    public String getFeedbackDet() {
        return feedbackDet;
    }

    public void setFeedbackDet(String feedbackDet) {
        this.feedbackDet = feedbackDet;
    }

    public int getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(int idVendor) {
        this.idVendor = idVendor;
    }
}
