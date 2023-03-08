package tr.com.teta.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import tr.com.teta.model.Subscriber;
import tr.com.teta.repository.SubscriberRepository;

import java.util.Collection;
import java.util.Optional;

@ThreadSafe
@Service
public class SimpleSubscriberService implements SubscriberService {
    private final SubscriberRepository subscriberRepository;

    public SimpleSubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public Subscriber save(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    @Override
    public boolean deleteById(int id) {
        var fileOptional = findById(id);
        if (fileOptional.isEmpty()) {
            return false;
        }
        return subscriberRepository.deleteById(id);
    }

    @Override
    public boolean update(Subscriber subscriber) {
        return subscriberRepository.update(subscriber);
    }

    @Override
    public Optional<Subscriber> findById(int id) {
        return subscriberRepository.findById(id);
    }

    @Override
    public Collection<Subscriber> findByCompanyId(int id) {
        return subscriberRepository.findByCompanyId(id);
    }

    @Override
    public Optional<Subscriber> findByNameSurname(String name, String surname) {
        return subscriberRepository.findByNameSurname(name, surname);
    }

    @Override
    public Optional<Subscriber> findBySubscriberNumber(String number) {
        return subscriberRepository.findBySubscriberNumber(number);
    }

    @Override
    public Collection<Subscriber> findByPlate(String plate) {
        return subscriberRepository.findByPlate(plate);
    }

    @Override
    public Collection<Subscriber> findAll() {
        return subscriberRepository.findAll();
    }
}
