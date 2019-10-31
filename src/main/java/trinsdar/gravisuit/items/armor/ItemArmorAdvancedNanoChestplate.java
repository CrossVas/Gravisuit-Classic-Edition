package trinsdar.gravisuit.items.armor;

import ic2.core.IC2;
import ic2.core.item.armor.base.ItemArmorJetpackBase;
import ic2.core.item.armor.electric.ItemArmorNanoSuit;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.util.obj.ToolTipType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.gravisuit.util.GravisuitConfig;

import java.util.List;
import java.util.Map;

public class ItemArmorAdvancedNanoChestplate extends ItemArmorNanoSuit implements ItemArmorJetpackBase.IIndirectJetpack {
    ItemArmorJetpackBase jetpack;
    String texture;
    int index;

    public ItemArmorAdvancedNanoChestplate(ItemArmorJetpackBase jetpack, String name, LocaleComp comp, String tex, int index) {
        super(44, EntityEquipmentSlot.CHEST);
        this.setRegistryName(name.toLowerCase());
        this.setUnlocalizedName(comp);
        this.setCreativeTab(IC2.tabIC2);
        this.jetpack = jetpack;
        this.texture = tex;
        this.index = index;
        this.transferLimit = GravisuitConfig.powerValues.advancedNanoChestplateTransfer;
        this.maxCharge = GravisuitConfig.powerValues.advancedNanoChestplateStorage;
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

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int i) {
        return Ic2Icons.getTextures("gravisuit_items")[index];
    }

    @Override
    public boolean canProvideEnergy(ItemStack stack) {
        return true;
    }

    @Override
    public String getTexture() {
        return "gravisuit:textures/models/" + texture;
    }

    @Override
    public void onSortedItemToolTip(ItemStack stack, EntityPlayer player, boolean debugTooltip, List<String> tooltip, Map<ToolTipType, List<String>> sortedTooltip) {
        super.onSortedItemToolTip(stack, player, debugTooltip, tooltip, sortedTooltip);
        jetpack.onSortedItemToolTip(stack, player, debugTooltip, tooltip, sortedTooltip);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        super.onArmorTick(world, player, itemStack);
        jetpack.onArmorTick(world, player,itemStack);
    }

    @Override
    public ItemArmorJetpackBase getJetpack() {
        return this.jetpack;
    }
}
