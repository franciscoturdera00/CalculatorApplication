package Utils;

public final class Utils {

    /**
     * Evaluates a string and checks if it is alphanumeric
     * @param str Input to be evaluated
     * @return True if str is alphanumeric, False otherwise
     */
    public static final boolean isAlphanumeric(String str) {
        return str.matches("^[a-zA-Z0-9]*$");
    }

    /**
     * Evaluates the String. If it is an integer, it returns the integer, otherwise null
     * @param value The String to be evaluated
     * @return Integer or Null
     */
    public static final Integer integerValue(String value){
        try{
            return Integer.valueOf(value);
        } catch(NumberFormatException e) {
            return null;
        }
    }

}
