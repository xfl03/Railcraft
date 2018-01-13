/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2017
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/
package mods.railcraft.common.carts;

import mods.railcraft.api.carts.locomotive.LocomotiveRenderType;
import mods.railcraft.common.blocks.RailcraftBlocks;
import mods.railcraft.common.blocks.machine.worldspike.WorldspikeVariant;
import mods.railcraft.common.core.Railcraft;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.core.RailcraftConstants;
import mods.railcraft.common.items.IRailcraftItemSimple;
import mods.railcraft.common.items.ItemWrapper;
import mods.railcraft.common.items.ModItems;
import mods.railcraft.common.modules.ModuleCharge;
import mods.railcraft.common.modules.ModuleLocomotives;
import mods.railcraft.common.modules.ModuleSteam;
import mods.railcraft.common.modules.ModuleThaumcraft;
import mods.railcraft.common.plugins.color.EnumColor;
import mods.railcraft.common.plugins.forge.CraftingPlugin;
import mods.railcraft.common.plugins.forge.RailcraftRegistry;
import mods.railcraft.common.plugins.thaumcraft.EntityLocomotiveSteamMagic;
import mods.railcraft.common.util.crafting.CartDisassemblyRecipe;
import mods.railcraft.common.util.misc.EntityIDs;
import mods.railcraft.common.util.misc.Game;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public final class RailcraftCarts {

    // Vanilla Carts
    public static final ItemCartContainer<? extends ItemCart>   BASIC = new ItemCartContainer<>("cart_basic",EntityCartBasic.class, (c) -> (ItemCart) Items.MINECART);

    public static final ItemCartContainer<? extends ItemCart>   CHEST("cart_chest",EntityCartChest.class, (c) ->  (ItemCart)Items.CHEST_MINECART,

    from(Blocks.CHEST));

    public static final ItemCartContainer<? extends ItemCart>  COMMAND_BLOCK("cart_command_block",EntityCartCommand.class, (c) ->(ItemCart)Items.COMMAND_BLOCK_MINECART,

    from(Blocks.COMMAND_BLOCK));

    public static final ItemCartContainer<? extends ItemCart>  FURNACE("cart_furnace",EntityCartFurnace.class, (c) ->(ItemCart)Items.FURNACE_MINECART,

    from(Blocks.FURNACE));

    public static final ItemCartContainer<? extends ItemCart> HOPPER("cart_hopper",EntityCartHopper.class, (c) ->(ItemCart)Items.HOPPER_MINECART,

    from(Blocks.HOPPER));

    public static final ItemCartContainer<? extends ItemCart>  TNT("cart_tnt",EntityCartTNT.class, (c) ->(ItemCart)Items.TNT_MINECART,

    from(Blocks.TNT));

    // Railcraft Carts
    public static final ItemCartContainer<? extends ItemCart>  BORE("bore",EntityTunnelBore.class, ItemTunnelBore::new),

    public static final ItemCartContainer<? extends ItemCart>  CARGO("cart_cargo",EntityCartCargo.class, ItemCartCargo::new, from(Blocks.TRAPPED_CHEST)),

    public static final ItemCartContainer<? extends ItemCart>  ENERGY_BATBOX("cart_ic2_batbox",EntityCartEnergyBatBox.class, ItemCart::new, ModItems.BAT_BOX::get),

    public static final ItemCartContainer<? extends ItemCart>   ENERGY_CESU("cart_ic2_cesu",EntityCartEnergyCESU.class, ItemCart::new, ModItems.CESU::get),

    public static final ItemCartContainer<? extends ItemCart>  ENERGY_MFE("cart_ic2_mfe",EntityCartEnergyMFE.class, ItemCart::new, ModItems.MFE::get),

    public static final ItemCartContainer<? extends ItemCart>  ENERGY_MFSU("cart_ic2_MFSU",EntityCartEnergyMFSU.class, ItemCart::new, ModItems.MFSU::get),

    public static final ItemCartContainer<? extends ItemCart>  GIFT("cart_gift",EntityCartGift.class, ItemCartGift::new),

    public static final ItemCartContainer<? extends ItemCart>   JUKEBOX("cart_jukebox",EntityCartJukebox.class, ItemCartJukebox::new, from(Blocks.JUKEBOX)),

    public static final ItemCartContainer<? extends ItemCart>   MOW_TRACK_LAYER("mow_track_layer",EntityCartTrackLayer.class, ItemCartMOWTrackLayer::new),

    public static final ItemCartContainer<? extends ItemCart>  MOW_TRACK_RELAYER("mow_track_relayer",EntityCartTrackRelayer.class, ItemCartMOWTrackRelayer::new),

    public static final ItemCartContainer<? extends ItemCart>   MOW_TRACK_REMOVER("mow_track_remover",EntityCartTrackRemover.class, ItemCartMOWTrackRemover::new),

    public static final ItemCartContainer<? extends ItemCart>  MOW_UNDERCUTTER("mow_undercutter",EntityCartUndercutter.class, ItemCartMOWUndercutter::new),

    public static final ItemCartContainer<? extends ItemCart>   PUMPKIN("cart_pumpkin",EntityCartPumpkin.class, ItemCartPumpkin::new),

    public static final ItemCartContainer<? extends ItemCart> REDSTONE_FLUX("cart_redstone_flux",EntityCartRF.class, ItemCartRF::new),

    public static final ItemCartContainer<? extends ItemCart> TANK("cart_tank",EntityCartTank.class, ItemCartTank::new, () ->

    {
//        ItemStack stack = EnumMachineBeta.TANK_IRON_GAUGE.getStack();
//        return !InvTools.isEmpty(stack) ? stack : TODO
        return new ItemStack(Blocks.GLASS, 8);
    }),

    public static final ItemCartContainer<? extends ItemCart> TNT_WOOD = new ItemCartContainer<>("cart_tnt_wood",EntityCartTNTWood.class, ItemCartTNTWood::new);

    public static final ItemCartContainer<? extends ItemCart> WORK("cart_work",EntityCartWork.class, ItemCartWork::new, from(Blocks.CRAFTING_TABLE));

    // Railcraft Locomotives
    public static final ItemCartContainer<? extends ItemCart> LOCO_STEAM_SOLID("locomotive_steam_solid",EntityLocomotiveSteamSolid.class, ItemLocoSteamSolid::new) {
        {
            conditions().add(ModuleLocomotives.class);
            conditions().add(ModuleSteam.class);
        }
    };

    public static final ItemCartContainer<? extends ItemCart> LOCO_STEAM_MAGIC(1,"locomotive_steam_magic",EntityLocomotiveSteamMagic.class, (c) ->new

    ItemLocomotive(c, LocomotiveRenderType.STEAM_MAGIC, EnumColor.PURPLE, EnumColor.SILVER))

    {
        {
            conditions().add(ModuleLocomotives.class);
            conditions().add(ModuleThaumcraft.class);
        }
    },

    public static final ItemCartContainer<? extends ItemCart> LOCO_ELECTRIC(1,"locomotive_electric",EntityLocomotiveElectric.class, ItemLocoElectric::new) {
        {
            conditions().add(ModuleLocomotives.class);
            conditions().add(ModuleCharge.class);
        }
    },

    public static final ItemCartContainer<? extends ItemCart> LOCO_CREATIVE(3,"locomotive_creative",EntityLocomotiveCreative.class, (c) ->new

    ItemLocomotive(c, LocomotiveRenderType.ELECTRIC, EnumColor.BLACK, EnumColor.MAGENTA))

    {
        {
            conditions().add(ModuleLocomotives.class);
        }
    },

    WORLDSPIKE_STANDARD("cart_worldspike_standard", EntityCartWorldspikeStandard.class, ItemCartWorldspikeStandard::new, WorldspikeVariant.STANDARD::getStack) {
        {
            conditions().add(RailcraftBlocks.WORLDSPIKE);
            conditions().add(WorldspikeVariant.STANDARD);
        }
    },

    public static final ItemCartContainer<? extends ItemCart> WORLDSPIKE_ADMIN = new ItemCartContainer<ItemCartWorldspike>("cart_worldspike_admin", EntityCartWorldspikeAdmin.class, ItemCartWorldspike::new) {
        {
            conditions().add(RailcraftBlocks.WORLDSPIKE);
            conditions().add(WorldspikeVariant.ADMIN);
        }
    };

    public static final ItemCartContainer<? extends ItemCart> WORLDSPIKE_PERSONAL(0,"cart_worldspike_personal",EntityCartWorldspikePersonal.class, ItemCartWorldspikePersonal::new, WorldspikeVariant.PERSONAL::getStack) {
        {
            conditions().add(RailcraftBlocks.WORLDSPIKE);
            conditions().add(WorldspikeVariant.PERSONAL);
        }
    },;

    private static Supplier<ItemStack> from(Block block) {
        return () -> new ItemStack(block);
    }

//    RailcraftCarts(int rarity, String tag, Class<? extends EntityMinecart> type, Function<RailcraftCarts, Item> itemSupplier) {
//        this(rarity, tag, type, itemSupplier, null);
//    }

//    RailcraftCarts(int rarity, String tag, Class<? extends EntityMinecart> type, Function<RailcraftCarts, Item> itemSupplier, @Nullable Supplier<ItemStack> contentsSupplier) {
//        int entityId;
//        try {
//            entityId = (byte) EntityIDs.class.getField(tag.toUpperCase(Locale.ROOT)).getInt(null);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//        this.def = new Definition(this, tag, null);
//        this.itemSupplier = itemSupplier;
//        this.contentsSupplier = contentsSupplier;
//        this.id = (byte) entityId;
//        this.rarity = (byte) rarity;
//        this.type = type;
//        conditions().add(RailcraftConfig::isCartEnabled, () -> "disabled via config");
//    }

    private RailcraftCarts() {}

    @Nullable
    public static IRailcraftCartContainer getCartType(@Nullable ItemStack cart) {
//        if (cart == null)
//            return null; TODO
//        if (cart.getItem() == Items.MINECART)
//            return RailcraftCarts.BASIC;
//        if (cart.getItem() == Items.CHEST_MINECART)
//            return RailcraftCarts.CHEST;
//        if (cart.getItem() == Items.TNT_MINECART)
//            return RailcraftCarts.TNT;
//        if (cart.getItem() == Items.FURNACE_MINECART)
//            return RailcraftCarts.FURNACE;
//        if (cart.getItem() == Items.HOPPER_MINECART)
//            return RailcraftCarts.HOPPER;
//        if (cart.getItem() == Items.COMMAND_BLOCK_MINECART)
//            return RailcraftCarts.COMMAND_BLOCK;
//        if (cart.getItem() instanceof ItemCart)
//            return ((ItemCart) cart.getItem()).getCartType();
        return null;
    }

    public static class BaseCartContainer implements IRailcraftCartContainer {

        private final Definition def;
        private final Class<? extends EntityMinecart> type;
        private final byte id;

        BaseCartContainer(String tag, Class<? extends EntityMinecart> type) {
            int entityId;
            try {
                entityId = (byte) EntityIDs.class.getField(tag.toUpperCase(Locale.ROOT)).getInt(null);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            this.def = new Definition(this, tag);
            this.id = (byte) entityId;
            this.type = type;
            conditions().add(RailcraftConfig::isCartEnabled, () -> "disabled via config");
        }

        @Override
        public ResourceLocation getRegistryName() {
            return new ResourceLocation(Railcraft.MOD_ID, getBaseTag());
        }

        @Override
        public Definition getDef() {
            return def;
        }

        @Override
        @SideOnly(Side.CLIENT)
        @OverridingMethodsMustInvokeSuper
        public void initializeClient() {
        }

        @Override
        @OverridingMethodsMustInvokeSuper
        public void defineRecipes() {
        }

        @Override
        @OverridingMethodsMustInvokeSuper
        public void finalizeDefinition() {
        }

        @Override
        @OverridingMethodsMustInvokeSuper
        public void finalizeClient() {
        }

        @Override
        public String getEntityLocalizationTag() {
            return "entity.railcraft." + getBaseTag() + ".name";
        }

        @Override
        public Class<? extends EntityMinecart> getCartClass() {
            return type;
        }

        @Override
        public EntityMinecart makeCart(ItemStack stack, World world, double i, double j, double k) {
            try {
                Constructor<? extends EntityMinecart> con = type.getConstructor(World.class, double.class, double.class, double.class);
                EntityMinecart entity = con.newInstance(world, i, j, k);
                if (entity instanceof IRailcraftCart)
                    ((IRailcraftCart) entity).initEntityFromItem(stack);
                return entity;
            } catch (Throwable ex) {
                Game.logThrowable("Failed to create cart entity!", ex);
            }
            return new EntityCartBasic(world, i, j, k);
        }

//    private ItemCart defineItem() {
//        switch (this) {
//            case LOCO_STEAM_SOLID:
//                return new ItemLocomotive(this, LocomotiveRenderType.STEAM_SOLID, EnumColor.SILVER, EnumColor.GRAY);
//            case LOCO_STEAM_MAGIC:
//                return new ItemLocomotive(this, LocomotiveRenderType.STEAM_MAGIC, EnumColor.SILVER, EnumColor.GRAY);
//            case LOCO_ELECTRIC:
//                return new ItemLocomotive(this, LocomotiveRenderType.ELECTRIC, EnumColor.YELLOW, EnumColor.BLACK);
//            case LOCO_CREATIVE:
//                return new ItemLocomotive(this, LocomotiveRenderType.ELECTRIC, EnumColor.BLACK, EnumColor.MAGENTA);
//            default:
//                return new ItemCart(this);
//        }
//    }

        @SuppressWarnings("unchecked")
        private void registerEntity() {
            if (id < 0)
                return;
            EntityRegistry.registerModEntity(type, getBaseTag(), id, Railcraft.getMod(), 256, 2, true);

            // Legacy stuff
//        EntityList.NAME_TO_CLASS.put("Railcraft." + getTag(), type);
//        if (this == LOCO_STEAM_SOLID)
//            EntityList.NAME_TO_CLASS.put("Railcraft.railcraft.cart.loco.steam", type);
        }

        @Override
        @OverridingMethodsMustInvokeSuper
        public void register() {
            registerEntity();
        }


        @Override
        public boolean isEnabled() {
            return conditions().test(this);
        }

        @Override
        public String toString() {
            return "Entity{" + getBaseTag() + "}";
        }
    }

    public static class ItemCartContainer<T extends Item & IRailcraftItemSimple> extends BaseCartContainer implements IRailcraftCartContainer.WithItem<T> {

        private final Function<? super ItemCartContainer<T>, T> itemSupplier;
        @Nullable
        private final Supplier<ItemStack> contentsSupplier;
        private T item;

        ItemCartContainer(String tag, Class<? extends EntityMinecart> type, Function<? super ItemCartContainer<T>, T> itemSupplier) {
            this(tag, type, itemSupplier, null);
        }

        ItemCartContainer(String tag, Class<? extends EntityMinecart> type, Function<? super ItemCartContainer<T>, T> itemSupplier, @Nullable Supplier<ItemStack> contentsSupplier) {
            super(tag, type);
            this.itemSupplier = itemSupplier;
            this.contentsSupplier = contentsSupplier;
        }

        @Override
        public T item() {
            throw new UnsupportedOperationException("not implemented"); //TODO Implement this
        }

        @Override
        @OverridingMethodsMustInvokeSuper
        public void register() {
            super.register();
            item = itemSupplier.apply(this);
            item.setRegistryName(getRegistryName());
            item.setUnlocalizedName("railcraft.entity." + getBaseTag().replace("_", "."));
            RailcraftRegistry.register((Item) item);

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
            if (contentsSupplier != null)
                CraftingPlugin.addRecipe(new CartDisassemblyRecipe.RailcraftVariant(this));
        }

        @SideOnly(Side.CLIENT)
        @Override
        public void finalizeClient() {
            super.finalizeClient();
            item.finalizeClient();
        }

        @Override
        @Nullable
        public ItemStack getContents() {
            return contentsSupplier == null ? null : contentsSupplier.get();
        }
    }



}
