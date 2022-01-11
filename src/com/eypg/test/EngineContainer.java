package com.eypg.test;

import java.util.List;

public class EngineContainer {

    private List<String> engineList;
    private static int count = 0;

    public EngineContainer(List<String> engineList) {
        this.engineList = engineList;
    }

    public synchronized String getEngineUrl() {
        if (engineList.size() == count) {
            count = 0;
        }
        return engineList.get(count++);
    }

    public boolean isEmpty() {
        boolean isEmpty = false;
        if (engineList.size() == 0)
            isEmpty = true;
        return isEmpty;
    }

}
