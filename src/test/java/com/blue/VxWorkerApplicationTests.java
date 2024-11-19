package com.blue;

import com.blue.config.WxCpConfiguration;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpMessageService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpMessageServiceImpl;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@Slf4j
@SpringBootTest
class VxWorkerApplicationTests {


    @Test
    void contextLoads() throws WxErrorException {

        WxCpService cpService = WxCpConfiguration.getCpService(10000028);


        if (cpService != null){
            System.out.println("cpService is not null");
        }
        System.out.println("cpService");

    }

}
