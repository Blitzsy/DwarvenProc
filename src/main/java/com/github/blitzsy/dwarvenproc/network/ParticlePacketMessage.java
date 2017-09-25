package com.github.blitzsy.dwarvenproc.network;

import com.github.blitzsy.dwarvenproc.util.VarUtils;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class ParticlePacketMessage implements IMessage, IMessageHandler<ParticlePacketMessage, IMessage>
{
    public String particleName;
    public double locationX;
    public double locationY;
    public double locationZ;
    public int particleAmount;

    public ParticlePacketMessage()
    {
        this("", 0.0d, 0.0d, 0.0d, 0);
    }

    public ParticlePacketMessage(String particleName, double locationX, double locationY, double locationZ, int particleAmount)
    {
        this.particleName = particleName;
        this.locationX = locationX;
        this.locationY = locationY;
        this.locationZ = locationZ;
        this.particleAmount = particleAmount;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        particleName = ByteBufUtils.readUTF8String(buf);
        locationX = buf.readDouble();
        locationY = buf.readDouble();
        locationZ = buf.readDouble();
        particleAmount = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, particleName);
        buf.writeDouble(locationX);
        buf.writeDouble(locationY);
        buf.writeDouble(locationZ);
        buf.writeInt(particleAmount);

    }

    @Override
    public IMessage onMessage(ParticlePacketMessage message, MessageContext ctx)
    {
        if (ctx.side == Side.CLIENT)
        {
            for (int i = 0; i < message.particleAmount; i++)
                Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.getByName(message.particleName), message.locationX + VarUtils.RANDOM.nextGaussian() * 1.0D, message.locationY + VarUtils.RANDOM.nextGaussian() * 1.5D, message.locationZ + VarUtils.RANDOM.nextGaussian() * 1.D, VarUtils.RANDOM.nextGaussian() * 0.05D, VarUtils.RANDOM.nextGaussian() * 0.05D, VarUtils.RANDOM.nextGaussian() * 0.05D);
        }
        return null;
    }
}
