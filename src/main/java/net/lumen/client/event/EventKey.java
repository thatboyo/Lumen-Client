package net.lumen.client.event;

public class EventKey {
    public final int key;
    public final int scancode;
    public final int action;
    public final int modifiers;

    public EventKey(int key, int scancode, int action, int modifiers) {
        this.key = key;
        this.scancode = scancode;
        this.action = action;
        this.modifiers = modifiers;
    }
}
