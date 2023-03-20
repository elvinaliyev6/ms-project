package az.elvinali.os.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespPayment {
    private Long orderId;
    private Double amount;
    private String transactionId;
    private String paymentStatus;
}
