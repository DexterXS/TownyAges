package org.dexterx.mc.townyages;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TownyAgeTabCompleter implements TabCompleter {

    private final File ageDirectory;

    public TownyAgeTabCompleter(File dataFolder) {
        this.ageDirectory = new File(dataFolder, "ages");
        if (!ageDirectory.exists()) {
            if (ageDirectory.mkdirs()) {
                System.out.println("Directory 'ages' created successfully.");
            } else {
                System.out.println("Failed to create directory 'ages'.");
            }
        }
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        if (command.getName().equalsIgnoreCase("t")) {
            if (args.length == 1) {
                completions.add("age");
            } else if (args.length == 2) {
                completions.add("set");
                completions.add("edit");
                completions.add("del");
                completions.add("menu");
            } else if (args.length == 3 && args[1].equalsIgnoreCase("set")) {
                completions.addAll(getAgeNames());
            } else if (args.length == 3 && args[1].equalsIgnoreCase("edit")) {
                completions.addAll(getAgeNames());
            } else if (args.length == 3 && args[1].equalsIgnoreCase("del")) {
                completions.addAll(getAgeNames());
            }
        }
        return completions;
    }

    private List<String> getAgeNames() {
        List<String> ageNames = new ArrayList<>();
        if (ageDirectory.exists() && ageDirectory.isDirectory()) {
            for (File file : Objects.requireNonNull(ageDirectory.listFiles())) {
                if (file.isFile()) {
                    ageNames.add(file.getName().replace(".yml", "")); // Assuming ages are stored as .yml files
                }
            }
        }
        return ageNames;
    }
}
