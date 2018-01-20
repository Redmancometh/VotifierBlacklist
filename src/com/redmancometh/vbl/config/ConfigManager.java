package com.redmancometh.vbl.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Modifier;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;

public class ConfigManager<T>
{
    @Getter
    private Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.PROTECTED).setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();
    private String configName;
    private T currentConfig;
    private Class<T> confClass;
    private File configFile;

    public ConfigManager(String configName, Class<T> confClass)
    {
        this.configName = configName;
        this.confClass = confClass;
    }

    public void processMappings()
    {
        this.getCurrentConfig();
    }

    public void init(JavaPlugin plugin)
    {
        File configFile = new File(plugin.getDataFolder(), configName);
        if (!configFile.exists())
        {
            plugin.saveResource(configName, false);
        }
        this.configFile = configFile;
        reload();
    }

    public void reload()
    {
        try (FileInputStream fileIn = new FileInputStream(configFile))
        {
            try (InputStreamReader in = new InputStreamReader(fileIn))
            {
                this.currentConfig = getGson().fromJson(in, confClass);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public T getCurrentConfig()
    {
        return currentConfig;
    }

    public void setCurrentConfig(T currentConfig)
    {
        this.currentConfig = currentConfig;
    }

}
