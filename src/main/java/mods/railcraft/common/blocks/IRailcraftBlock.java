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
import mods.railcraft.client.render.models.resource.ModelManager;
import mods.railcraft.client.util.textures.TextureAtlasSheet;
import mods.railcraft.common.core.IRailcraftObject;
import mods.railcraft.common.core.RailcraftConfig;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by CovertJaguar on 7/16/2016 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public interface IRailcraftBlock extends IRailcraftObject<Block> {

    @SideOnly(Side.CLIENT)
    @Nullable
    default StateMapperBase getStateMapper() {
        return null;
    }

    @SideOnly(Side.CLIENT)
    @Override
    default void initializeClient() {
        StateMapperBase stateMapper = getStateMapper();
        if (stateMapper != null)
            ModelLoader.setCustomStateMapper(getObject(), stateMapper);
    }

    @SideOnly(Side.CLIENT)
    default ResourceLocation getBlockTexture() {
        return getRegistryName();
    }

    @SideOnly(Side.CLIENT)
    default void registerTextures(TextureMap textureMap) {
        TextureAtlasSheet.unstitchIcons(textureMap, getBlockTexture(), getTextureDimensions());
    }

    /**
     * The first integer is column count. The second one is row count.
     */
    @SideOnly(Side.CLIENT)
    default Tuple<Integer, Integer> getTextureDimensions() {
        return new Tuple<>(1, 1);
    }

    interface WithVariant<V extends Enum<V> & IVariantEnum> extends IRailcraftBlock, IRailcraftObject.WithVariant<Block, V> {
        IBlockState getState(V variant);

        default boolean isEnabled(V variant) {
            if (variant instanceof IVariantEnumBlockSpecific) {
                IVariantEnumBlockSpecific<?> specific = (IVariantEnumBlockSpecific<?>) variant;
                return specific.isEnabled();
            }
            return true;
        }

        @Override
        default ItemStack makeStack(int quantity, int meta) {
            return new ItemStack(getObject(), quantity, meta);
        }

        default IBlockState getItemRenderState(V variant) {
            return getState(variant);
        }

        @SideOnly(Side.CLIENT)
        default void registerItemModel(ItemStack stack, V variant) {
            ModelManager.registerBlockItemModel(stack, getItemRenderState(variant));
        }

        @SideOnly(Side.CLIENT)
        default void registerTextures(TextureMap textureMap) {
            TextureAtlasSheet.unstitchIcons(textureMap, getBlockTexture(), getTextureDimensions());
            IVariantEnum[] variants = getVariants();
            for (IVariantEnum variant : variants) {
                if (variant instanceof IVariantEnumBlockSpecific)
                    TextureAtlasSheet.unstitchIcons(textureMap,
                            new ResourceLocation(getRegistryName() + "_" + variant.getResourcePathSuffix()),
                            ((IVariantEnumBlockSpecific<?>) variant).getTextureDimensions());
            }
        }
    }
}
