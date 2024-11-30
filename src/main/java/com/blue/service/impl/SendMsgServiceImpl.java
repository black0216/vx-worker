package com.blue.service.impl;

import com.blue.config.WxCpConfiguration;
import com.blue.entity.ImageContent;
import com.blue.entity.Receive;
import com.blue.service.SendMsgService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpMessageService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpMessageServiceImpl;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Slf4j
@Service
public class SendMsgServiceImpl implements SendMsgService {

    public final Integer TYPE_TEXT = 2;
    public final Integer TYPE_IMAGE = 14;


    @Override
    public void sendMsg(Receive msg) {

        if (msg.getUser_id() == null) {
            return;
        }


        WxCpService wxCpService = WxCpConfiguration.getCpService(msg.getUser_id());

        if (wxCpService != null) {
            WxCpMessageService messageService = new WxCpMessageServiceImpl(wxCpService);
            if (Objects.equals(msg.getMsg_type(), TYPE_TEXT)) {

                log.info("\n已记录消息： 用户id {}  转发应用id {} \n 消息内容： {}", msg.getUser_id(),wxCpService.getWxCpConfigStorage().getAgentId(),msg.getContent());
                WxCpMessage wxCpMessage = WxCpMessage
                        .TEXT()
                        .agentId(wxCpService.getWxCpConfigStorage().getAgentId())
                        .toUser("@all")
                        .content(msg.getContent().toString())
                        .build();
                try {
                    messageService.send(wxCpMessage);
                } catch (WxErrorException e) {
                    throw new RuntimeException(e);
                }
            } else if (Objects.equals(msg.getMsg_type(), TYPE_IMAGE)) {

                log.info("\n收到图片消息：{}  消息对象 {}", msg.getUser_id(),msg.getContent().toString());

                try {
                    ImageContent imageContent = (ImageContent)msg.getContent();
                    File file = new File(imageContent.getFile_path());
                    // 上传图片
                    WxMediaUploadResult res = wxCpService.getMediaService().upload("image", file);

                    WxCpMessage wxCpMessage = WxCpMessage
                            .IMAGE()
                            .agentId(wxCpService.getWxCpConfigStorage().getAgentId())
                            .toUser("@all")
                            .mediaId(res.getMediaId())
                            .build();
                    messageService.send(wxCpMessage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }


        } else {
            log.warn("\n未收录的消息：用户id {}  内容：{}", msg.getUser_id(), msg.getContent().toString());
        }


    }
}
