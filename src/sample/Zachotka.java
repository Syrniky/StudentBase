package sample;

import java.util.ArrayList;

public class Zachotka {

    private ArrayList<Ozinka> ozinky;
    private double sredniyBal;

    public Zachotka(double sredniyBal) {
        ozinky = new ArrayList<>();
        this.sredniyBal = sredniyBal;
    }

    public Zachotka() {
        ozinky = new ArrayList<Ozinka>();
        sredniyBal = 0;
    }

    public void sort() { // Шелла
        int N = ozinky.size();
        for (int d = N / 2; d >= 1; d/= 2) {
            for (int i = d; i < N; ++i) {
                for (int j = i; j >= d && ozinky.get(j - d).getOzinka() > ozinky.get(j).getOzinka(); j -= d) {
                    swap(j, j - d);
                }
            }
        }
    }

    public void renewSredniyBal() {
        double s = 0;
        for (Ozinka ozinka : ozinky) {
            s += ozinka.getOzinka();
        }
        sredniyBal = s / ozinky.size();
    }

    public void deleteOzinka(Ozinka delete) {
        ozinky.remove(delete);
        renewSredniyBal();
    }

    public void addOzinka(String predmet, double ozinka) {
        ozinky.add(new Ozinka(predmet, ozinka));
        renewSredniyBal();
    }

    public void addOzinka(Ozinka ozinka) { ozinky.add(ozinka); }

    public double getSredniyBal() { return sredniyBal; }

    public ArrayList<Ozinka> getOzinky() { return ozinky; }

    public void setSredniyBal(double sredniyBal) { this.sredniyBal = sredniyBal; }

    private void swap(int x, int y) {
        Ozinka temporary = ozinky.get(x);
        ozinky.set(x,ozinky.get(y));
        ozinky.set(y,temporary);
    }
}
