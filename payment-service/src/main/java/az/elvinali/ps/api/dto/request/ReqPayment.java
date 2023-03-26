package az.elvinali.ps.api.dto.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ReqPayment {

    @Min(value = 1)
    private Long orderId;

    @Min(value = 1)
    @Max(value = 1000)
    private Double amount;

}
