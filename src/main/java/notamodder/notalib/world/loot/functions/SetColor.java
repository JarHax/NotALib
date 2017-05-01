package notamodder.notalib.world.loot.functions;

import java.util.Random;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import notamodder.notalib.NotALib;
import notamodder.notalib.lib.MCColor;
import notamodder.notalib.utils.StackUtils;

public class SetColor extends LootFunction {

    /**
     * The color to apply. If null, the color will be random.
     */
    private final MCColor color;

    /**
     * Constructs a new LootFunctionColor which uses a random color when applied.
     *
     * @param conditions The traditional loot conditions.
     */
    public SetColor (LootCondition[] conditions) {

        this(null, conditions);
    }

    /**
     * Constructs a new LootFunctionColor which uses the specified color when applied.
     *
     * @param color The color to use.
     * @param conditions The traditional loot conditions.
     */
    protected SetColor (MCColor color, LootCondition[] conditions) {

        super(conditions);
        this.color = color;
    }

    @Override
    public ItemStack apply (ItemStack stack, Random rand, LootContext context) {

        final MCColor colorToApply = this.color == null ? MCColor.getRandomColor(rand) : this.color;
        colorToApply.writeToStack(StackUtils.prepareStack(stack));
        return stack;
    }

    public static class Serializer extends LootFunction.Serializer<SetColor> {

        public Serializer () {

            super(new ResourceLocation(NotALib.MODID, "set_color"), SetColor.class);
        }

        @Override
        public void serialize (JsonObject object, SetColor function, JsonSerializationContext serializationContext) {

            object.add("color", serializationContext.serialize(function.color.getComponents()));
        }

        @Override
        public SetColor deserialize (JsonObject object, JsonDeserializationContext deserializationContext, LootCondition[] conditionsIn) {

            return new SetColor(new MCColor((int[]) deserializationContext.deserialize(object.get("color"), int[].class)), conditionsIn);
        }
    }
}