package az.elvinali.ps.api.mapper;

import az.elvinali.ps.api.dto.request.ReqPayment;
import az.elvinali.ps.api.dto.response.RespPayment;
import az.elvinali.ps.api.entity.Payment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PaymentMapperTest {

    @Test
    void toEntityTest() {
        //arrange
        ReqPayment request = new ReqPayment();
        request.setAmount(50.0);
        request.setOrderId(1L);

        Payment expected = new Payment();
        expected.setAmount(50.0);
        expected.setOrderId(1L);

        //act
        Payment actual = PaymentMapper.mapRequestToEntity(request);
        actual.setTransactionId(null);
        actual.setPaymentStatus(null);

        //assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void toResponseTest(){
        //arrange
        Payment payment=new Payment();
        payment.setTransactionId("transaction12345");
        payment.setAmount(20.0);
        payment.setOrderId(1L);
        payment.setPaymentStatus("success");

        RespPayment expected=new RespPayment();
        expected.setTransactionId("transaction12345");
        expected.setAmount(20.0);
        expected.setOrderId(1L);
        expected.setPaymentStatus("success");

        //act
        RespPayment actual=PaymentMapper.mapEntityToResponse(payment);

        //assert
        Assertions.assertThat(actual).isEqualTo(expected);

    }

}