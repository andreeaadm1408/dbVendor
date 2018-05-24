package ro.db.vendor.model;

import java.util.List;

public class VendorSkillsModelForAdd {

  private String vendorName;
  private List<SkillLevelModel> skillsLevelList;

  public String getVendorName() {
    return vendorName;
  }

  public void setVendorName(String vendorName) {
    this.vendorName = vendorName;
  }

  public List<SkillLevelModel> getSkillsLevelList() {
    return skillsLevelList;
  }

  public void setSkillsLevelList(List<SkillLevelModel> skillsLevelList) {
    this.skillsLevelList = skillsLevelList;
  }
}
