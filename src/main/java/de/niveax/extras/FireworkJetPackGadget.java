package de.niveax.extras;

import de.niveax.main.Main;
import de.niveax.utils.ItemClass;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;
import org.bukkit.FireworkEffect.Type;

import java.util.Objects;
import java.util.Random;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class FireworkJetPackGadget implements Listener {


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        try {
            if (e.getItem() != null && e.getItem().getType() == Material.FIREWORK && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Feuerwerk")) {
                Player p = e.getPlayer();
                e.setCancelled(true);

                Location location = p.getLocation();
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    location = e.getClickedBlock().getRelative(BlockFace.UP).getLocation();
                }

                // Item while scheduler is running
                p.getInventory().setItem(7, ItemClass.createItem(Material.BARRIER, (byte) 0, "§cWarte...", null));
                p.updateInventory();


                FireworkMeta fireworkMeta = (FireworkMeta) e.getItem().getItemMeta();
                fireworkMeta.setPower(1);
                final Random random = new Random();
                final FireworkEffect effect = FireworkEffect.builder().flicker(random.nextBoolean()).withColor(Objects.requireNonNull(getColor(random.nextInt(17) + 1))).withFade(Objects.requireNonNull(getColor(random.nextInt(17) + 1))).with(Type.values()[random.nextInt(ProcessBuilder.Redirect.Type.values().length)]).trail(random.nextBoolean()).build();
                fireworkMeta.addEffect(effect);
                Firework firework = location.getWorld().spawn(location, Firework.class);
                firework.setFireworkMeta(fireworkMeta);

                Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        p.getInventory().setItem(7, ItemClass.createItem(Material.FIREWORK, (byte) 0, "§9FeuerWerk", "§7§oLass Silvester bei dir entstehen"));
                        p.updateInventory();
                    }
                }, 20 * 3);
            }


            // Jetpack
            if (Objects.requireNonNull(e.getItem()).getType() == Material.NETHER_STAR && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cJetpack")) {
                Vector vector = e.getPlayer().getVelocity();
                vector.setY(2.1D);
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERDRAGON_WINGS, 3, 2);
                e.getPlayer().playEffect(e.getPlayer().getLocation(), Effect.FLAME, 2);

                // Item while scheduler is running
                e.getPlayer().getInventory().setItem(7, ItemClass.createItem(Material.BARRIER, (byte) 0, "§cWarte...", null));
                e.getPlayer().updateInventory();
                Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        e.getPlayer().getInventory().setItem(7, ItemClass.createItem(Material.NETHER_STAR, (byte) 0, "§cJetPack", "§7§oFühle dich wie Ironman"));
                        e.getPlayer().updateInventory();
                    }
                }, 20 * 2);
            }

        } catch (Exception e1) {

        }
    }

    @EventHandler
    public void onPlayerInteract2(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        try {
            // Ask for material of jet pack || display name is right
            if (e.getItem().getType() == Material.NETHER_STAR && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cJetPack")) {
                Vector v = p.getLocation().getDirection().multiply(4).setY(2);
                p.setVelocity(v);
                p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 3, 3);
                p.setFallDistance(-1000);
            }
        } catch (Exception e2) {

        }
    }

    // Method for all colors (firework gadget)
    private static Color getColor(final int i) {
        switch (i) {
            case 1:
                return Color.AQUA;
            case 2:
                return Color.BLACK;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.FUCHSIA;
            case 5:
                return Color.GRAY;
            case 6:
                return Color.GREEN;
            case 7:
                return Color.LIME;
            case 8:
                return Color.MAROON;
            case 9:
                return Color.NAVY;
            case 10:
                return Color.OLIVE;
            case 11:
                return Color.ORANGE;
            case 12:
                return Color.PURPLE;
            case 13:
                return Color.RED;
            case 14:
                return Color.SILVER;
            case 15:
                return Color.TEAL;
            case 16:
                return Color.WHITE;
            case 17:
                return Color.YELLOW;
        }
        return null;
    }

}
