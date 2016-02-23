package com.github.blitzsy.dwarvenproc.util;

import com.github.blitzsy.dwarvenproc.network.ParticlePacketMessage;
import com.github.blitzsy.dwarvenproc.reference.Settings.Config;
import com.github.blitzsy.dwarvenproc.reference.Types.Potion.Potions;
import com.github.blitzsy.dwarvenproc.reference.Types.Network.Wrappers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.WorldServer;

public class ProcUtils
{
    public static void giveEntityProc(EntityLivingBase entity)
    {
        if (!entity.worldObj.isRemote)
        {
            entity.worldObj.playSoundAtEntity(entity, "mob.wither.hurt", 1.0f, 0.1f);
            Wrappers.clientNetworkWrapper.sendToDimension(new ParticlePacketMessage("happyVillager", entity.posX, entity.posY, entity.posZ, 64), entity.dimension);
        }

        entity.addPotionEffect(new PotionEffect(Potions.dwarvenProcEffect.getId(), 3 * 20, 0, false));
        entity.addPotionEffect(new PotionEffect(Potion.field_76444_x.getId(), 8 * 20, 4, false));
    }

    public static void killEntity(EntityLivingBase attacker, EntityLivingBase attacked)
    {
        if (attacker instanceof EntityPlayer)
            attacked.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker).setDamageBypassesArmor().setDamageIsAbsolute(), attacked.getHealth());
        else
            attacked.attackEntityFrom(DamageSource.causeMobDamage(attacker).setDamageBypassesArmor().setDamageIsAbsolute(), attacked.getHealth());
    }

    public static boolean canItemProc(String itemType, String itemName)
    {
        boolean canItemProc = true;

        if (!Config.allowAnyItemName)
        {
            synchronized (Config.procItemNames)
            {
                boolean foundName = false;

                for (String name: Config.procItemNames)
                {
                    if (VarUtils.stripControlCodes(itemName).equalsIgnoreCase(VarUtils.stripControlCodes(name)))
                    {
                        foundName = true;
                        break;
                    }
                }
                canItemProc = foundName;
            }
        }

        if (canItemProc && !Config.allowAnyProcItem)
        {
            boolean foundType = false;

            synchronized (Config.procItemTypes)
            {
                for (String type: Config.procItemTypes)
                {
                    if (itemType.equalsIgnoreCase(type))
                    {
                        foundType = true;
                        break;
                    }
                }
            }
            canItemProc = foundType;
        }
        return canItemProc;
    }


    public static EntityPlayer findPlayerByName(String name)
    {
        EntityPlayer player = null;

        for (WorldServer worldServer : MinecraftServer.getServer().worldServers)
        {
            player = worldServer.getPlayerEntityByName(name);

            if (player != null)
                break;
        }

        return player;
    }
}
