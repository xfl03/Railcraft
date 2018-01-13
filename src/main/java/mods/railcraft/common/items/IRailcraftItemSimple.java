/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2017
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/

package mods.railcraft.common.items;

import mods.railcraft.api.core.IVariantEnum;
import mods.railcraft.client.render.models.resource.ModelManager;
import mods.railcraft.common.core.RailcraftConstants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by CovertJaguar on 7/18/2016 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public interface IRailcraftItemSimple extends IRailcraftItem {

    @Override
    @SideOnly(Side.CLIENT)
    default void initializeClient() {
        ModelManager.registerItemModel(getObject(), 0);
    }

    interface WithVariant<V extends Enum<V> & IVariantEnum> extends IRailcraftItemSimple, IRailcraftItem.WithVariant<V> {
        @Override
        @SideOnly(Side.CLIENT)
        default void initializeClient() {
            V[] variants = getVariants();
            for (int i = 0, variantsLength = variants.length; i < variantsLength; i++) {
                V variant = variants[i];
                ModelManager.registerItemModel(getObject(), i, getResourcePath() + RailcraftConstants.SEPERATOR + variant.getResourcePathSuffix());
            }
        }
    }

}
