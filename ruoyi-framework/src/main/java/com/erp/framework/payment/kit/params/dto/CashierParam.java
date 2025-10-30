package com.erp.framework.payment.kit.params.dto;

import java.util.Date;
import java.util.List;

import com.erp.framework.payment.kit.core.utils.StringUtils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 支付参数
 *
 * @author Chopper
 * @since 2021-01-25 19:09
 */
@Data
@ToString
public class CashierParam {

    @ApiModelProperty(value = "价格 (元)")
    private Double price;

    @ApiModelProperty(value = "支付title")
    private String title;

    @ApiModelProperty(value = "支付详细描述")
    private String detail;

    @ApiModelProperty(value = "订单sn集合")
    private String orderSns;

    @ApiModelProperty(value = "支持支付方式")
    private List<String> support;


    @ApiModelProperty(value = "订单创建时间")
    private Date createTime;

    @ApiModelProperty(value = "支付自动结束时间")
    private Long autoCancel;

    @ApiModelProperty(value = "剩余余额")
    private Double walletValue;

    public String getDetail() {
        if (StringUtils.isEmpty(detail)) {
            return "清单详细";
        }
        return StringUtils.filterSpecialChart(detail);
    }
}
