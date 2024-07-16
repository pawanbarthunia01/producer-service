package com.forward.payment.controller;

import com.forward.payment.entity.Payment;
import com.forward.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<String> doPayment(@RequestBody Payment payment){
        return new ResponseEntity<>(paymentService.doPayment(payment), HttpStatus.CREATED);
    }
}
