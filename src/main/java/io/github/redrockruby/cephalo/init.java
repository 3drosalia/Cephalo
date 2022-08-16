package io.github.redrockruby.cephalo;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class init implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Cephalo");

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Made by Marie and Callie");
	}
}
