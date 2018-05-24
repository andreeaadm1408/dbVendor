package ro.db.vendor.service;

import java.util.List;
import ro.db.vendor.domain.Skills;

public interface SkillService {
  List<Skills> findAll();
}
