package com.flowsand.project.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class IdRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
}

