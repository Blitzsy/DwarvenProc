package com.github.blitzsy.dwarvenproc.proxy.side;

import com.github.blitzsy.dwarvenproc.command.DwarvenProcCommands;
import com.github.blitzsy.dwarvenproc.command.KillCountCommands;
import com.github.blitzsy.dwarvenproc.configuration.ConfigurationSettings;
import com.github.blitzsy.dwarvenproc.handler.EntityCombatHandler;
import com.github.blitzsy.dwarvenproc.handler.EntityPotionHandler;
import com.github.blitzsy.dwarvenproc.network.ParticlePacketMessage;
import com.github.blitzsy.dwarvenproc.potion.DwarvenProcEffect;
import com.github.blitzsy.dwarvenproc.proxy.ISidedProxy;
import com.github.blitzsy.dwarvenproc.reference.Reference;
import com.github.blitzsy.dwarvenproc.reference.Reference.ModInfo;
import com.github.blitzsy.dwarvenproc.reference.Settings;
import com.github.blitzsy.dwarvenproc.reference.Types;
import com.github.blitzsy.dwarvenproc.reference.Types.Commands.Handlers;
import com.github.blitzsy.dwarvenproc.reference.Types.Network.Wrappers;
import com.github.blitzsy.dwarvenproc.reference.Types.Configuration.Configurations;
import com.github.blitzsy.dwarvenproc.reference.Types.Event.Events;
import com.github.blitzsy.dwarvenproc.reference.Types.Potion.Potions;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;

import java.io.File;

public class ProxySideCommon implements ISidedProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        Configurations.configurationSettings = new ConfigurationSettings(new File(event.getModConfigurationDirectory(), "dwarvenproc.cfg"));
        Potions.dwarvenProcEffect = new DwarvenProcEffect();
        Events.entityCombatHandler = new EntityCombatHandler();
        Events.entityPotionHandler = new EntityPotionHandler();
        Wrappers.clientNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MOD_ID);

    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(Events.entityCombatHandler);
        MinecraftForge.EVENT_BUS.register(Events.entityPotionHandler);
        Wrappers.clientNetworkWrapper.registerMessage(ParticlePacketMessage.class, ParticlePacketMessage.class, 0, Side.CLIENT);
        ForgeRegistries.POTIONS.register(Potions.dwarvenProcEffect);
    }

    public void fmlLifeCycle(FMLServerStartingEvent event)
    {
        Handlers.dwarvenProcCommands = new DwarvenProcCommands();
        Handlers.killCountCommands = new KillCountCommands();

        event.registerServerCommand(Handlers.dwarvenProcCommands);
        event.registerServerCommand(Handlers.killCountCommands);
    }
}
