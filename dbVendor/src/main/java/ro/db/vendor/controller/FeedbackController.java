package ro.db.vendor.controller;

import java.util.List;
import javax.inject.Inject;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.db.vendor.domain.Employees;
import ro.db.vendor.domain.Feedback;
import ro.db.vendor.domain.VendorAllocation;
import ro.db.vendor.domain.Vendors;
import ro.db.vendor.model.FeedbackModel;
import ro.db.vendor.model.FeedbackModelForListOfFeedback;
import ro.db.vendor.service.FeedbackService;
import ro.db.vendor.service.VendorService;

@RestController
@RequestMapping("/feedback")
@CrossOrigin
public class FeedbackController {

  private FeedbackService feedbackService;
  private VendorService vendorService;

  @Inject
  public FeedbackController(FeedbackService feedbackService, VendorService vendorService) {
    this.feedbackService = feedbackService;
    this.vendorService = vendorService;
  }

  @GetMapping
  public List<Feedback> findAll() {
    return feedbackService.findAll();
  }

  @GetMapping("/getFeedbacksForVendor")
  public List<FeedbackModelForListOfFeedback> findByVendorId(@RequestParam("vendorId") Integer vendorId,
      @RequestParam("start") Integer start, @RequestParam("rows") Integer rows) {
    return feedbackService.findByVendorId(vendorId, PageRequest.of(start, rows));
  }

  @GetMapping("/details")
  public Vendors findDetailofVendorByIdVendorForManager(@RequestParam("id") int id) {
    return vendorService.findDetailofVendorByIdVendorForManager(id);
  }

  @PostMapping("/addFeedback")
  public Feedback create(@RequestBody FeedbackModel feedback) {
    return feedbackService.save(feedback);
  }

  @PutMapping("/endProject")
  public VendorAllocation update(@RequestParam("id") int id) {
    return feedbackService.update(id);
  }

  @GetMapping("/NotifyAll")
  public List<Employees> findEmployeeToNotifyThemById(@RequestParam("id") int id) {
    return feedbackService.findEmployeeToNotifyThemByIdVendor(id);
  }
}
