package net.lumen.client.module;

import net.lumen.client.LumenClient;
import net.lumen.client.event.EventBus;
import net.lumen.client.setting.Setting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class Module {
    private final String name;
    private final String description;
    private final Category category;
    private final int keybind;
    private boolean enabled;
    private final List<Setting<?>> settings = new ArrayList<>();
    private final List<Subscription<?>> subscriptions = new ArrayList<>();
    protected boolean isGhost;
    protected boolean isFavorited;

    protected Module(String name, String description, Category category, int keybind) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.keybind = keybind;
        this.enabled = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public int getKeybind() {
        return keybind;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isGhost() {
        return isGhost;
    }

    public boolean isFavorited() {
        return isFavorited;
    }

    public void setFavorited(boolean favorited) {
        isFavorited = favorited;
    }

    public List<Setting<?>> getSettings() {
        return settings;
    }

    protected <T> void registerSetting(Setting<T> setting) {
        settings.add(setting);
    }

    protected <T> void subscribe(Class<T> eventClass, Consumer<T> handler) {
        EventBus bus = LumenClient.getEventBus();
        if (bus != null) {
            bus.subscribe(eventClass, handler);
            subscriptions.add(new Subscription<>(eventClass, handler));
        }
    }

    protected <T> void unsubscribe(Class<T> eventClass, Consumer<T> handler) {
        EventBus bus = LumenClient.getEventBus();
        if (bus != null) {
            bus.unsubscribe(eventClass, handler);
        }
    }

    protected void clearSubscriptions() {
        EventBus bus = LumenClient.getEventBus();
        if (bus != null) {
            for (Subscription<?> subscription : new ArrayList<>(subscriptions)) {
                bus.unsubscribe(subscription.eventClass, subscription.handler);
            }
            subscriptions.clear();
        }
    }

    public void toggle() {
        if (enabled) {
            disable();
        } else {
            enable();
        }
    }

    public void enable() {
        if (enabled) {
            return;
        }
        enabled = true;
        onEnable();
    }

    public void disable() {
        if (!enabled) {
            return;
        }
        enabled = false;
        clearSubscriptions();
        onDisable();
    }

    protected void onEnable() {
    }

    protected void onDisable() {
    }

    private static class Subscription<T> {
        private final Class<T> eventClass;
        private final Consumer<T> handler;

        public Subscription(Class<T> eventClass, Consumer<T> handler) {
            this.eventClass = eventClass;
            this.handler = handler;
        }
    }
}
