package io.github.redrockruby.cephalo;

import com.mojang.blaze3d.platform.InputUtil;
import net.minecraft.client.option.KeyBind;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.glfw.GLFW;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

public class CephaloClient extends Cephalo implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {

		KeyBind swimmingBind = KeyBindingHelper.registerKeyBinding(new KeyBind("key.cephalo.swim", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_ALT, "category.cephalo.test"));

		Cephalo.updater = new CephaloPlayerUpdater() {
			@Override
			public void setSquidSwimming(PlayerEntity entity, boolean state) {
				var buf = PacketByteBufs.create();
				buf.writeBoolean(state);
				ClientPlayNetworking.send(Cephalo.setSquidSwimming, buf);
			}
		};
		ClientPlayNetworking.registerGlobalReceiver(Cephalo.setSquidSwimming, (client, handler, buf, responseSender) -> {
			var uuid = buf.readUuid();
			var value = buf.readBoolean();

			try {
				var cephalo = (swimming) client.world.getPlayerByUuid(uuid);
				cephalo.cephaloSetSwimming(value);
			} catch (Exception e) {}
		});
	}
}
