package net.lumen.client.module.render;

import net.lumen.client.event.EventRender3D;
import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.ColorSetting;
import net.lumen.client.setting.StringSetting;

public class BlockESPModule extends Module {
    private final StringSetting blockId;
    private final ColorSetting color;

    public BlockESPModule() {
        super("BlockESP", "Highlights specific block types in the world.", Category.RENDER, 0);
        blockId = new StringSetting("Block ID", "Block identifier to highlight.", "minecraft:obsidian");
        color = new ColorSetting("Color", "Highlight color.", 0xFFFFAA00);
        registerSetting(blockId);
        registerSetting(color);
    }

    @Override
    protected void onEnable() {
        subscribe(EventRender3D.class, this::onRender3D);
    }

    private void onRender3D(EventRender3D event) {
        // highlight matching blocks with outlines.
    }
}
