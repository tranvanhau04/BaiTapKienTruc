public class Main {
    public static void main(String[] args) {

        Stock stock = new Stock();

        Investor a = new Investor("Investor A");
        Investor b = new Investor("Investor B");

        stock.attach(a);
        stock.attach(b);

        stock.setPrice(100);
        stock.setPrice(120);
    }
}
