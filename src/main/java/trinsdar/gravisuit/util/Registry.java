package trinsdar.gravisuit.util;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import trinsdar.gravisuit.items.ItemComponents;
import trinsdar.gravisuit.items.tools.ItemRelocator;
import trinsdar.gravisuit.items.tools.ItemToolGravitool;
import trinsdar.gravisuit.items.tools.ItemToolVajra;

import java.util.Map;

public class Registry {
    public static final Map<Identifier, Item> REGISTRY = new Object2ObjectArrayMap<>();
    /*private static ItemArmorAdvancedElectricJetpack advancedElectricJetpack;
    private static ItemArmorAdvancedNuclearJetpack advancedNuclearJetpack;
    public static final ItemArmorAdvancedNanoChestplate advancedNanoChestplate = new ItemArmorAdvancedNanoChestplate(new ItemArmorAdvancedElectricJetpack(false), "advancedNanoChestplate", GravisuitLang.advancedNanoChestplate, "advanced_nano_chestplate", 15);
    public static final ItemArmorAdvancedNuclearNanoChestplate advancedNuclearNanoChestplate = new ItemArmorAdvancedNuclearNanoChestplate();
    public static final ItemArmorGravisuit gravisuit = new ItemArmorGravisuit();
    public static final ItemArmorNuclearGravisuit nuclearGravisuit = new ItemArmorNuclearGravisuit();
    private static ItemArmorAdvancedLappack advancedLappack;
    private static ItemArmorAdvancedLappack ultimateLappack;*/
    public static final ItemToolGravitool GRAVITOOL = new ItemToolGravitool();
    //public static final ItemToolAdvancedDiamondDrill advancedDiamondDrill = new ItemToolAdvancedDiamondDrill();
    //public static final ItemToolAdvancedChainsaw advancedChainsaw = new ItemToolAdvancedChainsaw();
    public static final ItemToolVajra VAJRA = new ItemToolVajra();
    public static final ItemRelocator RELOCATOR = new ItemRelocator();
    public static final ItemComponents SUPER_CONDUCTOR_COVER = new ItemComponents("super_conductor_cover");
    public static final ItemComponents SUPER_CONDUCTOR = new ItemComponents("super_conductor");
    public static final ItemComponents COOLING_CORE = new ItemComponents("cooling_core");
    public static final ItemComponents GRAVITATION_ENGINE = new ItemComponents("gravitation_engine");
    public static final ItemComponents MAGNETRON = new ItemComponents("magnetron");
    public static final ItemComponents VAJRA_CORE = new ItemComponents("vajra_core");
    public static final ItemComponents ENGINE_BOOST = new ItemComponents("engine_boost");

    /*public static ItemArmorAdvancedElectricJetpack getAdvancedElectricJetpack() {
        return advancedElectricJetpack;
    }

    public static ItemArmorAdvancedNuclearJetpack getAdvancedNuclearJetpack() {
        return advancedNuclearJetpack;
    }

    public static ItemArmorAdvancedLappack getAdvancedLappack() {
        return advancedLappack;
    }

    public static ItemArmorAdvancedLappack getUltimateLappack() {
        return ultimateLappack;
    }*/

    /*public static void initVars(){
        IBaublesPlugin plugin = IC2.loader.getPlugin("baubles", IBaublesPlugin.class);
        if (plugin != null){
            advancedElectricJetpack = new ItemArmorBaublesAdvancedElectricJetpack();
            advancedNuclearJetpack = new ItemArmorBaublesAdvancedNuclearJetpack();
            advancedLappack = new ItemArmorBaublesAdvancedLappack("advancedLappack", GravisuitLang.advancedLappack, 2, GravisuitConfig.powerValues.advancedLappackStorage, 16, GravisuitConfig.powerValues.advancedLappackTransfer);
            ultimateLappack = new ItemArmorBaublesAdvancedLappack("ultimateLappack", GravisuitLang.ultimateLappack, 3, GravisuitConfig.powerValues.ultimateLappackStorage, 19, GravisuitConfig.powerValues.ultimateLappackTransfer);
        }else {
            advancedElectricJetpack = new ItemArmorAdvancedElectricJetpack();
            advancedNuclearJetpack = new ItemArmorAdvancedNuclearJetpack();
            advancedLappack = new ItemArmorAdvancedLappack("advancedLappack", GravisuitLang.advancedLappack, 2, GravisuitConfig.powerValues.advancedLappackStorage, 16, GravisuitConfig.powerValues.advancedLappackTransfer);
            ultimateLappack = new ItemArmorAdvancedLappack("ultimateLappack", GravisuitLang.ultimateLappack, 3, GravisuitConfig.powerValues.ultimateLappackStorage, 19, GravisuitConfig.powerValues.ultimateLappackTransfer);
        }
    }*/

    public static void init(){
    }
}
