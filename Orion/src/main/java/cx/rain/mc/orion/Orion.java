package cx.rain.mc.orion;

import cx.rain.mc.orion.api.VersionString;
import cx.rain.mc.orion.api.annotation.OrionInject;
import cx.rain.mc.orion.locale.ILocale;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Orion extends JavaPlugin {
    public static final String NAME = "Orion";
    public static final VersionString VERSION = new VersionString(1, 0, 0);

    public static Orion INSTANCE = null;

    private Logger log = null;
    private FileConfiguration config = null;

    @OrionInject
    private ILocale locale;

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;

        log = getLogger();
        config = getConfig();
    }

    private void load(boolean hasLoaded) {

    }

    private void loadConfig() {

    }

    /*
    private void setI18n() {
        String locale = config.getString("language");
        if (locale == null || locale.isEmpty()) {
            Locale locDef = Locale.getDefault();
            i18n = new I18n(this, locDef);
            log.warning(i18n.format("languages.not_set", locDef));
        } else {
            String[] localeAndCountry = locale.split("_");
            if (localeAndCountry.length != 2) {
                Locale.setDefault(new Locale(localeAndCountry[0]));
            } else {
                Locale.setDefault(new Locale(localeAndCountry[0], localeAndCountry[1]));
            }
        }
        if (i18n == null) {
            i18n = new I18n(this, Locale.getDefault());
        }
    }
     */

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        INSTANCE = null;

        log = null;
        config = null;
    }

    public ILocale getLocale() {
        return locale;
    }
}
