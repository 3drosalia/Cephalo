package io.github.redrockruby.cephalo;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class Cephalo implements ModInitializer {
	public static final Identifier setSquidSwimming = new Identifier("cephalo", "setswimming");
	public static CephaloPlayerUpdater updater;

	@Override
	public void onInitialize(ModContainer mod) {
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
