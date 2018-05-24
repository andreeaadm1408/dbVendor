package ro.db.vendor.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.db.vendor.domain.Employees;
import ro.db.vendor.domain.Feedback;
import ro.db.vendor.domain.VendorAllocation;
import ro.db.vendor.domain.Vendors;
import ro.db.vendor.model.FeedbackModel;
import ro.db.vendor.model.FeedbackModelForListOfFeedback;
import ro.db.vendor.repository.EmployeesRepository;
import ro.db.vendor.repository.FeedbackRepository;
import ro.db.vendor.repository.VendorAllocationRepository;
import ro.db.vendor.repository.VendorRepository;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

  private final FeedbackRepository feedbackRepository;
  private final VendorAllocationRepository vendorAllocationRepository;
  private final VendorRepository vendorRepository;
  private final EmployeesRepository employeesRepository;
  private final SmtpMailSenderService smtpMailSenderService;

  @Inject
  public FeedbackServiceImpl(FeedbackRepository feedbackRepository,
      VendorAllocationRepository vendorAllocationRepository, VendorRepository vendorRepository,
      EmployeesRepository employeesRepository, SmtpMailSenderService smtpMailSenderService) {
    this.feedbackRepository = feedbackRepository;
    this.vendorAllocationRepository = vendorAllocationRepository;
    this.vendorRepository = vendorRepository;
    this.employeesRepository = employeesRepository;
    this.smtpMailSenderService = smtpMailSenderService;
  }

  @Override
  public List<Feedback> findAll() {
    Stream<Feedback> stream = feedbackRepository.findAll().stream();
    return stream.collect(Collectors.toList());
  }

  @Override
  public List<FeedbackModelForListOfFeedback> findByVendorId(Integer vendorId, Pageable pageable) {
    List<Feedback> feedbacks = feedbackRepository.findByVendorId(vendorId, pageable);
    List<FeedbackModelForListOfFeedback> feedbackModelForListOfFeedbackList = new ArrayList<>();
    for (Feedback feedback : feedbacks) {
      FeedbackModelForListOfFeedback feedbackModelForListOfFeedback = new FeedbackModelForListOfFeedback();
      feedbackModelForListOfFeedback.setNameEmployee(feedback.getEmployeesByIdEmp().getEmpName());
      feedbackModelForListOfFeedback.setFeedbackDet(feedback.getFeedbackDet());
      feedbackModelForListOfFeedback.setIdVendor(feedback.getVendorsByIdVendor().getIdVendor());
      feedbackModelForListOfFeedbackList.add(feedbackModelForListOfFeedback);
    }
    return feedbackModelForListOfFeedbackList;
  }

  @Override
  public Feedback save(FeedbackModel feedbackModel) {
    Feedback feedback = new Feedback();
    Vendors vendor = new Vendors();
    vendor.setIdVendor(feedbackModel.getIdVendor());
    Employees employee = new Employees();
    employee.setIdEmp(AuthService.user.getIdEmp());
    feedback.setVendorsByIdVendor(vendor);
    feedback.setEmployeesByIdEmp(employee);
    feedback.setFeedbackDet(feedbackModel.getFeedbackDet());
    feedback.setFbDate(new Timestamp(System.currentTimeMillis()));
    return this.feedbackRepository.save(feedback);
  }

  @Override
  public VendorAllocation update(int id) {
    Vendors vendor = vendorRepository.findVendorByIdVendor(id);
    VendorAllocation vendorAllocation = vendorAllocationRepository
        .findVendorAllocationByVendor(vendor);
    vendorAllocation.setEndDate(new Timestamp(System.currentTimeMillis()));
    return this.vendorAllocationRepository.save(vendorAllocation);
  }

  @Override
  public List<Employees> findEmployeeToNotifyThemByIdVendor(int id) {
    Vendors vendor = vendorRepository.findVendorByIdVendor(id);
    Employees manager = employeesRepository
        .findManager(vendor.getEmployeesByIdDbManager().getIdEmp());
    List<Employees> managersEmployees = employeesRepository.findEmployeesByIdManager(manager);
    List<Employees> employeesToNotify = new ArrayList<>();
    List<Employees> employeesFromFeedback = feedbackRepository
        .findEmployeesByVendorFromFeedback(vendor);
    for (Employees employee_Manager : managersEmployees) {
      if (!employeesFromFeedback.contains(employee_Manager)) {
        employeesToNotify.add(employee_Manager);
      }
    }
    for (Employees employees : employeesToNotify) {
      smtpMailSenderService
          .sendVerificationMail(employees.getEmail(), "Ask for feedback", employees.getEmpName(),
              vendor.getVendorName());
    }
    return employeesToNotify;
  }
}

