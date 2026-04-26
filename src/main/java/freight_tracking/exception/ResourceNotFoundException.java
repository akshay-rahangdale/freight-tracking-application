package freight_tracking.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String shipmentNotFound) {
        super(shipmentNotFound);
    }
}
