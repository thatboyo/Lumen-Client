package net.lumen.client.event;

public class EventMotion {
    public float yaw;
    public float pitch;
    public boolean onGround;

    public EventMotion(float yaw, float pitch, boolean onGround) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }
}
