package az.elvinali.ps.api.controller;

import az.elvinali.ps.api.dto.request.ReqPayment;
import az.elvinali.ps.api.dto.response.RespPayment;
import az.elvinali.ps.api.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("")
    public RespPayment doPayment(@RequestBody ReqPayment reqPayment){
        return paymentService.doPayment(reqPayment);
    }

    @GetMapping("/{orderId}")
    public RespPayment getPaymentHistorybyOrderId(@PathVariable Long orderId){
        return paymentService.getPaymentHistorybyOrderId(orderId);
    }
}
