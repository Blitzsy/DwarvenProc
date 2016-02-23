package com.github.blitzsy.dwarvenproc.potion;

import com.github.blitzsy.dwarvenproc.reference.Settings.Config;
import com.github.blitzsy.dwarvenproc.reference.Types.Potion.Colors;
import com.github.blitzsy.dwarvenproc.reference.Types.Potion.EffectTypes;
import com.github.blitzsy.dwarvenproc.reference.Types.Potion.Names;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;

public class DwarvenProcEffect extends Potion
{
    public DwarvenProcEffect()
    {
        super(Config.dwarvenProcPotionID, EffectTypes.DWARVEN_PROC_EFFECT, Colors.DWARVEN_PROC_EFFECT);

        setPotionName(Names.DWARVEN_PROC_EFFECT);
        setIconIndex(4, 0);
        func_111184_a(SharedMonsterAttributes.movementSpeed, "7394513f-a08c-44ff-8b38-6de1143e4cb5", 0.5D, 2);
    }

    public Potion setIconIndex(int par1, int par2)
    {
        super.setIconIndex(par1, par2);
        return this;
    }
}
