package com.erp.framework.payment.service;

import com.erp.framework.payment.kit.dto.PaymentSuccessParams;

/**
 * 支付日志 业务层
 *
 * @author Chopper
 * @since 2020-12-19 09:25
 */
public interface PaymentService {

    /**
     * 支付成功通知
     *
     * @param paymentSuccessParams 支付成功回调参数
     */
    void success(PaymentSuccessParams paymentSuccessParams);


    /**
     * 平台支付成功
     *
     * @param paymentSuccessParams 支付成功回调参数
     */
    void adminPaySuccess(PaymentSuccessParams paymentSuccessParams);


    /**
     * 获取支付日志
     *
     * @param initPage
     * @param initWrapper
     * @return
     */
//    IPage<PaymentLog> page(Page<PaymentLog> initPage, QueryWrapper<PaymentLog> initWrapper);
}