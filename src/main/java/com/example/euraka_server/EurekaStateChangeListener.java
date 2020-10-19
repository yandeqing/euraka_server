package com.example.euraka_server;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.eureka.registry.AbstractInstanceRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EurekaStateChangeListener {
    private static final Logger logger = LoggerFactory.getLogger(AbstractInstanceRegistry.class);

    @EventListener(condition = "#event.replication==false")
    public void listen(EurekaInstanceCanceledEvent event) {
        System.err.println();
        logger.info("微服务({})下线了", event.getAppName());
    }

    @EventListener(condition = "#event.replication==false")
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        logger.info("微服务({})上线了", instanceInfo.getAppName());
    }

    @EventListener(condition = "#event.replication==false")
    public void listen(EurekaInstanceRenewedEvent event) {
        logger.info("收到微服务({})续约", event.getAppName());
    }


    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        logger.info("服务中心进入可用状态");
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        logger.info("服务中心启动成功");
        //Server启动
    }
}
