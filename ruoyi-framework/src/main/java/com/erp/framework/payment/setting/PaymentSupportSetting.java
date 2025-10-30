package com.erp.framework.payment.setting;

import java.util.ArrayList;
import java.util.List;

import com.erp.framework.payment.enums.ClientTypeEnum;
import com.erp.framework.payment.enums.PaymentMethodEnum;
import com.erp.framework.payment.setting.dto.PaymentSupportForm;
import com.erp.framework.payment.setting.dto.PaymentSupportItem;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 支持的支付方式
 *
 * @author Chopper
 * @since 2021-01-26 15:52
 */
@Data
@Accessors(chain = true)
public class PaymentSupportSetting {

    private List<PaymentSupportItem> paymentSupportItems;


    public PaymentSupportSetting() {

    }

    public PaymentSupportSetting(PaymentSupportForm paymentSupportForm) {

        List<PaymentSupportItem> paymentSupportItems = new ArrayList<>();

        for (ClientTypeEnum client : paymentSupportForm.getClients()) {
            PaymentSupportItem paymentSupportItem = new PaymentSupportItem();

            List<String> supports = new ArrayList<>();
            for (PaymentMethodEnum payment : paymentSupportForm.getPayments()) {
                supports.add(payment.name());
            }
            paymentSupportItem.setClient(client.name());
            paymentSupportItem.setSupports(supports);
            paymentSupportItems.add(paymentSupportItem);

        }
        this.paymentSupportItems = paymentSupportItems;
    }
}
