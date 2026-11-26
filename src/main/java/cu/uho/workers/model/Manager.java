package cu.uho.workers.model;

import cu.uho.workers.model.enums.Category;
import cu.uho.workers.model.enums.Sex;
import cu.uho.workers.model.enums.ValidationError;
import cu.uho.workers.model.errors.InvalidWorkerDataException;

/**
 * Manager
 */
public final class Manager extends Worker {

    private static final long serialVersionUID = 0L;
    private final String managedArea;
    private final boolean inCadreReserve;

    public Manager(String id, String name, Sex sex, String address, String managedArea, boolean inCadreReserve) {
        super(id, name, sex, address);
        if (managedArea == null || managedArea.isBlank()) {
            throw new InvalidWorkerDataException(ValidationError.AREA_REQUIRED);
        }
        this.managedArea = managedArea;
        this.inCadreReserve = inCadreReserve;
    }

    @Override
    public Category getCategory() {
        return Category.MANAGER;
    }

    public String getManagedArea() {
        return managedArea;
    }

    public boolean isInCadreReserve() {
        return inCadreReserve;
    }

}
