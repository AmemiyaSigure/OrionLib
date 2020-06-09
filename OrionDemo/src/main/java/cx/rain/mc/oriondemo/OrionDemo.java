package cx.rain.mc.oriondemo;

import cx.rain.mc.orion.bootstrap.OrionBootstrap;
import cx.rain.mc.orion.bootstrap.PluginBuilder;
import org.bukkit.plugin.java.JavaPlugin;

public final class OrionDemo extends JavaPlugin {
    @Override
    public void onEnable() {
        //OrionBootstrap.init();
        PluginBuilder.create().withName("OrionDemo").build(this);
    }

    @Override
    public void onDisable() {

    }
}
