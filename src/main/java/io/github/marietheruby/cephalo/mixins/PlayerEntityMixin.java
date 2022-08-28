package io.github.marietheruby.cephalo.mixins;

import io.github.marietheruby.cephalo.CephaloClient;
import io.github.marietheruby.cephalo.swimming;
import net.minecraft.entity.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import io.github.marietheruby.cephalo.weapons.InkAmount;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements swimming {

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "updatePose", at = @At("HEAD"), cancellable = true)
	public void updatePose(CallbackInfo ci) {
		if (CephaloClient.swimmingBind.isPressed()) {
			setPose(EntityPose.SWIMMING);
			InkAmount.refillInk();
			ci.cancel();
		}
	}
}
