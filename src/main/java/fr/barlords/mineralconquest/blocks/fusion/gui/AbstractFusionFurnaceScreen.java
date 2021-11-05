package fr.barlords.mineralconquest.blocks.fusion.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.barlords.mineralconquest.blocks.fusion.container.AbstractFusionFurnaceContainer;
import net.minecraft.client.gui.recipebook.AbstractRecipeBookGui;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractFusionFurnaceScreen<T extends AbstractFusionFurnaceContainer> extends ContainerScreen<T> implements IRecipeShownListener {
    private static final ResourceLocation RECIPE_BUTTON_LOCATION = new ResourceLocation("textures/gui/recipe_button.png");
    public final AbstractRecipeBookGui recipeBookComponent;
    private boolean widthTooNarrow;
    protected final ResourceLocation texture;

    public AbstractFusionFurnaceScreen(T fusionFurnaceContainer, AbstractRecipeBookGui recipeBookGui, PlayerInventory playerInv, ITextComponent textComponent, ResourceLocation ressourceLocation) {
        super(fusionFurnaceContainer, playerInv, textComponent);
        this.recipeBookComponent = recipeBookGui;
        this.texture = ressourceLocation;
    }

    public void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
        this.leftPos = this.recipeBookComponent.updateScreenPosition(this.widthTooNarrow, this.width, this.imageWidth);
        /*this.addButton(new ImageButton(this.leftPos + 20, this.height / 2 - 49, 20, 18, 0, 0, 19, RECIPE_BUTTON_LOCATION, (p_214087_1_) -> {
            this.recipeBookComponent.initVisuals(this.widthTooNarrow);
            this.recipeBookComponent.toggleVisibility();
            this.leftPos = this.recipeBookComponent.updateScreenPosition(this.widthTooNarrow, this.width, this.imageWidth);
            ((ImageButton)p_214087_1_).setPosition(this.leftPos + 20, this.height / 2 - 49);
        }));*/
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    public void tick() {
        super.tick();
        this.recipeBookComponent.tick();
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float p_230430_4_) {
        this.renderBackground(matrixStack);
        if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
            this.renderBg(matrixStack, p_230430_4_, mouseX, mouseY);
            this.recipeBookComponent.render(matrixStack, mouseX, mouseY, p_230430_4_);
        } else {
            this.recipeBookComponent.render(matrixStack, mouseX, mouseY, p_230430_4_);
            super.render(matrixStack, mouseX, mouseY, p_230430_4_);
            this.recipeBookComponent.renderGhostRecipe(matrixStack, this.leftPos, this.topPos, true, p_230430_4_);
        }

        this.renderTooltip(matrixStack, mouseX, mouseY);
        this.recipeBookComponent.renderTooltip(matrixStack, this.leftPos, this.topPos, mouseX, mouseY);
    }

    protected void renderBg(MatrixStack matrixStack, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(this.texture);
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
        if (this.menu.isLit()) {
            int k = this.menu.getLitProgress();
            this.blit(matrixStack, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.menu.getBurnProgress();
        this.blit(matrixStack, i + 79, j + 34, 176, 14, l + 1, 16);
    }

    public boolean mouseClicked(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
        if (this.recipeBookComponent.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_)) {
            return true;
        } else {
            return this.widthTooNarrow && this.recipeBookComponent.isVisible() ? true : super.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_);
        }
    }

    protected void slotClicked(Slot slotIn, int slotId, int mouseButton, ClickType type) {
        super.slotClicked(slotIn, slotId, mouseButton, type);
        this.recipeBookComponent.slotClicked(slotIn);
    }

    public boolean keyPressed(int p_231046_1_, int p_231046_2_, int p_231046_3_) {
        return this.recipeBookComponent.keyPressed(p_231046_1_, p_231046_2_, p_231046_3_) ? false : super.keyPressed(p_231046_1_, p_231046_2_, p_231046_3_);
    }

    protected boolean hasClickedOutside(double p_195361_1_, double p_195361_3_, int p_195361_5_, int p_195361_6_, int p_195361_7_) {
        boolean flag = p_195361_1_ < (double)p_195361_5_ || p_195361_3_ < (double)p_195361_6_ || p_195361_1_ >= (double)(p_195361_5_ + this.imageWidth) || p_195361_3_ >= (double)(p_195361_6_ + this.imageHeight);
        return this.recipeBookComponent.hasClickedOutside(p_195361_1_, p_195361_3_, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, p_195361_7_) && flag;
    }

    public boolean charTyped(char p_231042_1_, int p_231042_2_) {
        return this.recipeBookComponent.charTyped(p_231042_1_, p_231042_2_) ? true : super.charTyped(p_231042_1_, p_231042_2_);
    }

    public void recipesUpdated() {
        this.recipeBookComponent.recipesUpdated();
    }

    public RecipeBookGui getRecipeBookComponent() {
        return this.recipeBookComponent;
    }

    public void removed() {
        this.recipeBookComponent.removed();
        super.removed();
    }
}