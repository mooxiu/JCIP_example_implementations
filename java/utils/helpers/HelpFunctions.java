package utils.helpers;

public final class HelpFunctions {
    public static void timeConsuming(Long v) {
        boolean isPrime = true;
        for (int i = 2; i < v; i++) {
            for (int j = 2; j< i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                }
            }
        }
    }
}
