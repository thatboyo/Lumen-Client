package net.lumen.client.event;

import net.minecraft.network.Packet;

public class EventPacket {
    public final Packet<?> packet;
    public boolean cancelled = false;

    public EventPacket(Packet<?> packet) {
        this.packet = packet;
    }
}
