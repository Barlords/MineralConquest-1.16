package fr.barlords.mineralconquest.init;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModEvents {

    @SubscribeEvent
    public void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
        if(event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();

            final Item L_FEET = player.getItemBySlot(EquipmentSlotType.FEET).getItem();
            final Item L_LEGS = player.getItemBySlot (EquipmentSlotType.LEGS) .getItem ();
            final Item L_CHEST = player.getItemBySlot (EquipmentSlotType.CHEST) .getItem ();
            final Item L_HEAD = player.getItemBySlot (EquipmentSlotType.HEAD) .getItem ();


            //-----------------------------------------------------------------------------------------------
            //LEGENDARY EQUIPMENT
            if(L_FEET == ModItems.SPEED_BOOTS.get()) {
                player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20*99999, 10, true, false, true));
            }
            if(L_LEGS == ModItems.AQUA_LEGGINGS.get()) {
                player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 20*99999, 10, true, false, true));
            }
            if(L_CHEST == ModItems.HASTE_CHESTPLATE.get()) {
                player.addEffect(new EffectInstance(Effects.DIG_SPEED, 20*99999, 10, true, false, true));
            }
            if(L_HEAD == ModItems.AQUA_HELMET.get()) {
                player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 20*99999, 10, true, false, true));
            }

            //-----------------------------------------------------------------------------------------------
            //ALL BOOTS
            if(L_FEET == ModItems.TITANE_BOOTS.get()) {
                player.addEffect (new EffectInstance(Effects.MOVEMENT_SPEED, 10*99999, 0, true, false, true));
            }
            else if(    L_FEET == ModItems.TERRASTEEL_BOOTS.get() ||
                        L_FEET == ModItems.BARLORITE_BOOTS.get()) {
                player.addEffect (new EffectInstance(Effects.MOVEMENT_SPEED, 10*99999, 1, true, false, true));
            }
            else if(L_FEET == ModItems.PURIUM_BOOTS.get()) {
                player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 10*99999, 2, true, false, true));
            }
            else{ player.removeEffect(Effects.MOVEMENT_SPEED); }

            //-----------------------------------------------------------------------------------------------
            //TITANE
            if(     L_HEAD == ModItems.TITANE_HELMET.get()  &&
                    L_CHEST == ModItems.TITANE_CHESTPLATE.get()  &&
                    L_LEGS == ModItems.TITANE_LEGGINGS.get()  &&
                    L_FEET == ModItems.TITANE_BOOTS.get() )
            {
                player.addEffect (new EffectInstance(Effects.DAMAGE_RESISTANCE,10*99999, 0, true, false, true));
            }
            else{ player.removeEffect(Effects.DAMAGE_RESISTANCE); }

            //-----------------------------------------------------------------------------------------------
            //TERRASTEEL & PURIUM
            if(     (L_HEAD == ModItems.PURIUM_HELMET.get() || player.getItemBySlot (EquipmentSlotType.HEAD) .getItem () == ModItems.TERRASTEEL_HELMET.get()) &&
                    (L_CHEST == ModItems.PURIUM_CHESTPLATE.get() || L_CHEST == ModItems.TERRASTEEL_CHESTPLATE.get()) &&
                    (L_LEGS == ModItems.PURIUM_LEGGINGS.get() || L_LEGS == ModItems.TERRASTEEL_LEGGINGS.get()) &&
                    (L_FEET == ModItems.PURIUM_BOOTS.get() || L_FEET == ModItems.TERRASTEEL_BOOTS.get()))
            {
                player.addEffect (new EffectInstance(Effects.NIGHT_VISION,20*99999,0,  true, false, true));
            }
            else{ player.removeEffect(Effects.NIGHT_VISION); }

            //-----------------------------------------------------------------------------------------------
            //BARLORITE & PURIUM
            if(     (L_HEAD == ModItems.PURIUM_HELMET.get() || L_HEAD == ModItems.BARLORITE_HELMET.get()) &&
                    (L_CHEST == ModItems.PURIUM_CHESTPLATE.get() || L_CHEST == ModItems.BARLORITE_CHESTPLATE.get()) &&
                    (L_LEGS == ModItems.PURIUM_LEGGINGS.get() || L_LEGS == ModItems.BARLORITE_LEGGINGS.get()) &&
                    (L_FEET == ModItems.PURIUM_BOOTS.get() || L_FEET == ModItems.BARLORITE_BOOTS.get()))
            {
                player.addEffect (new EffectInstance(Effects.FIRE_RESISTANCE,10*99999, 0, true, false, true));
            }
            else{ player.removeEffect(Effects.FIRE_RESISTANCE); }



            //-----------------------------------------------------------------------------------------------
            //PURIUM
            if(L_HEAD == ModItems.PURIUM_HELMET.get()) {
                player.addEffect (new EffectInstance(Effects.DAMAGE_BOOST, 10*99999, 0 ,true, false, true));
            }
            else{ player.removeEffect(Effects.DAMAGE_BOOST); }

            if(L_CHEST == ModItems.PURIUM_CHESTPLATE.get()) {
                player.addEffect (new EffectInstance(Effects.DIG_SPEED, 10*99999, 0, true, false, true));
            }
            else{ player.removeEffect(Effects.DIG_SPEED); }

            if(L_LEGS == ModItems.PURIUM_LEGGINGS.get()) {
                player.addEffect (new EffectInstance(Effects.DAMAGE_RESISTANCE, 10*99999, 0, true, false, true));
            }
            else{ player.removeEffect(Effects.DAMAGE_RESISTANCE); }

            //-----------------------------------------------------------------------------------------------
            //CELESTIUM

        }
    }

}
