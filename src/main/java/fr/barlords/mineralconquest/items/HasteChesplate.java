package fr.barlords.mineralconquest.items;

import fr.barlords.mineralconquest.lists.CustomArmorEffects;
import fr.barlords.mineralconquest.lists.CustomArmorTiers;
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

public class HasteChesplate extends CustomArmorEffects {
    public IArmorMaterial armorMaterial;

    public HasteChesplate(IArmorMaterial materialIn, EquipmentSlotType slotIn, Item.Properties properties) {
        super(materialIn, slotIn, properties);
        armorMaterial = materialIn;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        tooltip.add(new StringTextComponent("\247eJe n'ai jamais cess\u00e9 d'\u00eatre en col\u00e8re."));
        if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
            tooltip.add(new StringTextComponent("\247e- Effet de Haste X"));
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
