package me.dimqnd.UltraHardCore.events;

import me.dimqnd.UltraHardCore.CraftingCooldowns;
import me.dimqnd.UltraHardCore.Main;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Events implements Listener {
    private Main mainReference;
    private Map<UUID, BukkitRunnable> andurilTimer = new HashMap<>();

    public Events(Main mainReference) { this.mainReference = mainReference; }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        mainReference.getCoreInstance().spawnNpcPackets_individual(player);

        if (CraftingCooldowns.allPlayersWithCooldowns.get(player.getUniqueId()) == null) return;
        new CraftingCooldowns(player.getUniqueId());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        //CraftingCooldowns.allPlayersWithCooldowns.remove(CraftingCooldowns.allPlayersWithCooldowns.get(player.getUniqueId()));
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        // need to add a check to see if player died by themselves or by nature and cancel

        Player deadPlayer = e.getEntity();
        Location deathLocation = deadPlayer.getLocation();
        ItemStack item = mainReference.getPlayerHeadInstance().returnItem(deadPlayer);

        deathLocation.getWorld().dropItem(deathLocation, item);
    }

    // TODO: Need to create a few functions in this events file because it's messy
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        String itemTag = e.getItemInHand().getItemMeta().getPersistentDataContainer().get(
                new NamespacedKey(mainReference, "custom-item-type"), PersistentDataType.STRING
        );

        if (itemTag == null) return;
        if (!itemTag.equals("player-head") && !itemTag.equals("golden-head")) return;
        e.setCancelled(true);

    }

    @EventHandler
    public void onSlotSwap(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();

        boolean hasKey;
        try {
            hasKey = player.getInventory().getItem(e.getNewSlot()).getItemMeta().getPersistentDataContainer()
                    .get(new NamespacedKey(mainReference, "custom-item-type"), PersistentDataType.STRING)
                    .equals("anduril");
        } catch (NullPointerException ex) {
            hasKey = false;
        }

        if (hasKey) {
            try { andurilTimer.get(player.getUniqueId()).cancel(); } catch (NullPointerException ex) { ; }
            andurilTimer.remove(player.getUniqueId());
            andurilTimer.put(player.getUniqueId(), new BukkitRunnable() {

                @Override
                public void run() {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1 * 20, 0, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1 * 20, 0, false, false));
                }

            });

            andurilTimer.get(player.getUniqueId()).runTaskTimer(mainReference, 0, 1 * 20);
        } else {
            BukkitRunnable runnableReference = null;
            try {
                runnableReference = andurilTimer.get(player.getUniqueId());
                runnableReference.cancel();
            } catch (NullPointerException ex) {
                ;
            }
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        BukkitRunnable runnableReference = null;

        boolean hasKey;
        try {
            hasKey = e.getItemDrop().getItemStack().getItemMeta().getPersistentDataContainer()
                    .get(new NamespacedKey(mainReference, "custom-item-type"), PersistentDataType.STRING)
                    .equals("anduril");
        } catch (NullPointerException ex) {
            hasKey = false;
        }

        if (hasKey) {
            try {
                runnableReference = andurilTimer.get(player.getUniqueId());
                runnableReference.cancel();
            } catch (NullPointerException ex) {
                ;
            }
        }
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player)) { return; }
        Player player = (Player) e.getEntity();

        BukkitRunnable tempRunnable = new BukkitRunnable() {

            @Override
            public void run() {
                if (!player.getInventory().getItemInMainHand().isSimilar(e.getItem().getItemStack())) { return; }

                boolean hasKey;
                try {
                    hasKey = e.getItem().getItemStack().getItemMeta().getPersistentDataContainer()
                            .get(new NamespacedKey(mainReference, "custom-item-type"), PersistentDataType.STRING)
                            .equals("anduril");
                } catch (NullPointerException ex) {
                    hasKey = false;
                }

                if (hasKey) {
                    try { andurilTimer.get(player.getUniqueId()).cancel(); } catch (NullPointerException ex) { ; }
                    andurilTimer.remove(player.getUniqueId());
                    andurilTimer.put(player.getUniqueId(), new BukkitRunnable() {

                        @Override
                        public void run() {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1 * 20, 0, false, false));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1 * 20, 0, false, false));
                        }

                    });

                    andurilTimer.get(player.getUniqueId()).runTaskTimer(mainReference, 0, 1 * 20);
                }
            }

        };

        tempRunnable.runTaskLater(mainReference, 4); // 1/5 of a second, 1 sec being 20 ticks
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player)) { return; }
        Player player = (Player) e.getPlayer();

        boolean hasKey;
        try {
            hasKey = player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer()
                    .get(new NamespacedKey(mainReference, "custom-item-type"), PersistentDataType.STRING)
                    .equals("anduril");
        } catch (NullPointerException ex) {
            hasKey = false;
        }

        if (hasKey) {
            try { andurilTimer.get(player.getUniqueId()).cancel(); } catch (NullPointerException ex) { ; }
            andurilTimer.remove(player.getUniqueId());
            andurilTimer.put(player.getUniqueId(), new BukkitRunnable() {

                @Override
                public void run() {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1 * 20, 0, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1 * 20, 0, false, false));
                }

            });

            andurilTimer.get(player.getUniqueId()).runTaskTimer(mainReference, 0, 1 * 20);
        }

        if (!hasKey) {
            BukkitRunnable runnableReference = null;
            try {
                runnableReference = andurilTimer.get(player.getUniqueId());
                runnableReference.cancel();
            } catch (NullPointerException ex) {
                ;
            }
        }
    }


    @EventHandler
    public void onPlayerPVP(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player ePlayer = null;
        if (e.getDamager() instanceof Player) {
            ePlayer = (Player) e.getDamager();
        } else if (e.getDamager() instanceof Arrow) {
            if (!(((Arrow) e.getDamager()).getShooter() instanceof Player)) return;
            ePlayer = (Player) ((Arrow) e.getDamager()).getShooter();
        }

        if (ePlayer == null) return;
        if (ePlayer.getInventory().getHelmet() == null) return;

        if (!ePlayer.getInventory().getHelmet().getItemMeta().getPersistentDataContainer()
                .get(new NamespacedKey(mainReference, "custom-item-type"), PersistentDataType.STRING)
                .equals("exodus")) return;

        ePlayer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 3 * 20, 1, false, false));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        Player player = (Player) e.getWhoClicked();

        player.sendMessage("test1");
        ItemStack playerHelmet = player.getInventory().getHelmet();
        if (playerHelmet == null) return;
        player.sendMessage("test2");

        String playerHelmetTag = playerHelmet.getItemMeta().getPersistentDataContainer().get(
                new NamespacedKey(mainReference, "custom-item-type"), PersistentDataType.STRING
        );
        if (playerHelmetTag == null) return;
        player.sendMessage("test3");
        player.sendMessage(playerHelmetTag);
        if (!playerHelmetTag.equals("player-head") && !playerHelmetTag.equals("golden-head")) return;

        player.sendMessage("test4");
        e.setCancelled(true);
    }

}
