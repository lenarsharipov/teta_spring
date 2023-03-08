package tr.com.teta.model;

import java.util.Map;
import java.util.Objects;

public class Subscriber {
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "name", "name",
            "surname", "surname",
            "subscriber_number", "subscriberNumber",
            "plate", "plate",
            "company_id", "companyId"
    );

    private int id;
    private String name;
    private String surname;
    private String subscriberNumber;
    private String plate;
    private int companyId;

    public Subscriber() {

    }

    public Subscriber(int id, String name, String surname,
                      String subscriberNumber, String plate, int companyId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.subscriberNumber = subscriberNumber;
        this.plate = plate;
        this.companyId = companyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    public void setSubscriberNumber(String subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subscriber that = (Subscriber) o;
        return id == that.id && Objects.equals(subscriberNumber, that.subscriberNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subscriberNumber);
    }
}