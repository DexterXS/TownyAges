package org.dexterx.mc.townyages;

import fr.andross.banitem.BanItem;
import fr.andross.banitem.BanItemAPI;
import fr.andross.banitem.actions.BanAction;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@SuppressWarnings("unused")
public class TownyAge extends JavaPlugin implements TabExecutor {

    @Override
    public void onEnable() {
        PluginCommand commandT = Objects.requireNonNull(getCommand("t"), "Command t not found");
        commandT.setExecutor(this);
        commandT.setTabCompleter(new TownyAgeTabCompleter(this.getDataFolder()));

        saveDefaultConfig();
        initializeIntegrations();
    }

    private void initializeIntegrations() {
        if (getServer().getPluginManager().getPlugin("BanItem") != null) {
            final BanItem banItem = (BanItem) getServer().getPluginManager().getPlugin("BanItem");
            if (banItem != null) {
                BanItemAPI banItemApi = banItem.getApi();
                getLogger().info("Successfully integrated with BanItem");

                // Temporary use of banItemApi
                Player tempPlayer = getServer().getPlayerExact("ExamplePlayerName");  // Replace with an actual player name for testing
                if (tempPlayer != null) {
                    boolean isBanned = banItemApi.isBanned(tempPlayer, Material.DIAMOND_SWORD, BanAction.INTERACT);
                    getLogger().info("Is DIAMOND_SWORD banned: " + isBanned);
                } else {
                    getLogger().info("ExamplePlayerName not found for testing isBanned.");
                }
            } else {
                getLogger().warning("BanItem not found, disabling related features.");
            }
        }

        // Initialize integration with Towny, LuckPerms here if needed
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("t")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("age")) {
                    handleAgeCommand(sender, args);
                    return true;
                }
            }
        }
        return false;
    }

    private void handleAgeCommand(@NotNull CommandSender sender, @NotNull String[] args) {
        if (args.length < 2) {
            sender.sendMessage("Usage: /t age <set|edit|del|menu> ...");
            return;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return;
        }

        Player player = (Player) sender;
        String subCommand = args[1];

        switch (subCommand.toLowerCase()) {
            case "set":
                handleSetAge(player, args);
                break;
            case "edit":
                handleEditAge(player, args);
                break;
            case "del":
                handleDelAge(player, args);
                break;
            case "menu":
                handleMenuAge(player);
                break;
            default:
                player.sendMessage("Unknown subcommand: " + subCommand);
                break;
        }
    }

    private void handleSetAge(@NotNull Player player, @NotNull String[] args) {
        // Implement the logic for /t age set
        player.sendMessage("Age set command executed.");
    }

    private void handleEditAge(@NotNull Player player, @NotNull String[] args) {
        // Implement the logic for /t age edit
        player.sendMessage("Age edit command executed.");
    }

    private void handleDelAge(@NotNull Player player, @NotNull String[] args) {
        // Implement the logic for /t age del
        player.sendMessage("Age delete command executed.");
    }

    private void handleMenuAge(@NotNull Player player) {
        // Implement the logic for /t age menu
        player.sendMessage("Age menu command executed.");
    }
}
