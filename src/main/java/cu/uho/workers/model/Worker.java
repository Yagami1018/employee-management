package cu.uho.workers.model;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public abstract sealed class Worker implements Serializable permits Laborer, Technician, Manager {

    private static final long serialVersionUID = 0L;
    private String id;
    private String name;
    private Sex sex;
    private int age;
    private String address;
    private Category category;
    private final List<Double> evaluations = new ArrayList<>();

    public Worker(String id, String name, Sex sex, String address) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.address = address;
    }

    private static LocalDate birthdate(String id) {
        int yy = Integer.parseInt(id.substring(0, 2));
        int mm = Integer.parseInt(id.substring(2, 4));
        int dd = Integer.parseInt(id.substring(4, 6));
        int c = Character.getNumericValue(id.charAt(6));
        int century = c == 9 ? 1800 : c <= 5 ? 1900 : 2000;
        try {

            return LocalDate.of(century + yy, mm, dd);
        } catch (DateTimeException _) {
            throw new InvalidWorkerDataException(ValidationError.INVALID_BIRTH_DATE);
        }
    }

    public int getAge() {
        return Period.between(birthdate(id), LocalDate.now()).getYears();
    }

}
