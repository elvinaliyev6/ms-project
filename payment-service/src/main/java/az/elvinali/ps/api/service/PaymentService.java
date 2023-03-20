package az.elvinali.ps.api.service;

import az.elvinali.ps.api.dto.request.ReqPayment;
import az.elvinali.ps.api.dto.response.RespPayment;
import az.elvinali.ps.api.entity.Payment;
import az.elvinali.ps.api.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public RespPayment doPayment(ReqPayment reqPayment) {
        Payment payment = new Payment();
        payment.setAmount(reqPayment.getAmount());
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPaymentStatus(paymentProcession());
        payment.setOrderId(reqPayment.getOrderId());

        paymentRepository.save(payment);
        RespPayment respPayment = convertFromEntity(payment);
        return respPayment;
    }

    private RespPayment convertFromEntity(Payment payment) {
        return RespPayment.builder()
                .amount(payment.getAmount())
                .paymentStatus(payment.getPaymentStatus())
                .orderId(payment.getOrderId())
                .transactionId(payment.getTransactionId())
                .build();
    }

    private String paymentProcession() {
        return new Random().nextBoolean() ? "success" : "fail";
    }

    public RespPayment getPaymentHistorybyOrderId(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        return convertFromEntity(payment);
    }
}
