package trinsdar.gravisuit.items.tools;

import ic2.core.utils.IC2ItemGroup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import trinsdar.gravisuit.GravisuitClassic;
import trinsdar.gravisuit.util.Registry;

public class ItemToolVajra extends Item/*ItemElectricTool implements IMiningDrill*/ {

    public ItemToolVajra() {
        super(new Item.Properties().tab(IC2ItemGroup.TAB_TOOLS));
        Registry.REGISTRY.put(new ResourceLocation(GravisuitClassic.MODID, "vajra"), this);
        /*this.attackDamage = 1.0F;
        this.maxCharge = GravisuitConfig.powerValues.vajraStorage;
        this.transferLimit = GravisuitConfig.powerValues.vajraTransfer;
        this.tier = 3;*/
    }

    /*public void setTier(int tier){
        this.tier = tier;
    }

    public void setMaxCharge(int storage){
        this.maxCharge = storage;
    }

    public void setMaxTransfer(int maxTransfer) {
        this.transferLimit = maxTransfer;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
        return Items.DIAMOND_PICKAXE.canHarvestBlock(state) || Items.DIAMOND_SHOVEL.canHarvestBlock(state) || Items.DIAMOND_AXE.canHarvestBlock(state) || Items.DIAMOND_SWORD.canHarvestBlock(state);
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass, EntityPlayer player, IBlockState blockState) {
        return 3;
    }

    @Override
    public int getEnergyCost(ItemStack stack) {
        return 3333;
    }

    @Override
    public float getMiningSpeed(ItemStack stack) {
        return 16384.0F;
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of("pickaxe", "shovel", "axe", "sword");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
        ItemStack stack = player.getHeldItem(handIn);
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
        boolean silkTouch = nbt.getBoolean("silkTouch");
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
        if (IC2.platform.isSimulating() && IC2.keyboard.isModeSwitchKeyDown(player)) {
            if (silkTouch){
                nbt.setBoolean("silkTouch", false);
                enchantments.remove(Enchantments.SILK_TOUCH, 1);
                EnchantmentHelper.setEnchantments(enchantments, stack);
                IC2.platform.messagePlayer(player, GravisuitLang.silkTouchOff);
            }else {
                nbt.setBoolean("silkTouch", true);
                enchantments.put(Enchantments.SILK_TOUCH, 1);
                EnchantmentHelper.setEnchantments(enchantments, stack);
                IC2.platform.messagePlayer(player, GravisuitLang.silkTouchOn);
            }
            return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
        } else {
            return super.onItemRightClick(worldIn, player, handIn);
        }
    }

    @Override
    public void onSortedItemToolTip(ItemStack stack, EntityPlayer player, boolean debugTooltip, List<String> tooltip, Map<ToolTipType, List<String>> sortedTooltip) {
        NBTTagCompound nbt = StackUtil.getNbtData(stack);
        boolean silkTouch = nbt.getBoolean("silkTouch");
        if (silkTouch){
            tooltip.add(GravisuitLang.silkMode.getLocalizedFormatted(GravisuitLang.vajraSilktouchOn));
        }else{
            tooltip.add(GravisuitLang.silkMode.getLocalizedFormatted(GravisuitLang.vajraSilktouchOff));
        }
        List<String> ctrlTip = sortedTooltip.get(ToolTipType.Ctrl);
        ctrlTip.add(Ic2Lang.onItemRightClick.getLocalized());
        ctrlTip.add(Ic2Lang.pressTo.getLocalizedFormatted(IC2.keyboard.getKeyName(2), GravisuitLang.vajraSilktouchToggle.getLocalized()));
    }

    private boolean shouldBreak(EntityPlayer playerIn, World worldIn, BlockPos pos) {
        IBlockState blockState = worldIn.getBlockState(pos);
        if (blockState.getMaterial() == Material.AIR) {
            return false;
        }
        if (blockState.getMaterial().isLiquid()) {
            return false;
        }
        float blockHardness = blockState.getBlockHardness(worldIn, pos);
        if (blockHardness < 0) {
            return false;
        }
        if (!blockState.getBlock().canHarvestBlock(worldIn, pos, playerIn)){
            return false;
        }

        return true;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        IBlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        SoundType soundType = block.getSoundType(blockState, world, pos, player);
        if (ElectricItem.manager.getCharge(stack) >= getEnergyCost(stack) && shouldBreak(player, world, pos)){
            ElectricItem.manager.use(stack, this.getEnergyCost(stack), player);
            blockState.getBlock().harvestBlock(world, player, pos, blockState, world.getTileEntity(pos), stack);
            world.playSound(null, pos, soundType.getBreakSound(), SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F);
            world.setBlockToAir(pos);
            world.removeTileEntity(pos);
            return EnumActionResult.SUCCESS;
        }
        return super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState blockIn, BlockPos pos, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            IC2.achievements.issueStat((EntityPlayer) entityLiving, "blocksDrilled");
        }
        return super.onBlockDestroyed(stack, worldIn, blockIn, pos, entityLiving);
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int i) {
        return Ic2Icons.getTextures("gravisuit_items")[13];
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
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = HashMultimap.create();
        if (slot == EntityEquipmentSlot.MAINHAND) {
            if (ElectricItem.manager.getCharge(stack) >= getEnergyCost(stack) * 2){
                multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Vajra Powered Damage", 25, 0));
            }else {
                multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Vajra Unpowered Damage", 3, 0));
            }
        }
        return multimap;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        ElectricItem.manager.use(stack, (double)getEnergyCost(stack) * 2, attacker);
        return true;
    }

    @Override
    public boolean canMine(ItemStack d) {
        return ElectricItem.manager.canUse(d, this.getEnergyCost(d));
    }

    @Override
    public boolean canMineBlock(ItemStack d, IBlockState state, IBlockAccess access, BlockPos pos) {
        return ForgeHooks.canToolHarvestBlock(access, pos, d);
    }*/
}
