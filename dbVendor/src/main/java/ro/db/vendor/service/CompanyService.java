package ro.db.vendor.service;

import ro.db.vendor.domain.Companies;

import java.util.List;

public interface CompanyService {
    List<Companies> findAll();
}
