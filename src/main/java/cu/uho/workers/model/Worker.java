package cu.uho.workers.model;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Worker
 */
public abstract sealed class Worker implements Serializable permits Laborer, Technician, Manager {

    private static final long serialVersionUID = 0L;
    private String id;
    private String name;
    private Sex sex;
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

    public void addEvaluation(double evaluation) {
        if (evaluation < 0) {
            throw new InvalidWorkerDataException(ValidationError.NEGATIVE_EVALUATION);
        }
        evaluations.add(evaluation);
    }

    public List<Double> getEvaluations() {
        return Collections.unmodifiableList(evaluations);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return Period.between(birthdate(id), LocalDate.now()).getYears();
    }

    public Sex getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public Category getCategory() {
        return category;
    }

    public double getAverage() {
        return evaluations.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Worker other = (Worker) obj;
        return Objects.equals(this.id, other.id);
    }

}
