package io.github.redrockruby.cephalo.mixins;

import io.github.redrockruby.cephalo.swimming;
import net.minecraft.entity.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static io.github.redrockruby.cephalo.CephaloClient.*;


@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements swimming {

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "updatePose", at = @At("HEAD"), cancellable = true)
	public void updatePose(CallbackInfo ci) {
		if (swimmingBind.isPressed()) {
			setPose(EntityPose.SWIMMING);
			ci.cancel();
		}
	}
}
