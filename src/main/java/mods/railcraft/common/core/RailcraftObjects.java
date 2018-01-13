/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2017
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/

package mods.railcraft.common.core;

import mods.railcraft.api.core.IVariantEnum;
import mods.railcraft.common.blocks.IRailcraftBlock;
import mods.railcraft.common.blocks.IRailcraftItemBlock;
import mods.railcraft.common.blocks.ItemBlockRailcraftSubtyped;
import mods.railcraft.common.items.IRailcraftItemSimple;
import mods.railcraft.common.modules.RailcraftModuleManager;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by CovertJaguar on 3/29/2017 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public final class RailcraftObjects {

    private static Set<Object> simpleItemContainers;
    private static Set<Object> variantItemContainers;
    private static Collection<Object> blockContainers;
    private static Collection<Object> blockItemContainers;
    private static Collection<Object> variantBlockContainers;

    private static void loadItemContainers() {
        if (RailcraftModuleManager.getStage().compareTo(RailcraftModuleManager.Stage.INIT) < 0)
            throw new IllegalStateException("access containers too early!");
        simpleItemContainers = RailcraftModuleManager.getEnabledContainers().stream()
                .filter(c -> c instanceof IRailcraftObjectContainer.IContainerItem)
                .filter(c -> !(c instanceof IRailcraftObjectContainer.IContainerItemedBlock))
                .collect(Collectors.toSet());
    }

    private static void loadVariantItemContainers() {
        if (RailcraftModuleManager.getStage().compareTo(RailcraftModuleManager.Stage.INIT) < 0)
            throw new IllegalStateException("access containers too early!");
        variantItemContainers = getItems().stream()
                .filter(c -> c instanceof IRailcraftObjectContainer.IContainerItemVariant)
                .collect(Collectors.toSet());
    }

    private static void loadBlockContainers() {
        blockContainers = RailcraftModuleManager.getEnabledContainers().stream()
                .filter(c -> c instanceof IRailcraftObjectContainer.IContainerBlock)
                .collect(Collectors.toList());
    }

    private static void loadBlockItemContainers() {
        if (blockContainers == null)
            loadBlockContainers();
        blockItemContainers = blockContainers.stream()
                .filter(c -> c instanceof IRailcraftObjectContainer.IContainerItemedBlock)
                .collect(Collectors.toList());
    }

    private static void loadVariantBlockContainers() {
        if (blockContainers == null)
            loadBlockContainers();
        variantBlockContainers = blockContainers.stream()
                .filter(c -> c instanceof IRailcraftObjectContainer.IContainerBlockVariant)
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private static <T extends Item & IRailcraftItemSimple> Set<IRailcraftObjectContainer.IContainerItem<T>> getItems() {
        if (simpleItemContainers == null)
            loadItemContainers();
        return (Set<IRailcraftObjectContainer.IContainerItem<T>>) (Object) simpleItemContainers;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Item & IRailcraftItemSimple.WithVariant<? extends IVariantEnum>> Set<IRailcraftObjectContainer.IContainerItemVariant<T, ? extends IVariantEnum>> getVariantItems() {
        if (variantItemContainers == null)
            loadVariantItemContainers();
        return (Set<IRailcraftObjectContainer.IContainerItemVariant<T, ? extends IVariantEnum>>) (Object) variantItemContainers;
    }

    @SuppressWarnings("unchecked")
    private static <B extends Block & IRailcraftBlock> Collection<IRailcraftObjectContainer.IContainerBlock<B>> getBlockContainers() {
        if (blockContainers == null)
            loadBlockContainers();
        return (Collection<IRailcraftObjectContainer.IContainerBlock<B>>) (Object) blockContainers;
    }

    @SuppressWarnings("unchecked")
    private static <B extends Block & IRailcraftBlock, I extends Item & IRailcraftItemBlock> Collection<IRailcraftObjectContainer.IContainerItemedBlock<B, I>> getBlockItemContainers() {
        if (blockItemContainers == null)
            loadBlockItemContainers();
        return (Collection<IRailcraftObjectContainer.IContainerItemedBlock<B, I>>) (Object) blockItemContainers;
    }

    @SuppressWarnings("unchecked")
    private static <B extends Block & IRailcraftBlock.WithVariant<? extends IVariantEnum>, I extends Item & IRailcraftItemBlock.WithVariant<? extends IVariantEnum>> Collection<IRailcraftObjectContainer.IContainerBlockVariant<B, I, ? extends IVariantEnum>> getVariantBlocks() {
        if (variantBlockContainers == null)
            loadVariantBlockContainers();
        return (Set<IRailcraftObjectContainer.IContainerBlockVariant<B, I, ? extends IVariantEnum>>) (Object) variantBlockContainers;
    }

    public static <B extends Block & IRailcraftBlock, I extends Item & IRailcraftItemBlock, V extends Block & IRailcraftBlock.WithVariant<? extends IVariantEnum>> void processBlocks(
            @Nullable Consumer<? super B> processBlock,
            @Nullable Consumer<? super I> processItem,
            @Nullable BiConsumer<? super V, ? super IVariantEnum> processVariants
    ) {
        // these generics are required! random generic for J just to compile
        RailcraftObjects.<B, I, V, ItemBlockRailcraftSubtyped<?>>processBlockContainers(
                processBlock == null ? null : c -> processBlock.accept(c.block()),
                processItem == null ? null : c -> processItem.accept(c.item()),
                processVariants == null ? null : (c, d) -> processVariants.accept(c.block(), d))
        ;
    }

    @SuppressWarnings("unchecked")
    public static <B extends Block & IRailcraftBlock, I extends Item & IRailcraftItemBlock, V extends Block & IRailcraftBlock.WithVariant<? extends IVariantEnum>, J extends Item & IRailcraftItemBlock.WithVariant<? extends IVariantEnum>> void processBlockContainers(
            @Nullable Consumer<? super IRailcraftObjectContainer.IContainerBlock<? extends B>> processBlock,
            @Nullable Consumer<? super IRailcraftObjectContainer.IContainerItemedBlock<? extends B, ? extends I>> processItem,
            @Nullable BiConsumer<? super IRailcraftObjectContainer.IContainerBlockVariant<? extends V, ? extends J, ? extends IVariantEnum>, ? super IVariantEnum> processVariants
    ) {
        if (processBlock != null) {
            RailcraftObjects.<B>getBlockContainers().forEach(processBlock);
        }

        if (processItem != null) {
            RailcraftObjects.<B, I>getBlockItemContainers().forEach(processItem);
        }

        if (processVariants != null) {
            for (IRailcraftObjectContainer.IContainerBlockVariant<V, J, ? extends IVariantEnum> variantBlockContainer : RailcraftObjects.<V, J>getVariantBlocks()) {
                V block = variantBlockContainer.block();
                for (IVariantEnum variant : block.getVariants()) {
                    processVariants.accept(variantBlockContainer, variant);
                }
            }
        }
    }

    public static <V extends Block & IRailcraftBlock.WithVariant<? extends IVariantEnum>> void processBlockVariants(BiConsumer<? super V, ? super IVariantEnum> processVariants) {
        processBlocks(null, null, processVariants);
    }

    public static <T extends Item & IRailcraftItemSimple> void processItems(Consumer<? super T> processItem) {
        // really funky, but the T before the method cannot be implied!
        for (IRailcraftObjectContainer.IContainerItem<T> itemContainer : RailcraftObjects.<T>getItems()) {
            processItem.accept(itemContainer.item());
        }
    }

    public static <T extends Item & IRailcraftItemSimple> void processItemContainers(Consumer<? super IRailcraftObjectContainer.IContainerItem<T>> processItem) {
        // really funky, but the T before the method cannot be implied!
        for (IRailcraftObjectContainer.IContainerItem<T> itemContainer : RailcraftObjects.<T>getItems()) {
            processItem.accept(itemContainer);
        }
    }

    public static <T extends Item & IRailcraftItemSimple.WithVariant<? extends IVariantEnum>> void processItemVariants(BiConsumer<? super T, ? super IVariantEnum> processVariants) {
        for (IRailcraftObjectContainer.IContainerItemVariant<T, ? extends IVariantEnum> itemContainer : RailcraftObjects.<T>getVariantItems()) {
//            itemContainer.getObject().ifPresent(item ->
//            {
//                IVariantEnum[] variants = item.getVariants();
//                if (variants != null) {
//                    for (IVariantEnum variant : variants) {
//                        processVariants.accept(item, variant);
//                    }
//                } else processVariants.accept(item, null);
//            });
            T item = itemContainer.item();
            for (IVariantEnum variant : item.getVariants()) {
                processVariants.accept(item, variant);
            }
        }
    }
}
