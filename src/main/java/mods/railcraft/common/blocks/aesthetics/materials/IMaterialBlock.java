/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2016
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/

package mods.railcraft.common.blocks.aesthetics.materials;

import mods.railcraft.api.core.IVariantEnum;
import mods.railcraft.common.blocks.IRailcraftBlock;
import mods.railcraft.common.items.ItemMaterials;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

/**
 * Created by CovertJaguar on 7/13/2016 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public interface IMaterialBlock extends IRailcraftBlock.WithVariant<Materials> {

    @Override
    default Class<Materials> getVariantEnum() {
        return Materials.class;
    }

    @Override
    default ItemStack getStack(int qty, Materials variant) {
        return Materials.getStack(getObject(), qty, variant);
    }

    /**
     * Materials are represented with an extended block state.
     */
    @Override
    default IBlockState getState(Materials variant) {
        return getObject().getDefaultState();
    }

    String getUnlocalizedName(Materials mat);
}
