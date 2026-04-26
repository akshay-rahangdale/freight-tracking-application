package freight_tracking.mapper;

import freight_tracking.dto.response.ShipmentResponse;
import freight_tracking.model.Shipment;

public class ShipmentMapper {

    public static ShipmentResponse toResponse(Shipment shipment) {
        return ShipmentResponse.builder()
                .trackingId(shipment.getTrackingId())
                .origin(shipment.getOrigin())
                .destination(shipment.getDestination())
                .status(shipment.getStatus().name())
                .createdAt(shipment.getCreatedAt())
                .updatedAt(shipment.getUpdatedAt())
                .build();
    }
}
