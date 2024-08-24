package net.minecraft.command.server;

import java.util.EnumSet;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import Rename.Class351;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class CommandTeleport
extends CommandBase {
    @Override
    public String getCommandName() {
        return "tp";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.tp.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Entity entity;
        if (args.length < 1) {
            throw new WrongUsageException("commands.tp.usage", new Object[0]);
        }
        int i = 0;
        if (args.length != 2 && args.length != 4 && args.length != 6) {
            entity = CommandTeleport.getCommandSenderAsPlayer(sender);
        } else {
            entity = CommandTeleport.getEntity(sender, args[0]);
            i = 1;
        }
        if (args.length != 1 && args.length != 2) {
            if (args.length < i + 3) {
                throw new WrongUsageException("commands.tp.usage", new Object[0]);
            }
            if (entity.worldObj != null) {
                int lvt_5_2_ = i + 1;
                CommandBase.CoordinateArg commandbase$coordinatearg = CommandTeleport.parseCoordinate(entity.posX, args[i], true);
                CommandBase.CoordinateArg commandbase$coordinatearg1 = CommandTeleport.parseCoordinate(entity.posY, args[lvt_5_2_++], 0, 0, false);
                CommandBase.CoordinateArg commandbase$coordinatearg2 = CommandTeleport.parseCoordinate(entity.posZ, args[lvt_5_2_++], true);
                CommandBase.CoordinateArg commandbase$coordinatearg3 = CommandTeleport.parseCoordinate(entity.rotationYaw, args.length > lvt_5_2_ ? args[lvt_5_2_++] : "~", false);
                CommandBase.CoordinateArg commandbase$coordinatearg4 = CommandTeleport.parseCoordinate(entity.rotationPitch, args.length > lvt_5_2_ ? args[lvt_5_2_] : "~", false);
                if (entity instanceof EntityPlayerMP) {
                    EnumSet<Class351.EnumFlags> set = EnumSet.noneOf(Class351.EnumFlags.class);
                    if (commandbase$coordinatearg.func_179630_c()) {
                        set.add(Class351.EnumFlags.X);
                    }
                    if (commandbase$coordinatearg1.func_179630_c()) {
                        set.add(Class351.EnumFlags.Y);
                    }
                    if (commandbase$coordinatearg2.func_179630_c()) {
                        set.add(Class351.EnumFlags.Z);
                    }
                    if (commandbase$coordinatearg4.func_179630_c()) {
                        set.add(Class351.EnumFlags.X_ROT);
                    }
                    if (commandbase$coordinatearg3.func_179630_c()) {
                        set.add(Class351.EnumFlags.Y_ROT);
                    }
                    float f = (float)commandbase$coordinatearg3.func_179629_b();
                    if (!commandbase$coordinatearg3.func_179630_c()) {
                        f = MathHelper.wrapAngleTo180_float(f);
                    }
                    float f1 = (float)commandbase$coordinatearg4.func_179629_b();
                    if (!commandbase$coordinatearg4.func_179630_c()) {
                        f1 = MathHelper.wrapAngleTo180_float(f1);
                    }
                    if (f1 > 90.0f || f1 < -90.0f) {
                        f1 = MathHelper.wrapAngleTo180_float(180.0f - f1);
                        f = MathHelper.wrapAngleTo180_float(f + 180.0f);
                    }
                    entity.mountEntity(null);
                    ((EntityPlayerMP)entity).playerNetServerHandler.setPlayerLocation(commandbase$coordinatearg.func_179629_b(), commandbase$coordinatearg1.func_179629_b(), commandbase$coordinatearg2.func_179629_b(), f, f1, set);
                    entity.setRotationYawHead(f);
                } else {
                    float f2 = (float)MathHelper.wrapAngleTo180_double(commandbase$coordinatearg3.func_179628_a());
                    float f3 = (float)MathHelper.wrapAngleTo180_double(commandbase$coordinatearg4.func_179628_a());
                    if (f3 > 90.0f || f3 < -90.0f) {
                        f3 = MathHelper.wrapAngleTo180_float(180.0f - f3);
                        f2 = MathHelper.wrapAngleTo180_float(f2 + 180.0f);
                    }
                    entity.setLocationAndAngles(commandbase$coordinatearg.func_179628_a(), commandbase$coordinatearg1.func_179628_a(), commandbase$coordinatearg2.func_179628_a(), f2, f3);
                    entity.setRotationYawHead(f2);
                }
                CommandTeleport.notifyOperators(sender, (ICommand)this, "commands.tp.success.coordinates", entity.getName(), commandbase$coordinatearg.func_179628_a(), commandbase$coordinatearg1.func_179628_a(), commandbase$coordinatearg2.func_179628_a());
            }
        } else {
            Entity entity1 = CommandTeleport.getEntity(sender, args[args.length - 1]);
            if (entity1.worldObj != entity.worldObj) {
                throw new CommandException("commands.tp.notSameDimension", new Object[0]);
            }
            entity.mountEntity(null);
            if (entity instanceof EntityPlayerMP) {
                ((EntityPlayerMP)entity).playerNetServerHandler.setPlayerLocation(entity1.posX, entity1.posY, entity1.posZ, entity1.rotationYaw, entity1.rotationPitch);
            } else {
                entity.setLocationAndAngles(entity1.posX, entity1.posY, entity1.posZ, entity1.rotationYaw, entity1.rotationPitch);
            }
            CommandTeleport.notifyOperators(sender, (ICommand)this, "commands.tp.success", entity.getName(), entity1.getName());
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return args.length != 1 && args.length != 2 ? null : CommandTeleport.getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return index == 0;
    }
}

