package com.github.blitzsy.dwarvenproc.util;

import net.minecraft.util.EnumParticleTypes;

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

    public static EnumParticleTypes lookupParticleByName(String particleName)
    {
        EnumParticleTypes particle = null;
        final String[] particleNames = EnumParticleTypes.func_179349_a();

        for (int i = 0; i < particleNames.length; i++)
        {
            if (particleNames[i].equalsIgnoreCase(particleName))
            {
                particle = EnumParticleTypes.getParticleFromId(i);
            }
        }
        return particle;
    }
}
