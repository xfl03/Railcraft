/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2017
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/

package mods.railcraft.common.carts;

import mods.railcraft.common.blocks.machine.worldspike.WorldspikeVariant;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.plugins.forge.CraftingPlugin;
import net.minecraft.init.Items;

/**
 * Created by CovertJaguar on 8/30/2016 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class ItemCartWorldspikePersonal extends ItemCartWorldspike {
    public ItemCartWorldspikePersonal(IRailcraftCartContainer cartType) {
        super(cartType);
    }

    @Override
    public void finalizeDefinition() {
        if (RailcraftConfig.canCraftPersonalWorldspikes()) {
            CraftingPlugin.addRecipe(getStack(),
                    "A",
                    "M",
                    'A', WorldspikeVariant.PERSONAL.getStack(),
                    'M', Items.MINECART);
        }
    }
}
