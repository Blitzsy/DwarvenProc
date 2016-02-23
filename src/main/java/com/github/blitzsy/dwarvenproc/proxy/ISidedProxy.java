package com.github.blitzsy.dwarvenproc.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public interface ISidedProxy
{
    void preInit(FMLPreInitializationEvent event);
    void init(FMLInitializationEvent event);
    void fmlLifeCycle(FMLServerStartingEvent event);
}
