package ro.db.vendor.service;

import java.util.List;
import java.util.Optional;
import ro.db.vendor.domain.VendorManagers;
import ro.db.vendor.domain.Vendors;
import ro.db.vendor.model.VendorModel;
import ro.db.vendor.model.VendorModelForEditing;
import ro.db.vendor.model.VendorModelForAdd;
import ro.db.vendor.model.VendorsNumberModelForChart;

public interface VendorService {

  List<Vendors> findAll();

  List<VendorModel> findVendorsByIdManager(int id);

  Vendors findDetailofVendorByIdVendorForManager(int id);

  List<VendorModel> findAllVendors();

  Vendors updateVendor(VendorModelForEditing vendors);

  Optional<Vendors> findVendorById(int id);

  VendorModelForEditing findVendorToBeSendForEditing(int id);

  List<VendorModel> convertVendorListToVendorModelList(List<Vendors> vendorsList);

  List<VendorManagers> findAllExternalManagers();

  Vendors save(VendorModelForAdd vendor);

  List<VendorsNumberModelForChart> findNumberOfVendorsPerProject();
}
