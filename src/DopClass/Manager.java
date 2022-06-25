package DopClass;

import java.util.Objects;

public class Manager {
    private Shop shop;
    private String name;

    public Manager(Shop shop, String name) {
        this.shop = shop;
        this.name = name;
    }
    public String SellerAll(){
        String NameAll = "";
        for (Seller sell: shop.sellers) NameAll += sell.getName() + "\n";
        return NameAll;
    }
    public boolean DelSeller(String name){
        for (Seller sell: shop.sellers)
            if(Objects.equals(sell.getName(), name)) {
                shop.sellers.remove(sell);
                return true;
            }
        return false;
    }
    public void AddSeller(String name){
        shop.sellers.add(new Seller(shop,name));
    }
}
