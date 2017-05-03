package notamodder.notalib.utils;

import javax.annotation.Nonnull;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

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

        if (!stack.hasTagCompound()) {
            stack.setTagCompound(tag);
        }

        return stack;
    }

    /**
     * Checks if two stacks are similar enough for crafting. For this to be true, both items
     * must be empty, or both items must share the same item and meta, or both items must share
     * the same item, and one of them must have a wildcard meta.
     *
     * @param stack1 The first stack.
     * @param stack2 The second stack.
     * @return Whether or not the two stacks are similar enough for crafting.
     */
    public static boolean areStacksSimilar (@Nonnull ItemStack stack1, @Nonnull ItemStack stack2) {

        return stack1.isEmpty() ? stack2.isEmpty() : stack1.getItem() == stack2.getItem() && (stack1.getMetadata() == OreDictionary.WILDCARD_VALUE || stack2.getMetadata() == OreDictionary.WILDCARD_VALUE || stack1.getMetadata() == stack2.getMetadata());
    }

    /**
     * Creates an ItemStack which represents an IBlockState.
     *
     * @param state The IBlockState to create.
     * @param size The amount of items to be placed in the stack.
     * @return An ItemStack which represents the passed IBlockState.
     */
    public static ItemStack getStateStack (IBlockState state, int size) {

        return new ItemStack(state.getBlock(), size, state.getBlock().getMetaFromState(state));
    }
}
