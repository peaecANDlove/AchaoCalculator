import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File file = new File("../result.txt");
        try (Writer out = new FileWriter(file)) {

            System.out.println("输入需要创建的题目数");
            Scanner sc = new Scanner(System.in);
            int count = sc.nextInt();
            CreateCalculation calculation = new CreateCalculation();
            for (int i = 0; i < count; i++) {
                String s = calculation.createCalculation();
                System.out.println(s);
                out.write(s);
                out.write("\r\n");
            }
            out.close();
            System.out.println("创建成功！");
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("创建失败");
        }

    }
}
