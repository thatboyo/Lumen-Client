package net.lumen.client.module.combat;

import net.lumen.client.event.EventPacket;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.ChanceSetting;
import net.lumen.client.setting.SliderSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public class VelocityModule extends Module {
    private final SliderSetting horizontalReduction;
    private final SliderSetting verticalReduction;
    private final ChanceSetting chance;

    public VelocityModule() {
        super("Velocity", "Reduces knockback from velocity packets.", Category.COMBAT, 0);
        this.isGhost = true;
        horizontalReduction = new SliderSetting("Horizontal", "Horizontal knockback reduction.", 50.0, 0.0, 100.0, 1.0);
        verticalReduction = new SliderSetting("Vertical", "Vertical knockback reduction.", 50.0, 0.0, 100.0, 1.0);
        chance = new ChanceSetting("Chance", "Chance to reduce knockback.", 75.0);
        registerSetting(horizontalReduction);
        registerSetting(verticalReduction);
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
            double horReduction = horizontalReduction.getValue() / 100.0;
            double verReduction = verticalReduction.getValue() / 100.0;
            
            Vec3d velocity = client.player.getVelocity();
            client.player.setVelocity(
                velocity.x * (1.0 - horReduction),
                velocity.y * (1.0 - verReduction),
                velocity.z * (1.0 - horReduction)
            );
        }
    }
}
