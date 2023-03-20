package az.elvinali.os.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqTransaction {
    private ReqOrder order;
    private ReqPayment payment;
}
