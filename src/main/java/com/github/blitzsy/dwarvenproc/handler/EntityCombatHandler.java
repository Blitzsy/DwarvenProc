package com.github.blitzsy.dwarvenproc.handler;

import com.github.blitzsy.dwarvenproc.reference.Settings;
import com.github.blitzsy.dwarvenproc.reference.Types.Potion.Potions;
import com.github.blitzsy.dwarvenproc.util.ProcUtils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EntityCombatHandler
{
    @SubscribeEvent
    public void entityAttackedEvent(LivingAttackEvent event)
    {
        if (event.entity instanceof EntityLivingBase && event.source.getEntity() instanceof EntityLivingBase)
        {
            if (event.source.isProjectile() && !Settings.Config.allowReverseBowProc)
                return;

            final EntityLivingBase attacker = (EntityLivingBase) event.source.getEntity();
            final EntityLivingBase attacked = (EntityLivingBase) event.entity;
            final ItemStack attackerHeldItem = attacker.getHeldItem();
            final String attackerHeldItemName = attackerHeldItem == null ? "" : attackerHeldItem.getDisplayName();
            final String attackerHeldItemType = attackerHeldItem == null ? "" : attackerHeldItem.getUnlocalizedName();

            if (!(attacker instanceof EntityPlayer) && !Settings.Config.allowProcingMobs)
                return;
            if (attacker instanceof EntityPlayer && attacked instanceof EntityPlayer && !Settings.Config.allowPlayerVsPlayerProc)
                return;
            if (attacked.deathTime > 0 || attacked.getHealth() - event.ammount <= 0)
                return;

            if (attacker.isPotionActive(Potions.dwarvenProcEffect) && ProcUtils.canItemProc(attackerHeldItemType, attackerHeldItemName))
            {
                ProcUtils.giveEntityProc(attacker);
                ProcUtils.killEntity(attacker, attacked);
            }
        }
    }

    @SubscribeEvent
    public void entityKilledEvent(LivingDeathEvent event)
    {
        if (event.entity instanceof EntityLivingBase && event.source.getEntity() instanceof EntityLivingBase)
        {
            if (event.source.isProjectile() && !Settings.Config.allowBowProc)
                return;

            final EntityLivingBase attacker = (EntityLivingBase) event.source.getEntity();
            final EntityLivingBase attacked = (EntityLivingBase) event.entity;
            final ItemStack attackerHeldItem = attacker.getHeldItem();
            final String attackerHeldItemName = attackerHeldItem == null ? "" : attackerHeldItem.getDisplayName();
            final String attackerHeldItemType = attackerHeldItem == null ? "" : attackerHeldItem.getUnlocalizedName();

            if (!(attacker instanceof EntityPlayer) && !Settings.Config.allowProcingMobs)
                return;
            if (attacker instanceof EntityPlayer && attacked instanceof EntityPlayer && !Settings.Config.allowPlayerVsPlayerProc)
                return;

            if (ProcUtils.canItemProc(attackerHeldItemType, attackerHeldItemName))
            {
                ProcUtils.giveEntityProc(attacker);

                if (attacker instanceof EntityPlayer)
                {
                    if (!attacker.getEntityData().hasKey("procKills"))
                    {
                        attacker.getEntityData().setInteger("procKills", 0);
                    }

                    attacker.getEntityData().setInteger("procKills", attacker.getEntityData().getInteger("procKills") + 1);
                }
            }
        }
    }
}
