package com.redmancometh.vbl;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.redmancometh.vbl.config.Config;
import com.redmancometh.vbl.config.ConfigManager;
import com.redmancometh.vbl.listeners.VoteListener;

import lombok.Getter;

public class VotifierBL extends JavaPlugin
{
    @Getter
    private ConfigManager<Config> cfg;

    @Override
    public void onEnable()
    {
        this.cfg = new ConfigManager("prefixes.json", Config.class);
        this.cfg.init(this);
        System.out.println(cfg());
        Bukkit.getPluginManager().registerEvents(new VoteListener(), this);
    }

    public Config cfg()
    {
        return cfg.getCurrentConfig();
    }

    public static VotifierBL instance()
    {
        return JavaPlugin.getPlugin(VotifierBL.class);
    }
}
