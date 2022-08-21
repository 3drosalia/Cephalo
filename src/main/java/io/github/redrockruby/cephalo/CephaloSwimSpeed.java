package io.github.redrockruby.cephalo;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;

import java.util.UUID;

import io.github.redrockruby.cephalo.CephaloClient;
import net.minecraft.entity.player.PlayerEntity;

import static io.github.redrockruby.cephalo.CephaloClient.swimmingBind;

public class CephaloSwimSpeed extends Cephalo {
	private static final EntityAttributeModifier SQUID_SWIM_SPEED = new EntityAttributeModifier(UUID.fromString("0507f005-27e0-42b6-9633-8c06b739fd91"), "Squid Swim Speed", 0.4, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);


	if (CephaloClient.swimmingBind.isPressed()) {

	}

}
