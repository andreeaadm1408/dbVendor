package ro.db.vendor.model;

public class FeedbackModelForListOfFeedback {
    private String NameEmployee;
    private String feedbackDet;
    private int idVendor;

    public String getNameEmployee() {
        return NameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        NameEmployee = nameEmployee;
    }

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
