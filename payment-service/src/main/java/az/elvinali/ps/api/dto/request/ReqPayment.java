package az.elvinali.ps.api.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqPayment {

    @NotBlank
    private Long orderId;

    @NotBlank
    private Double amount;

}
