import Data.Flight;

import java.io.*;
import java.util.*;

public class FunctionSys {

    private static final FunctionSys functionSys = new FunctionSys();
    private static final File file = new File("src\\Data\\Map");
    private static final Scanner scanner = new Scanner(System.in);
    private HashMap<Flight, Integer> map;

    FunctionSys() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            map = (HashMap<Flight, Integer>) ois.readObject();
        } catch (Exception e) {
            map = new HashMap<>();
            map.put(new Flight("MU5735", "B1791", "中国"), 100);
            map.put(new Flight("JAL123", "JA8119", "日本"), 100);
        }
    }

    public void close() {
        scanner.close();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAll() {
        map.forEach((key, value) -> System.out.println(key.toString() + "\n" + "Tickets: " + value));
    }

    public void search(){
        System.out.println("请输入目的地后按回车");
        search(scanner.nextLine());
    }
    public void search(String endStation) {
        LinkedList<Flight> list = new LinkedList<>();
        map.forEach((key, value) -> {
            if (Objects.equals(key.getEndStation(), endStation))
                list.add(key);
        });
        if(list.isEmpty()){
            System.out.println("未搜索到匹配结果！");
            search();
        }else {
            list.forEach((flight -> {
                System.out.println(flight + "\n" + "Tickets: " + map.get(flight));
            }));
        }
    }

    public void order(){
        System.out.println("请输入航班号后按回车");
        String flightCode = scanner.nextLine();
        System.out.println("请输入订票数量后按回车");
        Integer tickets = Integer.valueOf(scanner.nextLine());
        order(flightCode,tickets);
    }
    public void order(String flightCode,Integer tickets){
        Flight flight = null;
        for(Map.Entry<Flight,Integer> entry : map.entrySet()){
            if(entry.getKey().getFlightCode().equals(flightCode))
                flight=entry.getKey();
        }
        if(flight!=null){
            Integer i = map.get(flight);
            if(i>tickets){
                map.remove(flight);
                map.put(flight,i-tickets);
                System.out.println("OK");
                showAll();
            }else {
                System.out.println("机票余量不足！");
                order();
            }
        }else {
            System.out.println("没有查询到航班，请确认后输入！");
            order();
        }
    }

    public void reOrder(){
        System.out.println("请输入航班号后按回车");
        String flightCode = scanner.nextLine();
        System.out.println("请输入退票数量后按回车");
        Integer tickets = Integer.valueOf(scanner.nextLine());
        reOrder(flightCode,tickets);
    }
    public void reOrder(String flightCode,Integer tickets){
        Flight flight = null;
        for(Map.Entry<Flight,Integer> entry : map.entrySet()){
            if(entry.getKey().getFlightCode().equals(flightCode))
                flight=entry.getKey();
        }
        if(flight!=null){
            Integer i = map.get(flight);
            map.remove(flight);
            map.put(flight,i+tickets);
            System.out.println("OK");
            showAll();
        }else {
            System.out.println("没有查询到航班，请确认后输入！");
            reOrder();
        }
    }

    public void modify(){
        System.out.println("请输入航班信息");
        System.out.println("请输入航班号后回车");
        String flightCode = scanner.nextLine();
        System.out.println("请输入飞机编号后回车");
        String planeCode = scanner.nextLine();
        System.out.println("请输入终点后回车");
        String endStation = scanner.nextLine();
        System.out.println("请输入可用票数后回车");
        Integer tickets = Integer.valueOf(scanner.nextLine());
        modify(flightCode,planeCode,endStation,tickets);
    }
    public void modify(String flightCode, String planeCode, String endStation, Integer tickets){
        Flight flight = null;
        for(Map.Entry<Flight,Integer> entry : map.entrySet()){
            if(entry.getKey().getFlightCode().equals(flightCode))
                flight=entry.getKey();
        }
        if(flight!=null){
            map.remove(flight);
            map.put(new Flight(flightCode,planeCode,endStation),tickets);
            System.out.println("OK");
            showAll();
        }else{
            System.out.println("没有查询到航班，请确认后输入！");
            modify();
        }
    }
}
