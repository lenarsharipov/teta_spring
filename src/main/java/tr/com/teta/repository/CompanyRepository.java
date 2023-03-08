package tr.com.teta.repository;

import tr.com.teta.model.Company;

import java.util.Collection;
import java.util.Optional;

public interface CompanyRepository {
    Company save(Company company);

    boolean deleteById(int id);

    boolean update(Company company);

    Optional<Company> findById(int id);

    Collection<Company> findAll();
}
