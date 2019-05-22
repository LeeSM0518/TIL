public class Exercise04 {
    public static void main(String[] args) {
        int pencils = 534;
        int student = 30;

        // 학생 한 명이 가지는 연필 수
        int pencilsPerStudent = pencils / student;
        System.out.println(pencilsPerStudent);

        // 남은 연필 수
        int pencilsLeft = pencils % student;
        System.out.println(pencilsLeft);
    }
}
