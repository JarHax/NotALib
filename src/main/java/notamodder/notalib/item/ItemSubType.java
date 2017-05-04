package notamodder.notalib.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * A base class which handles making items with sub types.
 */
public abstract class ItemSubType extends Item implements IVariant {

    /**
     * Base constructor for this class.
     */
    public ItemSubType () {

        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata (int damage) {

        return damage;
    }

    @Override
    public String getUnlocalizedName (ItemStack stack) {

        final int meta = stack.getMetadata();
        final String[] variants = this.getVariant();
        return super.getUnlocalizedName() + "." + this.getPrefix().replace("_", ".") + (!(meta >= 0 && meta < variants.length) ? variants[0] : variants[meta]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems (Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {

        for (int meta = 0; meta < this.getVariant().length; meta++) {
            subItems.add(new ItemStack(this, 1, meta));
        }
    }
}
