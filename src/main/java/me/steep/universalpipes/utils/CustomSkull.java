package me.steep.universalpipes.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

/**
 * Move to UsefullThings
 */
@SuppressWarnings("all")
public enum CustomSkull {

    WITHER_SKELETON(new ItemStack(Material.WITHER_SKELETON_SKULL)),
    ZOMBIE(new ItemStack(Material.ZOMBIE_HEAD)),
    SKELETON(new ItemStack(Material.SKELETON_SKULL)),
    CREEPER(new ItemStack(Material.CREEPER_HEAD)),
    ENDER_DRAGON(new ItemStack(Material.DRAGON_HEAD)),
    GIANT(getCustomSkull("64528b3229660f3dfab42414f59ee8fd01e80081dd3df30869536ba9b414e089")),
    AXOLOTL(getCustomSkull("debf2b113f4a81370a1cf9d2504e8756b66deef79e9433187da774b96c9f35ba")),
    SHEEP(getCustomSkull("292df216ecd27624ac771bacfbfe006e1ed84a79e9270be0f88e9c8791d1ece4")),
    CAVE_SPIDER(getCustomSkull("604d5fcb289fe65b6786682e1c736c3f7b16f39d940e3d2f41cf0040704c6282"));


    private final ItemStack item;

    CustomSkull(ItemStack item) {
        this.item = item;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public static ItemStack getCustomSkull(String id) {
        Base64 base64 = new Base64();
        String url = "http://textures.minecraft.net/texture/" + id;
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        PropertyMap propertyMap = profile.getProperties();
        if (propertyMap == null) {
            throw new IllegalStateException("Profile doesn't contain a property map");
        }
        byte[] encodedData = base64.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        propertyMap.put("textures", new Property("textures", new String(encodedData)));
        ItemStack head = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
        ItemMeta headMeta = head.getItemMeta();
        Class<?> headMetaClass = headMeta.getClass();
        ReflectionUtils.getField(headMetaClass, "profile", GameProfile.class).set(headMeta, profile);
        head.setItemMeta(headMeta);
        return head;
    }
}
