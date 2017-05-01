package notamodder.notalib.lib;

import java.awt.Color;
import java.util.Random;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * This class is an extension of the AWT Color class. It adds several things which make working
 * with color in the MC environment less of a hassle.
 */
public class MCColor extends Color {

    /**
     * A serialization ID.
     */
    private static final long serialVersionUID = -6408679408589215496L;

    /**
     * The color of black dye.
     */
    public static final MCColor DYE_BLACK = new MCColor(25, 25, 25);

    /**
     * The color of red dye.
     */
    public static final MCColor DYE_RED = new MCColor(153, 51, 51);

    /**
     * The color of green dye.
     */
    public static final MCColor DYE_GREEN = new MCColor(102, 127, 51);

    /**
     * The color of brown dye.
     */
    public static final MCColor DYE_BROWN = new MCColor(102, 76, 51);

    /**
     * The color of blue dye.
     */
    public static final MCColor DYE_BLUE = new MCColor(51, 76, 178);

    /**
     * The color of purple dye.
     */
    public static final MCColor DYE_PURPLE = new MCColor(127, 63, 178);

    /**
     * The color of cyan dye.
     */
    public static final MCColor DYE_CYAN = new MCColor(76, 127, 153);

    /**
     * The color of light gray dye.
     */
    public static final MCColor DYE_LIGHT_GRAY = new MCColor(153, 153, 153);

    /**
     * The color of gray dye.
     */
    public static final MCColor DYE_GRAY = new MCColor(76, 76, 76);

    /**
     * The color of pink dye.
     */
    public static final MCColor DYE_PINK = new MCColor(242, 127, 165);

    /**
     * The color of lime dye.
     */
    public static final MCColor DYE_LIME = new MCColor(127, 204, 25);

    /**
     * The color of yellow dye.
     */
    public static final MCColor DYE_YELLOW = new MCColor(229, 229, 51);

    /**
     * The color of blue dye.
     */
    public static final MCColor DYE_LIGHT_BLUE = new MCColor(102, 153, 216);

    /**
     * The color of magenta dye.
     */
    public static final MCColor DYE_MAGENTA = new MCColor(178, 76, 216);

    /**
     * The color of orange dye.
     */
    public static final MCColor DYE_ORANGE = new MCColor(216, 127, 5);

    /**
     * The color of white dye.
     */
    public static final MCColor DYE_WHITE = new MCColor(255, 255, 255);

    /**
     * Constructs an MCColor from an ItemStack. Expects the stack to have already been checked
     * for validity.
     *
     * @param stack The ItemStack to construct a color from.
     */
    public MCColor (@Nonnull ItemStack stack) {

        this(stack.getTagCompound());
    }

    /**
     * Constructs an MCColor from an NBTTagCompound. Expects the tag to have already been
     * checked for validity.
     *
     * @param tag The NBTTagCompound to construct a color from.
     */
    public MCColor (@Nonnull NBTTagCompound tag) {

        this(tag.getIntArray("Color"));
    }

    /**
     * Constructs an MCColor from an array of color components. Expects the array to have
     * already been checked for validity.
     *
     * @param colors The array to construct a color from.
     */
    public MCColor (@Nonnull int[] colors) {

        this(colors[0], colors[1], colors[2]);
    }

    /**
     * Constructs an MCColor from raw color components.
     *
     * @param red The red component as an integer from 0 to 255.
     * @param green The green component as an integer from 0 to 255.
     * @param blue The blue component as an integer from 0 to 255.
     */
    public MCColor (int red, int green, int blue) {

        super(red, green, blue);
    }

    /**
     * Writes the color object's data to the ItemStack's NBTTagCompound.
     *
     * @param stack The ItemStack to write the color data to.
     */
    public void writeToStack (@Nonnull ItemStack stack) {

        this.writeToNBT(stack.getTagCompound());
    }

    /**
     * Writes the color object's data to the NBTTagCompound.
     *
     * @param tag The NBTTagCompound to write the color data to.
     */
    public void writeToNBT (@Nonnull NBTTagCompound tag) {

        tag.setIntArray("Color", new int[] { this.getRed(), this.getGreen(), this.getBlue() });
    }

    /**
     * Gets the components as an integer array.
     *
     * @return The components as an integer array.
     */
    public int[] getComponents () {

        return new int[] { this.getRed(), this.getGreen(), this.getBlue() };
    }

    /**
     * Creates a random MCColor.
     *
     * @param rand An instance of Random.
     * @return A random MCColor.
     */
    public static MCColor getRandomColor (@Nonnull Random rand) {

        return new MCColor(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    /**
     * Checks if an ItemStack is acceptable. For an ItemStack to be acceptable, it must not be
     * null or empty, and must have an NBTTagCompound which is deemed acceptable by
     * {@link #isAcceptable(NBTTagCompound)}.
     *
     * @param stack The ItemStack to check.
     * @return Whether or not the ItemStack was acceptable.
     */
    public static boolean isAcceptable (@Nonnull ItemStack stack) {

        return !stack.isEmpty() && stack.hasTagCompound() && isAcceptable(stack.getTagCompound());
    }

    /**
     * Checks if a NBTTagCompound is acceptable. For an NBTTagCompound to be acceptable, it
     * must not be null, and must have an integer array named Color with 3 elements.
     *
     * @param tag The NBTTagCompound to check.
     * @return Whether or not the ItemStack was acceptable.
     */
    public static boolean isAcceptable (@Nonnull NBTTagCompound tag) {

        return tag.hasKey("Color") && tag.getIntArray("Color").length == 3;
    }
}