package notamodder.notalib.utils;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public final class StackUtils {

    /**
     * Prepares an ItemStack's NBT with a blank tag.
     *
     * @param stack The ItemStack to prepare.
     * @return The passed ItemStack.
     */
    public static ItemStack prepareStack (@Nonnull ItemStack stack) {

        return prepareStack(stack, new NBTTagCompound());
    }

    /**
     * Prepares an ItemStack's NBT with the passed tag.
     *
     * @param stack The ItemStack to prepare.
     * @param tag The tag to prepare the stack with.
     * @return The passed stack.
     */
    public static ItemStack prepareStack (@Nonnull ItemStack stack, @Nonnull NBTTagCompound tag) {

        if (!stack.hasTagCompound())
            stack.setTagCompound(tag);

        return stack;
    }
}
