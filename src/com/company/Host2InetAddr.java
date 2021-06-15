package com.company;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Host2InetAddr {
    public static void main(String[] args) {
        String hostName = "www.google.com";

        try {
            InetAddress address = InetAddress.getByName(hostName);
            System.out.println("IP 주소 : " + address.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
    }
}
