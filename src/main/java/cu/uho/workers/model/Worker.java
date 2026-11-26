package cu.uho.workers.model;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import cu.uho.workers.model.enums.Category;
import cu.uho.workers.model.enums.Sex;
import cu.uho.workers.model.enums.ValidationError;
import cu.uho.workers.model.errors.InvalidWorkerDataException;

/**
 * Worker
 */
public abstract sealed class Worker implements Serializable permits Laborer, Technician, Manager {

    private static final long serialVersionUID = 0L;
    private final String id;
    private final String name;
    private final Sex sex;
    private final String address;
    private final List<Double> evaluations = new ArrayList<>();

    public Worker(String id, String name, Sex sex, String address) {
        if (id == null || !id.matches("\\d{11}")) {
            throw new InvalidWorkerDataException(ValidationError.INVALID_ID_FORMAT);
        }
        if (name == null || name.isBlank()) {
            throw new InvalidWorkerDataException(ValidationError.NAME_REQUIRED);
        }
        if (sex == null) {
            throw new InvalidWorkerDataException(ValidationError.SEX_REQUIRED);
        }
        birthdate(id);
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.address = address == null ? "" : address;
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

    public abstract Category getCategory();

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
