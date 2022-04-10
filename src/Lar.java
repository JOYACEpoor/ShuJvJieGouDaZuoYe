import java.util.Scanner;

public class Lar {
    private static final Scanner scanner = new Scanner(System.in);
    private static final FunctionSys functionSys = new FunctionSys();
    private static final Lar lar = new Lar();

    public static void main(String[] args) {
        lar.test();
    }

    public void test(){
        System.out.println("查询/订票/修改航班信息/退票");
        functionSys.showAll();
        switch(scanner.nextLine()){
            case "查询" : {
                functionSys.search();
                break;
            }
            case "订票" : {
                functionSys.order();
                break;
            }
            case "修改航班信息" : {
                functionSys.modify();
                break;
            }
            case "退票" : {
                functionSys.reOrder();
                break;
            }
            default: {
                System.out.println("输入错误！请重新输入");
                test();
                break;
            }
        }
        functionSys.close();
        scanner.close();
    }
}
