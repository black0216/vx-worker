package com.blue.config;


import com.google.common.collect.Maps;
import lombok.val;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

import java.util.Map;


@Configuration
@EnableConfigurationProperties(WxCpProperties.class)
public class WxCpConfiguration {

    private WxCpProperties properties;


    private static Map<String, WxCpService> cpServices = Maps.newHashMap();

    @Autowired
    public WxCpConfiguration(WxCpProperties properties) {

        this.properties = properties;
    }


    public static WxCpService getCpService(String userId) {
        return cpServices.get(userId);
    }


    public static WxCpService getCpService(Integer agentId) {

        for (WxCpService value : cpServices.values()) {
            if (value.getWxCpConfigStorage().getAgentId().equals(agentId)) {
                return value;
            }
        }
        return null;
    }

    @PostConstruct
    public void initServices() {

        for (WxCpProperties.AppConfig a : this.properties.getAppConfigs()) {
            val configStorage = new WxCpDefaultConfigImpl();
            configStorage.setCorpId(this.properties.getCorpId());
            configStorage.setAgentId(a.getAgentId());
            configStorage.setCorpSecret(a.getSecret());
            configStorage.setToken(a.getToken());
            configStorage.setAesKey(a.getAesKey());
            val service = new WxCpServiceImpl();
            service.setWxCpConfigStorage(configStorage);
            cpServices.put(a.getFromUserId(), service);
        }

    }


}
