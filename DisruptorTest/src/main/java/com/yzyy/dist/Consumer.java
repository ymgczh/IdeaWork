package com.yzyy.dist;

import com.lmax.disruptor.WorkHandler;
import com.sun.org.glassfish.gmbal.Description;

/**
 * 消费者 的 作用 是 读取 数据 进行 处理。 这里，
 * 数据 的 读取 已 经由 Disruptor 进行 封装， onEvent()
 * 方法 为 框架 的 回 调 方法。 因此， 这里 只需 要
 * 简单 地 进行 数据处理 即可。
 */
public class Consumer implements WorkHandler<PData> {
    public void onEvent(PData pData) throws Exception {
        System.out.println(Thread.currentThread().getId() +
        ":Event: --" + pData.getValue() * pData.getValue() + "--");
    }
}
