/*              No_Smile
 *          -----------------
 *           @Version: 0.25
 * @Author: Giuseppe "JustHvost" D'Amico
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Data
{
    public static String[] masters = {
            "<user 1>",
            "<user 2>"
    };

    public static String bot = "No_Smile";
    public static String server = " ";
    public static String chan = " ";  // Only chan name without "#". EG: "bots"
    public static String password = " ";
    public static double version = 0.25;


    public static Socket irc_connect;
    public static BufferedWriter lmao;
    public static BufferedReader rotfl;
    public static String parser;

    public static ArrayList<String> list;
    public static String path = " ";  // Database path. EG: C:\Users\<user>\Desktop\words.txt (Windows), /home/<user>/words.txt (Linux).
    public static Scanner s;

    public static String[] default_blacklist = {
            "-.-",
            "cmq",
            "xk"
    };
    public static String[] douchebag = {
            "Yes",
            "No",
            "Obviously he is!",
            "AHAHAHAHAH PLZ!",
            "The only pussy he ever seen is his cat, please."
    };

    public static String start = "'Sup everyone ^-^";
    public static String goodbye = "See ya, noobz ^-^";
}
