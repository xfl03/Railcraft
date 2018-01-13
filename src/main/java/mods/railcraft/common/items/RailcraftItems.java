/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2017
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/
package mods.railcraft.common.items;

import mods.railcraft.api.core.IVariantEnum;
import mods.railcraft.common.blocks.RailcraftBlocks;
import mods.railcraft.common.blocks.tracks.outfitted.ItemTrackKit;
import mods.railcraft.common.carts.*;
import mods.railcraft.common.core.IRailcraftObjectContainer;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.fluids.Fluids;
import mods.railcraft.common.fluids.ItemBottle;
import mods.railcraft.common.items.firestone.ItemFirestone;
import mods.railcraft.common.items.firestone.ItemFirestoneCracked;
import mods.railcraft.common.items.firestone.ItemFirestoneRefined;
import mods.railcraft.common.modules.ModuleMagic;
import mods.railcraft.common.modules.ModuleSignals;
import mods.railcraft.common.plugins.forestry.ForestryPlugin;
import mods.railcraft.common.plugins.forestry.ItemBackpackWrapper;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;
import mods.railcraft.common.plugins.forge.RailcraftRegistry;
import mods.railcraft.common.plugins.ic2.ItemLapotronUpgrade;
import mods.railcraft.common.plugins.misc.Mod;
import mods.railcraft.common.plugins.thaumcraft.ItemCrowbarThaumium;
import mods.railcraft.common.plugins.thaumcraft.ItemCrowbarVoid;
import mods.railcraft.common.util.inventory.InvTools;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author CovertJaguar <http://www.railcraft.info/>
 */
public final class RailcraftItems {

    public static final SimpleItemContainer<ItemSteelArmor> ARMOR_BOOTS_STEEL = new SimpleItemContainer<>(() -> new ItemSteelArmor(EntityEquipmentSlot.FEET), "armor_boots_steel", () -> Items.IRON_BOOTS);
    public static final SimpleItemContainer<ItemSteelArmor> ARMOR_HELMET_STEEL = new SimpleItemContainer<>(() -> new ItemSteelArmor(EntityEquipmentSlot.HEAD), "armor_helmet_steel", () -> Items.IRON_HELMET);
    public static final SimpleItemContainer<ItemSteelArmor> ARMOR_LEGGINGS_STEEL = new SimpleItemContainer<>(() -> new ItemSteelArmor(EntityEquipmentSlot.LEGS), "armor_leggings_steel", () -> Items.IRON_LEGGINGS);
    public static final SimpleItemContainer<ItemSteelArmor> ARMOR_CHESTPLATE_STEEL = new SimpleItemContainer<>(() -> new ItemSteelArmor(EntityEquipmentSlot.CHEST), "armor_chestplate_steel", () -> Items.IRON_CHESTPLATE);
    public static final SimpleItemContainer<ItemSteelAxe> AXE_STEEL = new SimpleItemContainer<>(ItemSteelAxe::new, "tool_axe_steel", () -> Items.IRON_AXE);
    public static final SimpleItemContainer<ItemBackpackWrapper> BACKPACK_APOTHECARY_T1 = new SimpleItemContainer<ItemBackpackWrapper>(() -> ForestryPlugin.instance().getBackpack("apothecary", "NORMAL"),
            "backpack_apothecary_t1") {{
        conditions().add(Mod.FORESTRY);
        conditions().add(ModuleMagic.class);
    }};
    public static final SimpleItemContainer<ItemBackpackWrapper> BACKPACK_APOTHECARY_T2 = new SimpleItemContainer<ItemBackpackWrapper>(() -> ForestryPlugin.instance().getBackpack("apothecary", "WOVEN"),
            "backpack_apothecary_t2") {{
        conditions().add(Mod.FORESTRY);
        conditions().add(ModuleMagic.class);
    }};
    public static final SimpleItemContainer<ItemBackpackWrapper> BACKPACK_ICEMAN_T1 = new SimpleItemContainer<ItemBackpackWrapper>(() -> ForestryPlugin.instance().getBackpack("iceman", "NORMAL"),
            "backpack_iceman_t1") {{
        conditions().add(Mod.FORESTRY);
    }};
    public static final SimpleItemContainer<ItemBackpackWrapper> BACKPACK_ICEMAN_T2 = new SimpleItemContainer<ItemBackpackWrapper>(() -> ForestryPlugin.instance().getBackpack("iceman", "WOVEN"),
            "backpack_iceman_t2") {{
        conditions().add(Mod.FORESTRY);
    }};
    public static final SimpleItemContainer<ItemBackpackWrapper> BACKPACK_TRACKMAN_T1 = new SimpleItemContainer<ItemBackpackWrapper>(() -> ForestryPlugin.instance().getBackpack("trackman", "NORMAL"),
            "backpack_trackman_t1") {{
        conditions().add(Mod.FORESTRY);
    }};
    public static final SimpleItemContainer<ItemBackpackWrapper> BACKPACK_TRACKMAN_T2 = new SimpleItemContainer<ItemBackpackWrapper>(() -> ForestryPlugin.instance().getBackpack("trackman", "WOVEN"),
            "backpack_trackman_t2") {{
        conditions().add(Mod.FORESTRY);
    }};
    public static final SimpleItemContainer<ItemBackpackWrapper> BACKPACK_SIGNALMAN_T1 = new SimpleItemContainer<ItemBackpackWrapper>(() -> ForestryPlugin.instance().getBackpack("signalman", "NORMAL"),
            "backpack_signalman_t1") {{
        conditions().add(Mod.FORESTRY);
        conditions().add(ModuleSignals.class);
    }};
    public static final SimpleItemContainer<ItemBackpackWrapper> BACKPACK_SIGNALMAN_T2 = new SimpleItemContainer<ItemBackpackWrapper>(() -> ForestryPlugin.instance().getBackpack("signalman", "WOVEN"),
            "backpack_signalman_t2") {{
        conditions().add(Mod.FORESTRY);
        conditions().add(ModuleSignals.class);
    }};
    public static final SimpleItemContainer<ItemRailcraft> BLEACHED_CLAY = new SimpleItemContainer<ItemRailcraft>(ItemRailcraft::new, "bleached_clay", () -> Items.CLAY_BALL) {{
        conditions().add(RailcraftBlocks.BRICK_BLEACHED_BONE);
    }};
    public static final SimpleItemContainer<ItemBoreHeadBronze> BORE_HEAD_BRONZE = new SimpleItemContainer<ItemBoreHeadBronze>(ItemBoreHeadBronze::new, "borehead_bronze") {{
        conditions().add(RailcraftCarts.BORE);
    }};
    public static final SimpleItemContainer<ItemBoreHeadIron> BORE_HEAD_IRON = new SimpleItemContainer<ItemBoreHeadIron>(ItemBoreHeadIron::new, "borehead_iron") {{
        conditions().add(RailcraftCarts.BORE);
    }};
    public static final SimpleItemContainer<ItemBoreHeadSteel> BORE_HEAD_STEEL = new SimpleItemContainer<ItemBoreHeadSteel>(ItemBoreHeadSteel::new, "borehead_steel") {{
        conditions().add(RailcraftCarts.BORE);
    }};
    public static final SimpleItemContainer<ItemBoreHeadDiamond> BORE_HEAD_DIAMOND = new SimpleItemContainer<ItemBoreHeadDiamond>(ItemBoreHeadDiamond::new, "borehead_diamond") {{
        conditions().add(RailcraftCarts.BORE);
    }};
    public static final VariedItemContainer<ItemCharge, ItemCharge.EnumCharge> CHARGE = new VariedItemContainer<>(ItemCharge::new, "charge", ItemCharge.EnumCharge.class);
    public static final SimpleItemContainer<ItemChargeMeter> CHARGE_METER = new SimpleItemContainer<>(ItemChargeMeter::new, "tool_charge_meter");
    public static final VariedItemContainer<ItemCircuit, ItemCircuit.EnumCircuit> CIRCUIT = new VariedItemContainer<>(ItemCircuit::new, "circuit", ItemCircuit.EnumCircuit.class);
    public static final SimpleItemContainer<ItemCoke> COKE = new SimpleItemContainer<>(ItemCoke::new, "fuel_coke");
    public static final SimpleItemContainer<ItemConcrete> CONCRETE = new SimpleItemContainer<>(ItemConcrete::new, "concrete");
    public static final SimpleItemContainer<ItemBottle> BOTTLE_CREOSOTE = new SimpleItemContainer<>(() -> new ItemBottle(Fluids.CREOSOTE), "fluid_bottle_creosote");
    public static final SimpleItemContainer<ItemBottle> BOTTLE_STEAM = new SimpleItemContainer<>(() -> new ItemBottle(Fluids.STEAM), "fluid_bottle_steam");
    public static final SimpleItemContainer<ItemCrowbarIron> CROWBAR_IRON = new SimpleItemContainer<>(ItemCrowbarIron::new, "tool_crowbar_iron");
    public static final SimpleItemContainer<ItemCrowbarSteel> CROWBAR_STEEL = new SimpleItemContainer<>(ItemCrowbarSteel::new, "tool_crowbar_steel");
    public static final SimpleItemContainer<ItemCrowbarThaumium> CROWBAR_THAUMIUM = new SimpleItemContainer<ItemCrowbarThaumium>(ItemCrowbarThaumium::new, "tool_crowbar_thaumium") {{
        conditions().add(Mod.THAUMCRAFT);
    }};
    public static final SimpleItemContainer<ItemCrowbarVoid> CROWBAR_VOID = new SimpleItemContainer<ItemCrowbarVoid>(ItemCrowbarVoid::new, "tool_crowbar_void") {{
        conditions().add(Mod.THAUMCRAFT);
    }};
    public static final SimpleItemContainer<ItemCrowbarDiamond> CROWBAR_DIAMOND = new SimpleItemContainer<>(ItemCrowbarDiamond::new, "tool_crowbar_diamond");
    public static final VariedItemContainer<ItemDust, ItemDust.EnumDust> DUST = new VariedItemContainer<>(ItemDust::new, "dust", ItemDust.EnumDust.class);
    public static final VariedItemContainer<ItemGear, ItemGear.EnumGear> GEAR = new VariedItemContainer<>(ItemGear::new, "gear", ItemGear.EnumGear.class);
    public static final SimpleItemContainer<ItemGoggles> GOGGLES = new SimpleItemContainer<>(ItemGoggles::new, "armor_goggles");
    public static final SimpleItemContainer<ItemFilterBee> FILTER_BEE = new SimpleItemContainer<ItemFilterBee>(ItemFilterBee::new, "filter_bee") {{
        conditions().add(Mod.FORESTRY);
    }};
    public static final SimpleItemContainer<ItemFilterBeeGenome> FILTER_BEE_GENOME = new SimpleItemContainer<ItemFilterBeeGenome>(ItemFilterBeeGenome::new, "filter_bee_genome") {{
        conditions().add(Mod.FORESTRY);
    }};
    public static final SimpleItemContainer<ItemFilterBlank> FILTER_BLANK = new SimpleItemContainer<>(ItemFilterBlank::new, "filter_blank");
    public static final SimpleItemContainer<ItemFilterType> FILTER_TYPE = new SimpleItemContainer<>(ItemFilterType::new, "filter_type");
    public static final SimpleItemContainer<ItemFilterOreDictionary> FILTER_ORE_DICT = new SimpleItemContainer<>(ItemFilterOreDictionary::new, "filter_ore_dict");
    public static final SimpleItemContainer<ItemFirestoneCracked> FIRESTONE_CRACKED = new SimpleItemContainer<>(ItemFirestoneCracked::new, "firestone_cracked");
    public static final SimpleItemContainer<ItemFirestone> FIRESTONE_CUT = new SimpleItemContainer<>(ItemFirestone::new, "firestone_cut");
    public static final SimpleItemContainer<ItemFirestone> FIRESTONE_RAW = new SimpleItemContainer<>(ItemFirestone::new, "firestone_raw");
    public static final SimpleItemContainer<ItemFirestoneRefined> FIRESTONE_REFINED = new SimpleItemContainer<>(ItemFirestoneRefined::new, "firestone_refined");
    public static final SimpleItemContainer<ItemSteelHoe> HOE_STEEL = new SimpleItemContainer<>(ItemSteelHoe::new, "tool_hoe_steel", () -> Items.IRON_HOE);
    public static final VariedItemContainer<ItemIngot, Metal> INGOT = new VariedItemContainer<>(ItemIngot::new, "ingot", Metal.class);
    public static final SimpleItemContainer<ItemLapotronUpgrade> LAPOTRON_UPGRADE = new SimpleItemContainer<ItemLapotronUpgrade>(ItemLapotronUpgrade::new, "ic2_upgrade_lapotron") {{
        conditions().add(() -> Mod.anyLoaded(Mod.IC2, Mod.IC2_CLASSIC), () -> "Mod Ic2 or Ic2Classic is not installed");
    }};
    public static final SimpleItemContainer<ItemMagnifyingGlass> MAG_GLASS = new SimpleItemContainer<>(ItemMagnifyingGlass::new, "tool_magnifying_glass");
    public static final SimpleItemContainer<ItemNotepad> NOTEPAD = new SimpleItemContainer<>(ItemNotepad::new, "tool_notepad");
    public static final VariedItemContainer<ItemNugget, Metal> NUGGET = new VariedItemContainer<>(ItemNugget::new, "nugget", Metal.class);
    public static final SimpleItemContainer<ItemOveralls> OVERALLS = new SimpleItemContainer<>(ItemOveralls::new, "armor_overalls");
    public static final SimpleItemContainer<ItemSteelPickaxe> PICKAXE_STEEL = new SimpleItemContainer<>(ItemSteelPickaxe::new, "tool_pickaxe_steel", () -> Items.IRON_PICKAXE);
    public static final VariedItemContainer<ItemPlate, Metal> PLATE = new VariedItemContainer<>(ItemPlate::new, "plate", Metal.class);
    public static final SimpleItemContainer<ItemRail> RAIL = new SimpleItemContainer<>(ItemRail::new, "rail");
    public static final SimpleItemContainer<ItemRailbed> RAILBED = new SimpleItemContainer<>(ItemRailbed::new, "railbed");
    public static final SimpleItemContainer<ItemRebar> REBAR = new SimpleItemContainer<>(ItemRebar::new, "rebar", () -> "ingotIron");
    public static final SimpleItemContainer<ItemRoutingTable> ROUTING_TABLE = new SimpleItemContainer<>(ItemRoutingTable::new, "routing_table", () -> Items.WRITABLE_BOOK);
    public static final SimpleItemContainer<ItemSteelShears> SHEARS_STEEL = new SimpleItemContainer<>(ItemSteelShears::new, "tool_shears_steel", () -> Items.SHEARS);
    public static final SimpleItemContainer<ItemSteelShovel> SHOVEL_STEEL = new SimpleItemContainer<>(ItemSteelShovel::new, "tool_shovel_steel", () -> Items.IRON_SHOVEL);
    public static final SimpleItemContainer<ItemSignalBlockSurveyor> SIGNAL_BLOCK_SURVEYOR = new SimpleItemContainer<>(ItemSignalBlockSurveyor::new, "tool_signal_surveyor");
    public static final SimpleItemContainer<ItemSignalLabel> SIGNAL_LABEL = new SimpleItemContainer<>(ItemSignalLabel::new, "tool_signal_label");
    public static final SimpleItemContainer<ItemSignalLamp> SIGNAL_LAMP = new SimpleItemContainer<>(ItemSignalLamp::new, "signal_lamp", () -> Blocks.REDSTONE_LAMP);
    public static final SimpleItemContainer<ItemSignalTuner> SIGNAL_TUNER = new SimpleItemContainer<>(ItemSignalTuner::new, "tool_signal_tuner");
    public static final SimpleItemContainer<ItemSpikeMaulIron> SPIKE_MAUL_IRON = new SimpleItemContainer<>(ItemSpikeMaulIron::new, "tool_spike_maul_iron");
    public static final SimpleItemContainer<ItemSpikeMaulSteel> SPIKE_MAUL_STEEL = new SimpleItemContainer<>(ItemSpikeMaulSteel::new, "tool_spike_maul_steel");
    public static final SimpleItemContainer<ItemStoneCarver> STONE_CARVER = new SimpleItemContainer<>(ItemStoneCarver::new, "tool_stone_carver");
    public static final SimpleItemContainer<ItemSteelSword> SWORD_STEEL = new SimpleItemContainer<>(ItemSteelSword::new, "tool_sword_steel", () -> Items.IRON_SWORD);
    public static final SimpleItemContainer<ItemTicket> TICKET = new SimpleItemContainer<>(ItemTicket::new, "routing_ticket", () -> Items.PAPER);
    public static final SimpleItemContainer<ItemTicketGold> TICKET_GOLD = new SimpleItemContainer<>(ItemTicketGold::new, "routing_ticket_gold", () -> Items.GOLD_NUGGET);
    public static final SimpleItemContainer<ItemTie> TIE = new SimpleItemContainer<>(ItemTie::new, "tie");
    public static final SimpleItemContainer<ItemTrackKit> TRACK_KIT = new SimpleItemContainer<ItemTrackKit>(ItemTrackKit::new, "track_kit") {{
        conditions().add(RailcraftBlocks.TRACK_OUTFITTED);
    }};
    public static final SimpleItemContainer<ItemTrackParts> TRACK_PARTS = new SimpleItemContainer<>(ItemTrackParts::new, "track_parts", () -> "ingotIron");
    public static final SimpleItemContainer<ItemTurbineBlade> TURBINE_BLADE = new SimpleItemContainer<>(ItemTurbineBlade::new, "turbine_blade", () -> "ingotSteel");
    public static final SimpleItemContainer<ItemTurbineDisk> TURBINE_DISK = new SimpleItemContainer<>(ItemTurbineDisk::new, "turbine_disk", () -> "blockSteel");
    public static final SimpleItemContainer<ItemTurbineRotor> TURBINE_ROTOR = new SimpleItemContainer<>(ItemTurbineRotor::new, "turbine_rotor");
    public static final SimpleItemContainer<ItemWhistleTuner> WHISTLE_TUNER = new SimpleItemContainer<>(ItemWhistleTuner::new, "tool_whistle_tuner");

    @Deprecated
    public static final RailcraftItems[] VALUES = null;

    public static class SimpleItemContainer<T extends Item & IRailcraftItemSimple> implements IRailcraftObjectContainer.IContainerItem<T> {

        private final Supplier<T> itemSupplier;
        private final Definition def;
        private T item;
        private Supplier<Object> alt;

        SimpleItemContainer(Supplier<T> itemSupplier, String tag) {
            this(itemSupplier, tag, null);
        }

        SimpleItemContainer(Supplier<T> itemSupplier, String tag, @Nullable Supplier<Object> alt) {
            this.def = new Definition(this, tag);
            this.alt = alt;
            this.itemSupplier = itemSupplier;
            conditions().add(RailcraftConfig::isItemEnabled, () -> "disabled via config");
        }

        @Override
        public Definition getDef() {
            return def;
        }

        @Override
        public void register() {
            item = itemSupplier.get();
            item.setRegistryName(getBaseTag());
            item.setUnlocalizedName(LocalizationPlugin.convertTag(getFullTag()));
            RailcraftRegistry.register((Item) item);
            item.initializeDefinition();
        }

        @SideOnly(Side.CLIENT)
        @Override
        public void initializeClient() {
            item.initializeClient();
        }

        @Override
        public void defineRecipes() {
            item.defineRecipes();
        }

        @Override
        public void finalizeDefinition() {
            item.finalizeDefinition();
        }

        public boolean isEqual(@Nullable ItemStack stack) {
            return !InvTools.isEmpty(stack) && item == stack.getItem();
        }

        public boolean isInstance(@Nullable ItemStack stack) {
            return !InvTools.isEmpty(stack) && (item == stack.getItem() || item.getClass().isInstance(stack.getItem()));
        }

        public boolean isEqual(@Nullable Item item) {
            return item != null && this.item == item;
        }

        public T item() {
            if (isLoaded())
                return checkNotNull(item);
            throw new RuntimeException("Trying to access disabled item");
        }

        public String getFullTag() {
            return "railcraft." + getBaseTag();
        }

        @Nullable
        public ItemStack getStack(int qty, int meta) {
            if (item == null)
                return InvTools.emptyStack();
            return new ItemStack(item, qty, meta);
        }

        @Override
        public String toString() {
            return "Item{" + getBaseTag() + "}";
        }
    }

    public static class VariedItemContainer<T extends Item & IRailcraftItemSimple.WithVariant<V>, V extends Enum<V> & IVariantEnum> extends SimpleItemContainer<T>
            implements IRailcraftObjectContainer.IContainerItemVariant<T, V> {

        private Class<V> variantClass;

        VariedItemContainer(Supplier<T> itemSupplier, String tag, Class<V> variantClass) {
            this(itemSupplier, tag, variantClass, null);
        }

        VariedItemContainer(Supplier<T> itemSupplier, String tag, Class<V> variantClass, @Nullable Supplier<Object> alt) {
            super(itemSupplier, tag, alt);
            this.variantClass = variantClass;
        }

        @Override
        public Class<? extends V> getVariantEnum() {
            return variantClass;
        }

        @Override
        public ItemStack getStack(int qty, V variant) {
            return item().getStack(qty, variant);
        }
    }

}
