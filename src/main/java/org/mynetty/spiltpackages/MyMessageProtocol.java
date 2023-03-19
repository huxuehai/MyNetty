package org.mynetty.spiltpackages;

import java.io.Serializable;
import java.util.Arrays;

public class MyMessageProtocol implements Serializable {
    private int len;
    private byte[] bytes = new byte[]{};

    public MyMessageProtocol() {
    }

    public MyMessageProtocol(int len, byte[] bytes) {
        this.len = len;
        this.bytes = bytes;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "MyMessageProtocol{" +
                "len=" + len +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }
}
