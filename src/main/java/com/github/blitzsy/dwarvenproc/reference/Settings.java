package com.github.blitzsy.dwarvenproc.reference;

import com.github.blitzsy.dwarvenproc.util.PotionUtils;

import java.util.Arrays;
import java.util.List;

public class Settings
{
    public static final class Config
    {
        public static boolean allowAnyProcItem = false;
        public static boolean allowAnyItemName = false;
        public static boolean allowBowProc = true;
        public static boolean allowReverseBowProc = true;
        public static boolean allowPlayerVsPlayerProc = false;
        public static boolean allowProcingMobs = false;
        public static List<String> procItemTypes = Arrays.asList("item.sworddiamond", "item.pickaxediamond", "item.hatchetdiamond", "item.shoveldiamond", "item.hoediamond");
        public static List<String> procItemNames = Arrays.asList("dwarven runeblade", "holy blade", "light bringer", "dwarven shovel", "tomb maker", "staff of defile", "excaliju");
        public static int dwarvenProcPotionID = PotionUtils.getFreePotionIDSlot();
        public static boolean displayProcKillCount = true;
    }

    public static final class Info
    {
        public static final class Groups
        {
            public static final String PROC_SETTINGS = "Proc";
            public static final String RESTRICTION_SETTINGS = "Restrictions";
        }

        public static final class Names
        {
            public static final String ALLOW_ANY_PROC_ITEM = "AllowAnyProcItem";
            public static final String ALLOW_ANY_ITEM_NAME = "AllowAnyItemName";
            public static final String ALLOW_BOW_PROC = "AllowBowProc";
            public static final String ALLOW_REVERSE_BOW_PROC = "AllowReverseBowProc";
            public static final String ALLOW_PLAYER_VS_PLAYER_PROC = "AllowPlayerVsPlayerProc";
            public static final String ALLOW_PROCING_MOBS = "AllowProcingMobs";
            public static final String PROC_ITEM_TYPES = "ProcItemTypes";
            public static final String PROC_ITEM_NAMES = "ProcItemNames";
            public static final String DWARVEN_PROC_POTION_ID = "DwarvenProcPotionID";
            public static final String DISPLAY_PROC_KILL_COUNT = "DisplayProcKillCount";
        }

        public static final class Descriptions
        {
            public static final String ALLOW_ANY_PROC_ITEM = "Setting this to true will allow any item to produce a proc. This setting does not affect AllowAnyItemName.";
            public static final String ALLOW_ANY_ITEM_NAME = "Setting this to true will allow a item to not require to be renamed to produce a proc. This setting does not affect AllowAnyProcItem";
            public static final String ALLOW_BOW_PROC = "Setting this to false will not allow a player to gain a proc using a ranged weapon such as a bow.";
            public static final String ALLOW_REVERSE_BOW_PROC = "Setting this to false will not allow a player to kill entities using a proc with a ranged weapon such as a bow.";
            public static final String ALLOW_PLAYER_VS_PLAYER_PROC = "Setting this to true will allow a player to gain procs off players and kill other players with a proc.";
            public static final String ALLOW_PROCING_MOBS = "Setting this to true will allow mobs to gain a proc using a weapon capable of procing.";
            public static final String PROC_ITEM_TYPES = "This is a list of unlocalized item names which can produce a proc, You can get a item by its name by using the /proc item command.";
            public static final String PROC_ITEM_NAMES = "This is a list of names that a item can be renamed to enable it to produce a proc, You can get a item by its name by using the /proc item command.";
            public static final String DWARVEN_PROC_POTION_ID = "This is the id for the proc potion effect which is required for the 1.7.10 version. This is usually auto generated fine, but can be changed if it conflicts with another mod.";
            public static final String DISPLAY_PRIC_KILL_COUNT = "Setting this to true will enable the kill streak chat message after a proc has worn off.";
        }
    }
}
