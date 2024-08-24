package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Objects;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
@Renamer
@StringEncryption
public class Class319
extends Class252 {
    private final Class257 killEffectValue = new Class257("Class319", "Squid", "LightningBolt", "Totem", "Flame", "Smoke", "Love", "Blood", "Off", "Squid");
    private final Class257 killSoundValue = new Class257("KillSound", "Squid", "Off", "Squid", "MNSJ");
    private final Class254 autoL = new Class254("Class266", false);
    private final Class471 animations = new Class471();
    private EntityLivingBase target;
    private EntitySquid squid;
    private double percent = 0.0;
    private int kills = 0;

    public Class319() {
        super("Class319", Class263.Render);
    }

    public static float nextFloat(float startInclusive, float endInclusive) {
        if (startInclusive == endInclusive || endInclusive - startInclusive <= 0.0f) {
            return startInclusive;
        }
        return (float)((double)startInclusive + (double)(endInclusive - startInclusive) * Math.random());
    }

    public double easeInOutCirc(double x) {
        return x < 0.5 ? (1.0 - Math.sqrt(1.0 - Math.pow(2.0 * x, 2.0))) / 2.0 : (Math.sqrt(1.0 - Math.pow(-2.0 * x + 2.0, 2.0)) + 1.0) / 2.0;
    }

    @Class14
    public void onUpdate(Class50 event) {
        if (this.killEffectValue.is("Squid") && this.squid != null) {
            if (Class319.mc.theWorld.loadedEntityList.contains(this.squid)) {
                if (this.percent < 1.0) {
                    this.percent += Math.random() * 0.048;
                }
                if (this.percent >= 1.0) {
                    this.percent = 0.0;
                    for (int i = 0; i <= 8; ++i) {
                        Class319.mc.effectRenderer.emitParticleAtEntity(this.squid, EnumParticleTypes.FLAME);
                    }
                    Class319.mc.theWorld.removeEntity(this.squid);
                    this.squid = null;
                    return;
                }
            } else {
                this.percent = 0.0;
            }
            double easeInOutCirc = this.easeInOutCirc(1.0 - this.percent);
            this.animations.animate((float)easeInOutCirc, 450);
            this.squid.setPositionAndUpdate(this.squid.posX, this.squid.posY + (double)this.animations.getOutput() * 0.9, this.squid.posZ);
        }
        if (this.squid != null && this.killEffectValue.is("Squid")) {
            this.squid.squidPitch = 0.0f;
            this.squid.prevSquidPitch = 0.0f;
            this.squid.squidYaw = 0.0f;
            this.squid.squidRotation = 90.0f;
        }
        if (this.target != null && this.target.getHealth() <= 0.0f && !Class319.mc.theWorld.loadedEntityList.contains(this.target)) {
            if (this.autoL.isEnabled()) {
                Class319.mc.thePlayer.sendChatMessage(Class266.getRandomText());
            }
            if (this.killSoundValue.is("MNSJ")) {
                ++this.kills;
                this.playSound(this.getSoundType(), 0.6f);
            }
            if (this.killSoundValue.is("Squid")) {
                this.playSound(SoundType.MTC, 0.6f);
            }
            if (this.killEffectValue.is("LightningBolt")) {
                EntityLightningBolt entityLightningBolt = new EntityLightningBolt(Class319.mc.theWorld, this.target.posX, this.target.posY, this.target.posZ);
                Class319.mc.theWorld.addEntityToWorld((int)(-Math.random() * 100000.0), entityLightningBolt);
                Class319.mc.theWorld.playSound(Class319.mc.thePlayer.posX, Class319.mc.thePlayer.posY, Class319.mc.thePlayer.posZ, "ambient.weather.thunder", 1.0f, 1.0f, false);
                Class319.mc.theWorld.playSound(Class319.mc.thePlayer.posX, Class319.mc.thePlayer.posY, Class319.mc.thePlayer.posZ, "random.explode", 1.0f, 1.0f, false);
            }
            if (this.killEffectValue.is("Squid")) {
                this.squid = new EntitySquid(Class319.mc.theWorld);
                Class319.mc.theWorld.addEntityToWorld(-8, this.squid);
                this.squid.setPosition(this.target.posX, this.target.posY, this.target.posZ);
            }
            this.target = null;
        }
        if (this.target != null && this.target.getHealth() <= 0.0f) {
            switch (this.killEffectValue.getMode()) {
                case "Totem": {
                    this.triggerTotemEffect((EntityPlayer)this.target);
                    this.target = null;
                    break;
                }
                case "Flame": {
                    Class319.mc.effectRenderer.emitParticleAtEntity(this.target, EnumParticleTypes.FLAME);
                    this.target = null;
                    break;
                }
                case "Smoke": {
                    Class319.mc.effectRenderer.emitParticleAtEntity(this.target, EnumParticleTypes.SMOKE_LARGE);
                    this.target = null;
                    break;
                }
                case "Water": {
                    Class319.mc.effectRenderer.emitParticleAtEntity(this.target, EnumParticleTypes.WATER_DROP);
                    this.target = null;
                    break;
                }
                case "Love": {
                    Class319.mc.effectRenderer.emitParticleAtEntity(this.target, EnumParticleTypes.HEART);
                    Class319.mc.effectRenderer.emitParticleAtEntity(this.target, EnumParticleTypes.WATER_DROP);
                    this.target = null;
                    break;
                }
                case "Blood": {
                    for (int i = 0; i < 10; ++i) {
                        Class319.mc.effectRenderer.spawnEffectParticle(EnumParticleTypes.BLOCK_CRACK.getParticleID(), this.target.posX, this.target.posY + (double)(this.target.height / 2.0f), this.target.posZ, this.target.motionX + (double) Class319.nextFloat(-0.5f, 0.5f), this.target.motionY + (double) Class319.nextFloat(-0.5f, 0.5f), this.target.motionZ + (double) Class319.nextFloat(-0.5f, 0.5f), Block.getStateId(Blocks.redstone_block.getDefaultState()));
                    }
                    this.target = null;
                }
            }
        }
    }

    private void triggerTotemEffect(EntityPlayer player) {
        for (int i = 0; i < 100; ++i) {
            double offsetX = player.worldObj.rand.nextGaussian() * 0.02;
            double offsetY = player.worldObj.rand.nextGaussian() * 0.02;
            double offsetZ = player.worldObj.rand.nextGaussian() * 0.02;
            player.worldObj.spawnParticle(EnumParticleTypes.SPELL, player.posX + (player.worldObj.rand.nextDouble() - 0.5) * (double)player.width, player.posY + player.worldObj.rand.nextDouble() * (double)player.height - 0.25, player.posZ + (player.worldObj.rand.nextDouble() - 0.5) * (double)player.width, offsetX, offsetY, offsetZ);
        }
    }

    private SoundType getSoundType() {
        switch (this.kills) {
            case 1: {
                return SoundType.KILL;
            }
            case 2: {
                return SoundType.Two_KILL;
            }
            case 3: {
                return SoundType.Three_KILL;
            }
            case 4: {
                return SoundType.Four_KILL;
            }
            case 5: {
                return SoundType.Five_KILL;
            }
        }
        if (this.kills >= 6) {
            return SoundType.Five_KILL;
        }
        return SoundType.KILL;
    }

    public void playSound(SoundType st, float volume) {
        new Thread(() -> {
            try {
                AudioInputStream as = AudioSystem.getAudioInputStream(new BufferedInputStream(Objects.requireNonNull(this.getClass().getResourceAsStream("/assets/minecraft/bloodline/sound/" + st.getName()))));
                Clip clip = AudioSystem.getClip();
                clip.open(as);
                clip.start();
                FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(volume);
                clip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Class14
    public void onWorld(Class55 event) {
        this.kills = 0;
    }

    @Class14
    public void onAttack(Class41 event) {
        if (event.getTarget() != null && !(event.getTarget() instanceof EntityTNTPrimed)) {
            this.target = (EntityLivingBase)event.getTarget();
        }
    }

    public enum SoundType {
        MTC("kills.wav"),
        KILL("kill.wav"),
        Two_KILL("two_kill.wav"),
        Three_KILL("three_kill.wav"),
        Four_KILL("four_kill.wav"),
        Five_KILL("five_kill.wav");

        final String music;

        SoundType(String fileName) {
            this.music = fileName;
        }

        String getName() {
            return this.music;
        }
    }
}

