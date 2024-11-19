package com.blue;

import com.blue.config.WxCpConfiguration;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpMessageService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpMessageServiceImpl;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VxWorkerApplicationTests {

    @Test
    void contextLoads() throws WxErrorException {

        WxCpService cpService = WxCpConfiguration.getCpService("123");

        WxCpMessageService messageService = new WxCpMessageServiceImpl(cpService);

        WxCpMessage wxCpMessage = WxCpMessage
          .TEXT()
          .agentId(cpService.getWxCpConfigStorage().getAgentId()) // 企业号应用ID
           .toUser("@all")
          .content("发送系统消息\n换行一下")
          .build();
        messageService.send(wxCpMessage);
    }

}
