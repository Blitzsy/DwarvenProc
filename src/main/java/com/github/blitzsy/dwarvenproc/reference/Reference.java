package com.github.blitzsy.dwarvenproc.reference;

public class Reference
{
    public static final class ModInfo
    {
        public static final String MOD_ID = "DwarvenProc";
        public static final String MOD_NAME = "Dwarven Proc";
        public static final String MOD_VERSION = "@MOD_VERSION";
        public static final String MOD_DEPENDENCIES = "required-after:Forge@[10.13.4.1558]";
    }

    public static final class SidedProxyInfo
    {
        public static final String PROXY_SIDE_CLIENT = "com.github.blitzsy.dwarvenproc.proxy.side.ProxySideClient";
        public static final String PROXY_SIDE_SERVER = "com.github.blitzsy.dwarvenproc.proxy.side.ProxySideCommon";
    }
}
