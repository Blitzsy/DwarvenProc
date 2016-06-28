package com.github.blitzsy.dwarvenproc.command;

import com.github.blitzsy.dwarvenproc.reference.Types.Translations.Commands;
import com.github.blitzsy.dwarvenproc.reference.Types.Commands.SubCommands;
import com.github.blitzsy.dwarvenproc.reference.Types.Commands.Aliases;
import com.github.blitzsy.dwarvenproc.util.ProcUtils;
import net.minecraft.command.CommandException;
import net.minecraft.command.EntitySelector;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

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
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
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

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return getCommandName() + " help";
    }

    private void displayHelpErrorMessage(ICommandSender sender)
    {
        sender.addChatMessage(new TextComponentTranslation(Commands.BAD_INPUT).setChatStyle(new Style().setColor(TextFormatting.RED)));
    }

    private void displayCommandHelp(ICommandSender sender)
    {
        sender.addChatMessage(new TextComponentTranslation(Commands.HELP_HEADER).setChatStyle(new Style().setColor(TextFormatting.WHITE)));
        sender.addChatMessage(new TextComponentTranslation(Commands.HELP_ITEMINFO).setChatStyle(new Style().setColor(TextFormatting.GREEN)));
        sender.addChatMessage(new TextComponentTranslation(Commands.HELP_GIVEPROC).setChatStyle(new Style().setColor(TextFormatting.GREEN)));
    }

    private void displayCommandItemInfo(ICommandSender sender, String[] args)
    {
        if (sender instanceof EntityPlayer)
        {
            final EntityPlayer player = (EntityPlayer) sender;
            final ItemStack heldItemMainhand = player.getHeldItem(EnumHand.MAIN_HAND);
            final ItemStack heldItemOffhand = player.getHeldItem(EnumHand.OFF_HAND);

            if (heldItemMainhand != null && heldItemOffhand != null)
            {
                player.addChatComponentMessage(new TextComponentTranslation(Commands.ITEM_INFO_HEADER).setChatStyle(new Style().setColor(TextFormatting.WHITE)));
                player.addChatComponentMessage(new TextComponentTranslation(Commands.ITEM_INFO_MAIN_HAND));
                player.addChatComponentMessage(new TextComponentTranslation(Commands.ITEM_INFO_TYPE, " " + TextFormatting.BLUE + heldItemMainhand.getUnlocalizedName()).setChatStyle(new Style().setColor(TextFormatting.GREEN)));
                player.addChatComponentMessage(new TextComponentTranslation(Commands.ITEM_INFO_NAME, " " + TextFormatting.BLUE + heldItemMainhand.getDisplayName()).setChatStyle(new Style().setColor(TextFormatting.GREEN)));
                player.addChatComponentMessage(new TextComponentTranslation(Commands.ITEM_INFO_OFF_HAND));
                player.addChatComponentMessage(new TextComponentTranslation(Commands.ITEM_INFO_TYPE, " " + TextFormatting.BLUE + heldItemOffhand.getUnlocalizedName()).setChatStyle(new Style().setColor(TextFormatting.GREEN)));
                player.addChatComponentMessage(new TextComponentTranslation(Commands.ITEM_INFO_NAME, " " + TextFormatting.BLUE + heldItemOffhand.getDisplayName()).setChatStyle(new Style().setColor(TextFormatting.GREEN)));
            }
            else if (heldItemMainhand != null)
            {
                player.addChatComponentMessage(new TextComponentTranslation(Commands.ITEM_INFO_HEADER).setChatStyle(new Style().setColor(TextFormatting.WHITE)));
                player.addChatComponentMessage(new TextComponentTranslation(Commands.ITEM_INFO_MAIN_HAND));
                player.addChatComponentMessage(new TextComponentTranslation(Commands.ITEM_INFO_TYPE, " " + TextFormatting.BLUE + heldItemMainhand.getUnlocalizedName()).setChatStyle(new Style().setColor(TextFormatting.GREEN)));
                player.addChatComponentMessage(new TextComponentTranslation(Commands.ITEM_INFO_NAME, " " + TextFormatting.BLUE + heldItemMainhand.getDisplayName()).setChatStyle(new Style().setColor(TextFormatting.GREEN)));
            }
            else if (heldItemOffhand != null)
            {
                player.addChatComponentMessage(new TextComponentTranslation(Commands.ITEM_INFO_HEADER).setChatStyle(new Style().setColor(TextFormatting.WHITE)));
                player.addChatComponentMessage(new TextComponentTranslation(Commands.ITEM_INFO_OFF_HAND));
                player.addChatComponentMessage(new TextComponentTranslation(Commands.ITEM_INFO_TYPE, " " + TextFormatting.BLUE + heldItemOffhand.getUnlocalizedName()).setChatStyle(new Style().setColor(TextFormatting.GREEN)));
                player.addChatComponentMessage(new TextComponentTranslation(Commands.ITEM_INFO_NAME, " " + TextFormatting.BLUE + heldItemOffhand.getDisplayName()).setChatStyle(new Style().setColor(TextFormatting.GREEN)));
            }
            else
            {
                sender.addChatMessage(new TextComponentTranslation(Commands.ITEM_NOT_HELD).setChatStyle(new Style().setColor(TextFormatting.RED)));
            }
        }
        else
        {
            sender.addChatMessage(new TextComponentTranslation(Commands.PLAYER_NOT_INGAME).setChatStyle(new Style().setColor(TextFormatting.RED)));
        }
    }

    private void performCommandProc(ICommandSender sender, String[] args)
    {
        if (args.length == 2)
        {
            final String playerName = args[1].trim().toLowerCase();
            List entities;

            if (playerName.startsWith("@"))
                entities = EntitySelector.matchEntities(sender, args[1], EntityLivingBase.class);
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
                            player.addChatMessage(new TextComponentTranslation(Commands.GIVE_PROC_OTHER, TextFormatting.WHITE + sender.getName()).setChatStyle(new Style().setColor(TextFormatting.GREEN)));
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
                sender.addChatMessage(new TextComponentTranslation(Commands.GIVE_PROC_SELF).setChatStyle(new Style().setColor(TextFormatting.GREEN)));
            }
            else
            {
                sender.addChatMessage(new TextComponentTranslation(Commands.PLAYER_NOT_INGAME).setChatStyle(new Style().setColor(TextFormatting.RED)));
            }
        }
    }

    private List getPlayerTabCompletes(MinecraftServer server, String tabCompletetion)
    {
        final String tabComplete = tabCompletetion.trim().toLowerCase();
        String[] playerNames = server.getPlayerList().getAllUsernames();

        if (tabComplete.length() == 0)
            return Arrays.asList(playerNames);

        final List<String> players = new ArrayList<String>();

        for (String user : playerNames)
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
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return sender.canCommandSenderUseCommand(4, this.getCommandName());
    }

    @Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos)
    {
        if (args.length == 1)
            return this.getCommandTabCompletion(args[0]);
        else if (args.length == 2)
            if (args[0].equalsIgnoreCase("give"))
                return this.getPlayerTabCompletes(server, args[1]);

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
