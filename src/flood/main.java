package flood;

import java.net.InetSocketAddress;
import net.spy.memcached.MemcachedClient;

/**
 *
 * @author Mustafa
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");      
        MemcachedClient c = new MemcachedClient(new InetSocketAddress("127.0.0.1", 12345));
        String myObj = "abcdef";
        c.set("myKey", 120, myObj);
        System.out.println("Trying to fetch.");
        System.out.println("From Memcache:" + c.get("myKey"));
        System.out.println("End of program.");       
        c.shutdown();
    }
}
