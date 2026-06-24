package validator;

public class InputValidator {

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isPositive(double value) {
        return value > 0;
    }
}
