package com.yzyy.dist;

import com.lmax.disruptor.EventFactory;

public class PDataFactory implements EventFactory<PData> {
    public PData newInstance() {
        return new PData();
    }
}
