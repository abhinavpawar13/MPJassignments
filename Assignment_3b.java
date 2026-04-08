package sample;

class Hillstations {

    void famousfood() {
        System.out.println("Hill station famous food");
    }

    void famousfor() {
        System.out.println("Hill station famous for scenic beauty");
    }
}

// Subclass 1
class Manali extends Hillstations {
    void famousfood() {
        System.out.println("Manali Famous Food: Siddu");
    }

    void famousfor() {
        System.out.println("Manali Famous For: Snow and Adventure Sports");
    }
}

// Subclass 2
class Mussoorie extends Hillstations {
    void famousfood() {
        System.out.println("Mussoorie Famous Food: Momos");
    }

    void famousfor() {
        System.out.println("Mussoorie Famous For: Hills and Waterfalls");
    }
}

// Subclass 3
class Shimla extends Hillstations {
    void famousfood() {
        System.out.println("Shimla Famous Food: Chana Madra");
    }

    void famousfor() {
        System.out.println("Shimla Famous For: Colonial Architecture");
    }
}

public class Assignment_3b {

    public static void main(String[] args) {

        Hillstations h;

        // Runtime Polymorphism
        h = new Manali();
        h.famousfood();
        h.famousfor();

        System.out.println();

        h = new Mussoorie();
        h.famousfood();
        h.famousfor();

        System.out.println();

        h = new Shimla();
        h.famousfood();
        h.famousfor();
    }
}