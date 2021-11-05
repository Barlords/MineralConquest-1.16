package fr.barlords.mineralconquest.items;

import fr.barlords.mineralconquest.init.ModItemGroups;
import fr.barlords.mineralconquest.lists.CustomToolTiers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import static fr.barlords.mineralconquest.lists.CustomEffects.*;

public class CustomHoe extends HoeItem {

    public IItemTier tier;

    public CustomHoe(IItemTier lTier, int attackDamageIn, float attackSpeedIn, Properties properties) {
        super(lTier, attackDamageIn, attackSpeedIn, properties
                .stacksTo(1)
                .tab(ModItemGroups.TAB_MOD));

        tier = lTier;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(tier == CustomToolTiers.BARLORITE)
            return super.hurtEnemy(stack, target, attacker) && applyFlamme(stack, target, attacker);
        if(tier == CustomToolTiers.TERRASTEEL)
            return super.hurtEnemy(stack, target, attacker) && applyWeakness(stack, target, attacker);
        if(tier == CustomToolTiers.PURIUM)
            return super.hurtEnemy(stack, target, attacker) && applyFlamme(stack, target, attacker) && applyWeakness(stack, target, attacker);
        else
            return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        tooltip = addToolInformation(tooltip, tier);
    }

    @Override
    public boolean isFireResistant() {
        if(tier == CustomToolTiers.BARLORITE || tier == CustomToolTiers.PURIUM)
            return true;
        else
            return false;
    }
}
