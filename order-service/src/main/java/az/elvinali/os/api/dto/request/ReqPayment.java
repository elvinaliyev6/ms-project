package az.elvinali.os.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqPayment {
    @NotBlank
    private Long orderId;
    @NotBlank
    private Double amount;
}
