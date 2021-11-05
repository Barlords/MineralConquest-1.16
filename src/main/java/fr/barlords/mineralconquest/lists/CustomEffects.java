package fr.barlords.mineralconquest.lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.Set;

public class CustomEffects {

    //----------------------------------------------------------------------------------------
    //Effets des outils
    public static boolean applyFlamme(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.setSecondsOnFire(5);
        return true;
    }

    public static boolean applyWeakness(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addEffect(new EffectInstance(Effects.WEAKNESS, 50 , 1));
        target.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 50 , 1));
        return true;
    }

    public static List<ITextComponent> addToolInformation(List<ITextComponent> lTooltip, IItemTier lTier) {

        if(lTier == CustomToolTiers.BARLORITE) {
            lTooltip.add(new StringTextComponent("\247cCarbonisation"));
            if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
                lTooltip.add(new StringTextComponent("\247c- Met votre cible en feu, ATTENTION CA BRULE!!!"));
                lTooltip.add(new StringTextComponent(""));
            }
            else{
                lTooltip.add(new StringTextComponent("\2477- Maintenez Shift pour plus d'info"));
                lTooltip.add(new StringTextComponent(""));
            }
        }
        else if(lTier == CustomToolTiers.TERRASTEEL) {
            lTooltip.add(new StringTextComponent("\247aAffaiblissement"));
            if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
                lTooltip.add(new StringTextComponent("\247a- Affaiblie votre cible"));
                lTooltip.add(new StringTextComponent(""));
            }
            else{
                lTooltip.add(new StringTextComponent("\2477- Maintenez Shift pour plus d'info"));
                lTooltip.add(new StringTextComponent(""));
            }
        }
        else if(lTier == CustomToolTiers.PURIUM) {
            lTooltip.add(new StringTextComponent("\2475Metal parfait"));
            if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
                lTooltip.add(new StringTextComponent("\247c- Carbonisation"));
                lTooltip.add(new StringTextComponent("\247a- Affaiblissement"));
                lTooltip.add(new StringTextComponent(""));
            }
            else{
                lTooltip.add(new StringTextComponent("\2477- Maintenez Shift pour plus d'info"));
                lTooltip.add(new StringTextComponent(""));
            }
        }

        return lTooltip;
    }

    public static List<ITextComponent> addArmorInformation(List<ITextComponent> lTooltip, IArmorMaterial lArmorMaterial, int lSlot) {

        if(lArmorMaterial == CustomArmorTiers.BARLORITE) {
            lTooltip.add(new StringTextComponent("\247cIl fait chaud vous trouvez pas ?  "+lSlot+"/4"));
            if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
                lTooltip.add(new StringTextComponent("\247c- Effet de Fire Resistance"));
                lTooltip.add(new StringTextComponent(""));
            }
            else{
                lTooltip.add(new StringTextComponent("\2477- Maintenir Shift pour plus d'info"));
                lTooltip.add(new StringTextComponent(""));
            }
        }
        else if(lArmorMaterial == CustomArmorTiers.TERRASTEEL) {
            lTooltip.add(new StringTextComponent("\247aQui \u00e0 allum\u00e9 la lumi\u00e8re ?  "+lSlot+"/4"));
            if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
                lTooltip.add(new StringTextComponent("\247a- Effet de Night Vision"));
                lTooltip.add(new StringTextComponent(""));
            }
            else{
                lTooltip.add(new StringTextComponent("\2477- Maintenir Shift pour plus d'info"));
                lTooltip.add(new StringTextComponent(""));
            }
        }
        else if(lArmorMaterial == CustomArmorTiers.PURIUM) {
            lTooltip.add(new StringTextComponent("\2475C'est moi l'plus fort!  "+lSlot+"/4"));
            if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
                lTooltip.add(new StringTextComponent("\247c- Il fait chaud vous trouvez pas ?  "+lSlot+"/4"));
                lTooltip.add(new StringTextComponent("\247a- Qui \u00e0 allum\u00e9 la lumi\u00e8re ?  "+lSlot+"/4"));
                lTooltip.add(new StringTextComponent(""));
            }
            else{
                lTooltip.add(new StringTextComponent("\2477- Maintenir Shift pour plus d'info"));
                lTooltip.add(new StringTextComponent(""));
            }
        }
        else if(lArmorMaterial == CustomArmorTiers.CELESTIUM) {
            lTooltip.add(new StringTextComponent("\2471I'm Iron Man  "+lSlot+"/4"));
            if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
                lTooltip.add(new StringTextComponent("\2471- La l\u00e9gende dit que tu pourra voler"));
                lTooltip.add(new StringTextComponent(""));
            }
            else{
                lTooltip.add(new StringTextComponent("\2477- Maintenir Shift pour plus d'info"));
                lTooltip.add(new StringTextComponent(""));
            }
        }

        return lTooltip;
    }
}
