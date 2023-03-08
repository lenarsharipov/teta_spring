package tr.com.teta.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import tr.com.teta.model.Subscriber;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oSubscriberRepository implements SubscriberRepository {
    private final Sql2o sql2o;

    public Sql2oSubscriberRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Subscriber save(Subscriber subscriber) {
        try (var connection = sql2o.open()) {
            var sql = """
                    INSERT INTO subscribers(name, surname, subscriber_number, plate, company_id)
                    VALUES (:name, :surname, :subscriberNumber, :plate, :companyId)
                    """;
            var query = connection.createQuery(sql, true)
                    .addParameter("name", subscriber.getName())
                    .addParameter("surname", subscriber.getSurname())
                    .addParameter("subscriberNumber", subscriber.getSubscriberNumber())
                    .addParameter("plate", subscriber.getPlate())
                    .addParameter("companyId", subscriber.getCompanyId());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            subscriber.setId(generatedId);
            return subscriber;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM subscribers WHERE id = :id");
            query.addParameter("id", id);
            var affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean update(Subscriber subscriber) {
        try (var connection = sql2o.open()) {
            var sql = """
                    UPDATE subscribers
                    SET name = :name, surname = :surname, subscriber_number = :subscriberNumber,
                    plate = :plate, company_id = :companyId
                    WHERE id = :id
                    """;
            var query = connection.createQuery(sql)
                    .addParameter("name", subscriber.getName())
                    .addParameter("surname", subscriber.getSurname())
                    .addParameter("subscriberNumber", subscriber.getSubscriberNumber())
                    .addParameter("plate", subscriber.getPlate())
                    .addParameter("companyId", subscriber.getCompanyId());
            var affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    @Override
    public Optional<Subscriber> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM subscribers WHERE id = :id");
            query.addParameter("id", id);
            var subscriber = query.setColumnMappings(Subscriber.COLUMN_MAPPING)
                    .executeAndFetchFirst(Subscriber.class);
            return Optional.ofNullable(subscriber);
        }
    }

    @Override
    public Collection<Subscriber> findByCompanyId(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM subscribers WHERE company_id = :id");
            query.addParameter("id", id);
            return query.setColumnMappings(Subscriber.COLUMN_MAPPING)
                    .executeAndFetch(Subscriber.class);
        }
    }

    @Override
    public Optional<Subscriber> findByNameSurname(String name, String surname) {
        try (var connection = sql2o.open()) {
            var sql = """
                    SELECT * FROM subscribers WHERE name = :name, surname = :surname
                    """;
            var query = connection.createQuery(sql);
            query.addParameter("name", name);
            query.addParameter("surname", surname);
            var subscriber = query.setColumnMappings(Subscriber.COLUMN_MAPPING)
                    .executeAndFetchFirst(Subscriber.class);
            return Optional.ofNullable(subscriber);
        }
    }

    @Override
    public Optional<Subscriber> findBySubscriberNumber(String number) {
        try (var connection = sql2o.open()) {
            var sql = """
                    SELECT * FROM subscribers WHERE subscriber_number = :subscriberNumber
                    """;
            var query = connection.createQuery(sql);
            query.addParameter("subscriberNumber", number);
            var subscriber = query.setColumnMappings(Subscriber.COLUMN_MAPPING)
                    .executeAndFetchFirst(Subscriber.class);
            return Optional.ofNullable(subscriber);
        }
    }

    @Override
    public Collection<Subscriber> findByPlate(String plate) {
        try (var connection = sql2o.open()) {
            var sql = """
                    SELECT id, name, surname, subscriber_number, plate, company_id FROM subscribers
                    WHERE LEVENSHTEIN(UPPER(plate), UPPER(:plate)) < 3
                    """;
            var query = connection.createQuery(sql);
            query.addParameter("plate", plate);
            return query.setColumnMappings(Subscriber.COLUMN_MAPPING)
                    .executeAndFetch(Subscriber.class);
        }
    }

    @Override
    public Collection<Subscriber> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM subscribers");
            return query.setColumnMappings(Subscriber.COLUMN_MAPPING)
                    .executeAndFetch(Subscriber.class);
        }
    }
}