package com.eypg.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.eypg.pojo.SysConfigure;
import com.eypg.service.SysConfigureService;

public class ApplicationListenerImpl implements ApplicationListener {

    @Autowired
    private SysConfigureService sysConfigureService;
    public static SysConfigure sysConfigureJson;

    public SysConfigureService getSysConfigureService() {
        return sysConfigureService;
    }

    public void setSysConfigureService(SysConfigureService sysConfigureService) {
        this.sysConfigureService = sysConfigureService;
    }

    public void onApplicationEvent(ApplicationEvent arg0) {
        if (arg0 instanceof ApplicationEvent) {
            sysConfigureJson = sysConfigureService.findById("1");
            System.err.println(sysConfigureJson.getSaitName());

        }
    }

}
