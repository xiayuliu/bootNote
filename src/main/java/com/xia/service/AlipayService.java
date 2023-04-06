/*
package com.xia.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.xia.contoller.PayRequest;
import com.xia.contoller.PayResponse;
import com.alipay.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AlipayService {

    @Value("${alipay.gatewayUrl}")
    private String gatewayUrl;

    @Value("${alipay.appId}")
    private String appId;

    @Value("${alipay.privateKey}")
    private String privateKey;

    @Value("${alipay.publicKey}")
    private String publicKey;

    @Value("${alipay.charset}")
    private String charset;

    @Value("${alipay.format}")
    private String format;

    @Value("${alipay.signType}")
    private String signType;

    @Resource
    private AlipayTradeService alipayTradeService;

    */
/**
     * 支付宝扫码支付
     *
     * @param payRequest 支付请求
     * @return 支付响应
     *//*

    public PayResponse pay(PayRequest payRequest) {
        PayResponse payResponse = new PayResponse();
        AlipayTradePrecreateResponse response;
        try {
            AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
            request.setBizContent("{" +
                    "\"out_trade_no\":\"" + payRequest.getOutTradeNo() + "\"," +
                    "\"total_amount\":\"" + payRequest.getTotalAmount() + "\"," +
                    "\"subject\":\"" + payRequest.getSubject() + "\"" +
                    "}");

            response = alipayTradeService.tradePrecreate(request);
            if (response.isSuccess()) {
                payResponse.setSuccess(true);
                payResponse.setQrCode(response.getQrCode());
            } else {
                payResponse.setSuccess(false);
                payResponse.setErrorMsg(response.getSubMsg());
            }
        } catch (AlipayApiException e) {
            payResponse.setSuccess(false);
            payResponse.setErrorMsg(e.getMessage());
        }

        return payResponse;
    }


    */
/**
     * 获取支付宝交易服务
     *
     * @return 支付宝交易服务
     *//*

    @Bean
    public AlipayTradeService alipayTradeService() {

        AlipayTradeService alipayTradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
        return new AlipayTradeServiceImpl.ClientBuilder()
                .setGatewayUrl(gatewayUrl)
                .setAppId(appId)
                .setPrivateKey(privateKey)
                .setAlipayPublicKey(publicKey)
                .setSignType(signType)
                .setCharset(charset)
                .build();
    }

}*/
