package cx.rain.mc.orion.inject;

import cx.rain.mc.orion.api.annotation.OrionInjectable;
import cx.rain.mc.orion.api.annotation.OrionSingleton;
import cx.rain.mc.orion.exception.InjectException;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.Consumer;

public class InjectorDepr {
    private Map<Class<?>, Object> singletons = Collections.synchronizedMap(new HashMap<>());

    private Map<Class<?>, Class<?>> singletonClasses = Collections.synchronizedMap(new HashMap<>());

    private Set<Class<?>> readyClasses = Collections.synchronizedSet(new HashSet<>());

    public InjectorDepr() {
        //singletons.put(Injector.class, this);
    }

    private <T, U extends T> InjectorDepr addSingleton(Class<T> target, U singleton) {
        Object result = singletons.put(target, singleton);
        if (result != null) {
            throw new InjectException("Duplicated singleton object for same class: " + target.getCanonicalName()
                    + " . Someone is fooling around. ");
        }
        return this;
    }

    public <T, U extends T> InjectorDepr addSingleton(Class<T> interfaceType, Class<U> clazz) {
        Object result = singletonClasses.put(interfaceType, clazz);
        if (result != null) {
            throw new InjectException("Duplicated singleton class: " + interfaceType.getCanonicalName()
                    + " . Are you kidding? ");
        }
        return this;
    }

    public <T> T createNew(Class<T> clazz) {
        return createNew(clazz, null);
    }

    public <T> T createNew(Class<T> clazz, Consumer<T> consumer) {
        Object result = singletons.get(clazz);
        if (result != null) {
            return (T) result;
        }

        List<Constructor<T>> constructors = new ArrayList<>();
        T target = null;

        for (Constructor<?> c : clazz.getConstructors()) {
            if (!c.isAnnotationPresent(OrionInjectable.class) && c.getParameterCount() > 0) {
                continue;
            }
            if (!c.isAccessible()) {
                continue;
            }
            constructors.add((Constructor<T>) c);
        }

        if (constructors.size() > 1) {
            throw new InjectException("Duplicated constructor for injection class: " + clazz.getCanonicalName()
                    + " . ");
        }
        if (constructors.size() == 0) {
            throw new InjectException("no accessible constructor for injection class: " + clazz.getCanonicalName()
                    + " . ");
        }

        readyClasses.add(clazz);
        //target = createFromConstructor(constructors.get(0));
        readyClasses.remove(clazz);

        boolean isSingleton = clazz.isAnnotationPresent(OrionSingleton.class);
        if (!isSingleton) {
            isSingleton = singletonClasses.containsKey(clazz);
        }
        if (isSingleton) {
            singletons.put(clazz, target);
        }
        if (consumer != null) {
            consumer.accept(target);
        }

        //injectMembers(target);

        return target;
    }
}
