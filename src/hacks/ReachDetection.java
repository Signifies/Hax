package hacks;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Signifies for Hax on 3/27/18/1:26 AM
 */
public class ReachDetection implements Listener
{

    @EventHandler
    public void onDetection(EntityDamageByEntityEvent event)
    {
        Entity attacker = event.getDamager();
        Entity recepient = event.getEntity();

        if(!(attacker instanceof Player)) return;

        //TODO Begin reach detection logic. See resources for more info.
    }

}
