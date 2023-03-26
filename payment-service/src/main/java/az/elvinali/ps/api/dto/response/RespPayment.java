package az.elvinali.ps.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespPayment {
    private Long orderId;
    private Double amount;
    private String transactionId;
    private String paymentStatus;
}
