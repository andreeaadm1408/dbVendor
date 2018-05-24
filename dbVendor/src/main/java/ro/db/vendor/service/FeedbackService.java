package ro.db.vendor.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import ro.db.vendor.domain.Employees;
import ro.db.vendor.domain.Feedback;
import ro.db.vendor.domain.VendorAllocation;
import ro.db.vendor.model.FeedbackModel;
import ro.db.vendor.model.FeedbackModelForListOfFeedback;

public interface FeedbackService {

  List<Feedback> findAll();

  List<FeedbackModelForListOfFeedback> findByVendorId(Integer vendorId, Pageable pageable);

  Feedback save(FeedbackModel feedback);

  VendorAllocation update(int id);

  List<Employees> findEmployeeToNotifyThemByIdVendor(int id);
}
