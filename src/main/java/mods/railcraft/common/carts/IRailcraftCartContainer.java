/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2017
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/

package mods.railcraft.common.carts;

import mods.railcraft.common.core.IRailcraftObjectContainer;
import mods.railcraft.common.items.IRailcraftItemSimple;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Defines a cart.
 * Created by CovertJaguar on 9/19/2015.
 */
public interface IRailcraftCartContainer extends IRailcraftObjectContainer {

    String getEntityLocalizationTag();

    Class<? extends EntityMinecart> getCartClass();

    EntityMinecart makeCart(ItemStack stack, World world, double i, double j, double k);

    interface WithItem<T extends Item & IRailcraftItemSimple> extends IRailcraftCartContainer, IRailcraftObjectContainer.IContainerItem<T> {
        // The contents for crafting
        @Nullable
        ItemStack getContents();
    }

}
