package com.vava.designpattern.backup;

import java.util.Stack;

/**
 * @author Steve
 * Created on 2020-04
 */
public class SnapshotHolder {
    private Stack<Snapshot> snapShotStack = new Stack<>();
    public Snapshot pop() {
        return snapShotStack.pop();
    }
    public void push(Snapshot snapShot) {
        snapShotStack.push(snapShot);
    }
}
