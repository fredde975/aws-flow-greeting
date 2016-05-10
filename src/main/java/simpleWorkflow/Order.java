package simpleWorkflow;

public class Order{
    int amout;
    float price;

    public Order(){}

    public Order(int amount, float price) {
        this.amout = amount;
        this.price = price;
    }

    public int getAmout() {
        return amout;
    }

    public void setAmout(int amout) {
        this.amout = amout;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
