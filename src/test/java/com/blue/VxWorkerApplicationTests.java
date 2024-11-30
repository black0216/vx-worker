package com.blue;

import com.blue.config.WxCpConfiguration;
import com.blue.entity.ImageContent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    void contextLoads() throws WxErrorException, JsonProcessingException {


        String jsonData = "{\"file_path\":\"E:\\\\企微缓存\\\\WXWork\\\\16888000000\\\\Cache\\\\Image\\\\Temp\\\\xxx.jpg\",\"file_size\":235192,\"source_type\":2}";


        ObjectMapper objectMapper = new ObjectMapper();
        ImageContent imageContent = objectMapper.readValue(jsonData, ImageContent.class);
        System.out.println(imageContent);

    }

}
