package io.github.marietheruby.cephalo.weapons;

import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;

import java.util.concurrent.TimeUnit;

public class InkAmount {
	static int InkAmount = 100;

	public static void refillInk() {
		if (InkAmount < 100) {
			InkAmount++;
		}
	}
}

