package com.workintech.library.model;

import com.workintech.library.enums.Category;
import com.workintech.library.enums.Genre;
import com.workintech.library.enums.Roles;
import com.workintech.library.enums.Status;
import com.workintech.library.interfaces.LibraryBookManagement;
import com.workintech.library.interfaces.SearchBookManagment;

import java.util.Iterator;
import java.util.TreeMap;

public class Librarian extends Person implements LibraryBookManagement, SearchBookManagment {
   Library library;

    public Librarian(String name, String surname, double idNumber, Roles role, Library library) {
        super(name, surname, idNumber, role);
        this.library = library;
    }

    @Override
    public void addBook(Book book) {
        if (book.getStock() == 0 && book.getStatus() == Status.DAMAGED) {
            book.setStatus(Status.NODAMAGED);
        }

        if (library.getBookList().containsKey(book.getID())) {
            // ID'ye sahip bir kitap zaten var, güncelleme yap
            updateBook(book, book.getAuthor(), book.getName(), book.getPrice(),
                    book.getStock(), book.getGenre(), book.getStatus(), book.getCategory());
        } else {
            book.setStock(book.getStock() + 1);
            TreeMap<Long, Book> sortedBooks = new TreeMap<>(library.getBookList());
            sortedBooks.put(book.getID(), book);
            library.setBookList(sortedBooks); //TreeMap sınıfı, Java'nın Map arabirimini uygular ve anahtar-değer çiftlerini depolamak için kullanılır. Anahtarlar sıralı bir şekilde saklanır, bu yüzden TreeMap kullanarak kitapları ID'lerine göre sıralı bir şekilde tutabiliriz.

            System.out.println("Kitap başarıyla eklenmiştir:" + book.getName());
        }
    }
    @Override
    public void deleteBook(Long ID) {//doğrudan koleksiyonun elemanlarına sıralı erişim sağlaR.
        Iterator<Book> iterator = library.getBookList().values().iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();//. Bu yöntem, döngüyü iteratorün son elemanına ulaşana kadar devam ettirmek için kullanılır.
            if (book.getID() == ID) {
                iterator.remove();
                System.out.println("Bu kitap sistemimizden kaldırılmıştır: " + book.getID() + "-" + book.getName());
                return;
            }
        }
        System.out.println("Sistemimizde bu kitap bulunmamaktadır.");
    }
//    Map<Long, Book> bookList = library.getBookList();
//    for (Book book : bookList.values()) {
//        if (book.getID().equals(ID)) {
//            bookList.remove(book.getID());
//            System.out.println("Bu kitap sistemimizden kaldırılmıştır: " + book.getID() + "-" + book.getName());
//            return;
//        }
//    }
//    System.out.println("Sistemimizde bu kitap bulunmamaktadır.");
//}
//public void deleteBook(Long ID) {
//    TreeMap<Long, Book> bookList = library.getBookList();
//    Book book = bookList.get(ID);
//    if (book != null) {
//        bookList.remove(ID);
//        System.out.println("Bu kitap sistemimizden kaldırılmıştır: " + book.getID() + "-" + book.getName());
//    } else {
//        System.out.println("Sistemimizde bu kitap bulunmamaktadır.");
//    }
//}


    @Override
    public void updateBook(Book book, String author, String name, double price, int stock, Genre genre, Status status, Category category) {
        if (searchBook(book.getName())) {
            book.setName(name);
            book.setGenre(genre);
            book.setAuthor(author);
            book.setPrice(price);
            book.setCategory(category);
            book.setStock(stock);
            book.setStatus(status);
        }
    }
    @Override
    public boolean searchBook(String bookName) {
        for (Book book:library.getBookList().values()){
            if (book.getName().equalsIgnoreCase(bookName)){
                System.out.println("Aradığınız kitap sistemimizde bulunmuştur: " + book.getID() + "- " + book.getName() + "  (" + book.getAuthor() + ")" + "  Stock:" + book.getStock());
                return true;
            }
        }
        System.out.println("Aradığınız kitap sistemimizde bulunmamaktadır.");
        return false;
    }
    @Override
    public boolean searchBook(Long ID) {
        for (Book book : library.getBookList().values()) {
            if (book.getID() == ID) {
                System.out.println("Aradığınız kitap sistemimizde bulunmuştur: " + book.getID() + "- " + book.getName() + "  (" + book.getAuthor() + ")" + "  Stock:" + book.getStock());
                return true;
            }
        }
        System.out.println("Aradığınız kitap sistemimizde bulunmamaktadır.");
        return false;
    }

    @Override
    public boolean searchBookByAuthor(String authorName) {
        for (Book book : library.getBookList().values()) {
            if (book.getAuthor() == authorName) {
                System.out.println("Aradığınız kitap sistemimizde bulunmuştur: " + book.getID() + "- " + book.getName() + "  (" + book.getAuthor() + ")" + "  Stock:" + book.getStock());
                return true;
            }
        }
        System.out.println("Aradığınız kitap sistemimizde bulunmamaktadır.");
        return false;
    }



    @Override
    public String toString() {
        return "Librarian{" +
                "library=" + library +
                '}';
    }
}
