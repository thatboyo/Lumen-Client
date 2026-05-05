package net.lumen.client.module.legit;

import net.lumen.client.module.Category;
import net.lumen.client.module.Module;
import net.lumen.client.setting.StringSetting;

public class ChatSuffixModule extends Module {
    private final StringSetting suffix;

    public ChatSuffixModule() {
        super("ChatSuffix", "Appends a custom suffix to outgoing chat messages.", Category.LEGIT, 0);
        suffix = new StringSetting("Suffix", "The text appended to chat messages.", " | Lumen");
        registerSetting(suffix);
    }
}
