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
import mods.railcraft.common.blocks.machine.RailcraftBlockMetadata;
import mods.railcraft.common.plugins.forge.CreativePlugin;
import mods.railcraft.common.util.collections.ArrayTools;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by CovertJaguar on 4/13/2016 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public abstract class BlockRailcraftSubtyped<V extends Enum<V> & IVariantEnum> extends BlockRailcraft implements ISubtypedBlock<V> {
    private RailcraftBlockMetadata annotation;
    private Class<V> variantClass;
    private V[] variantValues;
    private PropertyEnum<V> variantProperty;

    protected BlockRailcraftSubtyped(Material materialIn) {
        this(materialIn, materialIn.getMaterialMapColor());
    }

    protected BlockRailcraftSubtyped(Material material, MapColor mapColor) {
        super(material, mapColor);
        //setup();//This 'setup' is called after 'createBlockState' which is called in super's <init>, so it is useless
    }

    private void setup() {
        if (annotation == null) {
            annotation = getClass().getAnnotation(RailcraftBlockMetadata.class);
            //noinspection unchecked
            this.variantClass = (Class<V>) annotation.variant();
            this.variantValues = variantClass.getEnumConstants();
            this.variantProperty = PropertyEnum.create("variant", variantClass, variantValues);
        }
    }

    @Override
    public final IProperty<V> getVariantProperty() {
        setup();//'setup' before 'createBlockState'
        return variantProperty;
    }

    @Override
    public final Class<? extends V> getVariantEnum() {
        return variantClass;
    }

    @Override
    public final V[] getVariants() {
        return variantValues;
    }

    @SuppressWarnings("unchecked")
    @Override
    public IBlockState getState(@Nullable IVariantEnum variant) {
        if (variant != null) {
            checkVariant(variant);
            return getDefaultState().withProperty(getVariantProperty(), (V) variant);
        }
        return getDefaultState();
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        V[] variants = getVariants();
        if (variants != null) {
            for (V variant : variants) {
                if (!variant.isDeprecated())
                    CreativePlugin.addToList(list, getStack(variant));
            }
        } else {
            CreativePlugin.addToList(list, getStack(null));
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return getStack(getVariant(state));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getVariant(state).ordinal();
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = getDefaultState();
        if (ArrayTools.indexInBounds(variantValues.length, meta))
            state = state.withProperty(getVariantProperty(), variantValues[meta]);
        return state;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(getVariantProperty()).ordinal();
    }

    @Override
    protected BlockStateContainer createBlockState() {
         return new BlockStateContainer(this, getVariantProperty());
    }

    @Override
    public boolean canBeReplacedByLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }
}
