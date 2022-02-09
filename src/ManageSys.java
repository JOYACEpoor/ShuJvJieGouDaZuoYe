import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class ManageSys {

    private final File file = new File("src\\Data\\Map");
    private HashMap<String, String> map;
    private final Scanner scanner=new Scanner(System.in);

    ManageSys() {
        if (file.exists() && file.isFile()) {
            try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
                map = (HashMap<String, String>) ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else map = new HashMap<>();
    }

    public static void main(String[] args) {
        new ManageSys().close();
    }

    public boolean login(){
        System.out.println("请输入用户名");
        String username = scanner.nextLine();
        System.out.println("请输入密码");
        String password = scanner.nextLine();
        return login(username, password);
    }
    public boolean login(String username, String password) {
        return map.containsKey(username) && map.get(username).equals(password);
    }

    public void register(){
        System.out.println("请输入用户名");
        String username = scanner.nextLine();
        System.out.println("请输入密码");
        String password = scanner.nextLine();
        System.out.println(register(username, password));
    }
    public boolean register(String username, String password) {
        if (!map.containsKey(username)) {
            map.put(username, password);
            return true;
        } else {
            System.out.println("用户名已被占用");
            return false;
        }
    }

    public void delete(){
        System.out.println("请输入用户名");
        String username = scanner.nextLine();
        System.out.println("请输入密码");
        String password = scanner.nextLine();
        System.out.println(delete(username, password));
    }
    public boolean delete(String username, String password) {
        if (map.containsKey(username) && map.get(username).equals(password)) {
            map.remove(username, password);
            return true;
        }
        return false;
    }

    public void close() {
        scanner.close();
        try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this.map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
