package com.hanfz.threadlocal;

import com.hanfz.pojo.request.RequestDataBms;

/**
 * @Author Linuyx
 * @Description RequestData工具类
 * @Date Created in 2022-01-04 14:19
 */
public class RequestDataThreadLocal {

    private static ThreadLocal<RequestDataBms> requestDataThreadLocal = new ThreadLocal<>();

    /**
     * 赋值RequestData
     *
     * @param requestDataBms
     */
    public static void setRequestData(RequestDataBms requestDataBms){
        requestDataThreadLocal.set(requestDataBms);
    }

    /**
     * 获取RequestData
     *
     * @return
     */
    public static RequestDataBms getRequestData(){
        return requestDataThreadLocal.get();
    }

    /**
     * remove
     */
    public static void remove(){
        requestDataThreadLocal.remove();
    }

}
