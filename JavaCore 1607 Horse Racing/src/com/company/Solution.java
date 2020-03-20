package com.company;

/*

1607 Horse Racing
Understand what the program does.
Implement the calculateHorsesFinished method.
He must:
1. Count the number of horses that have finished and return it. Use the isFinished () method.
2. If the horse has not yet reached the finish line (! IsFinished ()), then:
2.1. Print “Waiting for” + horse.getName () to the console.
2.2. Wait until she finishes the race. Think about which method to use for this.
2.3. Do not consider such a horse to finish.

Requirements:
1. The calculateHorsesFinished method should return the number of horses that have finished.
2. The calculateHorsesFinished method should call the isFinished method on each horse from the transferred list.
3. If any of the horses listed in the list has not yet finished, the calculateHorsesFinished method should output to the console "Waiting for" + horse.getName (). Example message for the first horse: "Waiting for Horse_01".
4. If any of the horses listed in the list has not yet finished, the calculateHorsesFinished method must wait until it finishes. Use the correct method to wait.
5. After completion of the program, the console should contain information that all horses have finished. Example message for the first horse: "Horse_01 has finished the race!".

 */

import java.util.ArrayList;
import java.util.List;

/*
Horse Racing
*/

public class Solution {
    public static int countHorses = 10;

    public static void main(String[] args) throws InterruptedException {
        List<Horse> horses = prepareHorsesAndStart();
        while (calculateHorsesFinished(horses) != countHorses) {
        }
    }

    public static int calculateHorsesFinished(List<Horse> horses) throws InterruptedException {
        int countFinished = 0;

        for (int i = 0; i < horses.size(); i++){
            if (horses.get(i).isFinished()) countFinished++;
            else {
                System.out.println("Waiting for " + horses.get(i).getName());
                horses.get(i).join();
            }
        }

        return countFinished;
    }

    public static List<Horse> prepareHorsesAndStart() {
        List<Horse> horses = new ArrayList<Horse>(countHorses);
        String number;
        for (int i = 1; i < countHorses + 1; i++) {
            number = i < 10 ? ("0" + i) : "" + i;
            horses.add(new Horse("Horse_" + number));
        }

        for (int i = 0; i < countHorses; i++) {
            horses.get(i).start();
        }
        return horses;
    }

    public static class Horse extends Thread {

        private boolean isFinished;

        public Horse(String name) {
            super(name);
        }

        public boolean isFinished() {
            return isFinished;
        }

        public void run() {
            String s = "";
            for (int i = 0; i < 1001; i++) {
                s += "" + i;
                if (i == 1000) {
                    s = " has finished the race!";
                    System.out.println(getName() + s);
                    isFinished = true;
                }
            }
        }
    }
}



