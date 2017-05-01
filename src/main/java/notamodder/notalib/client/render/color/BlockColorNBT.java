package notamodder.notalib.client.render.color;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import notamodder.notalib.lib.MCColor;

/**
 * This implementation of IBlockColor provides reusable color handling for blocks which store
 * an MCColor on their tile entity nbt.
 */
public class BlockColorNBT implements IBlockColor {

    /**
     * A reusable instance of this color handler.
     */
    public static final IBlockColor INSTANCE = new BlockColorNBT();

    @Override
    public int colorMultiplier (IBlockState state, IBlockAccess worldIn, BlockPos pos, int pass) {

        final TileEntity tile = worldIn.getTileEntity(pos);
        final NBTTagCompound tag = tile != null ? tile.writeToNBT(new NBTTagCompound()) : new NBTTagCompound();
        return MCColor.isAcceptable(tag) ? new MCColor(tag).getRGB() : MCColor.DYE_WHITE.getRGB();
    }
}