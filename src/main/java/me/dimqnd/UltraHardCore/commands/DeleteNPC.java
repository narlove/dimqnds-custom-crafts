package me.dimqnd.UltraHardCore.commands;

import me.dimqnd.UltraHardCore.Main;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Map;

public class DeleteNPC implements CommandExecutor {
    private Main mainReference;

    public DeleteNPC(Main mainReference) { this.mainReference = mainReference; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "[Dimqnd's UHC] Only players can use this command!");
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "[Dimqnd's UHC] Please specify an identifier!");
            return true;
        }

        String npcIdentifier = args[0];

        Map<String, ServerPlayer> npcList = mainReference.getCoreInstance().npcList;
        Map<ServerPlayer, Location> npcLocationList = mainReference.getCoreInstance().npcLocationList;
        ServerPlayer npc = npcList.get(npcIdentifier);

        try {
            if (npcList.isEmpty()) {
                player.sendMessage(ChatColor.RED + "[Dimqnd's UHC] There are no NPCS to delete");
                return true;
            }

            ServerGamePacketListenerImpl connection = ((CraftPlayer) player).getHandle().connection;
            connection.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.REMOVE_PLAYER, npc));
            connection.send(new ClientboundRemoveEntitiesPacket(npc.getId()));
            npcList.remove(npcIdentifier);
            npcLocationList.remove(npc);

        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "[Dimqnd's Tavern] Please specify a valid identifier!");
            return true;
        }

        player.sendMessage(ChatColor.RED + "[Dimqnd's UHC] Successfully deleted the NPC!");

        return true;
    }
}
