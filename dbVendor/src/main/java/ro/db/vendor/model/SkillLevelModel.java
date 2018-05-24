package ro.db.vendor.model;

public class SkillLevelModel {
  private int id;
  private int level;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public SkillLevelModel(int id, int level) {
    this.id = id;
    this.level = level;
  }
}
