package flood;

import service.cache.SimpleCache;

/**
 *
 * @author Mustafa
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        test("mustafa-comment", 25, 1000, 5, 100);
        test("mustafa-comment", 25, 1000, 5, 500);
        test("mustafa-comment", 25, 1000, 5, 1000);        
    }

    static void test(String key, int count, int timeout, int limit, int sleep) throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("Starting Test with params:");
        System.out.println("Timeout: " + timeout);
        System.out.println("Limit: " + limit);
        System.out.println("Interval: " + sleep);
        System.out.println("");
        RateLimitManager manage = new RateLimitManager();
        manage.setCache(new SimpleCache());
        for (int i = 0; i < count; i++) {
            boolean b = manage.isAllowed(key, timeout, limit);
            long end = System.currentTimeMillis();
            System.out.print("@" + (end - start) + "\t\t");
            System.out.println(("#" + i + "\t\t") + (b ? "OK" : "NOT OK"));
            Thread.sleep(sleep);
        }
        System.out.println("Starting Test Ended");
        System.out.println("*******************");
    }
}
