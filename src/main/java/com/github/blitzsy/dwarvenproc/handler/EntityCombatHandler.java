package com.github.blitzsy.dwarvenproc.handler;

import com.github.blitzsy.dwarvenproc.reference.Settings;
import com.github.blitzsy.dwarvenproc.reference.Types.Potion.Potions;
import com.github.blitzsy.dwarvenproc.util.ProcUtils;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
        if (event.getEntity() instanceof EntityLivingBase && event.getSource().getEntity() instanceof EntityLivingBase)
        {
            if (event.getSource().isProjectile() && !Settings.Config.allowReverseBowProc)
                return;

            final EntityLivingBase attacker = (EntityLivingBase) event.getSource().getEntity();
            final EntityLivingBase attacked = (EntityLivingBase) event.getEntityLiving();
            final ItemStack attackerHeldItemMain = attacker.getHeldItem(EnumHand.MAIN_HAND);
            final ItemStack attackerHeldItemOffhand = attacker.getHeldItem(EnumHand.OFF_HAND);
            final String attackerHeldItemNameMain = attackerHeldItemMain == null ? "" : attackerHeldItemMain.getDisplayName();
            final String attackerHeldItemTypeMain = attackerHeldItemMain == null ? "" : attackerHeldItemMain.getUnlocalizedName();
            final String attackerHeldItemNameOffhand = attackerHeldItemOffhand == null ? "" : attackerHeldItemOffhand.getDisplayName();
            final String attackerHeldItemTypeOffhand = attackerHeldItemOffhand == null ? "" : attackerHeldItemOffhand.getUnlocalizedName();

            if (!(attacker instanceof EntityPlayer) && !Settings.Config.allowProcingMobs)
                return;
            if (attacker instanceof EntityPlayer && attacked instanceof EntityPlayer && !Settings.Config.allowPlayerVsPlayerProc)
                return;
            if (attacked.deathTime > 0 || attacked.getHealth() - event.getAmount() <= 0)
                return;

            if (attacker.isPotionActive(Potions.dwarvenProcEffect) && (ProcUtils.canItemProc(attackerHeldItemTypeMain, attackerHeldItemNameMain) || ProcUtils.canItemProc(attackerHeldItemTypeOffhand, attackerHeldItemNameOffhand)))
            {
                ProcUtils.giveEntityProc(attacker);
                ProcUtils.killEntity(attacker, attacked);
            }
        }
    }

    @SubscribeEvent
    public void entityKilledEvent(LivingDeathEvent event)
    {
        if (event.getEntity() instanceof EntityLivingBase && event.getSource().getEntity() instanceof EntityLivingBase)
        {
            if (event.getSource().isProjectile() && !Settings.Config.allowBowProc)
                return;

            final EntityLivingBase attacker = (EntityLivingBase) event.getSource().getEntity();
            final EntityLivingBase attacked = (EntityLivingBase) event.getEntity();
            final ItemStack attackerHeldItemMain = attacker.getHeldItem(EnumHand.MAIN_HAND);
            final ItemStack attackerHeldItemOffhand = attacker.getHeldItem(EnumHand.OFF_HAND);
            final String attackerHeldItemNameMain = attackerHeldItemMain == null ? "" : attackerHeldItemMain.getDisplayName();
            final String attackerHeldItemTypeMain = attackerHeldItemMain == null ? "" : attackerHeldItemMain.getUnlocalizedName();
            final String attackerHeldItemNameOffhand = attackerHeldItemOffhand == null ? "" : attackerHeldItemOffhand.getDisplayName();
            final String attackerHeldItemTypeOffhand = attackerHeldItemOffhand == null ? "" : attackerHeldItemOffhand.getUnlocalizedName();

            if (!(attacker instanceof EntityPlayer) && !Settings.Config.allowProcingMobs)
                return;
            if (attacker instanceof EntityPlayer && attacked instanceof EntityPlayer && !Settings.Config.allowPlayerVsPlayerProc)
                return;

            if ((ProcUtils.canItemProc(attackerHeldItemTypeMain, attackerHeldItemNameMain) || ProcUtils.canItemProc(attackerHeldItemTypeOffhand, attackerHeldItemNameOffhand)))
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
