package com.erp.common.core.domain.model;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 用户登录对象
 * 
 * @author erp
 */

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LoginBody
{
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid = "";
    
    /** 商户账号 */
    private String merchantAccount;
    
    /** 人员类型 1-站点管理员 2-站点用户 **/
    private String loginType;

}
