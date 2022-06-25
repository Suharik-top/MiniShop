package DopClass;

public class Seller {
    private Shop shop;
    private String name;
    public Seller(Shop shop, String name) {
        this.shop = shop;
        this.name = name;
    }
    public boolean DelProduct(String name){
        for (Product prod: shop.products)
            if(prod.getName().equals(name)) {
                shop.products.remove(prod);
                return true;
            }
        return false;
    }
    public void AddProduct(String name, double price){
        shop.products.add(new Product(name, price));
    }
    public String getName() {return name;}
}
