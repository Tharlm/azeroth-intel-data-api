package fr.bavencoff.wow.azerothinteldataapi.common.utils;

public final class NumberUtils {

    public static int compareIntegers(Integer a, Integer b) {
        if (a == null && b == null) {
            return 0;
        }
        if (a == null) {
            return -1;
        }
        if (b == null) {
            return 1;
        }
        return Integer.compare(a, b);
    }

    public static boolean equals(Integer a, Integer b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return (int) a == b;
    }
    public static boolean equals(Short a, Short b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return (int) a == b;
    }
}
