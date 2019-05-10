package inet_address_basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalIpGet {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ia = InetAddress.getLocalHost();
        System.out.println(ia);
    }
}
