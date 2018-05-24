package ro.db.vendor.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.db.vendor.domain.Skills;
import ro.db.vendor.repository.SkillRepository;

@Service
@Transactional(readOnly = true)
public class SkillServiceImpl implements SkillService {

  private final SkillRepository skillRepository;

  @Inject
  public SkillServiceImpl(SkillRepository skillRepository) {
    this.skillRepository = skillRepository;
  }

  @Override
  public List<Skills> findAll() {
    Stream<Skills> stream = skillRepository.findAll().stream();
    return stream.collect(Collectors.toList());
  }
}
