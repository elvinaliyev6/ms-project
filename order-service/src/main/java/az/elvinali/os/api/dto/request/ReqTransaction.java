package az.elvinali.os.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqTransaction {

    @Valid
    private ReqOrder order;
    private ReqPayment payment;
}
