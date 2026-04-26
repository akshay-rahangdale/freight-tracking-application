package freight_tracking.service.impl;

import freight_tracking.exception.ResourceNotFoundException;
import freight_tracking.model.Shipment;
import freight_tracking.model.ShipmentStatus;
import freight_tracking.repository.ShipmentRepository;
import freight_tracking.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImplementation implements ShipmentService {


        private final ShipmentRepository shipmentRepository;

        @Override
        public Shipment createShipment(String origin, String destination) {

            Shipment shipment = Shipment.builder()
                    .trackingId(generateTrackingId())
                    .origin(origin)
                    .destination(destination)
                    .status(ShipmentStatus.CREATED)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            return shipmentRepository.save(shipment);
        }

        @Override
        public Shipment getByTrackingId(String trackingId) {
            return shipmentRepository.findByTrackingId(trackingId)
                    .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));
        }

        @Override
        public List<Shipment> getAllShipments() {
            return shipmentRepository.findAll();
        }

        @Override
        public Shipment updateShipment(String trackingId, String origin, String destination) {

            Shipment shipment = shipmentRepository.findByTrackingId(trackingId)
                    .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));

            shipment.setOrigin(origin);
            shipment.setDestination(destination);
            shipment.setUpdatedAt(LocalDateTime.now());

            return shipmentRepository.save(shipment);
        }

        @Override
        public void deleteShipment(String trackingId) {

            Shipment shipment = shipmentRepository.findByTrackingId(trackingId)
                    .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));

            shipmentRepository.delete(shipment);
        }

        private String generateTrackingId() {
            return "TRK-" + UUID.randomUUID().toString().substring(0, 8);
        }
}
