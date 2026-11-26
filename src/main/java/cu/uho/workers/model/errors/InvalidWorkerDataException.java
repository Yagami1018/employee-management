package cu.uho.workers.model.errors;

import cu.uho.workers.model.enums.ValidationError;

public class InvalidWorkerDataException extends IllegalArgumentException {

    private static final long serialVersionUID = 0L;
    private ValidationError error;
    private int idLength;

    public InvalidWorkerDataException(ValidationError error) {
        super(error.name());
        this.error = error;
    }

    public InvalidWorkerDataException(ValidationError error, int idLength) {
        this(error);
        this.idLength = idLength;
    }

    public ValidationError getError() {
        return error;
    }

    public int getIdLength() {
        return idLength;
    }

}
