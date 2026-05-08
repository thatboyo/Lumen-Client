package net.lumen.client.hud;

import net.lumen.client.LumenClient;
import net.lumen.client.event.EventRender2D;
import net.lumen.client.gui.GuiManager;
import net.lumen.client.gui.elements.ArrayListHudElement;
import net.lumen.client.gui.elements.CoordinatesHudElement;
import net.lumen.client.gui.elements.FPSHudElement;
import net.lumen.client.gui.elements.KeystrokesHudElement;
import net.lumen.client.gui.elements.WatermarkHudElement;
import net.lumen.client.module.player.PotCounterModule;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

public class HudManager {
    private final List<HudElement> hudElements = new ArrayList<>();

    public void initialize() {
        hudElements.clear();
        hudElements.add(new WatermarkHudElement(4, 4, 1.0f));
        hudElements.add(new ArrayListHudElement(10, 20, 1.0f));
        hudElements.add(new CoordinatesHudElement(10, 100, 1.0f));
        hudElements.add(new FPSHudElement(10, 110, 1.0f));
        hudElements.add(new KeystrokesHudElement(10, 130, 1.0f));
        hudElements.add(new PotCounterModule(10, 150, 1.0f));
        LumenClient.getEventBus().subscribe(EventRender2D.class, this::onRender2D);
    }

    private void onRender2D(EventRender2D event) {
        renderHud(event.drawContext, event.tickDelta);
    }

    public void renderHud(DrawContext context, float tickDelta) {
        if (GuiManager.getInstance() != null && GuiManager.getInstance().isGuiOpen()) {
            return;
        }
        for (HudElement element : hudElements) {
            if (element.isEnabled()) {
                element.render(context, tickDelta);
            }
        }
    }

    public List<HudElement> getHudElements() {
        return hudElements;
    }
}
