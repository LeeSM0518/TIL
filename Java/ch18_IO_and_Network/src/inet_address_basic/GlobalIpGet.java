package inet_address_basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GlobalIpGet {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ia = InetAddress.getByName("www.naver.com");
        InetAddress[] iaArr = InetAddress.getAllByName("www.naver.com");
        System.out.println(ia);
        for (InetAddress ip : iaArr) {
            System.out.println(ip);
        }

        System.out.println(ia.getHostAddress());
    }
}
