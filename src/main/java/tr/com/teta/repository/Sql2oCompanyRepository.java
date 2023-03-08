package tr.com.teta.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import tr.com.teta.model.Company;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oCompanyRepository implements CompanyRepository {
    private final Sql2o sql2o;

    public Sql2oCompanyRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Company save(Company company) {
        try (var connection = sql2o.open()) {
            var sql =
                    """
                    INSERT INTO companies(name, phone, address)
                    VALUES (:name, :phone, :address)
                    """;
            var query = connection.createQuery(sql, true)
                    .addParameter("name", company.getName())
                    .addParameter("phone", company.getPhone())
                    .addParameter("address", company.getAddress());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            company.setId(generatedId);
            return company;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM companies WHERE id = :id");
            query.addParameter("id", id);
            var affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean update(Company company) {
        try (var connection = sql2o.open()) {
            var sql = """
                    UPDATE companies
                    SET name = :name, phone = :phone, address = :address
                    WHERE id = :id
                    """;
            var query = connection.createQuery(sql)
                    .addParameter("name", company.getName())
                    .addParameter("phone", company.getPhone())
                    .addParameter("address", company.getAddress())
                    .addParameter("id", company.getId());
            var affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    @Override
    public Optional<Company> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM companies WHERE id = :id");
            query.addParameter("id", id);
            var company = query.executeAndFetchFirst(Company.class);
            return Optional.ofNullable(company);
        }
    }

    @Override
    public Collection<Company> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM companies");
            return query.executeAndFetch(Company.class);
        }
    }
}
