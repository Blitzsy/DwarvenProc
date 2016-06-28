package com.github.blitzsy.dwarvenproc.potion;

import com.github.blitzsy.dwarvenproc.reference.Types.Potion.Colors;
import com.github.blitzsy.dwarvenproc.reference.Types.Potion.EffectTypes;
import com.github.blitzsy.dwarvenproc.reference.Types.Potion.Names;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class DwarvenProcEffect extends Potion
{
    public DwarvenProcEffect()
    {
        super(EffectTypes.DWARVEN_PROC_EFFECT, Colors.DWARVEN_PROC_EFFECT);

        setPotionName(Names.DWARVEN_PROC_EFFECT);
        setIconIndex(4, 0);
        registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7394513f-a08c-44ff-8b38-6de1143e4cb5", 0.5D, 2);

    }
}
