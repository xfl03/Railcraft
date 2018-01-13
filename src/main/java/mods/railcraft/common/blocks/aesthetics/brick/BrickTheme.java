/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2017
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/
package mods.railcraft.common.blocks.aesthetics.brick;

import mods.railcraft.api.crafting.ICrusherCraftingManager;
import mods.railcraft.api.crafting.RailcraftCraftingManager;
import mods.railcraft.common.blocks.aesthetics.generic.EnumGeneric;
import mods.railcraft.common.core.IRailcraftObjectContainer;
import mods.railcraft.common.plugins.forge.CraftingPlugin;
import mods.railcraft.common.plugins.forge.RailcraftRegistry;
import mods.railcraft.common.plugins.misc.MicroBlockPlugin;
import mods.railcraft.common.util.inventory.InvTools;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Locale;

import static com.google.common.base.Preconditions.checkNotNull;
import static mods.railcraft.common.blocks.aesthetics.brick.BrickVariant.BLOCK;
import static mods.railcraft.common.blocks.aesthetics.brick.BrickVariant.COBBLE;

/**
 * The Brick Themes (clever, I know)
 * Created by CovertJaguar on 3/12/2015.
 */
public enum BrickTheme implements IRailcraftObjectContainer.IContainerBlockVariant<BlockBrick, ItemBrick, BrickVariant> {
    ABYSSAL(MapColor.BLACK) {
        @Override
        public void initRecipes(BlockBrick block) {
            if (EnumGeneric.STONE_ABYSSAL.isEnabled()) {
                CraftingPlugin.addRecipe(new ItemStack(block, 1, 2),
                        "II",
                        "II",
                        'I', EnumGeneric.STONE_ABYSSAL.getStack());
                ItemStack abyssalStone = EnumGeneric.STONE_ABYSSAL.getStack();
                if (!InvTools.isEmpty(abyssalStone)) {
                    ICrusherCraftingManager.ICrusherRecipe recipe = RailcraftCraftingManager.rockCrusher.createAndAddRecipe(abyssalStone, true, false);
                    recipe.addOutput(getStack(1, COBBLE), 1.0F);
                }
            }
        }
    },
    BLEACHEDBONE(MapColor.ADOBE) {
        @Override
        public void initRecipes(BlockBrick block) {
            CraftingPlugin.addFurnaceRecipe(new ItemStack(Blocks.BONE_BLOCK), new ItemStack(block, 2, 2), 0.3F);
        }
    },
    BLOODSTAINED(MapColor.RED) {
        @Override
        public void initRecipes(BlockBrick block) {
            CraftingPlugin.addShapelessRecipe(new ItemStack(block, 1, 2), new ItemStack(Blocks.SANDSTONE, 1, 2), new ItemStack(Items.ROTTEN_FLESH));
            CraftingPlugin.addShapelessRecipe(new ItemStack(block, 1, 2), new ItemStack(Blocks.SANDSTONE, 1, 2), new ItemStack(Items.BEEF));
        }
    },
    FROSTBOUND(MapColor.BLUE) {
        @Override
        public void initRecipes(BlockBrick block) {
            CraftingPlugin.addRecipe(new ItemStack(block, 8, 2),
                    "III",
                    "ILI",
                    "III",
                    'I', new ItemStack(Blocks.ICE),
                    'L', "gemLapis");
            CraftingPlugin.addRecipe(new ItemStack(block, 8, 2),
                    "III",
                    "ILI",
                    "III",
                    'I', new ItemStack(Blocks.PACKED_ICE),
                    'L', "gemLapis");
        }
    },
    INFERNAL(MapColor.GRAY) {
        @Override
        public void initRecipes(BlockBrick block) {
//            ((ReplacerCube) EnumCube.INFERNAL_BRICK.getBlockDef()).replacementState = getBlock().getDefaultState().withProperty(BlockBrick.VARIANT, BrickVariant.BRICK);
            CraftingPlugin.addRecipe(new ItemStack(block, 2, 2),
                    "MB",
                    "BM",
                    'B', new ItemStack(Blocks.NETHER_BRICK),
                    'M', new ItemStack(Blocks.SOUL_SAND));
        }
    },
    QUARRIED(MapColor.SNOW) {
        @Override
        public void initRecipes(BlockBrick block) {
            if (EnumGeneric.STONE_QUARRIED.isEnabled()) {
                CraftingPlugin.addRecipe(new ItemStack(block, 1, 2),
                        "II",
                        "II",
                        'I', EnumGeneric.STONE_QUARRIED.getStack());
                ItemStack quarriedStone = EnumGeneric.STONE_QUARRIED.getStack();
                if (!InvTools.isEmpty(quarriedStone)) {
                    ICrusherCraftingManager.ICrusherRecipe recipe = RailcraftCraftingManager.rockCrusher.createAndAddRecipe(quarriedStone, true, false);
                    recipe.addOutput(getStack(1, COBBLE), 1.0F);
                }
            }
        }
    },
    SANDY(MapColor.SAND) {
        @Override
        public void initRecipes(BlockBrick block) {
//            ((ReplacerCube) EnumCube.SANDY_BRICK.getBlockDef()).replacementState = getBlock().getDefaultState().withProperty(BlockBrick.VARIANT, BrickVariant.BRICK);
            CraftingPlugin.addRecipe(new ItemStack(block, 1, 2),
                    "BM",
                    "MB",
                    'B', "ingotBrick",
                    'M', new ItemStack(Blocks.SAND));
        }
    },
    RED_SANDY(MapColor.DIRT) {
        @Override
        public void initRecipes(BlockBrick block) {
            CraftingPlugin.addRecipe(new ItemStack(block, 1, 2),
                    "BM",
                    "MB",
                    'B', "ingotBrick",
                    'M', new ItemStack(Blocks.SAND, 1, 1));
        }
    },
    NETHER(MapColor.NETHERRACK) {
        @Override
        public ItemStack getStack(int qty, BrickVariant variant) {
            if (variant == BrickVariant.BRICK)
                return new ItemStack(Blocks.NETHER_BRICK, qty);
            return super.getStack(qty, variant);
        }

//        @Nullable
//        @Override
//        public ItemStack getStack(int qty, int meta) {
//            if (BrickVariant.fromOrdinal(meta) == BrickVariant.BRICK)
//                return new ItemStack(Blocks.NETHER_BRICK, qty);
//            return super.getStack(qty, meta);
//        }

        @Override
        public IBlockState getState(BrickVariant variant) {
            if (variant == BrickVariant.BRICK)
                return Blocks.NETHER_BRICK.getDefaultState();
            return super.getState(variant);
        }

        @Override
        public void initRecipes(BlockBrick block) {
            CraftingPlugin.addFurnaceRecipe(new ItemStack(Blocks.NETHER_BRICK), getStack(1, BLOCK), 0);
        }

        @Override
        protected void initVariant(BlockBrick block, BrickVariant variant) {
            if (variant != BrickVariant.BRICK)
                super.initVariant(block, variant);
        }

    },
    RED_NETHER(MapColor.NETHERRACK) {
        @Override
        public ItemStack getStack(int qty, BrickVariant variant) {
            if (variant == BrickVariant.BRICK)
                return new ItemStack(Blocks.RED_NETHER_BRICK, qty);
            return super.getStack(qty, variant);
        }

//        @Nullable
//        @Override
//        public ItemStack getStack(int qty, int meta) {
//            if (BrickVariant.fromOrdinal(meta) == BrickVariant.BRICK)
//                return new ItemStack(Blocks.RED_NETHER_BRICK, qty);
//            return super.getStack(qty, meta);
//        }

        @Override
        public IBlockState getState(BrickVariant variant) {
            if (variant == BrickVariant.BRICK)
                return Blocks.RED_NETHER_BRICK.getDefaultState();
            return super.getState(variant);
        }

        @Override
        public void initRecipes(BlockBrick block) {
            CraftingPlugin.addFurnaceRecipe(new ItemStack(Blocks.RED_NETHER_BRICK), getStack(1, BLOCK), 0);
        }

        @Override
        protected void initVariant(BlockBrick block, BrickVariant variant) {
            if (variant != BrickVariant.BRICK)
                super.initVariant(block, variant);
        }

    },
    ANDESITE(MapColor.STONE) {
        @Override
        public ItemStack getStack(int qty, BrickVariant variant) {
            if (variant == BrickVariant.BLOCK)
                return new ItemStack(Blocks.STONE, qty, 6);
            return super.getStack(qty, variant);
        }

//        @Nullable
//        @Override
//        public ItemStack getStack(int qty, int meta) {
//            if (BrickVariant.fromOrdinal(meta) == BrickVariant.BLOCK)
//                return new ItemStack(Blocks.STONE, qty, 6);
//            return super.getStack(qty, meta);
//        }

        @Override
        public IBlockState getState(BrickVariant variant) {
            if (variant == BrickVariant.BLOCK)
                return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE_SMOOTH);
            return super.getState(variant);
        }

        @Override
        protected void initVariant(BlockBrick block, BrickVariant variant) {
            if (variant != BrickVariant.BLOCK)
                super.initVariant(block, variant);
        }
    },
    DIORITE(MapColor.QUARTZ) {
        @Override
        public ItemStack getStack(int qty, BrickVariant variant) {
            if (variant == BrickVariant.BLOCK)
                return new ItemStack(Blocks.STONE, qty, 4);
            return super.getStack(qty, variant);
        }

//        @Nullable
//        @Override
//        public ItemStack getStack(int qty, int meta) {
//            if (BrickVariant.fromOrdinal(meta) == BrickVariant.BLOCK)
//                return new ItemStack(Blocks.STONE, qty, 4);
//            return super.getStack(qty, meta);
//        }

        @Override
        public IBlockState getState(BrickVariant variant) {
            if (variant == BrickVariant.BLOCK)
                return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE_SMOOTH);
            return super.getState(variant);
        }

        @Override
        protected void initVariant(BlockBrick block, BrickVariant variant) {
            if (variant != BrickVariant.BLOCK)
                super.initVariant(block, variant);
        }
    },
    GRANITE(MapColor.DIRT) {
        @Override
        public ItemStack getStack(int qty, BrickVariant variant) {
            if (variant == BrickVariant.BLOCK)
                return new ItemStack(Blocks.STONE, qty, 2);
            return super.getStack(qty, variant);
        }

//        @Nullable
//        @Override
//        public ItemStack getStack(int qty, int meta) {
//            if (BrickVariant.fromOrdinal(meta) == BrickVariant.BLOCK)
//                return new ItemStack(Blocks.STONE, qty, 2);
//            return super.getStack(qty, meta);
//        }

        @Override
        public IBlockState getState(BrickVariant variant) {
            if (variant == BrickVariant.BLOCK)
                return Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE_SMOOTH);
            return super.getState(variant);
        }

        @Override
        protected void initVariant(BlockBrick block, BrickVariant variant) {
            if (variant != BrickVariant.BLOCK)
                super.initVariant(block, variant);
        }
    },
    PEARLIZED(MapColor.GREEN) {
        @Override
        public void initRecipes(BlockBrick block) {
            CraftingPlugin.addRecipe(new ItemStack(block, 8, 2),
                    "SSS",
                    "SPS",
                    "SSS",
                    'S', new ItemStack(Blocks.END_STONE),
                    'P', Items.ENDER_PEARL);
        }
    },;
    public static final BrickTheme[] VALUES = values();
    private final MapColor mapColor;
    private final Definition def;
    private BlockBrick block;
    private ItemBrick item;

    BrickTheme(MapColor mapColor) {
        this.def = new Definition(this, "brick_" + name().toLowerCase(Locale.ROOT));
        this.mapColor = mapColor;
    }

    @Override
    public Definition getDef() {
        return def;
    }

    @Override
    public void register() {
        ResourceLocation registryName = getRegistryName();
        block = new BlockBrick(this);
        block.setRegistryName(registryName);
        block.setUnlocalizedName("railcraft." + getBaseTag().replace("_", "."));
        item = new ItemBrick(block);
        item.setRegistryName(registryName);
        block.initializeDefinition();
        item.initializeDefinition();
        RailcraftRegistry.register(block, item);
    }



    @SideOnly(Side.CLIENT)
    @Override
    public void initializeClient() {
        block.initializeClient();
        item.initializeClient();
    }

    @Override
    public void defineRecipes() {
        block.defineRecipes();
        item.defineRecipes();
    }

    @Override
    public void finalizeDefinition() {
        block.finalizeDefinition();
        item.finalizeDefinition();
    }

    public final MapColor getMapColor() {
        return mapColor;
    }

    @Override
    public IBlockState getState(BrickVariant variant) {
        BlockBrick block = block();
        if (variant.isEnabled()) {
            return block.getDefaultState().withProperty(block.getVariantProperty(), variant);
        }
        return block.getDefaultState();
    }

    protected void initRecipes(BlockBrick block) {
    }

    protected void initVariant(BlockBrick block, BrickVariant variant) {
        MicroBlockPlugin.addMicroBlockCandidate(block, variant.ordinal());
    }

    @Override
    public Class<? extends BrickVariant> getVariantEnum() {
        return BrickVariant.class;
    }

    @Override
    public ItemStack getStack(int qty, BrickVariant variant) {
        BlockBrick blockBrick = block();
        int meta;
        if (variant.isEnabled()) {
            meta = variant.ordinal();
        } else
            meta = 0;
        return new ItemStack(blockBrick, qty, meta);
    }

    @Override
    public BlockBrick block() {
        if (isLoaded())
            return checkNotNull(block);
        throw new RuntimeException("object type disabled!");
    }

    @Override
    public ItemBrick item() {
        if (isLoaded())
            return checkNotNull(item);
        throw new RuntimeException("object type disabled!");
    }


//    @Nullable
//    @Override
//    public ItemStack getStack(int qty, int meta) {
//        BlockBrick blockBrick = getBlock();
//        if (blockBrick != null)
//            return new ItemStack(blockBrick, qty, meta);
//        return null;
//    }

}
