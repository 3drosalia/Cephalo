package io.github.marietheruby.cephalo.weapons;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import io.github.marietheruby.cephalo.weapons.InkAmount;

public class Splattershot_Jr extends Item {

	public Splattershot_Jr(Item.Settings settings) { super(settings); }

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand);
		if (InkAmount.InkAmount > 1) {
			world.playSound(
					null,
					user.getX(),
					user.getY(),
					user.getZ(),
					SoundEvents.ENTITY_SNOWBALL_THROW,
					SoundCategory.NEUTRAL,
					0.5F,
					0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
			);
			if (!world.isClient) {
				SnowballEntity inkEntity = new SnowballEntity(world, user);
				inkEntity.setItem(itemStack);
				inkEntity.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
				world.spawnEntity(inkEntity);
			}
			user.incrementStat(Stats.USED.getOrCreateStat(this));
			if (!user.getAbilities().creativeMode) {
				InkAmount.InkAmount--;
			}

			return TypedActionResult.success(itemStack, world.isClient());
		}
		return TypedActionResult.pass(itemStack);
	}
}
