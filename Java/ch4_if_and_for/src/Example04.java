public class Example04 {
    public static void main(String[] args) {
        int dice1 = (int)(Math.random()*6 + 1);
        int dice2 = (int)(Math.random()*6 + 1);
        System.out.println("(" + dice1 + "," + dice2 + ")");

        while((dice1 + dice2 != 5)){
            dice1 = (int)(Math.random()*6 + 1);
            dice2 = (int)(Math.random()*6 + 1);
            System.out.println("(" + dice1 + "," + dice2 + ")");
        }
    }
}
