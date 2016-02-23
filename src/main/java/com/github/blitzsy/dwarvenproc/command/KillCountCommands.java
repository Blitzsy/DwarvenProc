package com.github.blitzsy.dwarvenproc.command;

import com.github.blitzsy.dwarvenproc.reference.Types.Translations.Messages;
import com.github.blitzsy.dwarvenproc.reference.Types.Translations.Commands;
import com.github.blitzsy.dwarvenproc.reference.Types.Commands.Aliases;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class KillCountCommands implements ICommand
{
    @Override
    public String getName()
    {
        return Aliases.KILL_COUNT_COMMAND_ALIASES.get(0);
    }

    @Override
    public List getAliases()
    {
        return Aliases.KILL_COUNT_COMMAND_ALIASES;
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return getName();
    }

    @Override
    public void execute(ICommandSender sender, String[] args)
    {
        if (sender instanceof EntityPlayer)
        {
            final EntityPlayer player = (EntityPlayer) sender;

            if (player.getEntityData().hasKey("procStreak"))
                player.addChatComponentMessage(new ChatComponentTranslation(Messages.CHAT_MESSAGE_PROC_COUNT, EnumChatFormatting.WHITE + String.valueOf(player.getEntityData().getInteger("procStreak"))).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
            else
                sender.addChatMessage(new ChatComponentTranslation(Commands.NO_PROC_YET).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
        }
        else
        {
            sender.addChatMessage(new ChatComponentTranslation(Commands.PLAYER_NOT_INGAME).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
        }
    }

    @Override
    public boolean canCommandSenderUse(ICommandSender sender)
    {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
    {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] sender, int index)
    {
        return false;
    }

    @Override
    public int compareTo(Object o)
    {
        if (o instanceof ICommand)
        {
            final ICommand command = (ICommand) o;

            return this.getName().compareTo(command.getName());
        }
        return 0;
    }
}
