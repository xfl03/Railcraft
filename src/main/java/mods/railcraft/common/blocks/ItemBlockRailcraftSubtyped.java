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
import mods.railcraft.common.core.RailcraftConstants;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * @author CovertJaguar <http://www.railcraft.info/>
 */
public class ItemBlockRailcraftSubtyped<V extends Enum<V> & IVariantEnum> extends ItemBlockRailcraft implements IRailcraftItemBlock.WithVariant<V> {

    IRailcraftBlock.WithVariant<V> variedBlock;

    public <T extends Block & IRailcraftBlock.WithVariant<V>> ItemBlockRailcraftSubtyped(T block) {
        super(block);
        this.variedBlock = block;
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public Class<? extends V> getVariantEnum() {
        return variedBlock.getVariantEnum();
    }

    @Override
    public V[] getVariants() {
        return variedBlock.getVariants();
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        IVariantEnum variant = getVariant(stack);
        if (variant == null)
            return getUnlocalizedName();
        String tag = getUnlocalizedName() + RailcraftConstants.SEPERATOR + variant.getResourcePathSuffix();
        return LocalizationPlugin.convertTag(tag);
    }
}
