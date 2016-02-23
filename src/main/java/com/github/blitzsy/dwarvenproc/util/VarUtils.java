package com.github.blitzsy.dwarvenproc.util;

import java.util.Random;
import java.util.regex.Pattern;

public class VarUtils
{
    public static final Random RANDOM = new Random();
    private static final Pattern patternControlCode = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

    public static String stripControlCodes(String str)
    {
        return patternControlCode.matcher(str).replaceAll("");
    }
}
