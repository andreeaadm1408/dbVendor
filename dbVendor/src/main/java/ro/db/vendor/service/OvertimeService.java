package ro.db.vendor.service;

import ro.db.vendor.domain.Overtime;
import ro.db.vendor.model.OvertimeModel;

public interface OvertimeService {

  Overtime newOvertime(OvertimeModel overtime);
}
