package com.erp.web.controller.common;

import org.springframework.stereotype.Component;

import com.erp.framework.payment.enums.CashierEnum;
import com.erp.framework.payment.kit.dto.PayParam;
import com.erp.framework.payment.kit.dto.PaymentSuccessParams;
import com.erp.framework.payment.kit.params.CashierExecute;
import com.erp.framework.payment.kit.params.dto.CashierParam;

import lombok.extern.slf4j.Slf4j;

/**
 * 订单支付信息获取
 *
 * @author Chopper
 * @since 2021-01-25 20:00
 */
@Slf4j
@Component
public class OrderCashier implements CashierExecute {


    @Override
    public CashierEnum cashierEnum() {
        return CashierEnum.ORDER;
    }

    @Override
    public CashierParam getPaymentParams(PayParam payParam) {
//        if (payParam.getOrderType().equals(CashierEnum.ORDER.name())) {
//            //准备返回的数据
//            CashierParam cashierParam = new CashierParam();
//            //订单信息获取
//            TThesisOrder order = iTThesisOrderService.getById(Long.valueOf(payParam.getSn()));
//
//            //如果订单已支付，则不能发器支付
//            if (order.getPayStatus().equals("1")) {
//                throw new ServiceException(ResultCode.PAY_DOUBLE_ERROR);
//            }
//            //如果订单状态不是待付款，则抛出异常
//            if (!order.getStatus().equals(20)) {
//                throw new ServiceException(ResultCode.PAY_BAN, "只能支付【学校审核通过】状态的订单");
//            }
//            //如果订单状态不是后付费，则抛出异常
//            if (!order.getPayWay().equals("1")) {
//                throw new ServiceException(ResultCode.PAY_BAN, "只能支付【后付费】的订单");
//            }
//            cashierParam.setPrice(order.getUserAmount().doubleValue());
//            cashierParam.setTitle("论文润色，在线支付");
//            cashierParam.setDetail("个人费用支付");
//
//            cashierParam.setOrderSns(payParam.getSn());
//            cashierParam.setCreateTime(order.getCreateTime());
//            return cashierParam;
//        }

        return null;
    }

    @Override
    public void paymentSuccess(PaymentSuccessParams paymentSuccessParams) {

        PayParam payParam = paymentSuccessParams.getPayParam();
        if (payParam.getOrderType().equals(CashierEnum.ORDER.name())) {
//        	log.info("订单{}支付成功,金额{},方式{}", payParam.getSn(),
//        			paymentSuccessParams.getPaymentMethod(),
//        			paymentSuccessParams.getReceivableNo());
//        	try {
//	            TThesisOrder updateOrder = new TThesisOrder();
//	            updateOrder.setId(Long.valueOf(payParam.getSn()));
//	            updateOrder.setPayStatus("1");
//	            updateOrder.setStatus(30);
//	            iTThesisOrderService.updateById(updateOrder);
//        	} catch (Exception e) {
//        		log.error("修改订单状态失败，订单ID: {}", payParam.getSn());
//        	}
        }
    }

    @Override
    public Boolean paymentResult(PayParam payParam) {
//        if (payParam.getOrderType().equals(CashierEnum.ORDER.name())) {
//            TThesisOrder order = iTThesisOrderService.getById(Long.valueOf(payParam.getSn()));
//            if (order != null) {
//                return "1".equals(order.getPayStatus());
//            } else {
//                throw new ServiceException(ResultCode.PAY_NOT_EXIST_ORDER);
//            }
//        }
        return false;
    }
}
