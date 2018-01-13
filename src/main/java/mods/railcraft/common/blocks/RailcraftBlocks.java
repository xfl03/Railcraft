/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2017
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/

package mods.railcraft.common.blocks;

import mods.railcraft.api.core.IVariantEnum;
import mods.railcraft.common.blocks.aesthetics.brick.BrickTheme;
import mods.railcraft.common.blocks.aesthetics.generic.BlockGeneric;
import mods.railcraft.common.blocks.aesthetics.generic.EnumGeneric;
import mods.railcraft.common.blocks.aesthetics.generic.ItemBlockGeneric;
import mods.railcraft.common.blocks.aesthetics.glass.BlockStrengthGlass;
import mods.railcraft.common.blocks.aesthetics.glass.ItemStrengthGlass;
import mods.railcraft.common.blocks.aesthetics.materials.BlockLantern;
import mods.railcraft.common.blocks.aesthetics.materials.BlockRailcraftStairs;
import mods.railcraft.common.blocks.aesthetics.materials.BlockRailcraftWall;
import mods.railcraft.common.blocks.aesthetics.materials.ItemMaterial;
import mods.railcraft.common.blocks.aesthetics.materials.slab.BlockRailcraftSlab;
import mods.railcraft.common.blocks.aesthetics.materials.slab.ItemSlab;
import mods.railcraft.common.blocks.aesthetics.post.*;
import mods.railcraft.common.blocks.anvil.BlockRCAnvil;
import mods.railcraft.common.blocks.anvil.ItemAnvil;
import mods.railcraft.common.blocks.charge.*;
import mods.railcraft.common.blocks.detector.BlockDetector;
import mods.railcraft.common.blocks.detector.EnumDetector;
import mods.railcraft.common.blocks.detector.ItemDetector;
import mods.railcraft.common.blocks.logbook.BlockLogbook;
import mods.railcraft.common.blocks.machine.ItemMachine;
import mods.railcraft.common.blocks.machine.RailcraftBlockMetadata;
import mods.railcraft.common.blocks.machine.charge.BlockChargeFeeder;
import mods.railcraft.common.blocks.machine.charge.FeederVariant;
import mods.railcraft.common.blocks.machine.equipment.BlockMachineEquipment;
import mods.railcraft.common.blocks.machine.equipment.EquipmentVariant;
import mods.railcraft.common.blocks.machine.manipulator.BlockMachineManipulator;
import mods.railcraft.common.blocks.machine.manipulator.ManipulatorVariant;
import mods.railcraft.common.blocks.machine.single.BlockTradeStation;
import mods.railcraft.common.blocks.machine.single.ItemTradeStation;
import mods.railcraft.common.blocks.machine.wayobjects.actuators.ActuatorVariant;
import mods.railcraft.common.blocks.machine.wayobjects.actuators.BlockMachineActuator;
import mods.railcraft.common.blocks.machine.wayobjects.actuators.ItemMachineActuator;
import mods.railcraft.common.blocks.machine.wayobjects.boxes.BlockMachineSignalBoxRailcraft;
import mods.railcraft.common.blocks.machine.wayobjects.boxes.SignalBoxVariant;
import mods.railcraft.common.blocks.machine.wayobjects.signals.*;
import mods.railcraft.common.blocks.machine.worldspike.BlockWorldspike;
import mods.railcraft.common.blocks.machine.worldspike.BlockWorldspikePoint;
import mods.railcraft.common.blocks.machine.worldspike.ItemWorldspike;
import mods.railcraft.common.blocks.machine.worldspike.WorldspikeVariant;
import mods.railcraft.common.blocks.multi.*;
import mods.railcraft.common.blocks.ore.*;
import mods.railcraft.common.blocks.tracks.ItemTrack;
import mods.railcraft.common.blocks.tracks.behaivor.TrackTypes;
import mods.railcraft.common.blocks.tracks.elevator.BlockTrackElevator;
import mods.railcraft.common.blocks.tracks.flex.BlockTrackFlex;
import mods.railcraft.common.blocks.tracks.flex.BlockTrackFlexAbandoned;
import mods.railcraft.common.blocks.tracks.flex.BlockTrackFlexElectric;
import mods.railcraft.common.blocks.tracks.force.BlockTrackForce;
import mods.railcraft.common.blocks.tracks.outfitted.BlockTrackOutfitted;
import mods.railcraft.common.blocks.tracks.outfitted.ItemTrackOutfitted;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.items.IRailcraftItem;
import mods.railcraft.common.items.IRailcraftItemSimple;
import mods.railcraft.common.items.firestone.BlockRitual;
import mods.railcraft.common.plugins.color.EnumColor;
import mods.railcraft.common.plugins.forge.RailcraftRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by CovertJaguar on 4/13/2016 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
@SuppressWarnings({"unused"})
public final class RailcraftBlocks {
    public static final VariantBlockContainer<BlockMachineActuator, ItemMachineActuator, ActuatorVariant> ACTUATOR = new VariantBlockContainer<>("actuator", BlockMachineActuator.class, BlockMachineActuator::new, ItemMachineActuator::new);
    public static final VariantBlockContainer<BlockRCAnvil, ItemAnvil, BlockRCAnvil.DamageState> ANVIL_STEEL = new VariantBlockContainer<>("anvil", BlockRCAnvil.class, BlockRCAnvil::new, ItemAnvil::new);
    public static final BrickTheme BRICK_ABYSSAL = BrickTheme.ABYSSAL;
    public static final BrickTheme BRICK_ANDESITE = BrickTheme.ANDESITE;
    public static final BrickTheme BRICK_BLEACHED_BONE = BrickTheme.BLEACHEDBONE;
    public static final BrickTheme BRICK_BLOOD_STAINED = BrickTheme.BLOODSTAINED;
    public static final BrickTheme BRICK_DIORITE = BrickTheme.DIORITE;
    public static final BrickTheme BRICK_FROST_BOUND = BrickTheme.FROSTBOUND;
    public static final BrickTheme BRICK_GRANITE = BrickTheme.GRANITE;
    public static final BrickTheme BRICK_INFERNAL = BrickTheme.INFERNAL;
    public static final BrickTheme BRICK_NETHER = BrickTheme.NETHER;
    public static final BrickTheme BRICK_PEARLIZED = BrickTheme.PEARLIZED;
    public static final BrickTheme BRICK_QUARRIED = BrickTheme.QUARRIED;
    public static final BrickTheme BRICK_RED_NETHER = BrickTheme.RED_NETHER;
    public static final BrickTheme BRICK_RED_SANDY = BrickTheme.RED_SANDY;
    public static final BrickTheme BRICK_SANDY = BrickTheme.SANDY;
    public static final VariantBlockContainer<BlockChargeBattery, ItemChargeBattery, BatteryVariant> CHARGE_BATTERY = new VariantBlockContainer<>("charge_battery", BlockChargeBattery.class, BlockChargeBattery::new, ItemChargeBattery::new);
    public static final VariantBlockContainer<BlockChargeFeeder, ItemMachine<FeederVariant>, FeederVariant> CHARGE_FEEDER = new VariantBlockContainer<>("charge_feeder", BlockChargeFeeder.class, BlockChargeFeeder::new, ItemMachine::new);
    public static final VariantBlockContainer<BlockDetector, ItemDetector, EnumDetector> DETECTOR = new VariantBlockContainer<>("detector", BlockDetector.class, BlockDetector::new, ItemDetector::new);
    public static final VariantBlockContainer<BlockMachineEquipment, ItemMachine<EquipmentVariant>, EquipmentVariant> EQUIPMENT = new VariantBlockContainer<>("equipment", BlockMachineEquipment.class, BlockMachineEquipment::new, ItemMachine::new);
    public static final VariantBlockContainer<BlockGeneric, ItemBlockGeneric, EnumGeneric> GENERIC = new VariantBlockContainer<>("generic", BlockGeneric.class, BlockGeneric::new, ItemBlockGeneric::new);
    public static final VariantBlockContainer<BlockStrengthGlass, ItemStrengthGlass, EnumColor> GLASS = new VariantBlockContainer<>("glass", BlockStrengthGlass.class, BlockStrengthGlass::new, ItemStrengthGlass::new);
    public static final ItemFormContainer<BlockLantern, ItemMaterial> LANTERN = new ItemFormContainer<>("lantern", BlockLantern.class, BlockLantern::new, ItemMaterial::new);

    //Good bye!
//    @Deprecated
//    public static final ItemFormContainer<BlockMachine<EnumMachineAlpha>, ItemMachine<EnumMachineAlpha>> MACHINE_ALPHA = new ItemFormContainer<>("machine_alpha", BlockMachine.class, () -> new BlockMachine<EnumMachineAlpha>(true), ItemMachine::new);
//    @Deprecated
//    public static final ItemFormContainer<BlockMachine<EnumMachineBeta>, ItemMachine<EnumMachineBeta>> MACHINE_BETA = new ItemFormContainer<>("machine_beta", BlockMachine.class, () -> new BlockMachine<EnumMachineBeta>(false), (b) -> new ItemMachine<EnumMachineBeta>(b));
//    @Deprecated
//    public static final ItemFormContainer<BlockMachine<EnumMachineEpsilon>, ItemMachine<EnumMachineEpsilon>> MACHINE_EPSILON = new ItemFormContainer<>("machine_epsilon", BlockMachine.class, () -> new BlockMachine<EnumMachineEpsilon>(true), ItemMachine::new);

    public static final VariantBlockContainer<BlockMachineManipulator, ItemMachine<ManipulatorVariant>, ManipulatorVariant> MANIPULATOR = new VariantBlockContainer<>("manipulator", BlockMachineManipulator.class, BlockMachineManipulator::new, ItemMachine::new);
    public static final VariantBlockContainer<BlockOre, ItemBlockRailcraftSubtyped<EnumOre>, EnumOre> ORE = new VariantBlockContainer<>("ore", BlockOre.class, BlockOre::new, ItemBlockRailcraftSubtyped::new);
    public static final VariantBlockContainer<BlockOreMagic, ItemBlockRailcraftSubtyped<EnumOreMagic>, EnumOreMagic> ORE_MAGIC = new VariantBlockContainer<>("ore_magic", BlockOreMagic.class, BlockOreMagic::new, ItemOreMagic::new);
    public static final VariantBlockContainer<BlockOreMetal, ItemBlockRailcraftSubtyped<EnumOreMetal>, EnumOreMetal> ORE_METAL = new VariantBlockContainer<>("ore_metal", BlockOreMetal.class, BlockOreMetal::new, ItemBlockRailcraftSubtyped::new);
    public static final VariantBlockContainer<BlockOreMetalPoor, ItemBlockRailcraftSubtyped<EnumOreMetalPoor>, EnumOreMetalPoor> ORE_METAL_POOR = new VariantBlockContainer<>("ore_metal_poor", BlockOreMetalPoor.class, BlockOreMetalPoor::new, ItemBlockRailcraftSubtyped::new);
    public static final VariantBlockContainer<BlockPost, ItemPost, EnumPost> POST = new VariantBlockContainer<>("post", BlockPost.class, BlockPost::new, ItemPost::new);
    public static final VariantBlockContainer<BlockPostMetal, ItemPostMetal, EnumColor> POST_METAL = new VariantBlockContainer<>("post_metal", BlockPostMetal.class, BlockPostMetal::new, ItemPostMetal::new);
    public static final VariantBlockContainer<BlockPostMetalPlatform, ItemPostMetal, EnumColor> POST_METAL_PLATFORM = new VariantBlockContainer<>("post_metal_platform", BlockPostMetalPlatform.class, BlockPostMetalPlatform::new, ItemPostMetal::new);
    public static final VariantBlockContainer<BlockMachineSignalRailcraft, ItemMachine<SignalVariant>, SignalVariant> SIGNAL = new VariantBlockContainer<>("signal", BlockMachineSignalRailcraft.class, BlockMachineSignalRailcraft::new, ItemSignal::new);
    public static final VariantBlockContainer<BlockMachineSignalDualRailcraft, ItemMachine<SignalDualVariant>, SignalDualVariant> SIGNAL_DUAL = new VariantBlockContainer<>("signal_dual", BlockMachineSignalDualRailcraft.class, BlockMachineSignalDualRailcraft::new, ItemSignal::new);
    public static final VariantBlockContainer<BlockMachineSignalBoxRailcraft, ItemMachine<SignalBoxVariant>, SignalBoxVariant> SIGNAL_BOX = new VariantBlockContainer<>("signal_box", BlockMachineSignalBoxRailcraft.class, BlockMachineSignalBoxRailcraft::new, ItemMachine::new);
    public static final ItemFormContainer<BlockRailcraftSlab, ItemSlab> SLAB = new ItemFormContainer<>("slab", BlockRailcraftSlab.class, BlockRailcraftSlab::new, ItemSlab::new);
    public static final ItemFormContainer<BlockRailcraftStairs, ItemMaterial> STAIR = new ItemFormContainer<>("stair", BlockRailcraftStairs.class, BlockRailcraftStairs::new, ItemMaterial::new);
    public static final ItemFormContainer<BlockRailcraftWall, ItemMaterial> WALL = new ItemFormContainer<>("wall", BlockRailcraftWall.class, BlockRailcraftWall::new, ItemMaterial::new);
    public static final VariantBlockContainer<BlockWorldspike, ItemWorldspike, WorldspikeVariant> WORLDSPIKE = new VariantBlockContainer<>("worldspike", BlockWorldspike.class, BlockWorldspike::new, ItemWorldspike::new);
    public static final ItemFormContainer<BlockChargeTrap, ItemBlockRailcraft> CHARGE_TRAP = new ItemFormContainer<>("charge_trap", BlockChargeTrap.class, BlockChargeTrap::new, ItemBlockRailcraft::new);
    public static final ItemFormContainer<BlockFrame, ItemBlockRailcraft> FRAME = new ItemFormContainer<>("frame", BlockFrame.class, BlockFrame::new, ItemBlockRailcraft::new);
    public static final ItemFormContainer<BlockLogbook, ItemBlockRailcraft> LOGBOOK = new ItemFormContainer<>("logbook", BlockLogbook.class, BlockLogbook::new, ItemBlockRailcraft::new);
    public static final ItemFormContainer<BlockTrackElevator, ItemTrack> TRACK_ELEVATOR = new ItemFormContainer<>("track_elevator", BlockTrackElevator.class, BlockTrackElevator::new, ItemTrack::new);
    public static final ItemFormContainer<BlockTrackFlexAbandoned, ItemTrack> TRACK_FLEX_ABANDONED = new ItemFormContainer<>("track_flex_abandoned", BlockTrackFlexAbandoned.class, () -> new BlockTrackFlexAbandoned(TrackTypes.ABANDONED.getTrackType()), ItemTrack::new);
    public static final ItemFormContainer<BlockTrackFlexElectric, ItemTrack> TRACK_FLEX_ELECTRIC = new ItemFormContainer<>("track_flex_electric", BlockTrackFlexElectric.class, () -> new BlockTrackFlexElectric(TrackTypes.ELECTRIC.getTrackType()), ItemTrack::new);
    public static final ItemFormContainer<BlockTrackFlex, ItemTrack> TRACK_FLEX_HIGH_SPEED = new ItemFormContainer<>("track_flex_high_speed", BlockTrackFlex.class, () -> new BlockTrackFlex(TrackTypes.HIGH_SPEED.getTrackType()), ItemTrack::new);
    public static final ItemFormContainer<BlockTrackFlexElectric, ItemTrack> TRACK_FLEX_HS_ELECTRIC = new ItemFormContainer<>("track_flex_hs_electric", BlockTrackFlexElectric.class, () -> new BlockTrackFlexElectric(TrackTypes.HIGH_SPEED_ELECTRIC.getTrackType()), ItemTrack::new);
    public static final ItemFormContainer<BlockTrackFlex, ItemTrack> TRACK_FLEX_REINFORCED = new ItemFormContainer<>("track_flex_reinforced", BlockTrackFlex.class, () -> new BlockTrackFlex(TrackTypes.REINFORCED.getTrackType()), ItemTrack::new);
    public static final ItemFormContainer<BlockTrackFlex, ItemTrack> TRACK_FLEX_STRAP_IRON = new ItemFormContainer<>("track_flex_strap_iron", BlockTrackFlex.class, () -> new BlockTrackFlex(TrackTypes.STRAP_IRON.getTrackType()), ItemTrack::new);
    public static final ItemFormContainer<BlockTrackForce, ItemTrack> TRACK_FORCE = new ItemFormContainer<>("track_force", BlockTrackForce.class, BlockTrackForce::new, ItemTrack::new);
    public static final ItemFormContainer<BlockTrackOutfitted, ItemTrackOutfitted> TRACK_OUTFITTED = new ItemFormContainer<>("track_outfitted", BlockTrackOutfitted.class, BlockTrackOutfitted::new, ItemTrackOutfitted::new);
    public static final ItemFormContainer<BlockWire, ItemBlockRailcraft> WIRE = new ItemFormContainer<>("wire", BlockWire.class, BlockWire::new, ItemBlockRailcraft::new);
    public static final ItemFormContainer<BlockWorldLogic, ItemBlockRailcraft> WORLD_LOGIC = new ItemFormContainer<>("worldlogic", BlockWorldLogic.class, BlockWorldLogic::new, ItemBlockRailcraft::new);
    public static final ItemFormContainer<BlockWorldspikePoint, ItemBlockRailcraft> WORLDSPIKE_POINT = new ItemFormContainer<>("worldspike_point", BlockWorldspikePoint.class, BlockWorldspikePoint::new, ItemBlockRailcraft::new);
    // singles
    public static final ItemFormContainer<BlockTradeStation, ItemTradeStation> TRADE_STATION = new ItemFormContainer<>("trade_station", BlockTradeStation.class, BlockTradeStation::new, ItemTradeStation::new);
    // multiblocks
    public static final ItemFormContainer<BlockCokeOven, ItemCokeOven> COKE_OVEN = new ItemFormContainer<>("coke_oven", BlockCokeOven.class, BlockCokeOven::new, ItemCokeOven::new);
    public static final ItemFormContainer<BlockBlastFurnace, ItemBlastFurnace> BLAST_FURNACE = new ItemFormContainer<>("blast_furnace", BlockBlastFurnace.class, BlockBlastFurnace::new, ItemBlastFurnace::new);
    public static final ItemFormContainer<BlockRockCrusher, ItemRockCrusher> ROCK_CRUSHER = new ItemFormContainer<>("rock_crusher", BlockRockCrusher.class, BlockRockCrusher::new, ItemRockCrusher::new);
    public static final ItemFormContainer<BlockSteamOven, ItemSteamOven> STEAM_OVEN = new ItemFormContainer<>("steam_oven", BlockSteamOven.class, BlockSteamOven::new, ItemSteamOven::new);
    public static final ItemFormContainer<BlockTankIronGauge, ItemTankIronGauge> TANK_IRON_GAUGE = new ItemFormContainer<>("tank_iron_gauge", BlockTankIronGauge.class, BlockTankIronGauge::new, ItemTankIronGauge::new);
    public static final ItemFormContainer<BlockTankIronValve, ItemTankIronValve> TANK_IRON_VALVE = new ItemFormContainer<>("tank_iron_valve", BlockTankIronValve.class, BlockTankIronValve::new, ItemTankIronValve::new);
    public static final ItemFormContainer<BlockTankIronWall, ItemTankIronWall> TANK_IRON_WALL = new ItemFormContainer<>("tank_iron_wall", BlockTankIronWall.class, BlockTankIronWall::new, ItemTankIronWall::new);
    public static final ItemFormContainer<BlockTankSteelGauge, ItemTankSteelGauge> TANK_STEEL_GAUGE = new ItemFormContainer<>("tank_steel_gauge", BlockTankSteelGauge.class, BlockTankSteelGauge::new, ItemTankSteelGauge::new);
    public static final ItemFormContainer<BlockTankSteelValve, ItemTankSteelValve> TANK_STEEL_VALVE = new ItemFormContainer<>("tank_steel_valve", BlockTankSteelValve.class, BlockTankSteelValve::new, ItemTankSteelValve::new);
    public static final ItemFormContainer<BlockTankSteelWall, ItemTankSteelWall> TANK_STEEL_WALL = new ItemFormContainer<>("tank_steel_wall", BlockTankSteelWall.class, BlockTankSteelWall::new, ItemTankSteelWall::new);
    // others
    public static final SingleBlockContainer<BlockRitual> RITUAL = new SingleBlockContainer<>("ritual", BlockRitual.class, BlockRitual::new);
    @Deprecated
    public static final RailcraftBlocks[] VALUES = null;

    public static class SingleBlockContainer<B extends Block & IRailcraftBlock> implements IRailcraftBlockContainer<B> {
        protected final Supplier<? extends B> blockSupplier;
        protected final Class<B> blockClass;
        protected final Definition def;
        protected B block;

        SingleBlockContainer(String tag, Class<B> blockClass, Supplier<? extends B> blockSupplier) {
            this.def = new Definition(this, tag);
            this.blockClass = blockClass;
            this.blockSupplier = blockSupplier;
            conditions().add(RailcraftConfig::isBlockEnabled, () -> "disabled via config");
        }

        @Override
        public Definition getDef() {
            return def;
        }

        @Override
        @OverridingMethodsMustInvokeSuper
        public void register() {
            markLoaded();
            block = blockSupplier.get();
            block.setRegistryName(getRegistryName());
            block.setUnlocalizedName("railcraft." + getBaseTag().replace("_", "."));

            RailcraftRegistry.register(block);
            block.initializeDefinition();
        }

        @Override
        @SideOnly(Side.CLIENT)
        @OverridingMethodsMustInvokeSuper
        public void initializeClient() {
            block.initializeClient();
        }

        @Override
        @OverridingMethodsMustInvokeSuper
        public void defineRecipes() {
            block.defineRecipes();
        }

        @Override
        @OverridingMethodsMustInvokeSuper
        public void finalizeDefinition() {
            block.finalizeDefinition();
        }

        public boolean isEqual(@Nullable Block block) {
            return isLoaded() && this.block == block;
        }

        public boolean isEqual(IBlockState state) {
            return isLoaded() && block == state.getBlock();
        }

        @Override
        public B block() {
            if (!isLoaded())
                throw new RuntimeException("Trying to access a disabled container!");
            return block;
        }

        @Override
        public String toString() {
            return "SingleBlockContainer{" + getBaseTag() + "}";
        }
    }

    public static class ItemFormContainer<B extends Block & IRailcraftBlock, I extends ItemBlock & IRailcraftItemBlock> extends SingleBlockContainer<B> implements IRailcraftBlockContainer.WithItemForm<B, I> {
        protected I item;
        protected final Function<? super B, ? extends I> itemSupplier;

        ItemFormContainer(String tag, Class<B> blockClass, Supplier<? extends B> blockSupplier, Function<? super B, ? extends I> itemSupplier) {
            this(tag, blockClass, blockSupplier, itemSupplier, null);
        }

        ItemFormContainer(String tag, Class<B> blockClass, Supplier<? extends B> blockSupplier,
                          Function<? super B, ? extends I> itemSupplier, @Nullable Supplier<?> altRecipeObject) {
            super(tag, blockClass, blockSupplier);
            this.itemSupplier = itemSupplier;
        }

        @Override
        public Definition getDef() {
            return def;
        }

        @Override
        @OverridingMethodsMustInvokeSuper
        public void register() {
            super.register();
            item = itemSupplier.apply(block);
            item.setRegistryName(getRegistryName());

            if (!(item instanceof IRailcraftItemBlock))
                throw new RuntimeException("Railcraft ItemBlocks must implement IRailcraftItemBlock");
            if (item instanceof IRailcraftItemSimple)
                throw new RuntimeException("Railcraft ItemBlocks must not implement IRailcraftItemSimple");
            item.initializeDefinition();
        }

        @Override
        @SideOnly(Side.CLIENT)
        @OverridingMethodsMustInvokeSuper
        public void initializeClient() {
            super.initializeClient();
            item.initializeClient();
        }

        @Override
        @OverridingMethodsMustInvokeSuper
        public void defineRecipes() {
            super.defineRecipes();
            item.defineRecipes();
        }

        @Override
        @OverridingMethodsMustInvokeSuper
        public void finalizeDefinition() {
            super.finalizeDefinition();
            item.finalizeDefinition();
        }

        public boolean isEqual(@Nullable Block block) {
            return block != null && this.block == block;
        }

        public boolean isEqual(IBlockState state) {
            return block != null && block == state.getBlock();
        }

        @Override
        public I item() {
            if (!isLoaded())
                throw new RuntimeException("Trying to access a disabled container!");
            return item;
        }

        @Override
        public String toString() {
            return "ItemFormContainer{" + getBaseTag() + "}";
        }
    }

    public static class VariantBlockContainer<B extends Block & IRailcraftBlock.WithVariant<V>, I extends ItemBlock & IRailcraftItemBlock & IRailcraftItem.WithVariant<V>, V extends Enum<V> & IVariantEnum> extends ItemFormContainer<B, I>
            implements IRailcraftBlockContainer.VariantContainer<B, I, V> {

        protected final Class<? extends V> variantClass;

        VariantBlockContainer(String tag, Class<B> blockClass, Supplier<? extends B> blockSupplier, Function<? super B, ? extends I> itemSupplier) {
            this(tag, blockClass, blockSupplier, itemSupplier, null);
        }

        VariantBlockContainer(String tag, Class<B> blockClass, Supplier<? extends B> blockSupplier,
                              Function<? super B, ? extends I> itemSupplier, @Nullable Supplier<?> altRecipeObject) {
            super(tag, blockClass, blockSupplier, itemSupplier);
            RailcraftBlockMetadata annotation = blockClass.getAnnotation(RailcraftBlockMetadata.class);
            @SuppressWarnings("unchecked")
            Class<? extends V> type = (Class<? extends V>) annotation.variant();
            this.variantClass = type;
            checkNotNull(variantClass);
        }

        @Override
        public Class<? extends V> getVariantEnum() {
            return variantClass;
        }
    }
}
