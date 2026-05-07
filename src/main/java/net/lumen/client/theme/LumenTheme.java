package net.lumen.client.theme;

import net.minecraft.util.Identifier;

public enum LumenTheme {
    GHOST(0x0A0A0A, 0x111111, 0x222222, 0xFFFFFF, 0x666666, 0xFFFFFF, 0, 0, 0),
    CYBER(0x050510, 0x0A0A1A, 0x0D0D2B, 0x00AAFF, 0x0066CC, 0x00AAFF, 0, 0, 0),
    HACKER(0x010F01, 0x051005, 0x0A1F0A, 0x00FF41, 0x1A6B1A, 0x00FF41, 0, 0, 0);

    public final int background;
    public final int panel;
    public final int border;
    public final int accent;
    public final int muted;
    public final int enabledIndicator;
    public final int glow;
    public final int unused1;
    public final int unused2;

    public static LumenTheme active = GHOST;

    LumenTheme(int background, int panel, int border, int accent, int muted, int enabledIndicator, int glow, int unused1, int unused2) {
        this.background = background;
        this.panel = panel;
        this.border = border;
        this.accent = accent;
        this.muted = muted;
        this.enabledIndicator = enabledIndicator;
        this.glow = glow;
        this.unused1 = unused1;
        this.unused2 = unused2;
    }

    public Identifier watermarkAsset() {
        return Identifier.of("lumen", "textures/gui/watermark.png");
    }

    public Identifier nametagWatermarkAsset() {
        return Identifier.of("lumen", "textures/gui/nametag_watermark.png");
    }
}
