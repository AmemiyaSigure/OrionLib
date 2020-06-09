package cx.rain.mc.orion.bootstrap;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class PluginBuilder {
    private String PluginName = "";
    private JavaPlugin Plugin = null;

    private List<String> packages = new ArrayList<>();

    private PluginBuilder() {
    }

    public PluginBuilder withName(String name) {
        PluginName = name;
        return this;
    }

    public PluginBuilder addPackage(String packageName) {
        packages.add(packageName);
        return this;
    }

    public void build(JavaPlugin plugin) {
        Plugin = plugin;

        if (PluginName.isEmpty()) {
            PluginName = Plugin.getName();
        }

        if (packages.size() == 0) {
            packages.add(Plugin.getClass().getPackage().getName());
        }

        PluginContainer.register(PluginName, this);
    }

    public JavaPlugin getPlugin() {
        return Plugin;
    }

    public String getName() {
        return PluginName;
    }

    public List<String> getPackages() {
        return packages;
    }

    public static PluginBuilder create() {
        return new PluginBuilder();
    }
}
