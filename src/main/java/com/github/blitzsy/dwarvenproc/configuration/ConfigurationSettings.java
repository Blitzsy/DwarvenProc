package com.github.blitzsy.dwarvenproc.configuration;

import com.github.blitzsy.dwarvenproc.reference.Settings.Config;
import com.github.blitzsy.dwarvenproc.reference.Settings.Info.Names;
import com.github.blitzsy.dwarvenproc.reference.Settings.Info.Groups;
import com.github.blitzsy.dwarvenproc.reference.Settings.Info.Descriptions;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.Arrays;

public class ConfigurationSettings
{
    private Configuration configuration;

    public ConfigurationSettings(File configurationFile)
    {
        configuration = new Configuration(configurationFile);

        loadConfiguration();
    }

    private void loadConfiguration()
    {
        configuration.load();

        Config.allowAnyProcItem = configuration.getBoolean(Names.ALLOW_ANY_PROC_ITEM, Groups.RESTRICTION_SETTINGS, Config.allowAnyProcItem, Descriptions.ALLOW_ANY_PROC_ITEM);
        Config.allowAnyItemName = configuration.getBoolean(Names.ALLOW_ANY_ITEM_NAME, Groups.RESTRICTION_SETTINGS, Config.allowAnyItemName, Descriptions.ALLOW_ANY_ITEM_NAME);
        Config.allowBowProc = configuration.getBoolean(Names.ALLOW_BOW_PROC, Groups.RESTRICTION_SETTINGS, Config.allowBowProc, Descriptions.ALLOW_BOW_PROC);
        Config.allowReverseBowProc = configuration.getBoolean(Names.ALLOW_REVERSE_BOW_PROC, Groups.RESTRICTION_SETTINGS, Config.allowReverseBowProc, Descriptions.ALLOW_REVERSE_BOW_PROC);
        Config.allowPlayerVsPlayerProc = configuration.getBoolean(Names.ALLOW_PLAYER_VS_PLAYER_PROC, Groups.RESTRICTION_SETTINGS, Config.allowPlayerVsPlayerProc, Descriptions.ALLOW_PLAYER_VS_PLAYER_PROC);
        Config.allowProcingMobs = configuration.getBoolean(Names.ALLOW_PROCING_MOBS, Groups.RESTRICTION_SETTINGS, Config.allowProcingMobs, Descriptions.ALLOW_PROCING_MOBS);
        Config.procItemNames = Arrays.asList(configuration.getStringList(Names.PROC_ITEM_NAMES, Groups.PROC_SETTINGS, Config.procItemNames.toArray(new String[Config.procItemNames.size()]), Descriptions.PROC_ITEM_NAMES));
        Config.procItemTypes = Arrays.asList(configuration.getStringList(Names.PROC_ITEM_TYPES, Groups.PROC_SETTINGS, Config.procItemTypes.toArray(new String[Config.procItemTypes.size()]), Descriptions.PROC_ITEM_TYPES));
        Config.displayProcKillCount = configuration.getBoolean(Names.DISPLAY_PROC_KILL_COUNT, Groups.PROC_SETTINGS, Config.displayProcKillCount, Descriptions.DISPLAY_PRIC_KILL_COUNT);

        if (configuration.hasChanged())
            configuration.save();
    }
}
