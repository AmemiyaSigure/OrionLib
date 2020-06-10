package cx.rain.mc.orion.locale;

import cx.rain.mc.orion.Orion;
import cx.rain.mc.orion.bootstrap.PluginBuilder;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Locale;

public class LocaleI18n implements ILocale {
    private PluginBuilder plugin = null;
    private Locale locale = null;
    private YamlConfiguration language = null;

    public LocaleI18n(PluginBuilder plug, Locale loc) {
        plugin = plug;
        locale = loc;

        load();
    }

    public LocaleI18n(PluginBuilder plug, String lang, String country) {
        this(plug, new Locale(lang, country));
    }

    private void load() {

    }

    public String format(String key, Object... params) {
        String toFormat = language.getString(key);
        if (toFormat == null || toFormat.isEmpty()) {
            String missingKey = Orion.INSTANCE.getLocale().format("language.missing_key");
            plugin.getPlugin().getLogger().warning(missingKey);
        }
        return String.format(toFormat, params);
    }
}
