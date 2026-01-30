import book.*;
import borrow.*;
import java.util.Scanner;
import library.Library;
import observer.*;
import search.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Singleton
        Library library = Library.getInstance();

        // Observer
        NotificationService notifyService = new NotificationService();
        notifyService.addObserver(new Librarian("Admin"));

        boolean running = true;

        while (running) {
            System.out.println("\n===== HỆ THỐNG QUẢN LÝ THƯ VIỆN =====");
            System.out.println("1. Thêm sách mới");
            System.out.println("2. Xem danh sách sách");
            System.out.println("3. Tìm kiếm sách");
            System.out.println("4. Mượn sách");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {

                // 1️⃣ THÊM SÁCH (Factory + Observer)
                case 1:
                    System.out.print("Nhập loại sách (paper / ebook / audio): ");
                    String type = scanner.nextLine();

                    System.out.print("Nhập tên sách: ");
                    String title = scanner.nextLine();

                    System.out.print("Nhập tác giả: ");
                    String author = scanner.nextLine();

                    System.out.print("Nhập thể loại: ");
                    String category = scanner.nextLine();

                    Book book = BookFactory.createBook(type, title, author, category);
                    library.addBook(book);

                    notifyService.notifyAll("Có sách mới: " + title);
                    break;

                // 2️⃣ XEM DANH SÁCH
                case 2:
                    System.out.println("\nDanh sách sách trong thư viện:");
                    for (Book b : library.getBooks()) {
                        System.out.println("- " + b.getTitle()
                                + " | " + b.getAuthor()
                                + " | " + b.getCategory());
                    }
                    break;

                // 3️⃣ TÌM KIẾM (Strategy)
                case 3:
                    SearchContext searchContext = new SearchContext();

                    System.out.println("Chọn kiểu tìm kiếm:");
                    System.out.println("1. Theo tên");
                    System.out.println("2. Theo tác giả");
                    System.out.println("3. Theo thể loại");
                    int searchType = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nhập từ khóa: ");
                    String keyword = scanner.nextLine();

                    if (searchType == 1) {
                        searchContext.setStrategy(new SearchByTitle());
                    } else if (searchType == 2) {
                        searchContext.setStrategy(new SearchByAuthor());
                    } else {
                        searchContext.setStrategy(new SearchByCategory());
                    }

                    searchContext.execute(library.getBooks(), keyword);
                    break;

                // 4️⃣ MƯỢN SÁCH (Decorator)
                case 4:
                    Borrow borrow = new BasicBorrow();

                    System.out.println("Chọn hình thức mượn:");
                    System.out.println("1. Mượn cơ bản");
                    System.out.println("2. Mượn bản đặc biệt");
                    System.out.println("3. Mượn + gia hạn");
                    System.out.println("4. Mượn bản đặc biệt + gia hạn");

                    int borrowChoice = scanner.nextInt();

                    if (borrowChoice == 2) {
                        borrow = new SpecialEditionDecorator(borrow);
                    } else if (borrowChoice == 3) {
                        borrow = new ExtendTimeDecorator(borrow);
                    } else if (borrowChoice == 4) {
                        borrow = new ExtendTimeDecorator(
                                    new SpecialEditionDecorator(borrow));
                    }

                    borrow.borrow();
                    break;

                // 0️⃣ THOÁT
                case 0:
                    running = false;
                    System.out.println("Thoát hệ thống.");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }

        scanner.close();
    }
}
