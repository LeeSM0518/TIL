import java.io.*;

public class Test {

    public static void main(String[] args) throws IOException {
        String current = new java.io.File(".").getCanonicalPath();
        File file = new File(current + "/test.java");
        FileWriter fileWriter = new FileWriter(file, false);

        System.out.println();

        fileWriter.write("class A {\n\tpublic static void main(String[] args) {\n\t\t System.out.println(\"hello\");\n\t}\n}");
        fileWriter.flush();
        fileWriter.close();

        try {
            String[] cmdArray = {"javac test.java", "java Test"};
            Process process = Runtime.getRuntime().exec(cmdArray[1]);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = null;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//public class Test {
//    public static void main(String[] args) {
//        System.out.println("Hello");
//    }
//}