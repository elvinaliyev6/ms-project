package az.elvinali.os.api.controller;

import az.elvinali.os.api.dto.request.ReqTransaction;
import az.elvinali.os.api.dto.response.RespOrder;
import az.elvinali.os.api.dto.response.RespTransaction;
import az.elvinali.os.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/bookOrder")
    public RespTransaction saveOrder(@RequestBody @Valid ReqTransaction reqTransaction){
        return orderService.saveOrder(reqTransaction);
    }

}
