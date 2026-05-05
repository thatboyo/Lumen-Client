package net.lumen.client.module.combat;

import net.lumen.client.event.EventPacket;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.ChanceSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public class AntiKnockbackModule extends Module {
    private final ChanceSetting chance;

    public AntiKnockbackModule() {
        super("AntiKnockback", "Attempts to fully negate knockback packets.", Category.COMBAT, 0);
        this.isGhost = true;
        chance = new ChanceSetting("Chance", "Chance to negate knockback.", 60.0);
        registerSetting(chance);
    }

    @Override
    protected void onEnable() {
        subscribe(EventPacket.class, this::onPacket);
    }

    private void onPacket(EventPacket event) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) {
            return;
        }

        double random = Math.random();
        if (random <= (chance.getValue() / 100.0)) {
            // Completely negate knockback by resetting velocity
            client.player.setVelocity(Vec3d.ZERO);
        }
    }
}
