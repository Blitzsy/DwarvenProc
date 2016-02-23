package com.github.blitzsy.dwarvenproc.handler;

import com.github.blitzsy.dwarvenproc.reference.Settings.Config;
import com.github.blitzsy.dwarvenproc.reference.Types.Potion.Potions;
import com.github.blitzsy.dwarvenproc.reference.Types.Translations.Messages;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class EntityPotionHandler
{
    @SubscribeEvent
    public void onEntityUpdate(LivingUpdateEvent event)
    {
        if (event.entityLiving instanceof EntityPlayer && !event.entityLiving.isPotionActive(Potions.dwarvenProcEffect))
        {
            EntityPlayer player = (EntityPlayer) event.entityLiving;

            if (player.getEntityData().hasKey("procKills"))
            {
                final int procKills = player.getEntityData().getInteger("procKills");

                if (procKills > 0)
                {
                    if (Config.displayProcKillCount)
                    {
                        player.addChatComponentMessage(new ChatComponentTranslation(Messages.CHAT_MESSAGE_PROC_COUNT, EnumChatFormatting.WHITE + String.valueOf(procKills)).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
                    }

                    player.getEntityData().setInteger("procStreak", procKills);
                    player.getEntityData().setInteger("procKills", 0);
                }
            }
        }
    }
}
