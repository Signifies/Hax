package me.signifies.hax;

import configuration.HaxConfig;
import events.HaxListeners;
import hacks.ReachDetection;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import utilities.Debug;
import utilities.HaxUtils;

/**
 * Created by Signifies for Hax on 3/27/18/12:32 AM
 */
public class Hax extends JavaPlugin
{

    public PluginDescriptionFile pdfFile = this.getDescription();
    private PluginManager pm = Bukkit.getServer().getPluginManager();
    private HaxUtils utils = new HaxUtils();
    private HaxConfig hConfig = new HaxConfig(this);


    @Override
    public void onEnable()
    {
        //Debug.log(Debug.PREFIX + "",1); Bukkit has it's own logging messages...
        configuration();
        events();
        commands();

    }

    private void configuration()
    {
        Debug.log(Debug.PREFIX + "&6Loading Configuration...",1);
        hConfig.saveDefaultHaxConfig();
        hConfig.saveHaxConfig();
    }

    private void events()
    {
        Debug.log(Debug.PREFIX + "&bLoading Events...",1);
        pm.registerEvents(new HaxListeners(),this);
        pm.registerEvents(new ReachDetection(),this);
    }

    private void commands()
    {
        Debug.log(Debug.PREFIX + "&2Loading Commands...",1);
        //getCommand(" ").setExecutor();
    }


    @Override
    public void onDisable()
    {
        //Debug.log(Debug.PREFIX + "",1);

    }

    public HaxUtils getUtils()
    {
        return utils;
    }

    public HaxConfig getHConfig()
    {
        return hConfig;
    }


}
