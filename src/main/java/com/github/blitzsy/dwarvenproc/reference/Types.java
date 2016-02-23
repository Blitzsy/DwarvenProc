package com.github.blitzsy.dwarvenproc.reference;

import com.github.blitzsy.dwarvenproc.command.DwarvenProcCommands;
import com.github.blitzsy.dwarvenproc.command.KillCountCommands;
import com.github.blitzsy.dwarvenproc.configuration.ConfigurationSettings;
import com.github.blitzsy.dwarvenproc.handler.EntityCombatHandler;
import com.github.blitzsy.dwarvenproc.handler.EntityPotionHandler;
import com.github.blitzsy.dwarvenproc.potion.DwarvenProcEffect;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

import java.util.Arrays;
import java.util.List;

public class Types
{
    public static final class Potion
    {
        public static final class Potions
        {
            public static DwarvenProcEffect dwarvenProcEffect;
        }

        public static final class Names
        {
            public static final String DWARVEN_PROC_EFFECT = "Dwarven Proc";
        }

        public static final class EffectTypes
        {
            public static final boolean DWARVEN_PROC_EFFECT = false;
        }

        public static final class Colors
        {
            public static final int DWARVEN_PROC_EFFECT = 16711680;
        }
    }

    public static final class Event
    {
        public static final class Events
        {
            public static EntityCombatHandler entityCombatHandler;
            public static EntityPotionHandler entityPotionHandler;
        }
    }

    public static final class Network
    {
        public static final class Wrappers
        {
            public static SimpleNetworkWrapper clientNetworkWrapper;
        }
    }

    public static final class Configuration
    {
        public static final class Configurations
        {
            public static ConfigurationSettings configurationSettings;
        }
    }

    public static final class Commands {
        public static final class Aliases
        {
            public static final List<String> PROC_COMMAND_ALIASES = Arrays.asList("proc");
            public static final List<String> KILL_COUNT_COMMAND_ALIASES = Arrays.asList("streak");
        }

        public static final class SubCommands
        {
            public static final List<String> PROC_SUB_COMMANDS = Arrays.asList("help", "item", "give");
        }

        public static final class Handlers
        {
            public static DwarvenProcCommands dwarvenProcCommands;
            public static KillCountCommands killCountCommands;
        }
    }

    public static final class Translations
    {
        public static final class Commands
        {
            public static final String BAD_INPUT = "dwarvenproc.command.helpBadInput";
            public static final String HELP_HEADER = "dwarvenproc.command.help.header";
            public static final String HELP_ITEMINFO = "dwarvenproc.command.help.infoitem";
            public static final String HELP_GIVEPROC = "dwarvenproc.command.help.infogive";
            public static final String ITEM_INFO_HEADER = "dwarvenproc.command.item.infoheader";
            public static final String ITEM_INFO_TYPE = "dwarvenproc.command.item.weaponnameinfo";
            public static final String ITEM_INFO_NAME = "dwarvenproc.command.item.displaynameinfo";
            public static final String ITEM_NOT_HELD = "dwarvenproc.command.item.noitemheld";
            public static final String PLAYER_NOT_INGAME = "dwarvenproc.command.notIngame";
            public static final String GIVE_PROC_OTHER = "dwarvenproc.command.give.other";
            public static final String GIVE_PROC_SELF = "dwarvenproc.command.give.self";
            public static final String NO_PROC_YET = "dwarvenproc.command.noprocyet";
        }

        public static final class Messages
        {
            public static final String CHAT_MESSAGE_PROC_COUNT = "dwarvenproc.chat.prockills";
        }
    }
}
