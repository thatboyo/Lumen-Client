package net.lumen.client.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventBus {
    private final Map<Class<?>, List<Consumer<?>>> listeners = new HashMap<>();

    public <T> void subscribe(Class<T> eventClass, Consumer<T> listener) {
        listeners.computeIfAbsent(eventClass, key -> new ArrayList<>()).add(listener);
    }

    public <T> void unsubscribe(Class<T> eventClass, Consumer<T> listener) {
        List<Consumer<?>> eventListeners = listeners.get(eventClass);
        if (eventListeners != null) {
            eventListeners.remove(listener);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> void post(T event) {
        List<Consumer<?>> eventListeners = listeners.get(event.getClass());
        if (eventListeners == null) {
            return;
        }
        for (Consumer<?> listener : new ArrayList<>(eventListeners)) {
            ((Consumer<T>) listener).accept(event);
        }
    }
}
