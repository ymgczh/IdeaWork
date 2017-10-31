package com.yzyy.dist;

import com.lmax.disruptor.RingBuffer;
import java.nio.ByteBuffer;

public class Producer {

    private final RingBuffer<PData> buffer;

    public Producer(RingBuffer<PData> buffer) {
        this.buffer = buffer;
    }

    public void pushData(ByteBuffer byteBuffer) {
        long sequence = buffer.next(); // Grab the next sequence
        try {
            PData event = buffer.get(sequence); //get the entry in the disrupt for the sequence
            event.setValue(byteBuffer.getLong(0)); //Fill with data
        } finally {
            buffer.publish(sequence);
        }
    }
}
