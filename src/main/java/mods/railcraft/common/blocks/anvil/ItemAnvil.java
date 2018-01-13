/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2016
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/

package mods.railcraft.common.blocks.anvil;

import mods.railcraft.common.blocks.IRailcraftBlock;
import mods.railcraft.common.blocks.IRailcraftItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAnvilBlock;

/**
 * Created by CovertJaguar on 7/15/2016 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class ItemAnvil extends ItemAnvilBlock implements IRailcraftItemBlock, IRailcraftItemBlock.WithVariant<BlockRCAnvil.DamageState> {

    IRailcraftBlock.WithVariant<BlockRCAnvil.DamageState> railcraftBlock;

    public <T extends Block & IRailcraftBlock.WithVariant<BlockRCAnvil.DamageState>> ItemAnvil(T block) {
        super(block);
        this.railcraftBlock = block;
    }

    @Override
    public Item getObject() {
        return this;
    }

    @Override
    public Class<? extends BlockRCAnvil.DamageState> getVariantEnum() {
        return railcraftBlock.getVariantEnum();
    }

    @Override
    public BlockRCAnvil.DamageState[] getVariants() {
        return railcraftBlock.getVariants();
    }
}
