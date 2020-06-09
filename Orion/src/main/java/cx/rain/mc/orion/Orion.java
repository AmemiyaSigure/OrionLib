package cx.rain.mc.orion;

import cx.rain.mc.orion.api.VersionString;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Orion extends JavaPlugin {
    public static final String NAME = "Orion";
    public static final VersionString VERSION = new VersionString(1, 0, 0);

    protected static Orion INSTANCE = null;

    private Logger log = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;

        log = getLogger();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }

    protected Logger getLog() {
        return log;
    }
}
