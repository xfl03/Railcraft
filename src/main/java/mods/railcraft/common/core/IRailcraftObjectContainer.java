/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2017
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/

package mods.railcraft.common.core;

import mods.railcraft.api.core.IRailcraftModule;
import mods.railcraft.api.core.IRailcraftRecipeIngredientContainer;
import mods.railcraft.api.core.IVariantEnum;
import mods.railcraft.api.tracks.TrackKit;
import mods.railcraft.common.modules.RailcraftModuleManager;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.HashSet;
import java.util.Set;

/**
 * This interface is mainly to ensure that RailcraftBlocks and RailcraftItems have similar syntax.
 * An object container may contain more than one object, such as a block and item together and
 * a minecart type and item type together.
 *
 * Created by CovertJaguar on 4/13/2016.
 */
@SuppressWarnings("unused")
public interface IRailcraftObjectContainer {
    final class Definition {
        private final IRailcraftObjectContainer owner;
        private final Set<Class<? extends IRailcraftModule>> modules = new HashSet<>();
        private final InitializationConditional conditions;
        private final String tag;
        private final ResourceLocation registryName;
        // package private
        boolean loaded;
        @Nullable
        private Boolean enabled;

        public Definition(IRailcraftObjectContainer obj, String tag) {
            this.owner = obj;
            this.tag = tag;
            this.loaded = false;
            this.enabled = null;
            this.registryName = new ResourceLocation(obj.getResourceDomain(), tag);
            conditions = new InitializationConditional();
            conditions.add(c -> !modules.isEmpty(), () -> "it has no module");
        }

        boolean isEnabled() {
            if (enabled == null)
                enabled = conditions.test(owner);
            return enabled;
        }

        public IRailcraftObjectContainer getOwner() {
            return owner;
        }

        void addRequiredModule(Class<? extends IRailcraftModule> moduleClass) {
            modules.add(moduleClass);
            conditions.add(c -> RailcraftModuleManager.isModuleEnabled(moduleClass), () -> "module " + moduleClass.getSimpleName() + " is not enabled!");
        }

    }

    Definition getDef();

    default ResourceLocation getRegistryName() {
        return getDef().registryName;
    }

    default InitializationConditional conditions() {
        return getDef().conditions;
    }

    /**
     * Hook to mark the object as loaded
     */
    default void markLoaded() {
        getDef().loaded = true;
    }

    /**
     * Register the object in pre init. Call {@link #markLoaded()} and
     * {@link IRailcraftObject#initializeDefinition()} in this part!
     */
    @OverridingMethodsMustInvokeSuper
    void register();

    /**
     * To be called in batch by the client proxy in pre init.
     */
    @SideOnly(Side.CLIENT)
    @OverridingMethodsMustInvokeSuper
    void initializeClient();

    /**
     * Called in the module event handlers in init.
     */
    @OverridingMethodsMustInvokeSuper
    void defineRecipes();

    /**
     * Called in the module event handlers in post init.
     */
    @OverridingMethodsMustInvokeSuper
    void finalizeDefinition();

    @SideOnly(Side.CLIENT)
    @OverridingMethodsMustInvokeSuper
    void finalizeClient();

    default String getResourceDomain() {
        return RailcraftConstants.RESOURCE_DOMAIN;
    }

    default String getBaseTag() {
        return getDef().tag;
    }

    default boolean isEnabled() {
        return getDef().isEnabled();
    }

    /**
     * This must be called before using container object access methods, otherwise
     * things always fail!
     *
     * @return true If the object is loaded
     */
    default boolean isLoaded() {
        return getDef().loaded;
    }

    /**
     * Set the modules that this object belongs to. Each object must have at least one module. If the module is disabled,
     * this method will not get called, thus the object cannot get registered. The module may be kept for
     * debug use, etc.
     *
     * @param source The module that loads this object
     */
    default void requiresModule(Class<? extends IRailcraftModule> source) {
        getDef().addRequiredModule(source);
    }

    /**
     * Lets see if we can remove some boilerplate with this.
     * <p>
     * Created by CovertJaguar on 3/24/2016.
     */
    interface IContainerBlock<B extends Block & IRailcraftObject<Block>> extends IRailcraftObjectContainer {
        /**
         * Gets the block. Fails if the container is disabled.
         */
        B block();

        default IBlockState getDefaultState() {
            return block().getDefaultState();
        }

        @SideOnly(Side.CLIENT)
        @Override
        @OverridingMethodsMustInvokeSuper
        default void initializeClient() {
            block().initializeClient();
        }

        @Override
        @OverridingMethodsMustInvokeSuper
        default void defineRecipes() {
            block().defineRecipes();
        }

        @Override
        @OverridingMethodsMustInvokeSuper
        default void finalizeDefinition() {
            block().finalizeDefinition();
        }

        @Override
        @OverridingMethodsMustInvokeSuper
        @SideOnly(Side.CLIENT)
        default void finalizeClient() {
            block().finalizeClient();
        }
    }

    interface IContainerItemedBlock<B extends Block & IRailcraftObject<Block>, I extends Item & IRailcraftObject<Item>> extends IContainerBlock<B>, IContainerItem<I> {
        @Override
        @OverridingMethodsMustInvokeSuper
        @SideOnly(Side.CLIENT)
        default void initializeClient() {
            IContainerBlock.super.initializeClient();
            IContainerItem.super.initializeClient();
        }

        @Override
        default void defineRecipes() {
            IContainerBlock.super.defineRecipes();
            IContainerItem.super.defineRecipes();
        }

        @Override
        default void finalizeDefinition() {
            IContainerBlock.super.finalizeDefinition();
            IContainerItem.super.finalizeDefinition();
        }

        @Override
        @SideOnly(Side.CLIENT)
        default void finalizeClient() {
            IContainerBlock.super.finalizeClient();
            IContainerItem.super.finalizeClient();
        }
    }

    /**
     * Lets see if we can remove some boilerplate with this.
     * <p>
     * Created by CovertJaguar on 3/24/2016.
     */
    interface IContainerItem<T extends Item & IRailcraftObject<Item>> extends IRailcraftObjectContainer, IRailcraftRecipeIngredientContainer {
        /**
         * Gets the item. Fails if the container is disabled.
         */
        T item();

        @Override
        @OverridingMethodsMustInvokeSuper
        @SideOnly(Side.CLIENT)
        default void initializeClient() {
            item().initializeClient();
        }

        @OverridingMethodsMustInvokeSuper
        @Override
        default void defineRecipes() {
            item().defineRecipes();
        }

        @OverridingMethodsMustInvokeSuper
        @Override
        default void finalizeDefinition() {
            item().finalizeDefinition();
        }

        @OverridingMethodsMustInvokeSuper
        @Override
        @SideOnly(Side.CLIENT)
        default void finalizeClient() {
            item().finalizeClient();
        }

        default ItemStack getStack() {
            return new ItemStack(item());
        }

        default ItemStack getStack(int quantity) {
            return new ItemStack(item(), quantity);
        }

        @Override
        default Object getRecipeObject() {
            return getStack();
        }
    }

    /**
     * Can be combined
     */
    interface IContainerItemVariant<T extends Item & IRailcraftObject.WithVariant<Item, V>, V extends Enum<V> & IVariantEnum> extends IContainerItem<T> {

        /**
         * Gets the variant enum. Always not {@code null}.
         */
        Class<? extends V> getVariantEnum();

        default boolean isEnabled(V variant) {
            return isEnabled() && variant.isEnabled();
        }

        default ItemStack getStack(V variant) {
            return item().getStack(variant);
        }

        default ItemStack getStack(int quantity, V variant) {
            return item().getStack(quantity, variant);
        }
    }

    interface IContainerBlockVariant<B extends Block & IRailcraftObject.WithVariant<Block, V>, I extends Item & IRailcraftObject.WithVariant<Item, V>, V extends Enum<V> & IVariantEnum> extends IContainerItemedBlock<B, I>, IContainerItemVariant<I, V> {

        IBlockState getState(V variant);
    }

    interface IContainerPotion extends IRailcraftObjectContainer {
        Potion potion();
    }

    interface IContainerPotionType extends IRailcraftObjectContainer {
        PotionType potionType();
    }

    interface IContainerTrackKit extends IRailcraftObjectContainer {
        TrackKit trackKit();

        ItemStack getStack(int qty);

        default ItemStack getStack() {
            return getStack(1);
        }

    }

}
