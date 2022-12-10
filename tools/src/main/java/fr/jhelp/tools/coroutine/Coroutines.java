package fr.jhelp.tools.coroutine;

import androidx.annotation.NonNull;

import java.util.function.Consumer;

import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;

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
    public static void launch(@NonNull final Runnable action) {
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

    public static <T> void collectFlow(@NonNull final Flow<T> flow, @NonNull final Consumer<T> action) {
        Coroutines.launch(() -> flow.collect(new ConsumerToFlowCollector<T>(action), new DoesNotingContinuation<>(Dispatchers.getDefault())));
    }

    public static <T> void collectFlowMain(@NonNull final Flow<T> flow, @NonNull final Consumer<T> action) {
        Coroutines.launchMain(() -> flow.collect(new ConsumerToFlowCollector<T>(action), new DoesNotingContinuation<>(Dispatchers.getMain())));
    }

    public static <T> void collectFlowIO(@NonNull final Flow<T> flow, @NonNull final Consumer<T> action) {
        Coroutines.launchIO(() -> flow.collect(new ConsumerToFlowCollector<T>(action), new DoesNotingContinuation<>(Dispatchers.getIO())));
    }

}
