package com.github.blitzsy.dwarvenproc.command;

import com.github.blitzsy.dwarvenproc.reference.Types.Translations.Messages;
import com.github.blitzsy.dwarvenproc.reference.Types.Translations.Commands;
import com.github.blitzsy.dwarvenproc.reference.Types.Commands.Aliases;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class KillCountCommands implements ICommand
{
    @Override
    public String getCommandName()
    {
        return Aliases.KILL_COUNT_COMMAND_ALIASES.get(0);
    }

    @Override
    public List getCommandAliases()
    {
        return Aliases.KILL_COUNT_COMMAND_ALIASES;
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return getCommandName();
    }


    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (sender instanceof EntityPlayer)
        {
            final EntityPlayer player = (EntityPlayer) sender;

            if (player.getEntityData().hasKey("procStreak"))
                player.addChatComponentMessage(new TextComponentTranslation(Messages.CHAT_MESSAGE_PROC_COUNT, TextFormatting.WHITE + String.valueOf(player.getEntityData().getInteger("procStreak"))).setStyle(new Style().setColor(TextFormatting.GREEN)), false);
            else
                sender.addChatMessage(new TextComponentTranslation(Commands.NO_PROC_YET).setStyle(new Style().setColor(TextFormatting.RED)));
        }
        else
        {
            sender.addChatMessage(new TextComponentTranslation(Commands.PLAYER_NOT_INGAME).setStyle(new Style().setColor(TextFormatting.RED)));
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }


    @Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos)
    {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] sender, int index)
    {
        return false;
    }

    @Override
    public int compareTo(ICommand o)
    {
        return this.getCommandName().compareTo(o.getCommandName());
    }
}
