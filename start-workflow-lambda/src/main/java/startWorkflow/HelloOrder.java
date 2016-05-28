package startWorkflow;

public class HelloOrder {
    int amout;
    float price;

    public HelloOrder(){}

    public HelloOrder(int amount, float price) {
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
