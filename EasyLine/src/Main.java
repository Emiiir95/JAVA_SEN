/*
Sen
04/09/2024
TP applicative
* */

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Voyage alger = new Voyage();
        alger.setDestination("Kasba");
        alger.setDuree(10);
        System.out.println("La destination est " + alger.getDestination() + " pour " + alger.duree + " jours");

    }

}