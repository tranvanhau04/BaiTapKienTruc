package borrow;

public class SpecialEditionDecorator extends BorrowDecorator {
    public SpecialEditionDecorator(Borrow borrow) {
        super(borrow);
    }

    public void borrow() {
        borrow.borrow();
        System.out.println("➕ Mượn bản đặc biệt");
    }
}
