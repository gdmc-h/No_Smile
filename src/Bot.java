/*
 *                        No_Smile
 *	                 --------------------
 *	                    @Version: 0.25
 *	        @Author: Giuseppe "JustHvost" D'Amico
 *
 *                  IRC commands (Admin):
 *                  ---------------------
 *       #blacklist <word> => Add a word to the blacklist.
 *       #reset => Reset all the non-default blacklisted word.
 *       #exit => Seems obvious to me...
 *       #kick <user> <reason> => Kick someone from the chan.
 *       #kill <user> <reason> => Kill someone from the server.
 *
 *                  IRC commands (Users):
 *                  ---------------------
 *      #hlp => Show the help message.
 *      #list => Show the list of all the blacklisted words.
 *      #izadouchebag <user> => Let's see if this user's a douchebag...
 *      #who => Give some information about the bot.
 *
 *                     !!!WARNING!!!
 *                ------------------------
 *          No_Smile need the OP to work correctly.
 */

import java.io.*;

public class Bot extends Data
{
    public static void main(String[] args)
    {
        try {
            Core.init_blacklist();
            Core.connect_to(server);
            Core.authentication();

            while((parser = rotfl.readLine()) != null) {
                System.out.println(parser);
                Core.login(password);
                Core.cmd(parser);
            }
        } catch (IOException e) { e.printStackTrace(); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}

