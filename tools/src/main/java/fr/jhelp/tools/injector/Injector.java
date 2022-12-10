package fr.jhelp.tools.injector;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import java.util.HashMap;

/**
 * Inject or retrieve instance for a class, abstract class or interface.<br>
 * <br>
 * To inject use :<br>
 * <code>
 * Injector.inject(MyInterface.class, myInterfaceInstance);
 * </code><br>
 * <br>
 * To get instance:<br>
 * <code>
 * final MyInterface instance = Injector.instance(MyInterface.class);
 * </code><br>
 * <br>
 * The instance can be used anywhere, just be sure injection is done before use it.<br>
 * Injection can be changed by re-inject a different instance for same class, abstract class or interface.
 */
public class Injector {
    /**
     * Injected instances
     */
    private static final HashMap<String, Object> injected = new HashMap<String, Object>();

    /**
     * Inject instance for a class, abstract class or interface.
     *
     * @param interfaceClass Class to associate instance with
     * @param instance       Instance to associate
     * @param <I>            Class, abstract class or interface type
     * @param <T>            Instance type (must be <b>I</b> or extends <b>I</b>)
     */
    public static <I, T extends I> void inject(@NonNull final Class<I> interfaceClass, @NonNull final T instance) {
        synchronized (Injector.injected) {
            Injector.injected.put(interfaceClass.getName(), instance);
        }
    }

    /**
     * Retrieve injected instance for a class, abstract class or interface.
     *
     * @param interfaceClass Class to get the instance
     * @param <I>            Class, abstract class or interface type
     * @return Injected instance
     * @throws IllegalArgumentException If nothing was injected for specified class, abstract class or interface.
     */
    @NonNull
    public static <I> I instance(@NonNull final Class<I> interfaceClass) {
        final Object instance;

        synchronized (Injector.injected) {
            instance = Injector.injected.get(interfaceClass.getName());
        }

        if (instance == null) {
            throw new IllegalArgumentException("Nothing injected for the class : " + interfaceClass.getName());
        }

        return (I) instance;
    }

    /**
     * Clear registered injections, useful for tests
     */
    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public static void clearInjections() {
        synchronized (Injector.injected) {
            Injector.injected.clear();
        }
    }
}
