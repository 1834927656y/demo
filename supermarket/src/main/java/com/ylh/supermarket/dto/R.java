package com.ylh.supermarket.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class R {
    //    定义状态码：
    private Integer code;
    //    成功或失败标记
    private boolean success;
    //    返回文本
    private String msg;
    //返回到客户端的附加数据
    private Object data;
    //    定义ok返回结果
    public static R ok(){
        R r = new R();
        r.code = 0;
        r.success = true;
        r.msg = "成功";
        return  r;
    }

    public static R fail(){
        R r = new R();
        r.code = -1;
        r.success = false;
        r.msg = "失败";
        return  r;
    }


}
