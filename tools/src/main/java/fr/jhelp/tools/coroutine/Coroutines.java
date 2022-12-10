package fr.jhelp.tools.coroutine;

import androidx.annotation.NonNull;

import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.Dispatchers;

public class Coroutines {
    /**
     * Launch action in UI/Main thread
     *
     * @param action Action to launch
     */
    public static void launchMain(@NonNull final Runnable action) {
        Dispatchers.getMain().dispatch(EmptyCoroutineContext.INSTANCE, action);
    }

    /**
     * Launch action in default thread
     *
     * @param action Action to launch
     */
    public static void launchDefault(@NonNull final Runnable action) {
        Dispatchers.getDefault().dispatch(EmptyCoroutineContext.INSTANCE, action);
    }

    /**
     * Launch action in IO thread
     *
     * @param action Action to launch
     */
    public static void launchIO(@NonNull final Runnable action) {
        Dispatchers.getIO().dispatch(EmptyCoroutineContext.INSTANCE, action);
    }
}
