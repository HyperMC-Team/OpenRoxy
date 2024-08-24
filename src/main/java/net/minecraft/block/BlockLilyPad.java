package net.minecraft.block;

import java.util.List;
import Rename.Class2;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLilyPad
extends BlockBush {
    protected BlockLilyPad() {
        float f = 0.5f;
        float f1 = 0.015625f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, f1, 0.5f + f);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity) {
        if (collidingEntity == null || !(collidingEntity instanceof EntityBoat)) {
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
        return new AxisAlignedBB((double)pos.getX() + Class2.Method4(this.minX), (double)pos.getY() + Class2.Method5(this.minY), (double)pos.getZ() + Class2.Method6(this.minZ), (double)pos.getX() + Class2.Method7(this.maxX), (double)pos.getY() + Class2.Method8(this.maxY), (double)pos.getZ() + Class2.Method9(this.maxZ));
    }

    @Override
    public int getBlockColor() {
        return 7455580;
    }

    @Override
    public int getRenderColor(IBlockState state) {
        return 7455580;
    }

    @Override
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
        return 2129968;
    }

    @Override
    protected boolean canPlaceBlockOn(Block ground) {
        return ground == Blocks.water;
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        if (pos.getY() >= 0 && pos.getY() < 256) {
            IBlockState iblockstate = worldIn.getBlockState(pos.down());
            return iblockstate.getBlock().getMaterial() == Material.water && iblockstate.getValue(BlockLiquid.LEVEL) == 0;
        }
        return false;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }
}

