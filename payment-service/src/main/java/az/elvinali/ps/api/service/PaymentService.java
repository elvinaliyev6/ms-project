package az.elvinali.ps.api.service;

import az.elvinali.ps.api.dto.request.ReqPayment;
import az.elvinali.ps.api.dto.response.RespPayment;
import az.elvinali.ps.api.entity.Payment;
import az.elvinali.ps.api.mapper.PaymentMapper;
import az.elvinali.ps.api.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    Logger log= LoggerFactory.getLogger(PaymentService.class);

    public RespPayment doPayment(ReqPayment reqPayment) throws JsonProcessingException {
        log.info("Payment-Service Request : {}",new ObjectMapper().writeValueAsString(reqPayment));
        Payment payment=PaymentMapper.mapRequestToEntity(reqPayment);
        paymentRepository.save(payment);
        RespPayment respPayment = PaymentMapper.mapEntityToResponse(payment);
        return respPayment;
    }


    public RespPayment getPaymentHistorybyOrderId(Long orderId) throws JsonProcessingException {
        Payment payment = paymentRepository.findByOrderId(orderId);
        log.info("paymentService findPaymentHistoryByOrderId : {}",new ObjectMapper().writeValueAsString(payment));
        return PaymentMapper.mapEntityToResponse(payment);
    }
}
