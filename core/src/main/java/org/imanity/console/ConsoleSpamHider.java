package org.imanity.console;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LifeCycle;
import org.apache.logging.log4j.core.Logger;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class ConsoleSpamHider extends JavaPlugin {

    public static ConsoleSpamHider INSTANCE;

    @Getter
    private final List<String> filterMessages = new ArrayList<>();

    @Override
    public void onEnable() {
        INSTANCE = this;

        this.reload();

        String engineName;
        // Using LifeCycle to check, since older engine Filter wasn't implementing LifeCycle
        if (LifeCycle.class.isAssignableFrom(Filter.class)) {
            engineName = "Modern Log4J Engine";
            ((Logger) LogManager.getRootLogger()).addFilter(new ModernLogFilter(this.filterMessages));
        } else {
            engineName = "Legacy Log4J Engine";
            ((Logger) LogManager.getRootLogger()).addFilter(new LegacyLogFilter(this.filterMessages));
        }

        this.getCommand("csh-reload").setExecutor(new ConsoleSpamHiderCommand());
        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[ConsoleSpamHider] Loaded. Found " + engineName);
    }

    protected void reload() {
        this.filterMessages.clear();

        this.saveDefaultConfig();
        this.reloadConfig();
        this.filterMessages.addAll(this.getConfig().getStringList("hide"));

        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[ConsoleSpamHider] Deny List: " + String.join("\n", this.filterMessages));
    }
}
