package com.microservice.PaymentService.service;

import com.microservice.PaymentService.entity.TransactionDetails;
import com.microservice.PaymentService.model.PaymentMode;
import com.microservice.PaymentService.model.PaymentRequest;
import com.microservice.PaymentService.model.PaymentResponse;
import com.microservice.PaymentService.repository.PaymentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording payment Details {}:");
        TransactionDetails transactionDetails =
                TransactionDetails.builder()
                        .paymentDate(Instant.now())
                        .paymentMode(paymentRequest.getPaymentMode().name())
                        .paymentStatus("Success")
                        .orderId(paymentRequest.getOrderId())
                        .referenceNumber(paymentRequest.getReferenceNumber())
                        .amount(paymentRequest.getAmount())
                        .build();
        paymentRepository.save(transactionDetails);
        log.info("Transaction is Completed with Id: {}", transactionDetails.getPaymentId());
        return transactionDetails.getPaymentId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(String orderId) {
        log.info("Getting payment details for the Order Id: {}", orderId);

        TransactionDetails transactionDetails
                = paymentRepository.findByOrderId(Long.valueOf(orderId));

        PaymentResponse paymentResponse
                = PaymentResponse.builder()
                .paymentId(transactionDetails.getOrderId())
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                .paymentDate(transactionDetails.getPaymentDate())
                .orderId(transactionDetails.getOrderId())
                .status(transactionDetails.getPaymentStatus())
                .amount(transactionDetails.getAmount())
                .build();
        return paymentResponse;
    }
}
