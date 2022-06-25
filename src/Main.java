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
        do System.out.println("\n\nКто вы ???"); while(Authorization());
        switch (Status){
            case "1": Client();break;
            case "2": Manager();break;
            default: if(seller != null) Seller();break;
        }
    }
    private static boolean Authorization(){
        System.out.println("1) Покупатель");
        System.out.println("2) Менеджер");
        System.out.println("3) Продавец");
        switch (console.nextInt()){
            case 1: Status = "1";return false;
            case 2: Status = "2";return false;
            case 3: {
                String sellersAll = shop.getManager().SellerAll();
                if (sellersAll.length() != 0){
                    System.out.println("\n\n!!!  Все продавцы:  !!!\n"+sellersAll);
                    System.out.println("Назовите свое имя");
                    String id = shop.selectSeller(console.next());
                    if(id.equals("3_")){ System.out.println("Такого продовца нет");break;}
                    else {
                        Status = id;
                        if(shop.getSeller(Status.split("_")[1]) != null)
                            seller = shop.getSeller(Status.split("_")[1]);
                    }
                }
                else System.out.println("В магазине нет продавцов");
            }return false;
        }
        return true;
    }
    private static void Client(){
        while (true){
            System.out.println("\n1) Просмотреть все продукты");
            System.out.println("2) Купить продукт");
            System.out.println("3) Узнать свой баланс");
            System.out.println("4) Пополнить свой баланс");
            System.out.println("5>= ) Выйти");
            System.out.println("Выберете действие");
            switch (console.nextInt()){
                case 1: System.out.println(shop.AllProduct());break;
                case 2: if(shop.FreeSeller()) {
                    System.out.println("Введите название продукта");
                    double status = shop.BuyProduct(console.next(),balance);
                    if(status >= 0) {
                        balance -= status;
                        System.out.println("Спасибо за покупку");
                    }
                    if (status == -1) System.out.println("Недостаточно денег на балансе");
                    if (status == -2) System.out.println("Товар не найден");
                    break;
                } else System.out.println("!!!!Нет не одного продовца!!!!\nЖалобную книгу пожалуйста"); break;
                case 3: System.out.println("Ваш баланс " + balance);break;
                case 4: {
                    System.out.println("Введите сумму пополнения");
                    balance += console.nextDouble();
                }break;
                case 5: Start();return;
            }
        }
    }
    private static void Manager(){
        while (true){
            System.out.println("\n1) Просмотреть всех продовцов");
            System.out.println("2) Нанять продавца");
            System.out.println("3) Уволить продавца");
            System.out.println("4>= ) Выйти");
            System.out.println("Выберете действие");
            switch (console.nextInt()){
                case 1: System.out.println(shop.getManager().SellerAll());break;
                case 2: {
                    System.out.println("Введите имя продовца");
                    shop.getManager().AddSeller(console.next());
                }break;
                case 3: {
                    System.out.println("Введите id продовца");
                    if(shop.getManager().DelSeller(console.next()))
                        System.out.println("Продавец удален");
                    else System.out.println("Продавец не найден");
                }break;
                case 4: Start();return;
            }
        }
    }
    private static void Seller(){
        while (true){
            System.out.println("\n1) Просмотреть все продукты");
            System.out.println("2) Добавить продукт");
            System.out.println("3) Удалить продукт");
            System.out.println("4>= ) Выйти");
            System.out.println("Выберете действие");
            switch (console.nextInt()){
                case 1: System.out.println(shop.AllProduct());break;
                case 2: {
                    System.out.println("Введите имя и цену продукта   (пример  бананы 100,53)");
                    seller.AddProduct(console.next(),console.nextDouble());
                }break;
                case 3: {
                    System.out.println("Введите имя продукта");
                    if(seller.DelProduct(console.next()))
                        System.out.println("Продукт удален");
                    else System.out.println("Продукт не найден");
                }break;
                case 4: Start();return;
            }
        }
    }
}
