package io.github.redrockruby.cephalo;


import com.mojang.blaze3d.platform.InputUtil;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBind;
import org.lwjgl.glfw.GLFW;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class keyBinds implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {
		KeyBind keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBind(
				"key.cephalo.swim", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_Z, // The keycode of the key
				"category.cephalo.test" // The translation key of the keybinding's category.
		));

	}
}
