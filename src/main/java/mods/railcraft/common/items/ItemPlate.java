/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2017
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/
package mods.railcraft.common.items;

import mods.railcraft.api.crafting.RailcraftCraftingManager;
import mods.railcraft.common.core.IRailcraftObjectContainer;

import static mods.railcraft.common.items.Metal.*;

public class ItemPlate extends ItemMetal {

    public ItemPlate() {
        super(Form.PLATE, true, false, IRON, STEEL, TIN, COPPER, LEAD, SILVER, BRONZE, GOLD, NICKEL, INVAR, ZINC, BRASS);
    }

    @Override
    public void defineRecipes() {
        IRailcraftObjectContainer.IContainerItemVariant<?, Metal> plate = RailcraftItems.PLATE;

        for (Metal m : getMetalBiMap().values()) {
            RailcraftCraftingManager.rollingMachine.addRecipe(plate.getStack(4, m),
                    "II",
                    "II",
                    'I', m.getOreTag(Form.INGOT));
        }

        RailcraftCraftingManager.blastFurnace.addRecipe(plate.getStack(Metal.IRON), true, false, 1280, Metal.STEEL.getStack(Form.INGOT));
    }
}
