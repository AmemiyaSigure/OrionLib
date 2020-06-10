package cx.rain.mc.orion.utility;

import cx.rain.mc.orion.Orion;
import cx.rain.mc.orion.bootstrap.PluginBuilder;
import cx.rain.mc.orion.bootstrap.PluginContainer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;

public class CallerHelper {
    public static PluginBuilder getCallerPlugin() {
        Class<? extends JavaPlugin> caller = CallerHelper.getCallerPluginClass();
        assert caller != null;
        String pluginName = caller.getName();
        for (PluginBuilder plugin : PluginContainer.getPlugins().values()) {
            if (plugin.getPlugin().getName().equals(pluginName)) {
                return plugin;
            }
        }
        return null;
    }

    public static Class<? extends JavaPlugin> getCallerPluginClass() {
        List<Class<?>> classes = null;
        for (Class<?> clazz: getCallerClasses()) {
            if (clazz.isAssignableFrom(JavaPlugin.class)) {
                return (Class<? extends JavaPlugin>) clazz;
            }
        }
        new InvalidClassException("There is no bukkit plugin call!").printStackTrace();
        return null;
    }

    public static List<Class<?>> getCallerClasses() {
        StackTraceElement[] stacks = new Exception().getStackTrace();
        List<Class<?>> classes = new ArrayList<>();
        for (StackTraceElement stack : stacks) {
            try {
                classes.add(Class.forName(stack.getClassName()));
            } catch (ClassNotFoundException ex) {
                Orion.INSTANCE.getLogger().warning("Class in stack trace not found!");
                ex.printStackTrace();
            }
        }
        return classes;
    }
}
