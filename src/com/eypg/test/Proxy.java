package com.eypg.test;

public class Proxy {
    private String ip;
    private Integer port;

    public Proxy() {
        super();
    }

    public Proxy(String ip, Integer port) {
        super();
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
