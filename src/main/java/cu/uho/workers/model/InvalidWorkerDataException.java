package cu.uho.workers.model;

public class InvalidWorkerDataException extends IllegalArgumentException {

    private ValidationError error;

    public InvalidWorkerDataException(ValidationError typeError) {
        super(typeError.name());
        this.error = typeError;
    }

    public ValidationError getError() {
        return error;
    }

}
