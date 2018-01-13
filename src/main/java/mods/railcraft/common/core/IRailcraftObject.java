/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2017
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/

package mods.railcraft.common.core;

import com.google.common.base.Preconditions;
import mods.railcraft.api.core.IVariantEnum;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * All Railcraft Items and Blocks should implement this.
 *
 * Created by CovertJaguar on 3/14/2016.
 */
@SuppressWarnings("unused")
public interface IRailcraftObject<T extends IForgeRegistryEntry<T>> extends IForgeRegistryEntry<T> {

    /**
     * Gets the object itself. Should be overridden at best.
     *
     * @return The object itself
     */
    @SuppressWarnings("unchecked")
    default T getObject() {
        return (T) this;
    }

    /**
     * Do pre initialization stage works here.
     */
    @OverridingMethodsMustInvokeSuper
    default void initializeDefinition() {
    }

    /**
     * Do initialization stage works here.
     */
    @OverridingMethodsMustInvokeSuper
    default void defineRecipes() {
    }

    /**
     * Do post initialization stage works here.
     */
    @OverridingMethodsMustInvokeSuper
    default void finalizeDefinition() {
    }

    /**
     * Do client-side pre initialization works here.
     */
    @SideOnly(Side.CLIENT)
    @OverridingMethodsMustInvokeSuper
    default void initializeClient() {
    }

    /**
     * Do client-side post initialization works here.
     */
    @SideOnly(Side.CLIENT)
    @OverridingMethodsMustInvokeSuper
    default void finalizeClient() {
    }

    default String getResourcePath() {
        return getObject().getRegistryName().getResourcePath();
    }

    /**
     * Represents objects that have item forms.
     */
    interface RecipeIngredient<T extends IForgeRegistryEntry<T>> extends IRailcraftObject<T> {

        ItemStack makeStack(int quantity, int meta);

        default ItemStack getStack() {
            return getStack(1);
        }

        default ItemStack getStack(int qty) {
            return makeStack(qty, 0);
        }

        default ItemStack getWildcard() {
            return makeStack(1, OreDictionary.WILDCARD_VALUE);
        }
    }

    /**
     * Represents objects with variants as meta data.
     */
    interface WithVariant<T extends IForgeRegistryEntry<T>, V extends Enum<V> & IVariantEnum> extends RecipeIngredient<T> {

        Class<? extends V> getVariantEnum();

        default ResourceLocation getRegistryName(V variant) {
            checkVariant(variant);
            return new ResourceLocation(getRegistryName().getResourceDomain(),
                    getRegistryName().getResourcePath() + "." + variant.getResourcePathSuffix());
        }

        default ItemStack getStack(V variant) {
            return getStack(1, variant);
        }

        default ItemStack getStack(int qty, V variant) {
            checkVariant(variant);
            return makeStack(qty, variant.ordinal());
        }

        default Object getRecipeObject(V variant) {
            return getStack(1, variant);
        }

        @Contract("null -> fail")
        default V checkVariant(@Nullable IVariantEnum variant) {
            Class<?> clazz = Preconditions.checkNotNull(variant).getClass();
            if (clazz.isAnonymousClass())
                clazz = clazz.getEnclosingClass(); // Subclass enums
            Class<? extends V> variantType = getVariantEnum();
            if (variantType != clazz)
                throw new RuntimeException("Incorrect Variant object used.");
            if (!variant.isEnabled())
                throw new RuntimeException("Variant is disabled!");
            return variantType.cast(variant);
        }

        default V[] getVariants() {
            Class<? extends V> variantEnum = getVariantEnum();
            return variantEnum.getEnumConstants();
        }
    }
}
