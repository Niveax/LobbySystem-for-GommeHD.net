package de.niveax.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Collections;

/**
 * @author Niveax
 * @version 1.0
 * <p>
 * Copyright © 2021. All rights reserved.
 * <p>
 */
public class ItemClass {

    // Methods to create items more easier

    public static ItemStack createItem(Material mat, int subid, String DisplayName, String lore){
        ItemStack item = new ItemStack(mat, 1, (short) subid);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(DisplayName);
        meta.setLore(Collections.singletonList(lore));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createBootsItem(Material mat, Color color, String DisplayName, String lore){
        ItemStack item = new ItemStack(mat, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setDisplayName(DisplayName);
        meta.setLore(Collections.singletonList(lore));
        meta.setColor(color);
        item.setItemMeta(meta);
        return item;
    }
}
