package fr.barlords.mineralconquest.blocks.fusion.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import fr.barlords.mineralconquest.blocks.fusion.container.AbstractFusionFurnaceContainer;
import fr.barlords.mineralconquest.blocks.fusion.container.FusionFurnaceContainer;
import net.minecraft.client.gui.recipebook.FurnaceRecipeGui;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FusionFurnaceScreen extends AbstractFusionFurnaceScreen<FusionFurnaceContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("mineralconquest:textures/gui/container/fusion_furnace_gui.png");

    public FusionFurnaceScreen(FusionFurnaceContainer fusionFurnaceContainer, PlayerInventory playerInv, ITextComponent textComponent) {
        super(fusionFurnaceContainer, new FurnaceRecipeGui(), playerInv, textComponent, TEXTURE);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        GlStateManager._color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(this.texture);
        int k = (this.getGuiLeft() );//- this.xSize) / 2;
        int l = (this.getGuiTop() );//- this.ySize) / 2;
        this.blit(matrixStack, k, l, 0, 0, this.getXSize(), this.getYSize() );
        int i1;
        if (((AbstractFusionFurnaceContainer)this.menu).isLit()) {
            i1 = ((AbstractFusionFurnaceContainer)this.menu).getLitProgress();
            this.blit(matrixStack, k + 105, l + 55 + 12 - i1, 176, 12 - i1, 14, i1 + 2 );
            i1 = ((AbstractFusionFurnaceContainer)this.menu).getLitProgress();
            this.blit(matrixStack, k + 55, l + 55 + 12 - i1, 176, 12 - i1, 14, i1 + 2 );
        }

        i1 =  ((AbstractFusionFurnaceContainer)this.menu).getBurnProgress();
        this.blit(matrixStack, k + 51, l + 34, 176, 14, i1 + 1, 16 );
        i1 =  ((AbstractFusionFurnaceContainer)this.menu).getBurnProgress();
        this.blit(matrixStack, k + 100, l + 34, 176, 31, 23, 16 );
        i1 =  ((AbstractFusionFurnaceContainer)this.menu).getBurnProgress();
        this.blit(matrixStack, k + 100, l + 34, 176, 47, 23 - i1, 16 );
        i1 =  ((AbstractFusionFurnaceContainer)this.menu).getBurnProgress();
        this.blit(matrixStack, k + 64, l + 4 + 29 - i1, 176, 92 - i1, 12, 29 );
        i1 =  ((AbstractFusionFurnaceContainer)this.menu).getBurnProgress();
        this.blit(matrixStack, k + 98, l + 4 + 29 - i1, 188, 92 - i1, 12, 29 );
    }
}