package com.mustafaakin.flood;

import java.util.Arrays;

/**
 *
 * @author Mustafa
 */
public class Tester {

    /**
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        boolean[][] expected = new boolean[3][25];
        Arrays.fill(expected[0], false);
        Arrays.fill(expected[1], true);
        Arrays.fill(expected[2], false);

        for (int i = 0; i < 5; i++) {
            expected[0][i] = expected[0][i + 16] = true;
        }

        for (int i = 0; i < 2; i++) {
            expected[2][i] = expected[2][i + 8] = expected[2][i + 16] = true;
        }
        expected[2][24] = true;

        boolean[][] tests = new boolean[3][25];
        tests[0] = test("mustafa-comment", 25, 1000, 5, 64, false);
        tests[1] = test("mustafa-comment", 25, 1000, 5, 256, false);
        tests[2] = test("mustafa-comment", 25, 1000, 2, 128, false);

        System.out.println("RESULTS:");
        for (int i = 0; i < 3; i++) {
            System.out.println("TEST NO: " + i);
            boolean result = check(tests[i], expected[i]);
            if ( result)
                System.out.println("OK.");
        }
    }

    private static boolean check(boolean[] test, boolean[] expected) {
        boolean testOK = true;
        for (int i = 0; i < expected.length; i++) {
            if (test[i] != expected[i]) {
                testOK = false;
                System.out.println("Cache hit #" + i + " should be '" + expected[i] + "' but it is '" + test[i] + "'");
            }
        }
        return testOK;
    }

    private static boolean[] test(String key, int count, int timeout, int limit, int sleep, boolean verbose) throws Exception {
        boolean results[] = new boolean[count];
        long start = System.currentTimeMillis();
        if (verbose) {
            System.out.println("Starting Test with params:");
            System.out.println("Timeout: " + timeout);
            System.out.println("Limit: " + limit);
            System.out.println("Interval: " + sleep);
            System.out.println("");
        } else {
            System.out.println("Starting Test..");
        }
        RateLimitManager manage = new RateLimitManager();
        manage.setCache(new SimpleNotCompleteCache());
        for (int i = 0; i < count; i++) {
            results[i] = manage.getAllowedActionCount(key, timeout, limit) > 0;
            long end = System.currentTimeMillis();
            if (verbose) {
                System.out.print("@" + (end - start) + "\t\t");
                System.out.println(("#" + i + "\t\t") + (results[i] ? "OK" : "NOT OK"));
            }
            Thread.sleep(sleep);
        }
        System.out.println("Test Ended");
        System.out.println("");
        return results;
    }
}
