package az.elvinali.os.api.service;

import az.elvinali.os.api.dto.request.ReqOrder;
import az.elvinali.os.api.dto.request.ReqPayment;
import az.elvinali.os.api.dto.request.ReqTransaction;
import az.elvinali.os.api.dto.response.RespOrder;
import az.elvinali.os.api.dto.response.RespPayment;
import az.elvinali.os.api.dto.response.RespTransaction;
import az.elvinali.os.api.entity.Order;
import az.elvinali.os.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@RefreshScope
public class OrderService {

    private final OrderRepository orderRepository;

    @Lazy
    private final RestTemplate restTemplate;
    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String PAYMENT_ENDPOINT_URL;

    public RespTransaction saveOrder(ReqTransaction reqTransaction) {
        ReqOrder reqOrder = reqTransaction.getOrder();
        Order order = convertFromReq(reqOrder);
        Order savedOrder = orderRepository.save(order);
        ReqPayment reqPayment = reqTransaction.getPayment();
        reqPayment.setAmount(savedOrder.getPrice());
        reqPayment.setOrderId(savedOrder.getId());

        RespOrder respOrder = convertFromModel(order);
        RespPayment respPayment = restTemplate.postForObject(PAYMENT_ENDPOINT_URL, reqPayment, RespPayment.class);

        String message = respPayment.getPaymentStatus()
                .equals("success") ? "payment processing successful and order placed" : "there is failure in payment apu, order added to cart";

        return RespTransaction.builder()
                .order(respOrder)
                .transactionId(respPayment.getTransactionId())
                .message(message)
                .amount(respPayment.getAmount())
                .build();
    }

    private RespOrder convertFromModel(Order order) {
        return RespOrder.builder()
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .name(order.getName())
                .build();
    }

    private Order convertFromReq(ReqOrder reqOrder) {
        return Order.builder()
                .name(reqOrder.getName())
                .price(reqOrder.getPrice())
                .quantity(reqOrder.getQuantity())
                .build();

    }
}
