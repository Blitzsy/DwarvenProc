package com.github.blitzsy.dwarvenproc.command;

import com.github.blitzsy.dwarvenproc.reference.Types.Translations.Commands;
import com.github.blitzsy.dwarvenproc.reference.Types.Commands.SubCommands;
import com.github.blitzsy.dwarvenproc.reference.Types.Commands.Aliases;
import com.github.blitzsy.dwarvenproc.util.ProcUtils;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DwarvenProcCommands implements ICommand
{
    @Override
    public String getCommandName()
    {
        return Aliases.PROC_COMMAND_ALIASES.get(0);
    }

    @Override
    public List getCommandAliases()
    {
        return Aliases.PROC_COMMAND_ALIASES;
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return getCommandName() + " help";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args)
    {
        if (args.length > 0)
        {
            if (args[0].equalsIgnoreCase("help"))
                displayCommandHelp(sender);
            else if (args[0].equalsIgnoreCase("item"))
                displayCommandItemInfo(sender, args);
            else if (args[0].equalsIgnoreCase("give"))
                performCommandProc(sender, args);
            else
                displayHelpErrorMessage(sender);
        }
        else
            displayHelpErrorMessage(sender);

    }

    private void displayHelpErrorMessage(ICommandSender sender)
    {
        sender.addChatMessage(new ChatComponentTranslation(Commands.BAD_INPUT).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
    }

    private void displayCommandHelp(ICommandSender sender)
    {
        sender.addChatMessage(new ChatComponentTranslation(Commands.HELP_HEADER).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.WHITE)));
        sender.addChatMessage(new ChatComponentTranslation(Commands.HELP_ITEMINFO).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
        sender.addChatMessage(new ChatComponentTranslation(Commands.HELP_GIVEPROC).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
    }

    private void displayCommandItemInfo(ICommandSender sender, String[] args)
    {
        if (sender instanceof EntityPlayer)
        {
            final EntityPlayer player = (EntityPlayer) sender;
            final ItemStack heldItem = player.getHeldItem();

            if (heldItem != null)
            {
                player.addChatComponentMessage(new ChatComponentTranslation(Commands.ITEM_INFO_HEADER).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.WHITE)));
                player.addChatComponentMessage(new ChatComponentTranslation(Commands.ITEM_INFO_TYPE, " " + EnumChatFormatting.BLUE + heldItem.getUnlocalizedName()).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
                player.addChatComponentMessage(new ChatComponentTranslation(Commands.ITEM_INFO_NAME, " " + EnumChatFormatting.BLUE + heldItem.getDisplayName()).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
            }
            else
            {
                sender.addChatMessage(new ChatComponentTranslation(Commands.ITEM_NOT_HELD).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
            }
        }
        else
        {
            sender.addChatMessage(new ChatComponentTranslation(Commands.PLAYER_NOT_INGAME).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
        }
    }

    private void performCommandProc(ICommandSender sender, String[] args)
    {
        if (args.length == 2)
        {
            final String playerName = args[1].trim().toLowerCase();
            List entities;

            if (playerName.startsWith("@"))
                entities = PlayerSelector.matchEntities(sender, args[1], EntityLivingBase.class);
            else
                entities = Arrays.asList(ProcUtils.findPlayerByName(args[1]));

            if (entities != null && entities.size() > 0)
            {
                for (Object entityObj : entities)
                {
                    if (entityObj != null && entityObj instanceof EntityLivingBase)
                    {
                        EntityLivingBase entity = (EntityLivingBase) entityObj;

                        ProcUtils.giveEntityProc(entity);

                        if (entity instanceof EntityPlayer)
                        {
                            EntityPlayer player = (EntityPlayer) entity;
                            player.addChatMessage(new ChatComponentTranslation(Commands.GIVE_PROC_OTHER, EnumChatFormatting.WHITE + sender.getCommandSenderName()).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
                        }
                    }
                }
            }
        }
        else
        {
            if (sender instanceof EntityPlayer)
            {
                ProcUtils.giveEntityProc((EntityPlayer) sender);
                sender.addChatMessage(new ChatComponentTranslation(Commands.GIVE_PROC_SELF).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
            }
            else
            {
                sender.addChatMessage(new ChatComponentTranslation(Commands.PLAYER_NOT_INGAME).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
            }
        }
    }

    private List getPlayerTabCompletes(final String tabCompletetion)
    {
        final String tabComplete = tabCompletetion.trim().toLowerCase();

        final String[] users = MinecraftServer.getServer().getConfigurationManager().getAllUsernames();

        if (tabComplete.length() == 0)
            return Arrays.asList(users);

        final List players = new ArrayList();

        for (String user : users)
        {
            if (user.toLowerCase().startsWith(tabComplete))
                players.add(user);
        }
        return players;
    }

    private List getCommandTabCompletion(final String tabCompletion)
    {
        final String tabComplete = tabCompletion.trim().toLowerCase();

        if (tabComplete.length() == 0)
            return SubCommands.PROC_SUB_COMMANDS;

        List completions = new ArrayList();

        synchronized (SubCommands.PROC_SUB_COMMANDS)
        {
            for (String subCommand : SubCommands.PROC_SUB_COMMANDS)
            {
                if (subCommand.startsWith(tabComplete))
                {
                    completions.add(subCommand);
                }
            }
        }

        return completions;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return sender.canCommandSenderUseCommand(4, this.getCommandName());
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
    {
        if (args.length == 1)
            return this.getCommandTabCompletion(args[0]);
        else if (args.length == 2)
            if (args[0].equalsIgnoreCase("give"))
                return this.getPlayerTabCompletes(args[1]);

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
