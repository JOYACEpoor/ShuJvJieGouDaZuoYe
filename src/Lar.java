import java.util.Scanner;

public class Lar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ManageSys manageSys = new ManageSys();
        FunctionSys functionSys = new FunctionSys();
        System.out.println("输入：我是用户/我是快递员");
        switch (scanner.nextLine()) {
            case "我是用户" -> functionSys.remove();
            case "我是快递员" ->{
                System.out.println("输入：登录/注册/注销");
                switch (scanner.nextLine()) {
                    case "登录" -> {
                        if (manageSys.login()) {
                            System.out.println("输入：查询/放入/取出/发送");
                            switch (scanner.nextLine()) {
                                case "查询" -> functionSys.search();
                                case "放入" -> functionSys.add();
                                case "取出" -> functionSys.remove();
                                case "发送" -> functionSys.sendMessage();
                            }
                        }
                    }
                    case "注册" -> manageSys.register();
                    case "注销" -> manageSys.delete();
                }
            }
        }
        functionSys.close();
        manageSys.close();
        scanner.close();
    }
}
