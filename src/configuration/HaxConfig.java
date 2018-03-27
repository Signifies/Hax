package configuration;

import me.signifies.hax.Hax;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.logging.Level;

/**
 * Created by Signifies for Hax on 3/27/18/1:00 AM
 */
public class HaxConfig
{
    private FileConfiguration HaxConfig = null;
    private File HaxConfigFile = null;
    private static final String fileName = "Config.yml";
    private Hax main;

    public HaxConfig(Hax instance)
    {
        main = instance;
    }

    public void reloadHaxConfig() {
        if (HaxConfigFile == null) {
            HaxConfigFile = new File(main.getDataFolder(),fileName);
        }
        HaxConfig = YamlConfiguration.loadConfiguration(HaxConfigFile);

        // Look for defaults in the jar
        Reader defConfigStream = null;
        try {
            defConfigStream = new InputStreamReader(main.getResource(fileName), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            HaxConfig.setDefaults(defConfig);
        }
    }

    public FileConfiguration getHaxConfig() {
        if (HaxConfig == null) {
            reloadHaxConfig();
        }
        return HaxConfig;
    }

    public void saveHaxConfig() {
        if (HaxConfig == null || HaxConfigFile == null) {
            return;
        }
        try {
            getHaxConfig().save(HaxConfigFile);
        } catch (IOException ex) {
            main.getLogger().log(Level.SEVERE, "Could not save config to " + HaxConfigFile, ex);
        }
    }

    public void saveDefaultHaxConfig() {
        if (HaxConfigFile == null) {
            HaxConfigFile = new File(main.getDataFolder(), fileName);
        }
        if (!HaxConfigFile.exists()) {
            main.saveResource(fileName, false);
        }
    }
}
