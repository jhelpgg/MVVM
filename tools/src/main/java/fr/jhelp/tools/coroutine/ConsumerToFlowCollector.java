package fr.jhelp.tools.coroutine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.function.Consumer;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

public class ConsumerToFlowCollector<T> implements FlowCollector<T> {
    @NonNull
    private final Consumer<T> consumer;

    public ConsumerToFlowCollector(@NonNull Consumer<T> consumer) {
        this.consumer = consumer;
    }

    @Nullable
    @Override
    public Object emit(@NonNull T element, @NonNull Continuation<? super Unit> continuation) {
        this.consumer.accept(element);
        return null;
    }
}
