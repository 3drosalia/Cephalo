package io.github.marietheruby.cephalo.weapons;

public class InkAmount {
	static int InkAmount = 100;

	public static void refillInk() {
		if (InkAmount < 100) {
			InkAmount++;
		}
	}
}

