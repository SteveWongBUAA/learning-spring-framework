package com.vava.designpattern.backup;

import java.util.Scanner;

/**
 * @author Steve
 * Created on 2020-04
 */
public class BackupApp {
    public static void main(String[] args) {
        InputText inputText = new InputText();
        SnapshotHolder snapshotHolder = new SnapshotHolder();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals(":list")) {
                System.out.println(inputText.getText());
            } else if (input.equals(":undo")) {
                Snapshot snapshot = snapshotHolder.pop();
                inputText.restoreSnapShot(snapshot);
            } else {
                Snapshot snapshot = inputText.createSnapShot();
                snapshotHolder.push(snapshot);
                inputText.append(input);
            }
        }


    }
}
