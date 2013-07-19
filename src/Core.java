/*              No_Smile
 *          -----------------
 *           @Version: 0.25
 * @Author: Giuseppe "JustHvost" D'Amico
 *
 */

import java.io.*;
import java.net.*;
import java.util.regex.*;
import java.util.*;

public class Core extends Data
{

    public static void login(String password) throws IOException, InterruptedException
    {
        Pattern strings = Pattern.compile("^:[^\\s]+ (376|422)", Pattern.CASE_INSENSITIVE);
        Matcher check_strings = strings.matcher(parser);

        if(check_strings.find()) {
            lmao.write("PRIVMSG NickServ IDENTIFY :" + password + "\n");
            lmao.flush();

            join(chan);
            msg_chan(start);
        }
    }

    public static void cmd(String cmds) throws IOException
    {
        Pattern nope = Pattern.compile("^:"+combine (masters, "|"), Pattern.CASE_INSENSITIVE);
        Matcher check_nope = nope.matcher(cmds);
        Pattern bl = Pattern.compile ("^:[^\\s]+ PRIVMSG [^\\s]+ :(.+)?(" + combine (list.toArray (new String[list.size()]), "|") + ")", Pattern.CASE_INSENSITIVE);
        Matcher cm = bl.matcher(cmds);
        Pattern ping = Pattern.compile("^PING :(.+)", Pattern.CASE_INSENSITIVE);
        Matcher check_ping = ping.matcher(cmds);
        Pattern blist = Pattern.compile(":#list", Pattern.CASE_INSENSITIVE);
        Matcher check_bl = blist.matcher(cmds);
        Pattern who = Pattern.compile("#who", Pattern.CASE_INSENSITIVE);
        Matcher check_who = who.matcher(cmds);
        Pattern help = Pattern.compile(":#hlp", Pattern.CASE_INSENSITIVE);
        Matcher check_help = help.matcher(cmds);
        Pattern ins = Pattern.compile(":#izadouchebag (.+)", Pattern.CASE_INSENSITIVE);
        Matcher check_ins = ins.matcher(cmds);

        if (cmds != null && check_nope.find())
        {
            Pattern exit = Pattern.compile(":#exit", Pattern.CASE_INSENSITIVE);
            Matcher check_exit = exit.matcher(cmds);
            Pattern blacklist = Pattern.compile(":#blacklist", Pattern.CASE_INSENSITIVE);
            Matcher check_blacklist = blacklist.matcher(cmds);
            Pattern remove = Pattern.compile(":#reset", Pattern.CASE_INSENSITIVE);
            Matcher check_remove = remove.matcher(cmds);
            Pattern kick = Pattern.compile(":#kick (.+)? (.+)");
            Matcher check_kick = kick.matcher(cmds);
            Pattern kill = Pattern.compile(":#kill (.+)? (.+)");
            Matcher check_kill = kill.matcher(cmds);

            if(check_exit.find())
                exit_irc();

            else if(check_blacklist.find()) {
                String[] childrenofbodom = cmds.split(" ");
                String alexi = childrenofbodom[4];
                learn(alexi);
                msg_chan("Ok ^-^");
            }

            else if(check_remove.find())
                reset();

            else if(check_kick.find()) {
                String usr = check_kick.group(1);
                String mtv = check_kick.group(2);
                kick(usr, mtv);
            }

            else if(check_kill.find()) {
                String usr = check_kill.group(1);
                String mtv = check_kill.group(2);
                kill(usr, mtv);
            }
        }


        if (check_help.find())
            msg_chan(
                    "Ok, I'll help you ^-^" +
                            " So... " +
                            "#blacklist <word> => Add a word to the blacklist, " +
                            "#reset => Reset all the non-default blacklisted word, " +
                            "#exit => Seems obvious to me... " +
                            "#list => Show the list of all the blacklisted words, " +
                            "#kick <user> <reason> => Kick someone from the chan, " +
                            "#kill <user> <reason> => Kill someone from the server, " +
                            "#izadouchebag <user> => Let's see if this user's a douchebag... " +
                            "#who => Give some information about the bot " +
                            "That's all, folks C:"
            );

        else if(check_ins.find())
        {
            Pattern idiot = Pattern.compile("^:([^\\s]+)!~?([^\\s]+)@([^\\s]+)");
            Matcher check_idiot = idiot.matcher(cmds);

            if (check_idiot.find()) {
                String idiot_found = check_ins.group(1);
                Random dat_ass = new Random();
                int dem_titties = dat_ass.nextInt(douchebag.length);
                msg_chan("Scanning " + idiot_found + "... => " + douchebag[dem_titties]);
            }
        }

        else if (check_who.find())
            msg_chan(
                    "My name is " + bot +
                            " => Version: " + version +
                            " => Masters: " + Arrays.toString(masters)
            );

        else if (cm.find())
        {
            Pattern bb = Pattern.compile("^:([^\\s]+)!~?([^\\s]+)@([^\\s]+)");
            Matcher check_bb = bb.matcher(cmds);
            if (check_bb.find()) {
                String asd = check_bb.group(1);
                Pattern no_kick= Pattern.compile("^:"+combine (masters, "|"), Pattern.CASE_INSENSITIVE);
                Matcher check_no_kick = no_kick.matcher(cmds);
                if(!check_no_kick.find())
                    kick_blacklist(asd);
            }
        }

        else if(check_ping.find()) {
            lmao.write("PONG :" + check_ping.group (1) + "\n");
            lmao.flush();
        }

        else if(check_bl.find())
        {
            Scanner kk = new Scanner(new File(path));
            Pattern varg = Pattern.compile("^:([^\\s]+)!~?([^\\s]+)@([^\\s]+)");
            Matcher check_varg = varg.matcher(cmds);
            if (check_varg.find()) {
                String user = check_varg.group(1);
                msg_chan(user + " You have a new PM C:");

                while (kk.hasNext()) {
                    msg_user(user, kk.next());
                }
                kk.close();
            }
        }
    }

    public static void init_blacklist() throws FileNotFoundException
    {
        s = new Scanner(new File(path));
        list = new ArrayList<String>();

        while (s.hasNext())
            list.add(s.next());

        s.close();
    }

    public static void connect_to(String where) throws IOException
    {
        irc_connect = new Socket(where, 6667);
        lmao = new BufferedWriter(new OutputStreamWriter(irc_connect.getOutputStream()));
        rotfl = new BufferedReader(new InputStreamReader(irc_connect.getInputStream()));
    }

    public static void authentication() throws IOException
    {
        lmao.write("NICK " + bot + "\n");
        lmao.write("USER " + bot + " 0 *: no\n");
        lmao.flush();
    }

    private static String combine(String[] s, String glue)
    {
        int k = s.length;
        if (k == 0)
            return null;

        StringBuilder out = new StringBuilder();
        out.append(Pattern.quote (s[0]));

        for (int x = 1; x < k; ++x)
            out.append(glue).append(s[x]);

        return out.toString();
    }

    private static void join(String chan) throws IOException
    {
        lmao.write("JOIN #" + chan + "\n");
        lmao.flush();
    }

    private static void msg_chan(String mess) throws IOException
    {
        lmao.write("PRIVMSG #" + chan + " :" + mess + "\n");
        lmao.flush();
    }

    private static void msg_user(String user, String mess) throws IOException
    {
        lmao.write("PRIVMSG " + user + " :" + mess + "\n");
        lmao.flush();
    }

    private static void kick_blacklist(String user) throws IOException
    {
        lmao.write("KICK #" + chan + " " + user + " :!!!WARNING: NOOB FOUND!!!\n");
        lmao.flush();
    }

    private static void kick(String user, String why) throws IOException
    {
        lmao.write("KICK #" + chan + " " + user + " :" + why + "\n");
        lmao.flush();
    }

    private static void kill(String user, String why) throws IOException
    {
        lmao.write("KILL " + user + " :" + why + "\n");
        lmao.flush();
    }

    private static void learn(String wut) throws IOException
    {
        File dat = new File(path);
        FileWriter jack = new FileWriter(dat.getAbsoluteFile(), true);
        BufferedWriter black_jack = new BufferedWriter(jack);
        BufferedReader seregor = new BufferedReader(new FileReader(path));
        if (seregor.readLine() != null) {
            black_jack.write(wut);
            black_jack.newLine();
        } else {
            black_jack.write(wut);
        }
        list.add(wut);
        black_jack.close();
    }

    private static void reset() throws IOException
    {
        new FileOutputStream(path, false).close(); // Delete the file contents

        File dat = new File(path);
        FileWriter nyan = new FileWriter(dat.getAbsoluteFile(), true);
        BufferedWriter nyan_cat = new BufferedWriter(nyan);
        list = new ArrayList<String>();

        for (String aDefault_blacklist : default_blacklist) {
            nyan_cat.write(aDefault_blacklist);
            nyan_cat.newLine();
            nyan_cat.flush();
            list.add(aDefault_blacklist);
        }
        msg_chan("Done ^-^");
    }

    private static void exit_irc() throws IOException
    {
        lmao.write("PART " + chan + "\n");
        lmao.write("QUIT " + " " + goodbye + "\n");
        lmao.flush();
        irc_connect.close();
    }
}
