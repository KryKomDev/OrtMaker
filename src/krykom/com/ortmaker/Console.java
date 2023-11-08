/* Better console i/o made by KryKomDev 2023
*  All rights reserved.
*/

package krykom.com.ortmaker;

import java.util.Scanner;

public class Console {

    private static Scanner sc;

    private static Scanner getScanner() {
        if (sc == null) {
            sc = new Scanner(System.in);
        }
        return sc;
    }

    /** Don't let anyone instantiate this class */
    private Console() { }

    /** Instead of System.out.println()
     * @see java.io.PrintStream */
    public static void writeLine(Object out){
        System.out.println(out);
    }

    /** Just prints new line. */
    public static void writeLine(){
        Console.write("\n");
    }

    /** Instead of System.out.print()
     * @see java.io.PrintStream */
    public static void write(Object out) {
        System.out.print(out);
    }

    /** Instead of the whole Scanner thing.
     * Call {@link #close()} when done with reading. */
    public static String readLine() {
        Scanner sc = getScanner();
        while (sc.hasNextLine()) return sc.nextLine();
        return "";
    }

    /** Closes input.
     * After that you cannot take input from anywhere.
     * @see Scanner Scanner.close() method */
    public static void close(){
        Scanner sc = getScanner();
        sc.close();
    }
}
