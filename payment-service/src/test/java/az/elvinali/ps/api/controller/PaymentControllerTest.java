package az.elvinali.ps.api.controller;


import az.elvinali.ps.api.dto.request.ReqPayment;
import az.elvinali.ps.api.dto.response.RespPayment;
import az.elvinali.ps.api.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PaymentController.class)
class PaymentControllerTest {
    private static String PAYMENT_PATH = "/payment";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    void doPayment_Success() throws Exception {
        //arrange
        ReqPayment request = new ReqPayment();
        request.setOrderId(1L);
        request.setAmount(50.0);

        RespPayment response = new RespPayment();
        response.setOrderId(1L);
        response.setAmount(50.0);

        when(paymentService.doPayment(request)).thenReturn(response);

        //act
        mockMvc.perform(post(PAYMENT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        //assert
        verify(paymentService, times(1))
                .doPayment(request);
    }

    @Test
    void doPayment_WhenAmountSizeExceed_BadRequest() throws Exception {
        //arrange
        ReqPayment request = new ReqPayment();
        request.setAmount(1200.0);
        request.setOrderId(1L);

        RespPayment response = new RespPayment();
        request.setAmount(1200.0);
        request.setOrderId(1L);

        when(paymentService.doPayment(request)).thenReturn(response);

        //act
        mockMvc.perform(post(PAYMENT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        //assert
        verifyNoInteractions(paymentService);
    }

    @Test
    void getPaymentHistoryByOrderId_Success() throws Exception {
//        arrange
        Long ORDER_ID = 1L;
        RespPayment response = new RespPayment();
        response.setAmount(60.0);
        response.setOrderId(ORDER_ID);

        when(paymentService.getPaymentHistorybyOrderId(ORDER_ID)).thenReturn(response);

//        act
        mockMvc.perform(get(PAYMENT_PATH + "/{orderId}", ORDER_ID))
                .andExpect(jsonPath("$.amount", Matchers.is(60.0)))
                .andExpect(status().isOk());

//assert
        verify(paymentService, times(1)).getPaymentHistorybyOrderId(ORDER_ID);
    }


}