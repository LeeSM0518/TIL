package check_problem;

class Tire {
    public void run() {
        System.out.println("일반 타이어가 굴러간다.");
    }
}

class SnowTire extends Tire {
    public void run() {
        System.out.println("스노우 타이어가 굴러간다.");
    }
}

class SnowTireExample {
    public static void main(String[] args) {
        SnowTire snowTire = new SnowTire();
        Tire tire = snowTire;

        snowTire.run();
        tire.run();
    }
}