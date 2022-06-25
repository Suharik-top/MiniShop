import DopClass.Seller;
import DopClass.Shop;

import java.util.Scanner;

public class Main {
    private static Shop shop = new Shop();
    private static double balance = 1000.0;
    private static String Status;
    private static Seller seller = null;
    private static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        Start();
    }
    private static void Start(){
        do System.out.println("\n\n��� �� ???"); while(Authorization());
        switch (Status){
            case "1": Client();break;
            case "2": Manager();break;
            default: if(seller != null) Seller();break;
        }
    }
    private static boolean Authorization(){
        System.out.println("1) ����������");
        System.out.println("2) ��������");
        System.out.println("3) ��������");
        switch (console.nextInt()){
            case 1: Status = "1";return false;
            case 2: Status = "2";return false;
            case 3: {
                String sellersAll = shop.getManager().SellerAll();
                if (sellersAll.length() != 0){
                    System.out.println("\n\n!!!  ��� ��������:  !!!\n"+sellersAll);
                    System.out.println("�������� ���� ���");
                    String id = shop.selectSeller(console.next());
                    if(id.equals("3_")){ System.out.println("������ �������� ���");break;}
                    else {
                        Status = id;
                        if(shop.getSeller(Status.split("_")[1]) != null)
                            seller = shop.getSeller(Status.split("_")[1]);
                    }
                }
                else System.out.println("� �������� ��� ���������");
            }return false;
        }
        return true;
    }
    private static void Client(){
        while (true){
            System.out.println("\n1) ����������� ��� ��������");
            System.out.println("2) ������ �������");
            System.out.println("3) ������ ���� ������");
            System.out.println("4) ��������� ���� ������");
            System.out.println("5>= ) �����");
            System.out.println("�������� ��������");
            switch (console.nextInt()){
                case 1: System.out.println(shop.AllProduct());break;
                case 2: if(shop.FreeSeller()) {
                    System.out.println("������� �������� ��������");
                    double status = shop.BuyProduct(console.next(),balance);
                    if(status >= 0) {
                        balance -= status;
                        System.out.println("������� �� �������");
                    }
                    if (status == -1) System.out.println("������������ ����� �� �������");
                    if (status == -2) System.out.println("����� �� ������");
                    break;
                } else System.out.println("!!!!��� �� ������ ��������!!!!\n�������� ����� ����������"); break;
                case 3: System.out.println("��� ������ " + balance);break;
                case 4: {
                    System.out.println("������� ����� ����������");
                    balance += console.nextDouble();
                }break;
                case 5: Start();return;
            }
        }
    }
    private static void Manager(){
        while (true){
            System.out.println("\n1) ����������� ���� ���������");
            System.out.println("2) ������ ��������");
            System.out.println("3) ������� ��������");
            System.out.println("4>= ) �����");
            System.out.println("�������� ��������");
            switch (console.nextInt()){
                case 1: System.out.println(shop.getManager().SellerAll());break;
                case 2: {
                    System.out.println("������� ��� ��������");
                    shop.getManager().AddSeller(console.next());
                }break;
                case 3: {
                    System.out.println("������� id ��������");
                    if(shop.getManager().DelSeller(console.next()))
                        System.out.println("�������� ������");
                    else System.out.println("�������� �� ������");
                }break;
                case 4: Start();return;
            }
        }
    }
    private static void Seller(){
        while (true){
            System.out.println("\n1) ����������� ��� ��������");
            System.out.println("2) �������� �������");
            System.out.println("3) ������� �������");
            System.out.println("4>= ) �����");
            System.out.println("�������� ��������");
            switch (console.nextInt()){
                case 1: System.out.println(shop.AllProduct());break;
                case 2: {
                    System.out.println("������� ��� � ���� ��������   (������  ������ 100,53)");
                    seller.AddProduct(console.next(),console.nextDouble());
                }break;
                case 3: {
                    System.out.println("������� ��� ��������");
                    if(seller.DelProduct(console.next()))
                        System.out.println("������� ������");
                    else System.out.println("������� �� ������");
                }break;
                case 4: Start();return;
            }
        }
    }
}
