package fr.jhelp.tools.coroutine;

import androidx.annotation.NonNull;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineDispatcher;

public class DoesNotingContinuation<T> implements Continuation<T> {
    private final CoroutineContext coroutineContext;

    public DoesNotingContinuation(@NonNull final CoroutineContext coroutineContext) {
        this.coroutineContext = coroutineContext;
    }

    public DoesNotingContinuation(@NonNull final CoroutineDispatcher coroutineDispatcher) {
        this.coroutineContext = (CoroutineContext) coroutineDispatcher;
    }


    @NonNull
    @Override
    public CoroutineContext getContext() {
        return this.coroutineContext;
    }

    @Override
    public void resumeWith(@NonNull Object o) {
        // Does nothing
    }
}
