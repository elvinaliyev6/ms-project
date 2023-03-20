package az.elvinali.ps.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespPayment {
    private Long orderId;
    private Double amount;
    private String transactionId;
    private String paymentStatus;
}
