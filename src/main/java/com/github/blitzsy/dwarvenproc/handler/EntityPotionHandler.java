package com.github.blitzsy.dwarvenproc.handler;

import com.github.blitzsy.dwarvenproc.reference.Settings.Config;
import com.github.blitzsy.dwarvenproc.reference.Types.Potion.Potions;
import com.github.blitzsy.dwarvenproc.reference.Types.Translations.Messages;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class EntityPotionHandler
{
    @SubscribeEvent
    public void onEntityUpdate(LivingUpdateEvent event)
    {
        if (event.getEntityLiving() instanceof EntityPlayer && !event.getEntityLiving().isPotionActive(Potions.dwarvenProcEffect))
        {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

            if (player.getEntityData().hasKey("procKills"))
            {
                final int procKills = player.getEntityData().getInteger("procKills");

                if (procKills > 0)
                {
                    if (Config.displayProcKillCount)
                    {
                        player.addChatComponentMessage(new TextComponentTranslation(Messages.CHAT_MESSAGE_PROC_COUNT, TextFormatting.WHITE + String.valueOf(procKills)).setStyle(new Style().setColor(TextFormatting.GREEN)));
                    }

                    player.getEntityData().setInteger("procStreak", procKills);
                    player.getEntityData().setInteger("procKills", 0);
                }
            }
        }
    }
}
