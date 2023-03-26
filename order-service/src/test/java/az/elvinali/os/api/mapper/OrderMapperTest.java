package az.elvinali.os.api.mapper;

import az.elvinali.os.api.dto.request.ReqOrder;
import az.elvinali.os.api.dto.response.RespOrder;
import az.elvinali.os.api.entity.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderMapperTest {

    @Test
    void toEntityTest() {
        //arrange
        ReqOrder request = new ReqOrder();
        request.setName("Book");
        request.setPrice(200.0);
        request.setQuantity(5);

        Order expected = new Order();
        expected.setName("Book");
        expected.setPrice(200.0);
        expected.setQuantity(5);

        //act
        Order actual = OrderMapper.mapRequestToEntity(request);

        //assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void toResponseTest() {
//        arrange
        Order order = new Order();
        order.setName("book");
        order.setPrice(900.0);
        order.setQuantity(8);

        RespOrder expected = new RespOrder();
        expected.setName("book");
        expected.setPrice(900.0);
        expected.setQuantity(8);

        //act
        RespOrder actual = OrderMapper.mapEntityToResponse(order);

        //assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }

}