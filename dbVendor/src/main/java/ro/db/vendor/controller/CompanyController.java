package ro.db.vendor.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.db.vendor.domain.Companies;
import ro.db.vendor.service.CompanyService;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/company")
@CrossOrigin
public class CompanyController {

    private CompanyService companyService;

    @Inject
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Companies> findAll(){
        return companyService.findAll();
    }
}
