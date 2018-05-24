package ro.db.vendor.controller;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.db.vendor.domain.Overtime;
import ro.db.vendor.domain.Skills;
import ro.db.vendor.domain.VendorManagers;
import ro.db.vendor.domain.VendorSkills;
import ro.db.vendor.domain.Vendors;
import ro.db.vendor.model.OffDayModel;
import ro.db.vendor.model.OvertimeModel;
import ro.db.vendor.model.VendorModel;
import ro.db.vendor.model.VendorModelForAdd;
import ro.db.vendor.model.VendorModelForEditing;
import ro.db.vendor.model.VendorsNumberModelForChart;
import ro.db.vendor.service.HolidayService;
import ro.db.vendor.service.OvertimeService;
import ro.db.vendor.service.SkillService;
import ro.db.vendor.service.VendorDaysOffService;
import ro.db.vendor.service.VendorManagerService;
import ro.db.vendor.service.VendorService;
import ro.db.vendor.service.VendorSkillService;

@RestController
@RequestMapping("/vendors")
@CrossOrigin
@Log4j
public class VendorController {

  private VendorService vendorService;
  private SkillService skillService;
  private VendorSkillService vendorSkillService;
  private VendorManagerService vendorManagerService;
  private HolidayService holidayService;
  private OvertimeService overtimeService;
  private VendorDaysOffService vendorDaysOffService;

  @Inject
  public VendorController(VendorService vendorService,
      SkillService skillService, VendorSkillService vendorSkillService,
      VendorManagerService vendorManagerService, HolidayService holidayService,
      OvertimeService overtimeService, VendorDaysOffService vendorDaysOffService) {
    this.vendorService = vendorService;
    this.skillService = skillService;
    this.vendorSkillService = vendorSkillService;
    this.vendorManagerService = vendorManagerService;
    this.holidayService = holidayService;
    this.overtimeService = overtimeService;
    this.vendorDaysOffService = vendorDaysOffService;
  }

  @GetMapping
  public List<Vendors> findAll() {
    return vendorService.findAll();
  }

  @GetMapping("/totalNumberOfVendors")
  public int returnTotalNumberOfVendors() {
    return vendorService.findAll().size();
  }

  @GetMapping("/manager")
  public List<VendorModel> findVendorsByIdManager(@RequestParam("id") int id) {
    return vendorService.findVendorsByIdManager(id);
  }

  @GetMapping("/detailOfVendor")
  public Vendors findDetailofVendorByIdVendorForManager(@RequestParam("id") int id) {
    return vendorService.findDetailofVendorByIdVendorForManager(id);
  }

  @PostMapping("/addVendor")
  public Vendors create(@RequestBody VendorModelForAdd vendorModel) {
    return vendorService.save(vendorModel);
  }

  @GetMapping("/skills")
  public List<Skills> findAllSkills() {
    return skillService.findAll();
  }

  @GetMapping("/vendorSkills")
  public List<VendorSkills> findAllVendorSkills() {
    return vendorSkillService.findAll();
  }

  @GetMapping("/allOriginMng")
  public List<VendorManagers> findAllOriginManagers() {
    return vendorManagerService.findAll();
  }

  @GetMapping("/getVendor")
  public VendorModelForEditing findVendorModelForEditing(@RequestParam("id") int id) {
    return vendorService.findVendorToBeSendForEditing(id);
  }

  @PutMapping("/updateVendor")
  public Vendors updateVendor(@RequestBody VendorModelForEditing vendors) {
    return vendorService.updateVendor(vendors);
  }

  @GetMapping("/getVendorManagers")
  public List<VendorManagers> getAllExternalManagers() {
    return vendorService.findAllExternalManagers();
  }

  @PostMapping("/addHoliday")
  public boolean addHolidays(@RequestBody OffDayModel offDayModel, HttpServletResponse response) {

    if (holidayService.insertHoliday(offDayModel)) {
      return true;
    } else {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      log.info("This user don't have days off...");
      return false;
    }
  }

  @GetMapping("/getVendorDaysOffLeft")
  public int currentVendorDaysOffLeft(@RequestParam("id") int id) {
    return vendorDaysOffService.vendorDaysOffRemaining(id);
  }

  @PostMapping("/addOvertime")
  public Overtime addOvertime(@RequestBody OvertimeModel overtime) {
    return overtimeService.newOvertime(overtime);
  }

  @GetMapping("/vendorsPerProject")
  public List<VendorsNumberModelForChart> getNoOfVendorsPerProject() {
    return vendorService.findNumberOfVendorsPerProject();
  }

}
