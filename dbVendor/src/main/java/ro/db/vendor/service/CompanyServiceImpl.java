package ro.db.vendor.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.db.vendor.domain.Companies;
import ro.db.vendor.repository.CompanyRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;

    @Inject
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Companies> findAll() {
        Stream<Companies> stream = companyRepository.findAll().stream();
        return stream.collect(Collectors.toList());
    }
}
