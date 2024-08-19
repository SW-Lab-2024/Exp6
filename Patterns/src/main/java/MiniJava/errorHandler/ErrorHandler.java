package MiniJava.errorHandler;

/**
 * Created by Alireza on 6/28/2015.
 */

public class ErrorHandler {
    private static boolean hasError = false;

    public static void printError(String msg) {
        hasError = true;
        System.out.println(msg);
    }

    public static void setError(boolean error) {
        hasError = error;
    }

    public static boolean hasError() {
        return hasError;
    }
}
