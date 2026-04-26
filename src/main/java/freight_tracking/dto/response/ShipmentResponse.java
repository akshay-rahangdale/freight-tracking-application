package freight_tracking.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShipmentResponse {

    private String trackingId;
    private String origin;
    private String destination;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
