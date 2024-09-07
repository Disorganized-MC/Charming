package net.disorganized.charming.mixin;

import net.disorganized.charming.registry.CharmingAttributes;
import net.disorganized.charming.registry.CharmingDataTrackers;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class AirJumpMixin extends LivingEntity {

    public AirJumpMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }


    @Inject(method = "createPlayerAttributes", at = @At("RETURN"))
    private static void addAttributes(final CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue().add(CharmingAttributes.PLAYER_AIR_JUMP);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    public void addTrackedData(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(CharmingDataTrackers.AIR_JUMP, 0);
    }


    @Inject(method = "checkGliding", at = @At("HEAD"), cancellable = true)
    public void checkAirJump(CallbackInfoReturnable<Boolean> cir) {
        boolean success = handleAirJump((PlayerEntity)(Object)this);
        if (success) cir.setReturnValue(false);
    }

    @Inject(method = "handleFallDamage", at = @At("HEAD"))
    public void resetAirJumps(float f, float d, DamageSource s, CallbackInfoReturnable<Boolean> cir) {
        this.getDataTracker().set(CharmingDataTrackers.AIR_JUMP, 0);
    }


    @Unique
    private static boolean handleAirJump(PlayerEntity player) {
        if (player.isCreative() || player.isOnGround() || player.isGliding() || player.isSwimming()) return false;

        DataTracker tracker = player.getDataTracker();
        int maxAirJumps = (int) player.getAttributeValue(CharmingAttributes.PLAYER_AIR_JUMP);
        int currentAirJumps = tracker.get(CharmingDataTrackers.AIR_JUMP);
        if (currentAirJumps >= maxAirJumps) return false;

        tracker.set(CharmingDataTrackers.AIR_JUMP, ++currentAirJumps);
        player.jump();
        return true;
    }

}
