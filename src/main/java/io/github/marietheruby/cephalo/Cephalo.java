package io.github.marietheruby.cephalo;

import io.github.marietheruby.cephalo.weapons.weaponRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;
import io.github.marietheruby.cephalo.weapons.weaponRegistry;

public class Cephalo implements ModInitializer {
	public static final Identifier setSquidSwimming = new Identifier("cephalo", "setswimming");
	public static CephaloPlayerUpdater updater;
	public static final String MOD_ID = "cephalo";

	@Override
	public void onInitialize(ModContainer mod) {
		weaponRegistry.registerItems();
		updater = new CephaloPlayerUpdater() {
			@Override
			public void setSquidSwimming(PlayerEntity entity, boolean state) {
			}
		};

		ServerPlayNetworking.registerGlobalReceiver(setSquidSwimming, (server, player, handler, buf, responseSender) -> {
			boolean state = buf.readBoolean();
			var CephaloPlayer = (swimming) player;
			CephaloPlayer.cephaloSetSwimming(state);

			for (ServerPlayerEntity serverPlayerEntity : server.getPlayerManager().getPlayerList()) {
				if (serverPlayerEntity == player)
					continue;
				if (serverPlayerEntity.canSee(player)) {
					var wBuf = PacketByteBufs.create();
					wBuf.writeUuid(player.getUuid());
					wBuf.writeBoolean(state);
					ServerPlayNetworking.send(serverPlayerEntity, setSquidSwimming, wBuf);
				}
			}

		});
	}
}
