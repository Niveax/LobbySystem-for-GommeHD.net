package de.niveax.nick;

import com.mojang.authlib.GameProfile;
import de.niveax.main.Main;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class NickMethods {

    private Field nameField;
    public String nickname;

    // This class is for all methods (change player name, packets..)

    public void unnickPlayer(CraftPlayer player, String Playername) {
        nameField = getField(GameProfile.class, "name");
        CraftPlayer cp = (CraftPlayer) player;
        try {
            nameField.set(cp.getProfile(), Playername);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(cp.getEntityId());
        sendPacket(destroy);
        removeFromTab(cp);

        new BukkitRunnable() {

            @Override
            public void run() {
                addToTab(cp);
                PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(cp.getHandle());
                for(Player all : Bukkit.getOnlinePlayers()) {
                    if(!all.equals(player)) {
                        ((CraftPlayer) all).getHandle().playerConnection.sendPacket(spawn);
                    }
                }
            }
        }.runTaskLater(Main.getPlugin(), 4);
    }

    public void nickPlayer(CraftPlayer player, String nickname) {
        this.nickname = nickname;
        nameField = getField(GameProfile.class, "name");
        CraftPlayer cp = (CraftPlayer) player;
        try {
            nameField.set(cp.getProfile(), nickname);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(cp.getEntityId());
        sendPacket(destroy);
        removeFromTab(cp);

        new BukkitRunnable() {

            @Override
            public void run() {
                addToTab(cp);
                PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(cp.getHandle());
                for(Player all : Bukkit.getOnlinePlayers()) {
                    if(!all.equals(player)) {
                        ((CraftPlayer) all).getHandle().playerConnection.sendPacket(spawn);
                    }
                }
            }
        }.runTaskLater(Main.getPlugin(), 4);
    }


    private void removeFromTab(CraftPlayer player) {
        PacketPlayOutPlayerInfo packetPlayOutPlayerInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, player.getHandle());
        sendPacket(packetPlayOutPlayerInfo);
    }

    private void addToTab(CraftPlayer player) {
        PacketPlayOutPlayerInfo packetPlayOutPlayerInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, player.getHandle());
        sendPacket(packetPlayOutPlayerInfo);
    }

    private void sendPacket(Packet<?> packet) {
        for(Player all : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) all).getHandle().playerConnection.sendPacket(packet);
        }
    }

    private Field getField(Class<?> clazz, String name) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return field;
        }catch(NoSuchFieldException | SecurityException e) {
            return null;
        }
    }
}
