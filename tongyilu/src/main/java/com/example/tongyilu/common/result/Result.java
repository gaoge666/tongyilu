package com.example.tongyilu.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author xwolf
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private boolean success;

    private String code;

    private String message;

    private Object content;

}

