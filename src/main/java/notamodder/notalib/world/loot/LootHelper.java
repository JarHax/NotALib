package notamodder.notalib.world.loot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Mods can create new instances of this class, and use it to handle additions to the loot
 * table. It will handle injecting data to the loot tables on world load, and also provides a
 * per-mod cache of entries.
 */
@EventBusSubscriber
public class LootHelper {

    /**
     * A map of all entries that have been added. This is injected into the loot tables using
     * {@link net.minecraftforge.event.LootTableLoadEvent}.
     */
    private static final Multimap<ResourceLocation, LootBuilder> entries = HashMultimap.create();

    /**
     * A local map of all the entires that have been added. This is on a per instance basis,
     * used to get mod-specific entries.
     */
    private final Map<ResourceLocation, LootBuilder> localEntries = new HashMap<>();

    /**
     * The id of the mod the loot helper instance belongs to.
     */
    private final String modid;

    /**
     * Constructs a new LootHelper for the specified mod id. Multiple helpers can exist with
     * the same id, but it's not recommended.
     *
     * @param modid The modid for the registry helper.
     */
    public LootHelper (@Nonnull String modid) {

        this.modid = modid;
    }

    /**
     * Creates a new loot entry that will be added to the loot pools when a world is loaded.
     *
     * @param location The loot table to add the loot to. You can use
     *        {@link net.minecraft.world.storage.loot.LootTableList} for convenience.
     * @param name The name of the entry being added. This will be prefixed with
     *        {@link #modid}.
     * @param pool The name of the pool to add the entry to. This pool must already exist.
     * @param weight The weight of the entry.
     * @param item The item to add.
     * @return A builder object. It can be used to fine tune the loot entry.
     */
    public LootBuilder addLoot (ResourceLocation location, String name, String pool, int weight, Item item) {

        return this.addLoot(location, new LootBuilder(this.modid + ":" + name, pool, weight, item));
    }

    /**
     * Creates a new loot entry that will be added to the loot pools when a world is loaded.
     *
     * @param location The loot table to add the loot to. You can use
     *        {@link net.minecraft.world.storage.loot.LootTableList} for convenience.
     * @param name The name of the entry being added. This will be prefixed with
     *        {@link #modid}.
     * @param pool The name of the pool to add the entry to. This pool must already exist.
     * @param weight The weight of the entry.
     * @param quality The quality of the entry. Quality is an optional value which modifies the
     *        weight of an entry based on the player's luck level. totalWeight = weight +
     *        (quality * luck)
     * @param item The item to add.
     * @param conditions A list of loot conditions.
     * @param functions A list of loot functions.
     * @return A builder object. It can be used to fine tune the loot entry.
     */
    public LootBuilder addLoot (ResourceLocation location, String name, String pool, int weight, int quality, Item item, List<LootCondition> conditions, List<LootFunction> functions) {

        return this.addLoot(location, new LootBuilder(this.modid + ":" + name, pool, weight, quality, item, conditions, functions));
    }

    /**
     * Creates a new loot entry that will be added to the loot pools when a world is loaded.
     *
     * @param location The loot table to add the loot to. You can use
     *        {@link net.minecraft.world.storage.loot.LootTableList} for convenience.
     * @param builder The loot builder to add.
     * @return A builder object. It can be used to fine tune the loot entry.
     */
    public LootBuilder addLoot (ResourceLocation location, LootBuilder builder) {

        entries.put(location, builder);
        this.localEntries.put(location, builder);
        return builder;
    }

    /**
     * This is an event hook which is used to inject the loot data to loot tables. This class
     * is registered with the event bus using the
     * {@link net.minecraftforge.fml.common.Mod.EventBusSubscriber} class level annotation.
     * Don't call or use this method yourself!
     *
     * @param event The event instance.
     */
    @SubscribeEvent
    public void onTableLoaded (LootTableLoadEvent event) {

        for (final LootBuilder builder : entries.get(event.getName())) {

            final LootPool pool = event.getTable().getPool(builder.getPool());

            if (pool != null)
                pool.addEntry(builder.build());
        }
    }
}