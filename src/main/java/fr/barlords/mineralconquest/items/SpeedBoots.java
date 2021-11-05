package fr.barlords.mineralconquest.items;

import fr.barlords.mineralconquest.lists.CustomArmorEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.List;

public class SpeedBoots extends CustomArmorEffects {

    public IArmorMaterial armorMaterial;

    public SpeedBoots(IArmorMaterial materialIn, EquipmentSlotType slotIn, Item.Properties properties) {
        super(materialIn, slotIn, properties);
        armorMaterial = materialIn;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        tooltip.add(new StringTextComponent("\247eJe m'appel Barry Allen"));
        if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
            tooltip.add(new StringTextComponent("\247e- Effet de Speed X"));
            tooltip.add(new StringTextComponent(""));
        }
        else{
            tooltip.add(new StringTextComponent("\2477- Maintenir Shift pour plus d'info"));
            tooltip.add(new StringTextComponent(""));
        }
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }
}
