package notamodder.notalib;

import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import notamodder.notalib.proxy.CommonProxy;
import notamodder.notalib.world.loot.conditions.BiomeSpecific;
import notamodder.notalib.world.loot.functions.SetColor;

@Mod(modid = NotALib.MODID, name = NotALib.NAME, version = "@VERSION@")
public class NotALib {

    public static final String MODID = "notalib";
    public static final String NAME = "Not A Lib";

    @Mod.Instance(MODID)
    public static NotALib INSTANCE;

    @SidedProxy(clientSide = "notamodder.notalib.proxy.ClientProxy", serverSide = "notamodder.notalib.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event) {

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init (FMLInitializationEvent event) {

        proxy.init(event);

        // TODO relocate
        LootFunctionManager.registerFunction(new SetColor.Serializer());
        LootConditionManager.registerCondition(new BiomeSpecific.Serializer());
    }

    @Mod.EventHandler
    public void postInit (FMLPostInitializationEvent event) {

        proxy.postInit(event);
    }
}
