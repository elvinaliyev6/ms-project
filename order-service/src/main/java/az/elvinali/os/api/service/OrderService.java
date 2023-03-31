package az.elvinali.os.api.service;

import az.elvinali.os.api.dto.request.ReqOrder;
import az.elvinali.os.api.dto.request.ReqPayment;
import az.elvinali.os.api.dto.request.ReqTransaction;
import az.elvinali.os.api.dto.response.RespOrder;
import az.elvinali.os.api.dto.response.RespPayment;
import az.elvinali.os.api.dto.response.RespTransaction;
import az.elvinali.os.api.entity.Order;
import az.elvinali.os.api.mapper.OrderMapper;
import az.elvinali.os.api.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;

@Service
@RequiredArgsConstructor
@RefreshScope

public class OrderService {

    private final OrderRepository orderRepository;

    Logger log= LoggerFactory.getLogger(OrderService.class);

    @Lazy
    private final RestTemplate restTemplate;
    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String PAYMENT_ENDPOINT_URL;

    public RespTransaction saveOrder(ReqTransaction reqTransaction) throws Exception {
        ReqOrder reqOrder = reqTransaction.getOrder();
        Order order = OrderMapper.mapRequestToEntity(reqOrder);
        Order savedOrder = orderRepository.save(order);
        ReqPayment reqPayment = reqTransaction.getPayment();
        reqPayment.setAmount(savedOrder.getPrice());
        reqPayment.setOrderId(savedOrder.getId());

        RespOrder respOrder = OrderMapper.mapEntityToResponse(order);

        log.info("Order-Service Request : "+new ObjectMapper().writeValueAsString(reqTransaction));
        //rest call
        RespPayment respPayment = restTemplate.postForObject(PAYMENT_ENDPOINT_URL, reqPayment, RespPayment.class);
        log.info("Order Service getting Response from Payment-Service : "+new ObjectMapper().writeValueAsString(respPayment));

        String message = respPayment.getPaymentStatus()
                .equals("success") ? "payment processing successful and order placed" : "there is failure in payment apu, order added to cart";

        return RespTransaction.builder()
                .order(respOrder)
                .transactionId(respPayment.getTransactionId())
                .message(message)
                .amount(respPayment.getAmount())
                .build();
    }

}
