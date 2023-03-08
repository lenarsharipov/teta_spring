package tr.com.teta.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import tr.com.teta.model.Company;
import tr.com.teta.repository.CompanyRepository;

import java.util.Collection;
import java.util.Optional;

@ThreadSafe
@Service
public class SimpleCompanyService implements CompanyService {
    private final CompanyRepository companyRepository;

    public SimpleCompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public boolean deleteById(int id) {
        var fileOptional = findById(id);
        if (fileOptional.isEmpty()) {
            return false;
        }
        return companyRepository.deleteById(id);
    }

    @Override
    public boolean update(Company vacancy) {
        return companyRepository.update(vacancy);
    }

    @Override
    public Optional<Company> findById(int id) {
        return companyRepository.findById(id);
    }

    @Override
    public Collection<Company> findAll() {
        return companyRepository.findAll();
    }
}
