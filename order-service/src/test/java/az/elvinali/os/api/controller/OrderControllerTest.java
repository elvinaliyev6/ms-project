package az.elvinali.os.api.controller;

import az.elvinali.os.api.dto.request.ReqOrder;
import az.elvinali.os.api.dto.request.ReqPayment;
import az.elvinali.os.api.dto.request.ReqTransaction;
import az.elvinali.os.api.dto.response.RespOrder;
import az.elvinali.os.api.dto.response.RespTransaction;
import az.elvinali.os.api.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    private static final String ORDER_PATH = "/order";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void saveOrder_Success() throws Exception {
        //arrange
        ReqTransaction request = new ReqTransaction();
        ReqOrder reqOrder = new ReqOrder();
        reqOrder.setName("book");
        reqOrder.setQuantity(5);
        reqOrder.setPrice(100.0);

        request.setOrder(reqOrder);
        request.setPayment(new ReqPayment());

        RespTransaction response = new RespTransaction();
        RespOrder respOrder = new RespOrder();
        respOrder.setName("book");
        respOrder.setQuantity(5);
        respOrder.setPrice(100.0);

        response.setOrder(respOrder);
        response.setAmount(100.0);

        when(orderService.saveOrder(request)).thenReturn(response);

        //act
        mockMvc.perform(post(ORDER_PATH+"/bookOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(jsonPath("$.amount", Matchers.is(100.0)))
                .andExpect(status().isOk());

        //assert
        verify(orderService, times(1)).saveOrder(request);

    }

    @Test
    void saveOrder_WhenNameIsBlank_BadRequest() throws Exception{
        //arrange
        ReqTransaction request = new ReqTransaction();
        ReqOrder reqOrder = new ReqOrder();
        reqOrder.setName("");
        reqOrder.setQuantity(5);
        reqOrder.setPrice(100.0);

        request.setOrder(reqOrder);
        request.setPayment(new ReqPayment());

        RespTransaction response = new RespTransaction();
        RespOrder respOrder = new RespOrder();
        respOrder.setName("");
        respOrder.setQuantity(5);
        respOrder.setPrice(100.0);

        response.setOrder(respOrder);
        response.setAmount(100.0);

        when(orderService.saveOrder(request)).thenReturn(response);

        //act
        mockMvc.perform(post(ORDER_PATH+"/bookOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        //assert
        verifyNoInteractions(orderService);
    }

    @Test
    void saveOrder_WhenQuantitySizeExceed_BadRequest() throws Exception{
        //arrange
        ReqTransaction request = new ReqTransaction();
        ReqOrder reqOrder = new ReqOrder();
        reqOrder.setName("Book");
        reqOrder.setQuantity(101);
        reqOrder.setPrice(100.0);

        request.setOrder(reqOrder);
        request.setPayment(new ReqPayment());

        RespTransaction response = new RespTransaction();
        RespOrder respOrder = new RespOrder();
        respOrder.setName("Book");
        respOrder.setQuantity(101);
        respOrder.setPrice(100.0);

        response.setOrder(respOrder);
        response.setAmount(100.0);

        when(orderService.saveOrder(request)).thenReturn(response);

        //act
        mockMvc.perform(post(ORDER_PATH+"/bookOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        //assert
        verifyNoInteractions(orderService);
    }

    @Test
    void saveOrder_WhenPriceSizeExceed_BadRequest() throws Exception{
        //arrange
        ReqTransaction request = new ReqTransaction();
        ReqOrder reqOrder = new ReqOrder();
        reqOrder.setName("Book");
        reqOrder.setQuantity(90);
        reqOrder.setPrice(109.0);

        request.setOrder(reqOrder);
        request.setPayment(new ReqPayment());

        RespTransaction response = new RespTransaction();
        RespOrder respOrder = new RespOrder();
        respOrder.setName("Book");
        respOrder.setQuantity(90);
        respOrder.setPrice(109.0);

        response.setOrder(respOrder);
        response.setAmount(109.0);

        when(orderService.saveOrder(request)).thenReturn(response);

        //act
        mockMvc.perform(post(ORDER_PATH+"/bookOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        //assert
        verifyNoInteractions(orderService);
    }

}