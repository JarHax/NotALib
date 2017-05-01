package notamodder.notalib.world.loot.conditions;

import java.util.Random;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import notamodder.notalib.NotALib;

public class BiomeSpecific implements LootCondition {

    private final Biome biome;

    public BiomeSpecific (Biome biome) {

        this.biome = biome;
    }

    @Override
    public boolean testCondition (Random rand, LootContext context) {

        return this.biome == context.getWorld().getBiome(context.getKiller().getPosition());
    }

    public static class Serializer extends LootCondition.Serializer<BiomeSpecific> {

        public Serializer () {

            super(new ResourceLocation(NotALib.MODID, "biome_specific"), BiomeSpecific.class);
        }

        @Override
        public void serialize (JsonObject json, BiomeSpecific value, JsonSerializationContext context) {

            json.add("biome_id", context.serialize(value.biome.getRegistryName().toString()));
        }

        @Override
        public BiomeSpecific deserialize (JsonObject json, JsonDeserializationContext context) {

            return new BiomeSpecific(ForgeRegistries.BIOMES.getValue(new ResourceLocation(context.deserialize(json.get("biome_id"), String.class))));
        }
    }
}
