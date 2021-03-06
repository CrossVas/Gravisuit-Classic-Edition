package trinsdar.gravisuit.items.tools;

import com.google.common.collect.ImmutableSet;
import ic2.api.classic.item.IMiningDrill;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.item.base.ItemElectricTool;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.ToolTipType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;
import trinsdar.gravisuit.util.Config;
import trinsdar.gravisuit.util.GravisuitLang;

import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ItemToolAdvancedDiamondDrill extends ItemElectricTool implements IStaticTexturedItem, IMiningDrill {

    public ItemToolAdvancedDiamondDrill() {
        super(0.0F, -3.0F, ToolMaterial.DIAMOND);
        this.setRegistryName("advanceddrill");
        this.setUnlocalizedName(GravisuitLang.advancedDrill);
        this.attackDamage = 4.0F;
        this.maxCharge = Config.advancedDrillStorage;
        this.transferLimit = Config.advancedDrillTransfer;
        this.tier = 2;
        this.setCreativeTab(IC2.tabIC2);
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
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
        ItemStack stack = player.getHeldItem(handIn);

        if (IC2.platform.isSimulating() && IC2.keyboard.isModeSwitchKeyDown(player)) {
            NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
            ToolMode toolMode = ToolMode.values()[nbt.getByte("ToolModeDrill")];
            toolMode = toolMode.getNext();
            nbt.setByte("ToolModeDrill", (byte)toolMode.ordinal());
            if (toolMode == ToolMode.NORMAL) {
                IC2.platform.messagePlayer(player, TextFormatting.AQUA, GravisuitLang.messageAdvancedDrillNormal);
            } else if (toolMode == ToolMode.LOWPOWER){
                IC2.platform.messagePlayer(player, TextFormatting.GOLD, GravisuitLang.messageAdvancedDrillLowPower);
            } else if (toolMode == ToolMode.FINE){
                IC2.platform.messagePlayer(player, TextFormatting.DARK_GREEN, GravisuitLang.messageAdvancedDrillFine);
            } else if (toolMode == ToolMode.BIGHOLES){
                IC2.platform.messagePlayer(player,TextFormatting.LIGHT_PURPLE, GravisuitLang.messageAdvancedDrillBigHoles);
            } else if (toolMode == ToolMode.MEDIUMHOLES){
                IC2.platform.messagePlayer(player, TextFormatting.BLUE, GravisuitLang.messageAdvancedDrillMediumHoles);
            } else {
                IC2.platform.messagePlayer(player, TextFormatting.DARK_PURPLE, GravisuitLang.messageAdvancedDrillTunnelHoles);
            }

            return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
        } else {
            return super.onItemRightClick(worldIn, player, handIn);
        }
    }

    @Override
    public void onSortedItemToolTip(ItemStack stack, EntityPlayer player, boolean debugTooltip, List<String> tooltip, Map<ToolTipType, List<String>> sortedTooltip) {
        NBTTagCompound nbt = StackUtil.getNbtData(stack);
        ToolMode toolMode = ToolMode.values()[nbt.getByte("ToolModeDrill")];
        if (toolMode == ToolMode.NORMAL){
            tooltip.add(GravisuitLang.toolMode.getLocalizedFormatted(GravisuitLang.advancedDrillNormal));
        }else if (toolMode == ToolMode.LOWPOWER){
            tooltip.add(GravisuitLang.toolMode.getLocalizedFormatted(GravisuitLang.advancedDrillLowPower));
        }else if (toolMode == ToolMode.FINE){
            tooltip.add(GravisuitLang.toolMode.getLocalizedFormatted(GravisuitLang.advancedDrillFine));
        }else if (toolMode == ToolMode.BIGHOLES){
            tooltip.add(GravisuitLang.toolMode.getLocalizedFormatted(GravisuitLang.advancedDrillBigHoles));
        }else if (toolMode == ToolMode.MEDIUMHOLES){
            tooltip.add(GravisuitLang.toolMode.getLocalizedFormatted(GravisuitLang.advancedDrillMediumHoles));
        }else if (toolMode == ToolMode.TUNNELHOLES){
            tooltip.add(GravisuitLang.toolMode.getLocalizedFormatted(GravisuitLang.advancedDrillTunnelHoles));
        }
        List<String> ctrlTip = sortedTooltip.get(ToolTipType.Ctrl);
        ctrlTip.add(Ic2Lang.onItemRightClick.getLocalized());
        ctrlTip.add(Ic2Lang.pressTo.getLocalizedFormatted(IC2.keyboard.getKeyName(2), GravisuitLang.multiModes.getLocalized()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int i) {
        return Ic2Icons.getTextures("gravisuit_items")[7];
    }

    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
        return Items.DIAMOND_PICKAXE.canHarvestBlock(state) || Items.DIAMOND_SHOVEL.canHarvestBlock(state);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
        NBTTagCompound nbt = StackUtil.getNbtData(stack);
        ToolMode toolMode = ToolMode.values()[nbt.getByte("ToolModeDrill")];
        World worldIn = player.world;
        if (!player.isSneaking()) {
            if (toolMode ==ToolMode.BIGHOLES){
                for (BlockPos additionalPos : get3X3TargetBlocks(worldIn, pos, player)) {
                    breakBlock(additionalPos, worldIn, player, stack);
                }
            }else if (toolMode == ToolMode.MEDIUMHOLES){
                for (BlockPos additionalPos : get2X3TargetBlocks(worldIn, pos, player)) {
                    breakBlock(additionalPos, worldIn, player, stack);
                }
            } else if (toolMode == ToolMode.TUNNELHOLES){
                breakBlock(get1x2TargetBlock(worldIn, pos, player), worldIn , player, stack);
            }

        }
        return false;
    }

    public Set<BlockPos> get3X3TargetBlocks(World worldIn, BlockPos pos, @Nullable EntityPlayer playerIn) {
        Set<BlockPos> targetBlocks = new HashSet<BlockPos>();
        if (playerIn == null) {
            return new HashSet<BlockPos>();
        }
        RayTraceResult raytrace = rayTrace(worldIn, playerIn, false);
        if(raytrace == null || raytrace.sideHit == null){
            return Collections.emptySet();
        }
        EnumFacing enumfacing = raytrace.sideHit;
        if (enumfacing == EnumFacing.SOUTH || enumfacing == EnumFacing.NORTH) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    BlockPos newPos = pos.add(i, j, 0);
                    if (shouldBreak(playerIn, worldIn, pos, newPos)) {
                        targetBlocks.add(newPos);
                    }
                }
            }
        } else if (enumfacing == EnumFacing.EAST || enumfacing == EnumFacing.WEST) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    BlockPos newPos = pos.add(0, j, i);
                    if (shouldBreak(playerIn, worldIn, pos, newPos)) {
                        targetBlocks.add(newPos);
                    }
                }
            }
        } else if (enumfacing == EnumFacing.DOWN || enumfacing == EnumFacing.UP) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    BlockPos newPos = pos.add(j, 0, i);
                    if (shouldBreak(playerIn, worldIn, pos, newPos)) {
                        targetBlocks.add(newPos);
                    }
                }
            }
        }
        return targetBlocks;
    }

    public Set<BlockPos> get2X3TargetBlocks(World worldIn, BlockPos pos, @Nullable EntityPlayer playerIn) {
        Set<BlockPos> targetBlocks = new HashSet<BlockPos>();
        if (playerIn == null) {
            return new HashSet<BlockPos>();
        }
        RayTraceResult raytrace = rayTrace(worldIn, playerIn, false);
        if(raytrace == null || raytrace.sideHit == null){
            return Collections.emptySet();
        }
        EnumFacing enumfacing = raytrace.sideHit;
        EnumFacing enumFacing2 = playerIn.getHorizontalFacing();
        if (enumfacing == EnumFacing.SOUTH) {
            for (int i = 0; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    BlockPos newPos = pos.add(i, j, 0);
                    if (shouldBreak(playerIn, worldIn, pos, newPos)) {
                        targetBlocks.add(newPos);
                    }
                }
            }
        } else if (enumfacing == EnumFacing.NORTH) {
            for (int i = -1; i < 1; i++) {
                for (int j = -1; j < 2; j++) {
                    BlockPos newPos = pos.add(i, j, 0);
                    if (shouldBreak(playerIn, worldIn, pos, newPos)) {
                        targetBlocks.add(newPos);
                    }
                }
            }
        } else if (enumfacing == EnumFacing.EAST) {
            for (int i = -1; i < 1; i++) {
                for (int j = -1; j < 2; j++) {
                    BlockPos newPos = pos.add(0, j, i);
                    if (shouldBreak(playerIn, worldIn, pos, newPos)) {
                        targetBlocks.add(newPos);
                    }
                }
            }
        } else if (enumfacing == EnumFacing.WEST) {
            for (int i = 0; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    BlockPos newPos = pos.add(0, j, i);
                    if (shouldBreak(playerIn, worldIn, pos, newPos)) {
                        targetBlocks.add(newPos);
                    }
                }
            }
        }  else if (enumfacing == EnumFacing.DOWN || enumfacing == EnumFacing.UP) {
            if (enumFacing2 == EnumFacing.SOUTH){
                for (int i = -1; i < 1; i++) {
                    for (int j = -1; j < 2; j++) {
                        BlockPos newPos = pos.add(i, 0, j);
                        if (shouldBreak(playerIn, worldIn, pos, newPos)) {
                            targetBlocks.add(newPos);
                        }
                    }
                }
            } else if (enumFacing2 == EnumFacing.NORTH){
                for (int i = 0; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        BlockPos newPos = pos.add(i, 0, j);
                        if (shouldBreak(playerIn, worldIn, pos, newPos)) {
                            targetBlocks.add(newPos);
                        }
                    }
                }
            } else if (enumFacing2 == EnumFacing.EAST){
                for (int i = 0; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        BlockPos newPos = pos.add(j, 0, i);
                        if (shouldBreak(playerIn, worldIn, pos, newPos)) {
                            targetBlocks.add(newPos);
                        }
                    }
                }
            }else {
                for (int i = -1; i < 1; i++) {
                    for (int j = -1; j < 2; j++) {
                        BlockPos newPos = pos.add(j, 0, i);
                        if (shouldBreak(playerIn, worldIn, pos, newPos)) {
                            targetBlocks.add(newPos);
                        }
                    }
                }
            }
        }
        return targetBlocks;
    }

    public BlockPos get1x2TargetBlock(World worldIn, BlockPos pos, @Nullable EntityPlayer playerIn) {
        if (playerIn == null) {
            return pos.up();
        }
        RayTraceResult raytrace = rayTrace(worldIn, playerIn, false);
        if(raytrace == null || raytrace.sideHit == null){
            return pos.up();
        }
        EnumFacing enumfacing = raytrace.sideHit;
        EnumFacing enumFacing2 = playerIn.getHorizontalFacing();
        BlockPos newPos = pos.up();
        if (enumfacing == EnumFacing.UP || enumfacing == EnumFacing.DOWN){
            if (enumFacing2 == EnumFacing.EAST){
                newPos = pos.add(1, 0, 0);
            } else if (enumFacing2 == EnumFacing.WEST){
                newPos = pos.add(-1, 0, 0);
            } else if (enumFacing2 == EnumFacing.SOUTH){
                newPos = pos.add(0, 0, 1);
            }else {
                newPos = pos.add(0, 0, -1);
            }
        }
        return newPos;
    }

    public void breakBlock(BlockPos pos, World world, EntityPlayer player, ItemStack drill) {
        IBlockState blockState = world.getBlockState(pos);

        if (!ElectricItem.manager.canUse(drill, this.getEnergyCost(drill))) {
            return;
        }

        ElectricItem.manager.use(drill, this.getEnergyCost(drill), player);
        blockState.getBlock().harvestBlock(world, player, pos, blockState, world.getTileEntity(pos), drill);
        world.setBlockToAir(pos);
        world.removeTileEntity(pos);
    }

    private boolean shouldBreak(EntityPlayer playerIn, World worldIn, BlockPos originalPos, BlockPos pos) {
        if (originalPos.equals(pos)) {
            return false;
        }
        IBlockState blockState = worldIn.getBlockState(pos);
        if (blockState.getMaterial() == Material.AIR) {
            return false;
        }
        if (blockState.getMaterial().isLiquid()) {
            return false;
        }
        float blockHardness = blockState.getPlayerRelativeBlockHardness(playerIn, worldIn, pos);
        if (blockHardness == -1.0F) {
            return false;
        }
        float originalHardness = worldIn.getBlockState(originalPos).getPlayerRelativeBlockHardness(playerIn, worldIn, originalPos);
        if ((originalHardness / blockHardness) > 10.0F) {
            return false;
        }

        return true;
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass, EntityPlayer player, IBlockState blockState) {
        return 3;
    }

    @Override
    public int getEnergyCost(ItemStack stack) {
        return 160;
    }

    @Override
    public float getMiningSpeed(ItemStack stack) {
        NBTTagCompound nbt = StackUtil.getNbtData(stack);
        ToolMode toolMode = ToolMode.values()[nbt.getByte("ToolModeDrill")];
        if (toolMode == ToolMode.NORMAL){
            return 48.0F;
        }else if (toolMode == ToolMode.LOWPOWER){
            return 16.0F;
        }else if (toolMode == ToolMode.FINE || toolMode == ToolMode.MEDIUMHOLES){
            return 8.0F;
        }else if (toolMode == ToolMode.BIGHOLES){
            return 5.3F;
        }else {
            return 24.0F;
        }
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of("pickaxe", "shovel");
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState blockIn, BlockPos pos,
                                    EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            IC2.achievements.issueStat((EntityPlayer) entityLiving, "blocksDrilled");
        }
        if (ElectricItem.manager.canUse(stack,this.getEnergyCost(stack))){
            IC2.audioManager.playOnce(entityLiving, Ic2Sounds.drillHard);
        }

        return super.onBlockDestroyed(stack, worldIn, blockIn, pos, entityLiving);
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    public EnumEnchantmentType getType(ItemStack itemStack) {
        return EnumEnchantmentType.DIGGER;
    }

    @Override
    public boolean isBasicDrill(ItemStack d) {
        return !d.isItemEnchantable();
    }

    @Override
    public int getExtraSpeed(ItemStack d) {
        int pointBoost = this.getPointBoost(d);
        return 0 + pointBoost;
    }

    private int getPointBoost(ItemStack drill) {
        int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, drill);
        return lvl <= 0 ? 0 : lvl * lvl + 1;
    }

    @Override
    public int getExtraEnergyCost(ItemStack d) {
        int points = this.getEnergyChange(d);
        return points > 0 ? points : 0;
    }

    public int getEnergyChange(ItemStack drill) {
        int eff = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, drill);
        int unb = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, drill);
        int points = eff * eff + 1;
        points -= unb * (unb + unb);
        return points;
    }

    @Override
    public void useDrill(ItemStack d) {
        ElectricItem.manager.use(d, this.getEnergyCost(d), (EntityLivingBase) null);
    }

    @Override
    public boolean canMine(ItemStack d) {
        return ElectricItem.manager.canUse(d, this.getEnergyCost(d));
    }

    @Override
    public boolean canMineBlock(ItemStack d, IBlockState state, IBlockAccess access, BlockPos pos) {
        return ForgeHooks.canToolHarvestBlock(access, pos, d);
    }

    public enum ToolMode {
        NORMAL,
        LOWPOWER,
        FINE,
        BIGHOLES,
        MEDIUMHOLES,
        TUNNELHOLES;

        private ToolMode() {
        }

        public ToolMode getNext() {
            if (this == NORMAL) {
                return LOWPOWER;
            } else if (this == LOWPOWER) {
                return FINE;
            } else if (this == FINE) {
                if (Config.enableAdvancedDrill3x3Mode){
                    return BIGHOLES;
                } else if (Config.enableAdvancedDrill2x3Mode){
                    return MEDIUMHOLES;
                }else if (Config.enableAdvancedDrill1x2Mode){
                    return TUNNELHOLES;
                }else {
                    return NORMAL;
                }
            } else if (this == BIGHOLES ) {
                if (Config.enableAdvancedDrill2x3Mode){
                    return MEDIUMHOLES;
                }else if (Config.enableAdvancedDrill1x2Mode){
                    return TUNNELHOLES;
                }else {
                    return NORMAL;
                }
            } else if (this == MEDIUMHOLES){
                if (Config.enableAdvancedDrill1x2Mode){
                    return TUNNELHOLES;
                }else {
                    return NORMAL;
                }
            } else {
                return NORMAL;
            }
        }
    }
}
