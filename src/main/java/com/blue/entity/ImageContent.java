package com.blue.entity;

import lombok.Data;

@Data
public class ImageContent {
    private String file_path;
    private Long file_size;
    private Integer source_type;
}
