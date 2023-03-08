package tr.com.teta.service;

import tr.com.teta.model.Subscriber;

import java.util.Collection;
import java.util.Optional;

public interface SubscriberService {
    Subscriber save(Subscriber subscriber);

    boolean deleteById(int id);

    boolean update(Subscriber subscriber);

    Optional<Subscriber> findById(int id);

    Collection<Subscriber> findByCompanyId(int id);

    Optional<Subscriber> findByNameSurname(String name, String surname);

    Optional<Subscriber> findBySubscriberNumber(String number);

    Collection<Subscriber> findByPlate(String plate);

    Collection<Subscriber> findAll();
}
