package freight_tracking.service;

import freight_tracking.model.Shipment;

import java.util.List;

public interface ShipmentService {
    Shipment createShipment(String origin, String destination);

    Shipment getByTrackingId(String trackingId);

    List<Shipment> getAllShipments();

    Shipment updateShipment(String trackingId, String origin, String destination);

    void deleteShipment(String trackingId);
}
