package cx.rain.mc.orion.bootstrap;

public class OrionBootstrap {
    public static boolean HAS_BOOTSTRAPPED = false;

    public static void init() {
        if (!HAS_BOOTSTRAPPED) {
            setup();
        }

        doInit();
    }

    private static void setup() {

    }

    private static void doInit() {

    }
}
