package com.forward.payment.service;

import com.forward.payment.entity.Payment;
import com.forward.payment.repo.PaymentRepository;
import com.forward.util.PaymentUtility;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${app.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private PaymentRepository paymentRepository;

    public String doPayment(Payment payment){
        payment.setAccountNumber(PaymentUtility.accountNumber());
        payment.setDebitAccount(PaymentUtility.accountNumber());
        payment.setPaymentDate(PaymentUtility.paymentDate());
        payment.setTrnID(PaymentUtility.trnIdGenerate());
        payment.setPaymentStatus(!payment.getTrnID().isEmpty() ? "FAILURE" : "SUCCESS");
        if(paymentRepository.save(payment).getId()!=0){
            String paymentJson=new Gson().toJson(payment);
            kafkaTemplate.send("payment",paymentJson);
          return "Payment done successfully";
        }else{
            return "Payment failure";
        }
    }
}
