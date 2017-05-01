package notamodder.notalib;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import notamodder.notalib.proxy.CommonProxy;

@Mod(modid = NotALib.MODID, name = NotALib.NAME, version = NotALib.VERSION)
public class NotALib {

    public static final String MODID = "notalib";
    public static final String NAME = "Not A Lib";
    public static final String VERSION = "1.0.0.0";

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
    }

    @Mod.EventHandler
    public void postInit (FMLPostInitializationEvent event) {

        proxy.postInit(event);
    }
}
