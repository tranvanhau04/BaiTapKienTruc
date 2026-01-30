package book;

public class BookFactory {

    public static Book createBook(String type, String title, String author, String category) {
        switch (type) {
            case "paper":
                return new PaperBook(title, author, category);
            case "ebook":
                return new EBook(title, author, category);
            case "audio":
                return new AudioBook(title, author, category);
            default:
                throw new IllegalArgumentException("Unknown book type");
        }
    }
}
