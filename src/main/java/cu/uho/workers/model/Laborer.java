package cu.uho.workers.model;

/**
 * Laborer
 */
public final class Laborer extends Worker {

    private static final long serialVersionUID = 0L;
    private final int salaryGroup;
    private final boolean rotatingShifts;

    public Laborer(String id, String name, Sex sex, String address, int salaryGroup, boolean rotatingShifts) {
        super(id, name, sex, address);
        this.salaryGroup = salaryGroup;
        this.rotatingShifts = rotatingShifts;
    }

    public int getSalaryGroup() {
        return salaryGroup;
    }

    public boolean hasRotatingShifts() {
        return rotatingShifts;
    }

    @Override
    public Category getCategory() {
        return Category.LABORER;
    }

}
