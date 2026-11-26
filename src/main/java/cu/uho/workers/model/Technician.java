package cu.uho.workers.model;

import cu.uho.workers.model.enums.Category;
import cu.uho.workers.model.enums.Sex;

/**
 * Technician
 */
public final class Technician extends Worker {

    private static final long serialVersionUID = 0L;
    private final boolean anirMember;
    private final boolean postGraduateStudies;

    public Technician(String id, String name, Sex sex, String address, boolean anirMember,
            boolean postGraduateStudies) {
        super(id, name, sex, address);
        this.anirMember = anirMember;
        this.postGraduateStudies = postGraduateStudies;
    }

    public boolean isAnirMember() {
        return anirMember;
    }

    public boolean hasPostGraduateStudies() {
        return postGraduateStudies;
    }

    @Override
    public Category getCategory() {
        return Category.TECHNICIAN;
    }

}
