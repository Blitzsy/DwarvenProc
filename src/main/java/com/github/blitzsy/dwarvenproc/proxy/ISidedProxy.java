package com.github.blitzsy.dwarvenproc.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public interface ISidedProxy
{
    void preInit(FMLPreInitializationEvent event);
    void init(FMLInitializationEvent event);
    void fmlLifeCycle(FMLServerStartingEvent event);
}
