package trinsdar.gravisuit.util;

import jdk.jfr.Name;


public class GravisuitConfig {
    private static final String CATEGORY_POWER_VALUES = "power values";
    private static final String CATEGORY_ENABLED_ITEMS = "enabled items";
    private static final String CATEGORY_MISC = "misc";

    //@Comment("Enable or Disable the 3x3 mining mode of the advanced drill here.")
    public static boolean enableAdvancedDrill3x3Mode = true;
    //@Comment("Enable or Disable the 2x3 mining mode of the advanced drill here.")
    public static boolean enableAdvancedDrill2x3Mode = true;
    //@Comment("Enable or Disable the 1x2 mining mode of the advanced drill here.")
    public static boolean enableAdvancedDrill1x2Mode = true;
    //@Comment("Enable or Disable the compacted electric jetpack charging items like a lappack does here.")
    //@RequiresMcRestart
    public static boolean enableCompactedElectricJetpackOverride = true;
    //@Comment("Enable or Disable the compacted nuclear jetpack charging items like a lappack does here.")
    //@RequiresMcRestart
    public static boolean enableCompactedNuclearJetpackOverride = true;
    //@Comment("Enable or Disable the quantum jetplate charging items like a lappack does here.")
    //@RequiresMcRestart
    public static boolean enableQuantumJetplateOverride = true;
    //@Comment("Enable or Disable the quantum nuclear jetplate charging items like a lappack does here.")
    //@RequiresMcRestart
    public static boolean enableQuantumNuclearJetplateOverride = true;
    //@Comment("Enable or Disable the overriding of compacted jetpack and jetplate recipes here. Also requires that the configs for making them charge items also be enabled.")
    //@RequiresMcRestart
    public static boolean enableIc2JetpackRecipOverrides = true;
    //@Comment("Enable or Disable the gravitool requiring the completly lossless version of the precision wrench here.")
    //@RequiresMcRestart
    public static boolean enableGravitoolRequiresLosslessPrecisionWrench = true;
    /*@Comment({
            "Enables the gravitation chestplate tier being tier 4.",
            "WARNING: If you don't have gtclassic or tech reborn or another mod with tier 4 energy storage, you will have to get a pesu in order to charge it."
    })*/
    public static boolean enableGravisuitTier4 = false;

    //@Comment(value = "Set the max EU storage and max EU transfer of each item here.")
    //@Name(value = "power values")
    public static PowerValues powerValues = new PowerValues();

    public static class PowerValues {

        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int advancedElectricJetpackStorage = 500000;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int advancedNuclearJetpackStorage = 500000;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int advancedLappackStorage = 600000;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int ultimateLappackStorage = 10000000;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int advancedNanoChestplateStorage = 600000;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int advancedNuclearNanoChestplateStorage = 600000;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int gravisuitStorage = 10000000;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int nuclearGravisuitStorage = 10000000;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int advancedChainsawStorage = 100000;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int advancedDrillStorage = 100000;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int gravitoolStorage = 50000;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int vajraStorage = 3000000;

        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int advancedElectricJetpackTransfer = 500;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int advancedNuclearJetpackTransfer = 500;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int advancedLappackTransfer = 500;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int ultimateLappackTransfer = 4000;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int advancedNanoChestplateTransfer = 500;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int advancedNuclearNanoChestplateTransfer = 500;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int gravisuitTransfer = 5000;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int nuclearGravisuitTransfer = 5000;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int advancedChainsawTransfer = 200;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int advancedDrillTransfer = 200;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int gravitoolTransfer = 400;
        //@RangeInt(min = 1)
        //@RequiresMcRestart
        public int vajraTransfer = 1000;
    }

    //@Comment(value = "Enable or Disable each item here.")
    //@Name(value = "enabled items")
    public static EnabledItems enabledItems = new EnabledItems();

    public static class EnabledItems {

        //@RequiresMcRestart
        public boolean enableAdvancedElectricJetpack = true;
        //@RequiresMcRestart
        public boolean enableAdvancedNuclearJetpack = true;
        //@RequiresMcRestart
        public boolean enableAdvancedLappack = true;
        //@RequiresMcRestart
        public boolean enableUltimateLappack = true;
        //@RequiresMcRestart
        public boolean enableAdvancedNanoChestplate = true;
        //@RequiresMcRestart
        public boolean enableAdvancedNuclearNanoChestplate = true;
        //@RequiresMcRestart
        public boolean enableGravisuit = true;
        //@RequiresMcRestart
        public boolean enableNuclearGravisuit = true;
        //@RequiresMcRestart
        public boolean enableAdvancedChainsaw = true;
        //@RequiresMcRestart
        public boolean enableAdvancedDrill = true;
        //@RequiresMcRestart
        public boolean enableGravitool = true;
        //@RequiresMcRestart
        public boolean enableVajra = true;
        //@RequiresMcRestart
        public boolean enableMiscCraftingItems = true;
    }

    //@Comment("Client only stuff.")
    public static Client client = new Client();

    public static class Client {
        //@Comment("Set the location of the hud position here.")
        public Positions positions = Positions.TOPLEFT;

        public enum Positions {
            TOPLEFT, TOPRIGHT, TOPMIDDLE, BOTTOMLEFT, BOTTOMRIGHT
        }
    }
}

