package fr.barlords.mineralconquest.blocks.fusion.tileentity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import fr.barlords.mineralconquest.blocks.fusion.AbstractFusionFurnaceBlock;
import fr.barlords.mineralconquest.blocks.fusion.recipe.AbstractFusionCookingRecipe;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class AbstractFusionFurnaceTileEntity extends LockableTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {

    private static final int[] SLOTS_FOR_UP = new int[]{0};
    private static final int[] SLOTS_FOR_DOWN = new int[]{2, 1};
    private static final int[] SLOTS_FOR_SIDES = new int[]{1};

    protected static final int[] SLOTS_FOR_TOP = new int[]{4};
    protected static final int[] SLOTS_FOR_LEFT = new int[]{0};
    protected static final int[] SLOTS_FOR_RIGHT = new int[]{3};
    protected static final int[] SLOTS_FOR_BOT = new int[]{2};
    protected static final int[] SLOTS_FOR_BEHIND = new int[]{1};

    protected NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY);
    private int litTime;
    private int litDuration;
    private int cookingProgress;
    private int cookingTotalTime;
    protected final IIntArray dataAccess = new IIntArray() {
        public int get(int index) {
            switch(index) {
                case 0:
                    return AbstractFusionFurnaceTileEntity.this.litTime;
                case 1:
                    return AbstractFusionFurnaceTileEntity.this.litDuration;
                case 2:
                    return AbstractFusionFurnaceTileEntity.this.cookingProgress;
                case 3:
                    return AbstractFusionFurnaceTileEntity.this.cookingTotalTime;
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch(index) {
                case 0:
                    AbstractFusionFurnaceTileEntity.this.litTime = value;
                    break;
                case 1:
                    AbstractFusionFurnaceTileEntity.this.litDuration = value;
                    break;
                case 2:
                    AbstractFusionFurnaceTileEntity.this.cookingProgress = value;
                    break;
                case 3:
                    AbstractFusionFurnaceTileEntity.this.cookingTotalTime = value;
            }

        }

        public int getCount() {
            return items.size();
        }
    };
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    protected final IRecipeType<? extends AbstractFusionCookingRecipe> recipeType;

    protected AbstractFusionFurnaceTileEntity(TileEntityType<?> tileEntity, IRecipeType<? extends AbstractFusionCookingRecipe> recipe) {
        super(tileEntity);
        this.recipeType = recipe;
    }

    @Deprecated //Forge - get burn times by calling ForgeHooks#getBurnTime(ItemStack)
    public static Map<Item, Integer> getFuel() {
        Map<Item, Integer> map = Maps.newLinkedHashMap();

        add(map, Items.BLAZE_ROD, 2400);

        return map;
    }

    private static boolean isNeverAFurnaceFuel(Item item) {
        return ItemTags.NON_FLAMMABLE_WOOD.contains(item);
    }

    private static void add(Map<Item, Integer> map, ITag<Item> itemTag, int p_213992_2_) {
        for(Item item : itemTag.getValues()) {
            if (!isNeverAFurnaceFuel(item)) {
                map.put(item, p_213992_2_);
            }
        }

    }

    private static void add(Map<Item, Integer> map, IItemProvider itemProvider, int burnTime) {
        Item item = itemProvider.asItem();
        if (isNeverAFurnaceFuel(item)) {
            if (SharedConstants.IS_RUNNING_IN_IDE) {
                throw (IllegalStateException)Util.pauseInIde(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item.getName((ItemStack)null).getString() + " a furnace fuel. That will not work!"));
            }
        } else {
            map.put(item, burnTime);
        }
    }

    private boolean isLit() {
        return this.litTime > 0;
    }

    public void load(BlockState blockState, CompoundNBT compound) { //TODO: MARK
        super.load(blockState, compound);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.items);
        this.litTime = compound.getInt("BurnTime");
        this.cookingProgress = compound.getInt("CookTime");
        this.cookingTotalTime = compound.getInt("CookTimeTotal");
        this.litDuration = this.getBurnDuration(this.items.get(1));
        CompoundNBT compoundnbt = compound.getCompound("RecipesUsed");

        for(String s : compoundnbt.getAllKeys()) {
            this.recipesUsed.put(new ResourceLocation(s), compoundnbt.getInt(s));
        }

    }

    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        compound.putInt("BurnTime", this.litTime);
        compound.putInt("CookTime", this.cookingProgress);
        compound.putInt("CookTimeTotal", this.cookingTotalTime);
        ItemStackHelper.saveAllItems(compound, this.items);
        CompoundNBT compoundnbt = new CompoundNBT();
        this.recipesUsed.forEach((p_235643_1_, p_235643_2_) -> {
            compoundnbt.putInt(p_235643_1_.toString(), p_235643_2_);
        });
        compound.put("RecipesUsed", compoundnbt);
        return compound;
    }

    public void tick() {
        boolean flag = this.isLit();
        boolean flag1 = false;
        if (this.isLit()) {
            --this.litTime;
        }

        if (!this.level.isClientSide) {
            ItemStack itemstack = this.items.get(1);
            if (this.isLit() || !itemstack.isEmpty() && !this.items.get(0).isEmpty()) {
                IRecipe<?> irecipe = this.level.getRecipeManager().getRecipeFor((IRecipeType<AbstractFusionCookingRecipe>)this.recipeType, this, this.level).orElse(null);
                if (!this.isLit() && this.canBurn(irecipe)) {
                    this.litTime = this.getBurnDuration(itemstack);
                    this.litDuration = this.litTime;
                    if (this.isLit()) {
                        flag1 = true;
                        if (itemstack.hasContainerItem())
                            this.items.set(1, itemstack.getContainerItem());
                        else
                        if (!itemstack.isEmpty()) {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);
                            if (itemstack.isEmpty()) {
                                this.items.set(1, itemstack.getContainerItem());
                            }
                        }
                    }
                }

                if (this.isLit() && this.canBurn(irecipe)) {
                    ++this.cookingProgress;
                    if (this.cookingProgress == this.cookingTotalTime) {
                        this.cookingProgress = 0;
                        this.cookingTotalTime = this.getTotalCookTime();
                        this.burn(irecipe);
                        flag1 = true;
                    }
                } else {
                    this.cookingProgress = 0;
                }
            } else if (!this.isLit() && this.cookingProgress > 0) {
                this.cookingProgress = MathHelper.clamp(this.cookingProgress - 2, 0, this.cookingTotalTime);
            }

            if (flag != this.isLit()) {
                flag1 = true;
                this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(AbstractFusionFurnaceBlock.LIT, Boolean.valueOf(this.isLit())), 3);
            }
        }

        if (flag1) {
            this.setChanged();
        }

    }

    protected boolean canBurn(@Nullable IRecipe<?> recipeIn) {
        if (!this.items.get(0).isEmpty() && recipeIn != null) {
            ItemStack itemstack = recipeIn.getResultItem();
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = this.items.get(2);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!itemstack1.sameItem(itemstack)) {
                    return false;
                } else if (itemstack1.getCount() + itemstack.getCount() <= this.getMaxStackSize() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        } else {
            return false;
        }
    }

    private void burn(@Nullable IRecipe<?> recipe) {
        if (recipe != null && this.canBurn(recipe)) {
            ItemStack input1 = this.items.get(0);
            ItemStack itemstack1 = recipe.getResultItem();
            ItemStack result = this.items.get(2);
            ItemStack input2 = this.items.get(3);
            ItemStack catalyser = this.items.get(4);
            if (result.isEmpty()) {
                this.items.set(2, itemstack1.copy());
            } else if (result.getItem() == itemstack1.getItem()) {
                result.grow(itemstack1.getCount());
            }

            if (!this.level.isClientSide) {
                this.setRecipeUsed(recipe);
            }

            if (input1.getItem() == Blocks.WET_SPONGE.asItem() && !this.items.get(1).isEmpty() && this.items.get(1).getItem() == Items.BUCKET) {
                this.items.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            input1.shrink(1);
            input2.shrink(1);
            catalyser.shrink(1);
        }
    }

    protected int getBurnDuration(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        } else {
            Item item = stack.getItem();
            return net.minecraftforge.common.ForgeHooks.getBurnTime(stack);
        }
    }

    protected int getTotalCookTime() {
        return this.level.getRecipeManager().getRecipeFor((IRecipeType<AbstractFusionCookingRecipe>)this.recipeType, this, this.level).map(AbstractFusionCookingRecipe::getCookingTime).orElse(200);
    }

    public static boolean isFuel(ItemStack stack) {
        return net.minecraftforge.common.ForgeHooks.getBurnTime(stack) > 0;
    }

    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.UP) {
            return SLOTS_FOR_TOP;
        } else if (side == Direction.DOWN){
            return SLOTS_FOR_BOT;
        } else if (side == Direction.WEST){
            return SLOTS_FOR_LEFT;
        } else if (side == Direction.EAST){
            return SLOTS_FOR_RIGHT;
        } else if (side == Direction.SOUTH){
            return SLOTS_FOR_BEHIND;
        } else {
            return null;
        }
    }

    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction side) {
        return this.canPlaceItem(index, stack);
    }

    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction side) {
        if (side == Direction.DOWN && index == 1) {
            Item item = stack.getItem();
            if (item != Items.WATER_BUCKET && item != Items.BUCKET) {
                return false;
            }
        }

        return true;
    }

    public int getContainerSize() {
        return this.items.size();
    }

    public boolean isEmpty() {
        for(ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public ItemStack getItem(int index) {
        return this.items.get(index);
    }

    public ItemStack removeItem(int index, int count) {
        return ItemStackHelper.removeItem(this.items, index, count);
    }

    public ItemStack removeItemNoUpdate(int index) {
        return ItemStackHelper.takeItem(this.items, index);
    }

    public void setItem(int index, ItemStack stack) {
        ItemStack itemstack = this.items.get(index);
        boolean flag = !stack.isEmpty() && stack.sameItem(itemstack) && ItemStack.tagMatches(stack, itemstack);
        this.items.set(index, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

        if (index == 0 && !flag) {
            this.cookingTotalTime = this.getTotalCookTime();
            this.cookingProgress = 0;
            this.setChanged();
        }

    }

    public boolean stillValid(PlayerEntity player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    public boolean canPlaceItem(int index, ItemStack stack) {
        if (index == 2) {
            return false;
        } else if (index != 1) {
            return true;
        } else {
            ItemStack itemstack = this.items.get(1);
            return isFuel(stack) || stack.getItem() == Items.BUCKET && itemstack.getItem() != Items.BUCKET;
        }
    }

    public void clearContent() {
        this.items.clear();
    }

    public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
        if (recipe != null) {
            ResourceLocation resourcelocation = recipe.getId();
            this.recipesUsed.addTo(resourcelocation, 1);
        }

    }

    @Nullable
    public IRecipe<?> getRecipeUsed() {
        return null;
    }

    public void awardUsedRecipes(PlayerEntity player) {
    }

    public void awardUsedRecipesAndPopExperience(PlayerEntity player) {
        List<IRecipe<?>> list = this.getRecipesToAwardAndPopExperience(player.level, player.position());
        player.awardRecipes(list);
        this.recipesUsed.clear();
    }

    public List<IRecipe<?>> getRecipesToAwardAndPopExperience(World worldIn, Vector3d vector) {
        List<IRecipe<?>> list = Lists.newArrayList();

        for(Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
            worldIn.getRecipeManager().byKey(entry.getKey()).ifPresent((p_235642_4_) -> {
                list.add(p_235642_4_);
                createExperience(worldIn, vector, entry.getIntValue(), ((AbstractFusionCookingRecipe)p_235642_4_).getExperience());
            });
        }

        return list;
    }

    private static void createExperience(World worldIn, Vector3d vector, int p_235641_2_, float p_235641_3_) {
        int i = MathHelper.floor((float)p_235641_2_ * p_235641_3_);
        float f = MathHelper.frac((float)p_235641_2_ * p_235641_3_);
        if (f != 0.0F && Math.random() < (double)f) {
            ++i;
        }

        while(i > 0) {
            int j = ExperienceOrbEntity.getExperienceValue(i);
            i -= j;
            worldIn.addFreshEntity(new ExperienceOrbEntity(worldIn, vector.x, vector.y, vector.z, j));
        }

    }

    public void fillStackedContents(RecipeItemHelper helper) {
        for(ItemStack itemstack : this.items) {
            helper.accountStack(itemstack);
        }

    }

    net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers =
            net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    @Override
    public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == Direction.UP)
                return handlers[0].cast();
            else if (facing == Direction.DOWN)
                return handlers[1].cast();
            else
                return handlers[2].cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        for (int x = 0; x < handlers.length; x++)
            handlers[x].invalidate();
    }
}
