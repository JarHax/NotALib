package notamodder.notalib.lib;

import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * A client side only class, which contains references to reusable color handlers for items and
 * blocks.
 */
@SideOnly(Side.CLIENT)
public class ColorHandlers {

    /**
     * A reusable color handler which uses the hash of
     * {@link net.minecraft.item.ItemStack#getDisplayName()}.
     */
    public static IItemColor ITEM_DISPLAY_NAME = (stack, index) -> stack.getDisplayName().hashCode();

    /**
     * A reusable color handler which uses the hash of
     * {@link net.minecraft.item.ItemStack#getUnlocalizedName()}.
     */
    public static IItemColor ITEM_UNLOCALIZED_NAME = (stack, index) -> stack.getUnlocalizedName().hashCode();

    /**
     * A reusable color handler which uses the hash of the
     * {@link net.minecraft.item.ItemStack#toString()}.
     */
    public static IItemColor ITEM_TO_STRING = (stack, index) -> stack.toString().hashCode();

    /**
     * A reusable color handler which uses the hash of the
     * {@link net.minecraft.item.Item#getRegistryName()}.
     */
    public static IItemColor ITEM_IDENTIFIER = (stack, index) -> stack.getItem().getRegistryName().toString().hashCode();

    /**
     * A reusable color handler which uses the hash of the
     * {@link net.minecraft.nbt.NBTTagCompound#toString()}.
     */
    public static IItemColor ITEM_NBT = (stack, index) -> stack.getTagCompound().toString().hashCode();

    /**
     * A reusable color handler which uses a MCColor which is read from
     * {@link net.minecraft.item.ItemStack#getTagCompound()};
     */
    public static IItemColor ITEM_MCCOLOR = (stack, index) -> MCColor.isAcceptable(stack) ? new MCColor(stack).getRGB() : MCColor.DYE_WHITE.getRGB();

    /**
     * A reusable color handler which uses a MCColor which is read from
     * {@link net.minecraft.tileentity.TileEntity#getTileData()}.
     */
    public static IBlockColor BLOCK_MCCOLOR = (state, world, pos, index) -> MCColor.isAcceptable(world, pos) ? new MCColor(world, pos).getRGB() : MCColor.DYE_WHITE.getRGB();
}
