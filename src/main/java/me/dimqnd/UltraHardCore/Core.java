package me.dimqnd.UltraHardCore;

import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public class Core {
    private Main mainReference;

    public Core(Main mainReference) { this.mainReference = mainReference; }

    // Other variables
    //         id      entity reference
    public Map<String, ServerPlayer> npcList = new HashMap<>();
    public Map<ServerPlayer, Location> npcLocationList = new HashMap<>();

    // Other methods
    public void spawnNpcPackets_all() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (ServerPlayer npc : npcList.values()) {
                ServerPlayer serverPlayer = ((CraftPlayer) player).getHandle();
                ServerGamePacketListenerImpl packetSender = serverPlayer.connection;

                Location npcLocation = npcLocationList.get(npc);
                float yaw = npcLocation.getYaw();
                float pitch = npcLocation.getPitch();

                packetSender.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, npc));
                packetSender.send(new ClientboundAddPlayerPacket(npc));
                packetSender.send(new ClientboundRotateHeadPacket(npc, (byte) ((yaw%360) * 256 / 360)));
                packetSender.send(new ClientboundMoveEntityPacket.Rot(npc.getBukkitEntity().getEntityId(), (byte) ((yaw%360) * 256 / 360), (byte) ((pitch%360) * 256 / 360), false));
            }
        }
    }
    
    public void spawnNpcPackets_individual(Player player) {
        for (ServerPlayer npc : npcList.values()) {
            ServerPlayer serverPlayer = ((CraftPlayer) player).getHandle();
            ServerGamePacketListenerImpl packetSender = serverPlayer.connection;

            Location npcLocation = npcLocationList.get(npc);
            float yaw = npcLocation.getYaw();
            float pitch = npcLocation.getPitch();

            packetSender.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, npc));
            packetSender.send(new ClientboundAddPlayerPacket(npc));
            packetSender.send(new ClientboundRotateHeadPacket(npc, (byte) ((yaw%360) * 256 / 360)));
            packetSender.send(new ClientboundMoveEntityPacket.Rot(npc.getBukkitEntity().getEntityId(), (byte) ((yaw%360) * 256 / 360), (byte) ((pitch%360) * 256 / 360), false));
        }
    }

    public boolean checkHandsForItems(Player player, String customItemType) {
        try {
            if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer()
                    .get(new NamespacedKey(mainReference, "custom-item-type"), PersistentDataType.STRING)
                    .equals(customItemType)) {
                return true;
            }
        } catch (NullPointerException err) {
            // if its not a custom item in the main hand, check off hand
            try {
                if (player.getInventory().getItemInOffHand().getItemMeta()
                        .getPersistentDataContainer()
                        .get(new NamespacedKey(mainReference, "custom-item-type"), PersistentDataType.STRING)
                        .equals(customItemType)) {
                    return true;
                }
            } catch (NullPointerException err1) {
                return false;
            }
        }

        return false;
    }

    public ItemMeta applyNamespacedKey(ItemMeta meta, String key) {
        NamespacedKey k = new NamespacedKey(mainReference, "custom-item-type");
        meta.getPersistentDataContainer().set(k, PersistentDataType.STRING, key);

        return meta;
    }

//    public ItemMeta applyNamespacedKey(EnchantmentStorageMeta meta, String key) {
//        NamespacedKey k = new NamespacedKey(mainReference, "custom-item-type");
//        meta.getPersistentDataContainer().set(k, PersistentDataType.STRING, key);
//
//        return meta;
//    }
}
