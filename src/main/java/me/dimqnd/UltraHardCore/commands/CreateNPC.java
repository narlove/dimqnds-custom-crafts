package me.dimqnd.UltraHardCore.commands;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.dimqnd.UltraHardCore.Main;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CreateNPC implements CommandExecutor {
    private Main mainReference;

    public CreateNPC(Main mainReference) { this.mainReference = mainReference; }

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

        player.sendMessage(ChatColor.GREEN + "[Dimqnd's UHC] Creating an NPC!");

        ServerPlayer serverPlayer = ((CraftPlayer) player).getHandle();

        MinecraftServer server = serverPlayer.getServer();
        ServerLevel level = serverPlayer.getLevel();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "nin3ty");

        String signature = "Oz5K/2MQWPRxvbxB3gJt8Zv+7UFqsD27/Tlidt4mpQ8lxniwSAmuP6WvTFuZUUjvFHh56CDA4yKuRkE5vdnwcGyNQghyS/Fn39ibeQGhyzT2Ff4IWVw4y7VrvkNwhDTpNNPNUMrP7tdNiNbAE7eStlVTeY4WhQb8tKYcAJ6fruewAEQm67FC0NK+CANonFq5oe3OI0sBDnKFrtly2h+E4SpXwp6qd7vH3Gpe5R3HEFjdforWYh+Yl8D8whCGDiwhsoeud24w1H1WnT27dE3T+5LYxinzTZ6U6/IwKfLA8FHE3BqByYIbeQWMYVVQNawYnj+HbICKeiJBwZC5tbvaxJ+h3mnCOy8vtEjAN4luG9ye2MmXPpd6GN8KtJTSQ2yL0oygHpkh8rE7jpN+D3duwd7iiHwWFitbULs3NPwSurOqach/LNrS/+DdQH/8rwtVoHbI9Pm+WsC47NtPIa3d7/IlV6cjwlrSpFUmJZQ76YZNgvnuN1eJXbGy49DHscx7w7lE+WuU+zs6I7j4oXlpJsL0q32iSsElD9i4Xxv/fnj9BOYPdQWVuxjrPmynYvJXk/jHuf67e2uk2YhZDTIfj1hIL6jtvU1puHI/1TX1W/Vi34f2vR7gKLrDD4TWbeVpgVYkzsvOeq56m7ns6j0c8ZqDylLXRBi3bKD2YjKl8zw=";
        String texture = "ewogICJ0aW1lc3RhbXAiIDogMTY1OTc4NzY5NjM0NiwKICAicHJvZmlsZUlkIiA6ICI2ODc0NTg3ZDdlNjg0OWY2YmZhM2RlZjE2ZDI3MWE1YSIsCiAgInByb2ZpbGVOYW1lIiA6ICJuaW4zdHkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjJjM2E4NjkzMDk2ZmU3MTljZDU5NjRlNDkxNmU4OGE2ZjgwMmRmMjRiMDZmMjQ2OWI5MmM3Yzc4NWM5OGExNSIKICAgIH0KICB9Cn0=";

        gameProfile.getProperties().put("textures", new Property("textures", texture, signature));

        ServerPlayer npc = new ServerPlayer(server, level, gameProfile);

        Location playerLocation = player.getLocation();
        npc.setPos(playerLocation.getX(), playerLocation.getY(), playerLocation.getZ());

        Location npcLocation = npc.getBukkitEntity().getLocation();
        npcLocation.setDirection(player.getEyeLocation().getDirection());

        mainReference.getCoreInstance().npcList.put(npcIdentifier, npc);
        mainReference.getCoreInstance().npcLocationList.put(npc, npcLocation);

        mainReference.getCoreInstance().spawnNpcPackets_all();

        return true;
    }
}
