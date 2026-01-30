package search;

import book.Book;
import java.util.List;

public class SearchByCategory implements SearchStrategy {
    public void search(List<Book> books, String keyword) {
        books.stream()
                .filter(b -> b.getCategory().contains(keyword))
                .forEach(b -> System.out.println(b.getTitle()));
    }
}
