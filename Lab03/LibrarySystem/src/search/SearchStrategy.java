package search;

import book.Book;
import java.util.List;

public interface SearchStrategy {
    void search(List<Book> books, String keyword);
}

