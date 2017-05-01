package notamodder.notalib.client.render.color;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import notamodder.notalib.lib.MCColor;

/**
 * This implementation of IItemColor provides reusable color handling for items which store an
 * MCColor on their stack nbt.
 */
public class ItemColorNBT implements IItemColor {

    /**
     * A reusable instance of this color handler.
     */
    public static final IItemColor INSTANCE = new ItemColorNBT();

    @Override
    public int getColorFromItemstack (ItemStack stack, int pass) {

        return MCColor.isAcceptable(stack) ? new MCColor(stack).getRGB() : MCColor.DYE_WHITE.getRGB();
    }
}
