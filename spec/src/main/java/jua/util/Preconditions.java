package jua.util;

public final class Preconditions {

    public static void ensureNotNull(Object value, String name) {
        if (value == null) {
            throw new IllegalArgumentException(name + " must be non-null");
        }
    }

    public static void ensureTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void ensureFalse(boolean condition, String message) {
        if (condition) {
            throw new IllegalArgumentException(message);
        }
    }
}
