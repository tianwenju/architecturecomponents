package com.delta.architecturecomponents.lifecycle.custom;

public abstract  class Lifecycle {

    public abstract void addObserver(LifecycleObserver observer);

    public abstract void removeObserver(LifecycleObserver observer);

    public abstract void handleLifecycleEvent( Event event);

    @SuppressWarnings("WeakerAccess")
    public enum Event {

        ON_CREATE,

        ON_START,

        ON_RESUME,

        ON_PAUSE,

        ON_STOP,

        ON_DESTROY,

        ON_ANY
    }
    @SuppressWarnings("WeakerAccess")
    public enum State {

        DESTROYED,


        INITIALIZED,


        CREATED,


        STARTED,


        RESUMED;


    }

}