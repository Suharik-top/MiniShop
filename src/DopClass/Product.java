package DopClass;

public class Product {
    private String Name;
    private double Price;

    public Product(String name, double price) {
        Name = name;
        Price = price;
    }

    public String getName() {return Name;}
    public void setName(String name) {Name = name;}

    public double getPrice() {return Price;}
    public void setPrice(double price) {Price = price;}
}
