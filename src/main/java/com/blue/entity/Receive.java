package com.blue.entity;

import lombok.Data;

import java.util.List;


@Data
public class Receive {

    private Integer type;
    private Integer msg_type;
    private String msg_id;
    private String user_id;
    private List<String> at_list;
    private Object content;
    private String sender;
    private Long time_stamp;
    private Integer is_self_msg;
    private Integer is_pc_msg;
    private String self_user_id;
    private Integer port;

}
