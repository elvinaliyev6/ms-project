package az.elvinali.ps.api.mapper;

import az.elvinali.ps.api.dto.request.ReqPayment;
import az.elvinali.ps.api.dto.response.RespPayment;
import az.elvinali.ps.api.entity.Payment;

import java.util.Random;
import java.util.UUID;

public class PaymentMapper {

    public static Payment mapRequestToEntity(ReqPayment reqPayment){
        return Payment.builder()
                .amount(reqPayment.getAmount())
                .paymentStatus(paymentProcession())
                .transactionId(UUID.randomUUID().toString())
                .orderId(reqPayment.getOrderId())
                .build();
    }

    public static RespPayment mapEntityToResponse(Payment payment){
        return RespPayment.builder()
                .amount(payment.getAmount())
                .paymentStatus(payment.getPaymentStatus())
                .orderId(payment.getOrderId())
                .transactionId(payment.getTransactionId())
                .build();
    }
    private static String paymentProcession() {
        return new Random().nextBoolean() ? "success" : "fail";
    }

}
