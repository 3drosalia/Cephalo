package io.github.redrockruby.cephalo.mixins;

import io.github.redrockruby.cephalo.Cephalo;
import io.github.redrockruby.cephalo.swimming;
import static io.github.redrockruby.cephalo.CephaloClient.setSquidSwimming;
import net.minecraft.entity.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static io.github.redrockruby.cephalo.CephaloClient.*;


@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements swimming {


	@Shadow
	public abstract PlayerAbilities getAbilities();

	@Shadow
	protected abstract float getVelocityMultiplier();

	private boolean cephalo_wasSwimming = false;
	private boolean cephalo_wasReloading = false;

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "updatePose", at = @At("HEAD"), cancellable = true)
	public void updatePose(CallbackInfo ci) {
		if (cephalo_wasSwimming) {
			setPose(EntityPose.SWIMMING);
			ci.cancel();
		}
	}

	@Inject(method = "travel", at = @At("HEAD"), cancellable = true)
	public void travel(Vec3d movementInput, CallbackInfo ci) {
		FluidState fluidState = this.world.getFluidState(this.getBlockPos());
		if ((isUsingItem()) || (this.getAbilities().flying) || (this.isTouchingWater() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState)) || (this.isInLava() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState)) || (this.isFallFlying()))
			return;

		if (isClimbing())
			return;

		if setSquidSwimming = true {
			Cephalo.updater.setSquidSwimming((PlayerEntity) (Object) this, cephalo_wasSwimming);

		}

	}
}
