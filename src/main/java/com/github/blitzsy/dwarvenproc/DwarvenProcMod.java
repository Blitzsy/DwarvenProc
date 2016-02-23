package com.github.blitzsy.dwarvenproc;

import com.github.blitzsy.dwarvenproc.proxy.ISidedProxy;
import com.github.blitzsy.dwarvenproc.reference.Reference.SidedProxyInfo;
import com.github.blitzsy.dwarvenproc.reference.Reference.ModInfo;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.File;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, dependencies = ModInfo.MOD_DEPENDENCIES)
public class DwarvenProcMod
{
    @Mod.Instance(ModInfo.MOD_ID)
    public static DwarvenProcMod instance;

    @SidedProxy(clientSide = SidedProxyInfo.PROXY_SIDE_CLIENT, serverSide = SidedProxyInfo.PROXY_SIDE_SERVER)
    public static ISidedProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void fmlLifeCycle(FMLServerStartingEvent event)
    {
        proxy.fmlLifeCycle(event);
    }
}
