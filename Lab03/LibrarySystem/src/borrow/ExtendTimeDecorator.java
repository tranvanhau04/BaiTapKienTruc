package borrow;

public class ExtendTimeDecorator extends BorrowDecorator {
    public ExtendTimeDecorator(Borrow borrow) {
        super(borrow);
    }

    public void borrow() {
        borrow.borrow();
        System.out.println("➕ Gia hạn thời gian mượn");
    }
}
