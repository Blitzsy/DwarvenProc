package com.github.blitzsy.dwarvenproc.util;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import java.util.Random;
import java.util.regex.Pattern;

public class VarUtils
{
    public static final Random RANDOM = new Random();
    private static final Pattern patternControlCode = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

    public static String stripControlCodes(String str)
    {
        return patternControlCode.matcher(str).replaceAll("");
    }

    public static SoundEvent getSoundByName(String soundName)
    {
        SoundEvent soundEvent = null;

        for (ResourceLocation resourceLocation: SoundEvent.soundEventRegistry.getKeys())
        {
            if (resourceLocation.getResourcePath().equalsIgnoreCase(soundName))
            {
                soundEvent = SoundEvent.soundEventRegistry.getObject(resourceLocation);
            }
        }

        return soundEvent;
    }
}
