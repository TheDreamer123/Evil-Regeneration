package net.dreamer.evilregeneration.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.AnimatedParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class TotemOfDyingParticle extends AnimatedParticle {
    TotemOfDyingParticle(ClientWorld world,double x,double y,double z,double velocityX,double velocityY,double velocityZ,SpriteProvider spriteProvider) {
        super(world, x, y, z, spriteProvider, 1.25F);
        this.velocityMultiplier = 0.6F;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.scale *= 0.75F;
        this.maxAge = 60 + this.random.nextInt(12);
        this.setSpriteForAge(spriteProvider);
        if (this.random.nextInt(4) == 0)
            this.setColor(0.9F + this.random.nextFloat() * 0.2F, 0.3F + this.random.nextFloat() * 0.2F, 0.1F + this.random.nextFloat() * 0.2F);
        else
            this.setColor(0.7F + this.random.nextFloat() * 0.2F, 0.0F + this.random.nextFloat() * 0.2F, 0.1F + this.random.nextFloat() * 0.2F);
    }

    @Environment(EnvType.CLIENT)
    public record Factory(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {

        public Particle createParticle(DefaultParticleType defaultParticleType,ClientWorld clientWorld,double d,double e,double f,double g,double h,double i) {
            return new TotemOfDyingParticle(clientWorld,d,e,f,g,h,i,this.spriteProvider);
        }
    }
}