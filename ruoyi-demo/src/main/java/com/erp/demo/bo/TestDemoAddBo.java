package com.erp.demo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.constraints.*;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 测试单添加对象 test_demo
 *
 * @author erp
 * @date 2022-03-19
 */
@Data
@ApiModel("测试单添加对象")
public class TestDemoAddBo {


    /** 用户id */
    @ApiModelProperty("用户id")
    private Long userId;

    /** 名称 */
    @ApiModelProperty("名称")
    private String name;

    /** 头像 */
    @ApiModelProperty("头像")
    private String img;

    /** 状态（0正常 1停用） */
    @ApiModelProperty("状态（0正常 1停用）")
    @NotBlank(message = "状态（0正常 1停用）不能为空")
    private String status;

    /** 出生日期 */
    @ApiModelProperty("出生日期")
    private Date birthday;

    /** 性别 */
    @ApiModelProperty("性别")
    private String sex;

    /** 爱好 */
    @ApiModelProperty("爱好")
    private String love;

    /** 附件 */
    @ApiModelProperty("附件")
    private String attachFiles;

    /** 简介 */
    @ApiModelProperty("简介")
    private String info;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;
}
