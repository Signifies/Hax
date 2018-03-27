package utilities;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Signifies for Hax on 3/27/18/12:56 AM
 */
public class Debug
{
    static public String FAILED_ACTION = "[FAILED ACTION] ";
    static public String ACTION = "[ACTION] ";
    static public String LOG = "[LOG] ";
    static public String SEVERE = "[SEVERE] &c";
    static public String PREFIX = HaxUtils.getPrefix();
    private final static String VERSION = "1.4.0";
    public static boolean DEBUG = true;
    private static int priorityLevel = 0;


    //Use prority in the form of a switch statement to be returned as boolean.
//We can then check those said values with our DEBUG variable.
//Variable priority ranges from 1-3 1 being the highest, 2 being moderate
//3 being the lowest.


    static private boolean priority(int priority_0_1)
    {
        priorityLevel = priority_0_1;

        if(!DEBUG)
        {
            switch(priorityLevel)
            {
                case 0:
                    return false;
                case 1:
                    return true;

                case 2:
                    return false;

                default:
                    return false;
            }
        }else
        {
            return true;
        }
    }

    static public void log(String msg, int level)
    {
        if(priority(level))
        {
            Bukkit.getServer().getConsoleSender().sendMessage(HaxUtils.color(msg));
        }
    }

    static public void log(int level, Player p, String msg)
    {
        if(priority(level))
        {
            p.sendMessage(HaxUtils.color(msg));
        }
    }

    static public void log(int level, CommandSender p, String msg)
    {
        if(priority(level))
        {
            p.sendMessage(HaxUtils.color(msg));
        }
    }
}
