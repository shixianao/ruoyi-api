package com.erp.demo.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.erp.common.core.domain.DiBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 测试单对象 test_demo
 *
 * @author erp
 * @date 2022-03-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Accessors(chain = true)
@TableName("test_demo_props")
public class TestDemoProps extends DiBaseEntity implements Serializable {

	private static final long serialVersionUID = -1;

	private Long demoId;
	
    /** 名称 */
    private String name;

}
