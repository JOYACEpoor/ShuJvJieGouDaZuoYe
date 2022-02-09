import Data.Cup;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class FunctionSys {

    private final File file = new File("src\\Data\\Array");
    private final Scanner scanner = new Scanner(System.in);
    private ArrayList<Cup> array;

    FunctionSys() {
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois1 = new ObjectInputStream(fis)) {
            array = (ArrayList<Cup>) ois1.readObject();
        } catch (Exception e) {
            array = new ArrayList<>();
            for(int i=0;i<10;i++)
                array.add(new Cup());
        }
    }

    public static void main(String[] args) {
        new FunctionSys().close();
    }

    public void add(){
        System.out.println("请输入快递柜号");
        int index=Integer.parseInt(scanner.nextLine());
        System.out.println("请输入电话号码");
        long phone=Long.parseLong(scanner.nextLine());
        int code=new Random(new Date().getTime()).nextInt(100000000);
        add(index,new Cup(checkSame(code),phone,false));
    }
    public void add(int index,Cup cup){
        array.set(index-1,cup);
    }

    public void remove(){
        System.out.println("请输入取件码");
        remove(Long.parseLong(scanner.nextLine()));
    }
    public void remove(Long code){
        for (int i=0;i<10;i++){
            if(array.get(i).code==code) {
                array.set(i, new Cup());
                System.out.println("已成功取出");
                return;
            }
        }
        System.out.println("取件码错误");
    }

    public void search() {
        for (int i = 0; i < 10; i++) {
            if (array.get(i).flag)
                System.out.println("第" + (i + 1) + "个格口可用");
        }
    }

    public void sendMessage() {
        for(int i=0;i<10;i++){
            if(!array.get(i).flag)
                System.out.println("快递柜号："+(i+1)+"\n"+"手机号："+array.get(i).phone+"\n"+"取件码："+array.get(i).code);
        }
    }

    private int checkSame(int code){
        for (int i=0;i<10;i++){
            if(array.get(i).code==code) {
                checkSame(new Random(new Date().getTime()).nextInt(100000000));
            }
        }
        return code;
    }

    public void close() {
        scanner.close();
        try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(array);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
