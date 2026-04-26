package freight_tracking.repository;

import freight_tracking.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
    Optional<Shipment> findByTrackingId(String trackingId);
    void deleteByTrackingId(String trackingId);
}
