package tr.com.teta.service;

import tr.com.teta.model.Company;

import java.util.Collection;
import java.util.Optional;

public interface CompanyService {
    Company save(Company vacancy);

    boolean deleteById(int id);

    boolean update(Company vacancy);

    Optional<Company> findById(int id);

    Collection<Company> findAll();
}
