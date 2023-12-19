/**
 * Write a description of class Log here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
}