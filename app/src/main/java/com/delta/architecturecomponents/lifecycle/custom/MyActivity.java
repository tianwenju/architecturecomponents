package com.delta.architecturecomponents.lifecycle.custom;

public class MyActivity {

    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry();

    public LifecycleRegistry getLifecycleRegistry() {
        return mLifecycleRegistry;
    }

    public static void main(String[] args) {
        LifecycleRegistry lifecycleRegistry = new MyActivity().getLifecycleRegistry();
        lifecycleRegistry.addObserver(new LifecycleObserver() {
            @Override
            public void onStateChanged(Lifecycle.Event mEvent) {

            }
        });

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);

    }
}