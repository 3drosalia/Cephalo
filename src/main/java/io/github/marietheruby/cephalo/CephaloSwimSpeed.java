package io.github.marietheruby.cephalo;

import io.github.marietheruby.cephalo.weapons.InkAmount;
import io.github.marietheruby.cephalo.weapons.Splattershot_Jr;
import net.minecraft.entity.attribute.EntityAttributeModifier;

import java.util.UUID;

public class CephaloSwimSpeed extends Cephalo {
	private static final EntityAttributeModifier SQUID_SWIM_SPEED = new EntityAttributeModifier(UUID.fromString("0507f005-27e0-42b6-9633-8c06b739fd91"), "Squid Swim Speed", 0.4, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);


	//if (CephaloClient.swimmingBind.isPressed()) {
	//
	//}

}
