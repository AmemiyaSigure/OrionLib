package cx.rain.mc.orion.bootstrap;

import java.util.LinkedHashMap;
import java.util.Map;

public class PluginContainer {
    private static Map<String, PluginBuilder> PLUGINS = new LinkedHashMap<>();

    protected static void register(String name, PluginBuilder plugin) {
        if (PLUGINS.containsKey(name) || PLUGINS.containsValue(plugin)) {
            throw new IllegalArgumentException("Plugin object is already registered, why do that?");
        }
        PLUGINS.put(name, plugin);
    }

    public static Map<String, PluginBuilder> getPlugins() {
        return new LinkedHashMap<>(PLUGINS);
    }
}
