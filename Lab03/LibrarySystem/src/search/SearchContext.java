package search;

import book.Book;
import java.util.List;

public class SearchContext {
    private SearchStrategy strategy;

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public void execute(List<Book> books, String keyword) {
        strategy.search(books, keyword);
    }
}
