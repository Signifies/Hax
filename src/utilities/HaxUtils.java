package utilities;

import me.signifies.hax.Hax;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Signifies for Hax on 3/27/18/12:41 AM
 */
public class HaxUtils
{
    /**
     * Plugin prefix.
     */
    private static String prefix = ChatColor.translateAlternateColorCodes('&',"&6Hax&4->");

    /**
     * Gets the configured prefix.
     * @return
     */
    public static String getPrefix()
    {
        return prefix;
    }


    /**
     *  Displays plugin description Information.
     *
     * @param sender
     * @param
     *
     */
    public void desc(CommandSender sender, Hax instance)
    {
        sender.sendMessage(color("&b------ " + getPrefix().replace("->","") + "&b------"));
        sender.sendMessage(color("&7[&6" + instance.pdfFile.getName() + "&7] &aCreated by, &b&l" +instance.pdfFile.getAuthors()+"&6."));
        sender.sendMessage(color("&2" + instance.pdfFile.getDescription() + "&2."));
        sender.sendMessage(color("&bWebsite: &e&l" + instance.pdfFile.getWebsite()));
        sender.sendMessage(color("Questions? &aEmail me at: &b&lSignifiesdev@icloud.com"));
        //sender.sendMessage(color("     &6&l>>>&2&l===============&6&l<<<\t"));
    }

    /**
     * Method that handles all the color formatting
     *
     * @param message
     * @return
     */
    public static String color(String message)
    {
        String msg =  message;
        msg = msg.replace("{prefix}",getPrefix());
        return ChatColor.translateAlternateColorCodes('&',msg);
    }

    /**
     * Development Information for Managers.
     * @param instance
     * @param sender
     * @return
     */
    public String getPluginVersion(Hax instance, CommandSender sender)
    {
        return color("&fHello, &a&n"+sender.getName() +".&r\nYou are currently running version &b&n"+instance.pdfFile.getVersion() + "&r of &e&n"+instance.pdfFile.getName() +"&r\n \n&6Your server is running version &c&n"+ instance.getServer().getBukkitVersion());
    }

    /**
     *  Logging message to the console.
     *
     * @param msg
     */
    public static void log(String msg)
    {
        Bukkit.getServer().getConsoleSender().sendMessage(color("&c&l[LOG]&f "+prefix +""+ msg));
    }

    /**
     * Time stamp Util, for Config and SQL.
     */
    static Calendar cal = Calendar.getInstance();
    static Date now = cal.getTime();
    public static java.sql.Timestamp stamp = new java.sql.Timestamp(now.getTime());
    public static java.sql.Timestamp getStamp() {
        return stamp;
    }

    /**
     * Time util conversion method. Will be implemented later.
     * @param string
     * @return
     */
    public long parseTime(String string) {
        // checks if the string is either null and empty and if so returns 0
        if (string == null || string.isEmpty())
            return 0L;
        // this replaces the regex for 0-9 and the other characters
        string = string.replaceAll("[^0-9smhdw]", "");
        // checks if the new string is empty since we removed some characters
        if (string.isEmpty())
            return 0L;
        // Check if string contains "w"
        if (string.contains("w")) {
            // Replace all non numbers with nothing
            string = string.replaceAll("[^0-9]", "");
            // Another empty check
            if (string.isEmpty())
                return 0L;
            // If it has a number we change the number value to days by
            // multiplying by 7 then we can change it to seconds
            return TimeUnit.DAYS.toSeconds(Long.parseLong(string) * 7);
        }
        // First we check for days using "d"
        TimeUnit unit = string.contains("d") ? TimeUnit.DAYS
                // If the string contains "h" it goes for hours
                : string.contains("h") ? TimeUnit.HOURS
                // If the string contains "m" it goes for minutes
                : string.contains("m") ? TimeUnit.MINUTES
                // Finally, if none match we go with seconds
                : TimeUnit.SECONDS;
        // Next we replace all the non-numbers with nothing so it can match a
        // number
        string = string.replaceAll("[^0-9]", "");
        // Another empty check to make sure something is there
        if (string.isEmpty())
            return 0L;
        // Then we return the string as a long in seconds using the unit
        // selected earlier
        return unit.toSeconds(Long.parseLong(string));
    }


    /**
     * Converting timestamp information from long format.
     * @param ms
     * @return
     */
    public static String convert(long ms) {
        String date = DurationFormatUtils.formatDuration(ms, "dd-H-mm-ss", false);
        String[] dateSpt = date.split("-");

        int day = Integer.valueOf(dateSpt[0]);
        int hour = Integer.valueOf(dateSpt[1]);
        int min = Integer.valueOf(dateSpt[2]);
        int sec = Integer.valueOf(dateSpt[3]);

        StringBuilder sb = new StringBuilder();
        if(day > 0) sb.append(day + "d ");
        if(hour > 0) sb.append(hour + "h ");
        if(min > 0) sb.append(min + "m ");
        sb.append(sec + "s");

        return sb.toString();
    }

    /**
     *  An adaption of AnonymousDr's Server Info Plugin.
     *
     *  https://github.com/AnonymousDr/ServerInfo/blob/master/src/org/togglecraft/serverinfo/main/ServerInfo.java
     *
     * @param plugin
     * @param author
     */
    public void information(Hax plugin, Player author)
    {

            String text = color("&0---- &6Your Information &0----");
            author.sendMessage(color("&cIP: &a"+ author.getAddress().toString()));
            author.sendMessage(color("&cUUID: &3"+author.getUniqueId().toString()));
            author.sendMessage(color("&cName: &e"+author.getName()));
            author.sendMessage(getPluginVersion(plugin,author));
            String format = color("&0------------------------");
            author.sendMessage(format);
            String os =  System.getProperty("os.name");
            author.sendMessage(color("&4OS: &a&l"+os));
            double freeD=new File(plugin.getDataFolder()+"/..").getFreeSpace()/1073741824;
            double totalD=new File(plugin.getDataFolder()+"/..").getTotalSpace()/1073741824;
            author.sendMessage(ChatColor.AQUA+"Disk space used: "+ChatColor.GREEN+new DecimalFormat("#.##").format(totalD-freeD)+ChatColor.YELLOW+"/"+new DecimalFormat("#.##").format(totalD)+ChatColor.YELLOW+" GB ("+new DecimalFormat("#.##").format(((totalD-freeD)/totalD)*100)+"% used)");

            double free = Runtime.getRuntime().freeMemory() / 1048576;
            double total = Runtime.getRuntime().totalMemory() / 1048576;
            author.sendMessage(ChatColor.RED + "RAM Used: " + ChatColor.GREEN + new DecimalFormat("#.###").format(total - free) + ChatColor.YELLOW + "/" + new DecimalFormat("#.###").format(total) + ChatColor.YELLOW + " MB (" + new DecimalFormat("#.##").format(((total - free) / total) * 100) + "% used)");
            author.sendMessage(ChatColor.RED+"Number of cores: "+ChatColor.YELLOW+Runtime.getRuntime().availableProcessors());
            author.sendMessage(ChatColor.RED+"Java version: "+ChatColor.YELLOW+System.getProperty("java.version"));
            int c=0;
            for(World w:Bukkit.getWorlds())c+=w.getLoadedChunks().length;
            author.sendMessage(ChatColor.RED+"Chunks loaded: "+ChatColor.YELLOW+c);
    }

    /**
     * Gets the players ping.
     * @param player
     * @param sender
     */
    public void getPing (Player player, Player sender)
    {
        try {
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            int  ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6The players "+player.getName()+"'s current ping is: &a" + ping ));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
