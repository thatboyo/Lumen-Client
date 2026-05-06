package net.lumen.client.module.ghost;

import net.lumen.client.event.EventKey;
import net.lumen.client.event.EventPacket;
import net.lumen.client.event.EventTick;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.BooleanSetting;
import net.lumen.client.setting.KeybindSetting;
import net.lumen.client.setting.SliderSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.Packet;

import java.util.ArrayList;
import java.util.List;

public class BlinkModule extends Module {
    private final SliderSetting bufferDuration;
    private final KeybindSetting releaseKey;
    private final BooleanSetting autoRelease;

    private final List<Packet<?>> bufferedPackets = new ArrayList<>();
    private long startTime;

    public BlinkModule() {
        super("Blink", "Buffers outgoing packets and releases them later.", Category.GHOST, 0);
        bufferDuration = new SliderSetting("Buffer Duration", "Milliseconds to hold packets.", 500.0, 100.0, 2000.0, 50.0);
        releaseKey = new KeybindSetting("Release Key", "Manual packet release key.", 335);
        autoRelease = new BooleanSetting("Auto Release", "Automatically release buffered packets.", false);
        registerSetting(bufferDuration);
        registerSetting(releaseKey);
        registerSetting(autoRelease);
    }

    @Override
    protected void onEnable() {
        subscribe(EventTick.class, this::onTick);
        subscribe(EventKey.class, this::onKey);
        subscribe(EventPacket.class, this::onPacket);
        bufferedPackets.clear();
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void onDisable() {
        releasePackets();
    }

    private void onTick(EventTick event) {
        if (autoRelease.getValue() && System.currentTimeMillis() - startTime > bufferDuration.getValue()) {
            releasePackets();
            toggle(); // Disable after auto release
        }
    }

    private void onKey(EventKey event) {
        if (event.keycode == releaseKey.getValue() && event.pressed) {
            releasePackets();
            toggle(); // Disable after manual release
        }
    }

    private void onPacket(EventPacket event) {
        if (!event.cancelled) {
            bufferedPackets.add(event.packet);
            event.cancelled = true;
        }
    }

    private void releasePackets() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.getNetworkHandler() != null) {
            for (Packet<?> packet : bufferedPackets) {
                client.getNetworkHandler().sendPacket(packet);
            }
        }
        bufferedPackets.clear();
    }
}
