package cx.rain.mc.orion.utility;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Locale;

public class I18n {
    private Locale Loc = null;
    private YamlConfiguration Lang = null;

    public I18n(String lang, String country) {
        Loc = new Locale(lang, country);

        //load();
    }

    /*
    private void load() {
        File file = new File();
        Lang = YamlConfiguration.loadConfiguration(file);
    }

    public String format(String key, Object... params) {

    }
     */
}
