/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2016
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/

package mods.railcraft.common.items;

import mods.railcraft.api.core.IVariantEnum;
import mods.railcraft.common.core.RailcraftConstants;
import mods.railcraft.common.plugins.forge.CreativePlugin;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by CovertJaguar on 7/18/2016 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class ItemRailcraftSubtyped<V extends Enum<V> & IVariantEnum> extends ItemRailcraft implements IRailcraftItemSimple.WithVariant<V> {
    private final Class<V> variantClass;
    private final V[] variantValues;

    public ItemRailcraftSubtyped(Class<V> variantClass) {
        this.variantClass = variantClass;
        this.variantValues = variantClass.getEnumConstants();
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public Class<V> getVariantEnum() {
        return variantClass;
    }

    @Override
    public V[] getVariants() {
        return variantValues;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (V variant : variantValues) {
            CreativePlugin.addToList(list, getStack(variant));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int damage = stack.getItemDamage();
        V[] variants = variantValues;
        if (damage < 0 || damage >= variants.length)
            return getUnlocalizedName();
        String tag = getUnlocalizedName() + RailcraftConstants.SEPERATOR + variants[damage].getResourcePathSuffix();
        return LocalizationPlugin.convertTag(tag);
    }

    @Override
    public String getOreTag(V variant) {
        if (variant.isEnabled())
            return variant.getOreTag();
        return null;
    }
}
