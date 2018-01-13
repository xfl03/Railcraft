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
import mods.railcraft.common.core.IRailcraftObjectContainer;
import mods.railcraft.common.items.IRailcraftItem;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

/**
 * Created by CovertJaguar on 7/26/2016 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public interface IRailcraftBlockContainer<B extends Block & IRailcraftBlock> extends IRailcraftObjectContainer, IRailcraftObjectContainer.IContainerBlock<B> {

    interface WithItemForm<B extends Block & IRailcraftBlock, I extends Item & IRailcraftItem> extends IRailcraftBlockContainer<B>, IRailcraftObjectContainer.IContainerItemedBlock<B, I> {

    }

    interface VariantContainer<B extends Block & IRailcraftBlock.WithVariant<V>, I extends Item & IRailcraftItem.WithVariant<V>, V extends Enum<V> & IVariantEnum> extends WithItemForm<B, I>, IRailcraftObjectContainer.IContainerBlockVariant<B, I, V> {
        default IBlockState getState(V variant) {
            return block().getState(variant);
        }
    }
}
