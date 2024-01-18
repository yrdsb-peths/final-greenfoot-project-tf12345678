/**
 * Prints the input given into the terminal window
 * 
 * @author Timothy Fung
 * @version 2023-12-18
 */
public class Log  
{
    public static boolean debug = true;

    /**
     * Prints a string input if debug boolean is true
     */
    public static void info(String input) {
        if(debug) {
            System.out.println(input);
        }
    }

    /**
     * Prints a integer input if debug boolean is true
     */
    public static void info(int input) {
        if(debug) {
            System.out.println(input);
        }
    }

    /**
     * Prints a double input if debug boolean is true
     */
    public static void info(double input) {
        if(debug) {
            System.out.println(input);
        }
    }
    
    /**
     * Prints a boolean input if debug boolean is true
     */
    public static void info(boolean input) {
        if(debug) {
            System.out.println(input);
        }
    }
    
    /**
     * Prints a Entity input if debug boolean is true
     */
    public static void info(Entity input) {
        if(debug) {
            System.out.println(input);
        }
    }
}