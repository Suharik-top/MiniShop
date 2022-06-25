package DopClass;

import java.util.ArrayList;
import java.util.Objects;

public class Shop {
    private Manager manager;
    protected ArrayList<Seller> sellers = new ArrayList<>();
    protected ArrayList<Product> products = new ArrayList<>();

    public Shop() {
        manager = new Manager(this,"�������");

        sellers.add(new Seller(this,"���������"));
        sellers.add(new Seller(this,"�����������"));

        products.add(new Product("���������",78));
        products.add(new Product("������",30.6));
        products.add(new Product("�����",60));
    }
    public Manager getManager() {return manager;}
    public String selectSeller(String name){
        String Status = "3_";
        for (Seller sell: sellers)
            if(Objects.equals(sell.getName(), name))
                Status += sell.getName();
        return Status;
    }
    public String AllProduct(){
        String allProduct = "";
        for (Product prod: products) allProduct += prod.getName() + "\t���� " + prod.getPrice() + "\n";
        return allProduct;
    }
    public double BuyProduct(String name, double balans){
        for (Product prod: products)
            if(prod.getName().equals(name))
                if(balans >= prod.getPrice()) return prod.getPrice();
                else return -1;
        return -2;
    }
    public boolean FreeSeller(){
        if(sellers.size() > 0) return true;
        return false;
    }
    public Seller getSeller(String name){
        for (Seller sell:sellers)
            if(sell.getName().equals(name))
                return sell;
        return null;
    }
}
