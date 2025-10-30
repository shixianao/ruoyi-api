package com.erp.demo.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.demo.domain.TestDemoRoute;
import com.erp.demo.service.ITestDemoRouteService;
import com.erp.demo.service.impl.TestDemoRouteServiceImpl;
import com.erp.module.bpm.enums.task.BpmProcessInstanceResultEnum;
import com.erp.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEvent;
import com.erp.module.bpm.framework.bpm.core.event.BpmProcessInstanceResultEventListener;

import cn.hutool.json.JSONUtil;

@Component
public class DemoRouteResultListener extends BpmProcessInstanceResultEventListener {
	
	@Autowired
	private ITestDemoRouteService testDemoRouteService;
	
    @Override
    protected String getProcessDefinitionKey() {
        return TestDemoRouteServiceImpl.PROCESS_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceResultEvent event) {
    	System.out.println(JSONUtil.toJsonStr(event));
    	if (BpmProcessInstanceResultEnum.APPROVE.getResult().equals(event.getResult())) {
    		TestDemoRoute demo = testDemoRouteService.getEntity(Long.valueOf(event.getBusinessKey()));
    		demo.setId(demo.getProcessToChangeId() > 0 ? demo.getProcessToChangeId() : null);
    		demo.setProcessToChangeId(null);
    		demo.setProcessInstanceId(null);
    		testDemoRouteService.createOrUpdateEntity(demo);
    	}
    }
    
}
