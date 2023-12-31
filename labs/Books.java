import java.util.HashMap;

class Book {
    private String isbn;
    private String title;
    private String author;
    private int copies;

    public Book(String isbn, String title, String author, int copies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.copies = copies;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCopies() {
        return copies;
    }
}

class Library {
    HashMap<String, Book> bookHashMap;

    public Library() {
        bookHashMap = new HashMap<>();
    }

    public void insertBook(Book book) {
        String isbn = book.getIsbn();
        if (!bookHashMap.containsKey(isbn)) {
            bookHashMap.put(isbn, book);
        } else {
            System.out.println("Книга с таким ISBN уже существует в библиотеке.");
        }
    }

    public Book findBook(String isbn) {
        if (bookHashMap.containsKey(isbn)) {
            return bookHashMap.get(isbn);
        } else {
            System.out.println("Книга с данным ISBN не найдена в библиотеке.");
            return null;
        }
    }

    public void removeBook(String isbn) {
        if (bookHashMap.containsKey(isbn)) {
            bookHashMap.remove(isbn);
            System.out.println("Книга с ISBN " + isbn + " была удалена из библиотеки.");
        } else {
            System.out.println("Книга с данным ISBN не найдена в библиотеке и не может быть удалена.");
        }
    }
}

public class Books {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("978-0-306-40615-7", "Программирование на Python", "Марк Лутц", 5);
        Book book2 = new Book("978-0321751041", "Python Crash Course", "Eric Matthes", 3);
        Book book3 = new Book("978-1491919538", "Python for Data Analysis", "Wes McKinney", 2);

        library.insertBook(book1);
        library.insertBook(book2);
        library.insertBook(book3);

        System.out.println("Поиск книги по ISBN:");
        Book foundBook = library.findBook("978-0-306-40615-7");
        if (foundBook != null) {
            System.out.println("Название: " + foundBook.getTitle());
            System.out.println("Автор: " + foundBook.getAuthor());
            System.out.println("Количество копий: " + foundBook.getCopies());
        }

        System.out.println("\nУдаление книги по ISBN:");
        library.removeBook("978-0321751041");

        System.out.println("\nСостояние библиотеки после удаления:");
        for (Book book : library.bookHashMap.values()) {
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("Название: " + book.getTitle());
            System.out.println("Автор: " + book.getAuthor());
            System.out.println("Количество копий: " + book.getCopies());
        }
    }
}
