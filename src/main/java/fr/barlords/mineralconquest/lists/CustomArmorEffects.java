package fr.barlords.mineralconquest.lists;

import fr.barlords.mineralconquest.init.ModItemGroups;
import fr.barlords.mineralconquest.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class CustomArmorEffects extends ArmorItem {

    int timer;

    public CustomArmorEffects(IArmorMaterial materialIn, EquipmentSlotType slot, Properties properties) {
        super(materialIn, slot, properties
                .tab(ModItemGroups.TAB_MOD)
        );
        timer = 0;
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player)
    {

        final Item L_FEET = player.getItemBySlot(EquipmentSlotType.FEET).getItem();
        final Item L_LEGS = player.getItemBySlot (EquipmentSlotType.LEGS) .getItem ();
        final Item L_CHEST = player.getItemBySlot (EquipmentSlotType.CHEST) .getItem ();
        final Item L_HEAD = player.getItemBySlot (EquipmentSlotType.HEAD) .getItem ();

        //-----------------------------------------------------------------------------------------------
        //CELESTIUM
        if(     !player.isCreative() &&
                L_HEAD == ModItems.CELESTIUM_HELMET.get() &&
                L_CHEST == ModItems.CELESTIUM_CHESTPLATE.get() &&
                L_LEGS == ModItems.CELESTIUM_LEGGINGS.get() &&
                L_FEET == ModItems.CELESTIUM_BOOTS.get())
        {
            player.abilities.mayfly = true;
            //modFlyingSpeed(0.025F, player);
            if(player.abilities.flying) {
                timer += 1;
                if(timer == 50) {
                    player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel()-1);
                    timer = 0;
                }
            }
        }
        else if(!player.isCreative()){
            player.abilities.mayfly = false;
        }
        else if(player.isCreative()) {
            //modFlyingSpeed(0.05F, player);
        }

        super.onArmorTick(stack, world, player);
    }

    private void modFlyingSpeed(float value, PlayerEntity player){
        player.abilities.setFlyingSpeed(value);
    }
}
