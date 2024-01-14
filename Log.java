/**
 * Prints the input given into the terminal window
 * 
 * @author Timothy Fung
 * @version 2023-12-18
 */
public class Log  
{
    public static boolean debug = true;

    public static void info(String input) {
        if(debug) {
            System.out.println(input);
        }
    }

    public static void info(int input) {
        if(debug) {
            System.out.println(input);
        }
    }

    public static void info(double input) {
        if(debug) {
            System.out.println(input);
        }
    }
    
    public static void info(boolean input) {
        if(debug) {
            System.out.println(input);
        }
    }
    
    public static void info(Entity input) {
        if(debug) {
            System.out.println(input);
        }
    }
}