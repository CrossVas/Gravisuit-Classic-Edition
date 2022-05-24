/*
package trinsdar.gravisuit.items.armor;

import ic2.core.IC2;
import ic2.core.item.armor.electric.ItemArmorCompactedElectricJetpack;
import ic2.core.item.render.model.JetpackModel;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.models.BaseModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.gravisuit.util.GravisuitConfig;
import trinsdar.gravisuit.util.GravisuitLang;

public class ItemArmorAdvancedElectricJetpack extends ItemArmorCompactedElectricJetpack {

    private int tier;
    private int maxCharge;
    private int transferLimit;

    public ItemArmorAdvancedElectricJetpack(boolean register){
        super(0);
        this.tier = 2;
        this.maxCharge = GravisuitConfig.powerValues.advancedElectricJetpackStorage;
        this.transferLimit = GravisuitConfig.powerValues.advancedElectricJetpackTransfer;
        if (register){
            this.setRegistryName("advancedelectricjetpack");
            this.setUnlocalizedName(GravisuitLang.advancedElectricJetpack);
            this.setCreativeTab(IC2.tabIC2);
        }
    }

    public ItemArmorAdvancedElectricJetpack(){
        this(true);
    }

    public void setTier(int tier){
        this.tier = tier;
    }

    public void setMaxCharge(int storage){
        this.maxCharge = storage;
    }

    public void setMaxTransfer(int maxTransfer) {
        this.transferLimit = maxTransfer;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BaseModel getModelFromItem(ItemStack item) {
        return new JetpackModel(Ic2Icons.getTextures("gravisuit_items")[14]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int i) {
        return Ic2Icons.getTextures("gravisuit_items")[14];
    }

    @Override
    public double getMaxCharge(ItemStack stack) {
        return (double) maxCharge;
    }

    @Override
    public int getTier(ItemStack stack) {
        return tier;
    }

    @Override
    public boolean canProvideEnergy(ItemStack stack) {
        return true;
    }

    @Override
    public double getTransferLimit(ItemStack stack) {
        return (double) transferLimit;
    }

    @Override
    public int getMaxHeight(ItemStack stack, int worldHeight) {
        return 256;
    }

    @Override
    public String getTexture() {
        return "gravisuit:textures/models/advanced_electric_jetpack";
    }
}
*/
