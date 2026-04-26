package freight_tracking.controller;

import freight_tracking.dto.request.CreateShipmentRequest;
import freight_tracking.dto.response.ShipmentResponse;
import freight_tracking.mapper.ShipmentMapper;
import freight_tracking.model.Shipment;
import freight_tracking.service.ShipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PostMapping
    public ShipmentResponse createShipment(@Valid @RequestBody CreateShipmentRequest request) {

        Shipment shipment = shipmentService.createShipment(
                request.getOrigin(),
                request.getDestination()
        );

        return ShipmentMapper.toResponse(shipment);
    }

    @GetMapping("/{trackingId}")
    public ShipmentResponse getShipment(@PathVariable String trackingId) {

        Shipment shipment = shipmentService.getByTrackingId(trackingId);
        return ShipmentMapper.toResponse(shipment);
    }

    @GetMapping
    public List<ShipmentResponse> getAllShipments() {

        return shipmentService.getAllShipments()
                .stream()
                .map(ShipmentMapper::toResponse)
                .toList();
    }

    @PutMapping("/{trackingId}")
    public ShipmentResponse updateShipment(
            @PathVariable String trackingId,
            @Valid @RequestBody CreateShipmentRequest request
    ) {

        Shipment shipment = shipmentService.updateShipment(
                trackingId,
                request.getOrigin(),
                request.getDestination()
        );

        return ShipmentMapper.toResponse(shipment);
    }

    @DeleteMapping("/{trackingId}")
    public void deleteShipment(@PathVariable String trackingId) {
        shipmentService.deleteShipment(trackingId);
    }
}
