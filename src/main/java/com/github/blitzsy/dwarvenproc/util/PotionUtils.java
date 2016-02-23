package com.github.blitzsy.dwarvenproc.util;

import net.minecraft.potion.Potion;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class PotionUtils
{
    public static boolean isPotionIDSlotFree(int slot)
    {
        return Potion.potionTypes[slot] == null;
    }

    public static int getFreePotionIDSlot()
    {
        for (int potionIndex = 1; potionIndex < 128; potionIndex++)
        {
            if (Potion.potionTypes[potionIndex] == null)
            {
                return potionIndex;
            }
        }
        return -1;
    }

    public static void resizePotionArray()
    {
        if (Potion.potionTypes.length < 256)
        {
            Potion[] potionTypes;

            for (Field f : Potion.class.getDeclaredFields())
            {
                if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a"))
                {
                    try
                    {
                        Field modfield = Field.class.getDeclaredField("modifiers");

                        modfield.setAccessible(true);
                        modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

                        potionTypes = (Potion[])f.get(null);
                        final Potion[] newPotionTypes = new Potion[256];
                        System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                        f.set(null, newPotionTypes);
                    }
                    catch (NoSuchFieldException e)
                    {
                        e.printStackTrace();
                        System.err.println("Failed to resize potion array, The mod may work incorrectly until this is fixed! Please report this to the mod author ASAP.");
                    }
                    catch (IllegalAccessException e)
                    {
                        e.printStackTrace();
                        System.err.println("Failed to resize potion array, The mod may work incorrectly until this is fixed! Please report this to the mod author ASAP.");
                    }
                }
            }
        }
    }
}
