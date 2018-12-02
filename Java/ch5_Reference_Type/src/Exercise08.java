public class Exercise08 {
    public static void main(String[] args) {
        int[][] array = {
                {95, 86},
                {83, 92, 96},
                {78, 83, 93, 87, 88}
        };

        int sum = 0;
        double avg = 0.0;

        for (int i=0; i<array.length; i++){
            for(int j=0; j<array[i].length; j++){
                sum += array[i][j];
            }
        }
        avg = (double) sum / ( array[0].length + array[1].length + array[2].length );

        System.out.println("sum: " + sum);
        System.out.println("avg: " + avg);
    }
}
