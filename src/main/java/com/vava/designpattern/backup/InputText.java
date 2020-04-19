package com.vava.designpattern.backup;

/**
 * @author Steve
 * Created on 2020-04
 */
public class InputText {
    private StringBuilder text = new StringBuilder();
    public String getText() {
        return text.toString();
    }

    public void append(String input) {
        text.append(input);
    }

    public Snapshot createSnapShot() {
        return new Snapshot(text.toString());
    }

    public void restoreSnapShot(Snapshot snapshot) {
        this.text.replace(0, text.length(), snapshot.getText());
    }

}
