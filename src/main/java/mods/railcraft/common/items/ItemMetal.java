/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2016
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/
package mods.railcraft.common.items;

import com.google.common.collect.BiMap;
import mods.railcraft.api.core.IVariantEnum;
import mods.railcraft.common.plugins.forestry.ForestryPlugin;
import mods.railcraft.common.plugins.forge.RailcraftRegistry;
import mods.railcraft.common.util.collections.CollectionTools;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import scala.tools.cmd.Meta;

import javax.annotation.Nullable;
import java.util.Map;

import static mods.railcraft.common.items.Metal.Form;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public abstract class ItemMetal extends ItemRailcraftSubtyped<Metal> {
    private final BiMap<Integer, Metal> metalBiMap;
    private final Metal[] variantArray;
    private final Form<Metal> form;
    private final boolean registerOreDict;
    private final boolean registerMinerBackpack;

    protected ItemMetal(Form<Metal> form, boolean registerOreDict, boolean registerMinerBackpack, Metal... variants) {
        super(Metal.class);
        this.form = form;
        this.registerOreDict = registerOreDict;
        this.registerMinerBackpack = registerMinerBackpack;
        this.metalBiMap = CollectionTools.createIndexedLookupTable(variants);
        this.variantArray = variants;
    }

    @Override
    public Metal[] getVariants() {
        return variantArray;
    }

    public final BiMap<Integer, Metal> getMetalBiMap() {
        return metalBiMap;
    }

    @Override
    public void initializeDefinition() {
        for (Map.Entry<Integer, Metal> entry : getMetalBiMap().entrySet()) {
            ItemStack stack = new ItemStack(this, 1, entry.getKey());
            RailcraftRegistry.register(this, entry.getValue(), stack);
        }
        if (registerOreDict)
            for (Metal m : variantArray) {
                OreDictionary.registerOre(m.getOreTag(form), m.getStack(form));
            }
        if (registerMinerBackpack)
            for (int i = 0; i < variantArray.length; i++) {
                ItemStack stack = new ItemStack(this, 1, i);
                ForestryPlugin.addBackpackItem("forestry.miner", stack);
            }
    }

    @Override
    public ItemStack getStack(int qty, Metal variant) {
        checkVariant(variant);
        int meta = metalBiMap.inverse().getOrDefault((Metal) variant, 0);
        return new ItemStack(this, qty, meta);
    }

    @Override
    public String getOreTag(Metal variant) {
        checkVariant(variant);
        return ((Metal) variant).getOreTag(form);
    }

}
