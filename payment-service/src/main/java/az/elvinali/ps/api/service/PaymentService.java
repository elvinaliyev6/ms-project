package az.elvinali.ps.api.service;

import az.elvinali.ps.api.dto.request.ReqPayment;
import az.elvinali.ps.api.dto.response.RespPayment;
import az.elvinali.ps.api.entity.Payment;
import az.elvinali.ps.api.mapper.PaymentMapper;
import az.elvinali.ps.api.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public RespPayment doPayment(ReqPayment reqPayment) {

        Payment payment=PaymentMapper.mapRequestToEntity(reqPayment);
        paymentRepository.save(payment);
        RespPayment respPayment = PaymentMapper.mapEntityToResponse(payment);
        return respPayment;
    }


    public RespPayment getPaymentHistorybyOrderId(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        return PaymentMapper.mapEntityToResponse(payment);
    }
}
