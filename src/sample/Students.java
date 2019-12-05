package sample;

import java.util.ArrayList;

public class Students implements Cloneable {
    private String name;
    private int age;
    private String gruppa;
    private int kurs;
    private String kurator;
    private Zachotka zachotka;
    //короче в этой зачетке надо нахуярить всяких оценочек и добавить возможность редактировать их спс
    //всю эту инфу затолкать в контрол.информ
    //продублируй эту контрол.информ кнопку только в дубликате добавь как раз таки возможность изменять информацию
    //у меня типо прога как для студента так и для деканата спс только вот я не реализовала еще разный вход
    //поэтому пускай будет еще одна кнопка


    public String getName() { return name; }
    public String getGruppa() { return gruppa; }
    public int getKurs() { return kurs; }
    public int getAge() { return age; }
    public String getKurator() { return kurator; }
    public Zachotka getZachotka() { return zachotka; }

    public Students(String name, int age, String gruppa, int kurs, String kurator, double sredniyball) {

        this.name = name;
        this.age = age;
        this.gruppa = gruppa;
        this.kurs = kurs;
        this.kurator = kurator;
        this.zachotka = new Zachotka();
        this.zachotka.setSredniyBal(sredniyball);
    }

    @Override
    public String toString() {
        return "Повне ім'я: \t\t"+this.getName()+
                "\nВік: \t\t\t\t" + this.getAge() +
                "\nГрупа: \t\t\t" +this.getGruppa()+
                "\nКурс: \t\t\t"+this.getKurs()+
                "\nКуратор: \t\t\t"+this.getKurator()+
                "\nСередній бал: \t"+this.getZachotka().getSredniyBal();
    }

    public static void sort(ArrayList<Students> list) {
        for (int i = 1; i < list.size(); ++i) {
            Students key = list.get(i);
            int j = i;
            while (j > 0 && list.get(j - 1).getName().compareTo(key.getName()) > 0) {
                swap(list, j, j - 1);
                j -= 1;
            }
            list.set(j, key);
        }
    }

    private static void swap(ArrayList<Students> list, int x, int y) {
        Students temporary = list.get(x);
        list.set(x,list.get(y));
        list.set(y,temporary);
    }
}
