package ro.db.vendor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.db.vendor.domain.Employees;
import ro.db.vendor.domain.VendorManagers;
import ro.db.vendor.domain.Vendors;
import ro.db.vendor.model.VendorModel;
import ro.db.vendor.model.VendorModelForAdd;
import ro.db.vendor.model.VendorModelForEditing;
import ro.db.vendor.model.VendorsNumberModel;
import ro.db.vendor.model.VendorsNumberModelForChart;
import ro.db.vendor.repository.EmployeesRepository;
import ro.db.vendor.repository.VendorManagerRepository;
import ro.db.vendor.repository.VendorRepository;

@Service
@Transactional(readOnly = true)
public class VendorServiceImpl implements VendorService {

  private VendorRepository vendorRepository;
  private EmployeesRepository employeesRepository;
  private VendorManagerRepository vendorManagerRepository;

  public VendorServiceImpl() {
  }

  @Inject
  public VendorServiceImpl(VendorRepository vendorRepository,
      EmployeesRepository employeesRepository,
      VendorManagerRepository vendorManagerRepository) {
    this.vendorRepository = vendorRepository;
    this.employeesRepository = employeesRepository;
    this.vendorManagerRepository = vendorManagerRepository;
  }

  public List<VendorModel> convertVendorListToVendorModelList(List<Vendors> vendorsList) {
    List<VendorModel> vendorModelList = new ArrayList<>();
    for (Vendors vendors : vendorsList) {
      VendorModel vendorModel = new VendorModel();
      vendorModel.setVendorName(vendors.getVendorName());
      vendorModel.setVendorId(vendors.getIdVendor());
      vendorModelList.add(vendorModel);
    }
    return vendorModelList;
  }

  @Override
  public List<Vendors> findAll() {
    Stream<Vendors> stream = vendorRepository.findAll().stream();
    return stream.collect(Collectors.toList());
  }

  @Override
  public List<VendorManagers> findAllExternalManagers() {
    return vendorManagerRepository.findAll();
  }

  @Override
  public List<VendorModel> findVendorsByIdManager(int id) {
    List<Vendors> vendorsList = vendorRepository.findVendorsByIdManager(id);
    return convertVendorListToVendorModelList(vendorsList);
  }

  @Override
  public Vendors findDetailofVendorByIdVendorForManager(int id) {
    Vendors vendors = vendorRepository.findDetailofVendorByIdVendorForManager(id);
    return vendors;
  }

  @Override
  public Vendors save(VendorModelForAdd vendorModel) {
    Vendors vendor = new Vendors();
    Employees manager = employeesRepository.findManager(vendorModel.getId_mng());
    VendorManagers originManager = new VendorManagers();
    originManager.setIdVendorMng(vendorModel.getId_orgMng());
    vendor.setVendorName(vendorModel.getVendorName());
    vendor.setEmail(vendorModel.getEmail());
    vendor.setPhone(vendorModel.getPhone());
    vendor.setDailyRate(vendorModel.getDailyRate());
    vendor.setVendorManagersByIdOriginManager(originManager);
    vendor.setEmployeesByIdDbManager(manager);
    return this.vendorRepository.save(vendor);
  }

  @Override
  public List<VendorsNumberModelForChart> findNumberOfVendorsPerProject() {
    List<VendorsNumberModel> initialList = vendorRepository.findNumberOfVendorsPerProject();
    List<VendorsNumberModelForChart> newList = new ArrayList<>();
    for (int i = 0; i < initialList.size(); i++) {
      VendorsNumberModelForChart vnmChart = new VendorsNumberModelForChart();
      vnmChart.setLabel(initialList.get(i).getProjectName());
      vnmChart.setValue(initialList.get(i).getVendorsNumber());
      newList.add(vnmChart);
    }
    return newList;
  }

  @Override
  public Vendors updateVendor(VendorModelForEditing vendor) {
    Vendors vendors = new Vendors();
    vendors.setIdVendor(vendor.getIdVendor());
    vendors.setVendorName(vendor.getName());
    vendors.setPhone(vendor.getPhone());
    vendors.setEmail(vendor.getEmail());
    vendors.setDailyRate(vendor.getDailyRate());
    Optional<Employees> employees = employeesRepository.findById(vendor.getIdDBManager());
    employees.ifPresent(vendors::setEmployeesByIdDbManager);
    Optional<VendorManagers> vendorManagers = vendorManagerRepository
        .findById(vendor.getIdExternalManager());
    vendorManagers.ifPresent(vendors::setVendorManagersByIdOriginManager);
    return vendorRepository.save(vendors);
  }

  @Override
  public Optional<Vendors> findVendorById(int id) {
    return vendorRepository.findById(id);
  }

  @Override
  public List<VendorModel> findAllVendors() {
    List<Vendors> vendorsListors = vendorRepository.findAll();
    return convertVendorListToVendorModelList(vendorsListors);
  }

  @Override
  public VendorModelForEditing findVendorToBeSendForEditing(int id) {
    Optional<Vendors> vendors = vendorRepository.findById(id);
    VendorModelForEditing vendorModelForEditing = new VendorModelForEditing();
    if (vendors.isPresent()) {
      Vendors vendors1 = vendors.get();
      vendorModelForEditing.setIdVendor(vendors1.getIdVendor());
      vendorModelForEditing.setEmail(vendors1.getEmail());
      vendorModelForEditing.setName(vendors1.getVendorName());
      vendorModelForEditing.setDailyRate(vendors1.getDailyRate());
      vendorModelForEditing.setPhone(vendors1.getPhone());
      vendorModelForEditing.setIdDBManager(vendors1.getEmployeesByIdDbManager().getIdEmp());
      vendorModelForEditing.setDbManagerName(vendors1.getEmployeesByIdDbManager().getEmpName());
      vendorModelForEditing
          .setIdExternalManager(vendors1.getVendorManagersByIdOriginManager().getIdVendorMng());
    }
    return vendorModelForEditing;
  }
}

