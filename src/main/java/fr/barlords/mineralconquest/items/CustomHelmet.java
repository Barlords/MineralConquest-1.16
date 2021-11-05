package fr.barlords.mineralconquest.items;

import fr.barlords.mineralconquest.init.ModItemGroups;
import fr.barlords.mineralconquest.lists.CustomArmorEffects;
import fr.barlords.mineralconquest.lists.CustomArmorTiers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.List;

import static fr.barlords.mineralconquest.lists.CustomEffects.addArmorInformation;

public class CustomHelmet extends CustomArmorEffects {

    public IArmorMaterial armorMaterial;

    public CustomHelmet(IArmorMaterial materialIn, EquipmentSlotType slotIn, Properties properties) {
        super(materialIn, slotIn, properties);
        armorMaterial = materialIn;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        tooltip = addArmorInformation(tooltip, armorMaterial, 4);
    }

    @Override
    public boolean isFireResistant() {
        return armorMaterial == CustomArmorTiers.BARLORITE || armorMaterial == CustomArmorTiers.PURIUM;
    }
}
