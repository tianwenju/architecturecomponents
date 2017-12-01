package com.delta.architecturecomponents.lifecycle.custom;

import java.util.ArrayList;
import java.util.List;

/**
 * @description :
 * @autHor :  Jason
 * @date : 2017/11/23 10:27
 */
public class LifecycleRegistry extends Lifecycle {

    private State mState;
    private List<LifecycleObserver> mObservers = new ArrayList<>();

    @Override
    public void addObserver(LifecycleObserver observer) {

        if (mObservers.contains(observer)) {
            return;
        }
        mObservers.add(observer);
    }

    @Override
    public void removeObserver(LifecycleObserver observer) {

        if (mObservers.contains(observer)) {
            mObservers.remove(observer);
        }
    }

    @Override
    public void handleLifecycleEvent(Event event) {

        for (LifecycleObserver mObserver : mObservers) {


            if (sync(event))
                //判断event状态执行change方法
                mObserver.onStateChanged(event);
        }
    }

    private boolean sync(Event event) {

        State mStateAfter = getStateAfter(event);
        if (mState == mStateAfter)
            return false;
        return true;
    }

    ;

    State getStateAfter(Event event) {
        switch (event) {
            case ON_CREATE:
            case ON_STOP:
                return State.CREATED;
            case ON_START:
            case ON_PAUSE:
                return State.STARTED;
            case ON_RESUME:
                return State.RESUMED;
            case ON_DESTROY:
                return State.DESTROYED;
            case ON_ANY:
                break;
        }
        throw new IllegalArgumentException("Unexpected event value " + event);
    }

}

