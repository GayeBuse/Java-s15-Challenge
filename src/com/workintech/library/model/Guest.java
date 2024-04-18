package com.workintech.library.model;

import com.workintech.library.enums.Status;
import com.workintech.library.interfaces.GuestBookManagment;

import java.util.LinkedList;
import java.util.List;

public class Guest extends Person implements GuestBookManagment {
    private double money;

    private List<Book> borrowedBooks;
    private Library library;
    private List<Bill> bills;

    public Guest(String name, String surname, String email, String phoneNumber,Library library) {
        super(name, surname, email, phoneNumber);
        this.borrowedBooks = new LinkedList<>();
        this.library = library; // Library nesnesini burada başlat
        this.bills = new LinkedList<>();
    }


    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
    public void borrowBook(Book book) {
        if (borrowedBooks.size() >= 2) {
            System.out.println("Özür dileriz, maksimum kitap limitine ulaştınız. Daha fazla kitap alamazsınız.");
            return;
        }
        if (book.getStock() <= 0 || book.getStatus() == Status.DAMAGED || book.getStatus() == Status.LOST) {
            System.out.println("Üzgünüz, " + book.getName() + " şu anda ödünç alınamaz.");
            return;
        }
        borrowedBooks.add(book);
        book.setStock(book.getStock() - 1);
        System.out.println(book.getName() + " kitabınız alınmıştır.");
    }



    @Override
    public boolean login(String inputEmail) {
        if (this.getEmail().equals(inputEmail)) {
            System.out.println("Misafir giriş başarılı. Hoş geldiniz, " + this.getName() + " " + this.getSurname());
            return true;
        } else {
            System.out.println("Misafir giriş başarısız. Geçersiz e-posta adresi.");
            return false;
        }
    }

    @Override
    public void borrow(String book) {

        if (borrowedBooks.size() >= 2) {
            System.out.println("Özür dileriz, maksimum kitap limitine ulaştınız.Daha fazla kitap alamazsınız.");
            return;
        }
        for (Book borrowedBook : borrowedBooks) {//ödünç listesi (borrowedBooks) ile her bir Book içinden  ödünç alınan kitaplar (borrowedBook) aynı ise uyarı
            if (book.equalsIgnoreCase(borrowedBook.getName())) {
                System.out.println("Üzgünüz, " + book + " zaten ödünç alınmış durumda.");
                return;
            }
        }
        for (Book targetBook : library.getBookList().values()) {
            if (targetBook.getName().equalsIgnoreCase(book) && targetBook.getStock() > 0) {
                if (targetBook.getStatus() == Status.DAMAGED) {
                    System.out.println(targetBook.getName() + " kitabı şu anda ödünç alınamaz.");
                    if (targetBook.getStatus() == Status.LOST) {
                        System.out.println(targetBook.getName() + " kitabı kayıp olduğu için ödünç alınamaz.");
                        return;
                    }

                }
// Fatura oluşturma işlemi
                Bill bill = new Bill(targetBook.getID(), targetBook.getName(), this.getName(),targetBook.getPrice());
                // Faturayı yazdırma işlemi
                bill.printBill();

                // Kitabı ödünç al
                borrowedBooks.add(targetBook);
                targetBook.setStock(targetBook.getStock() - 1);

                System.out.println(targetBook.getName() + " kitabınız alınmıştır. Faturanız oluşturulmuştur.");
                return;
            }
        }

        System.out.println(book + " kitabı stoklarda bulunmamaktadır veya ödünç alınamaz durumdadır.");
    }


    @Override
    public void returnBook(String returnBook, boolean isDamaged) {
        for (Book borrowedBook : borrowedBooks) {
            if (returnBook.equalsIgnoreCase(borrowedBook.getName())) {
                borrowedBooks.remove(borrowedBook); // Kitabı iade et

                if (isDamaged) {
                    borrowedBook.setStatus(Status.DAMAGED); // Kitap hasarlıysa durumunu güncelle
                    System.out.println("Üzgünüz, " + returnBook + " kitabı hasarlı iade edildi.  Para iadesi yapılmayacak.");
                } else {
                    if (borrowedBook.getStatus() == Status.LOST) {
                        System.out.println("Üzgünüz, " + returnBook + " kitabı kayıp olduğu için para iadesi yapılmayacak.");
                        return;
                    }
                    borrowedBook.setStock(borrowedBook.getStock() + 1); // Kitap hasarlı değilse stok sayısını arttır
                    double moneyBack = borrowedBook.getPrice() / 2.50;//kütüphanede kazansın
                    this.setMoney(this.getMoney() + moneyBack);
                    System.out.println(returnBook + " kitabınız başarıyla iade edildi. Ücret iadesi yapıldı: " + moneyBack + " TL");

                }
                return;
            }
        }
        System.out.println("Üzgünüz, " + returnBook + " adında bir kitap ödünç alınmamış durumda veya zaten iade edilmiş.");
    }

    @Override
    public boolean searchBook(String bookName) {
        if (library == null) {
            System.out.println("Hata: Kütüphane bulunamadı.");
            return false;
        }

        for (Book book : library.getBookList().values()) {
            if (book.getName().equalsIgnoreCase(bookName)) {
                System.out.println("Aradığınız kitap sistemimizde bulunmuştur: " + book.getID() + "- " + book.getName() + "  (" + book.getAuthor() + ")" + "  Stock:" + book.getStock());
                return true;
            }
        }
        System.out.println("Aradığınız kitap sistemimizde bulunmamaktadır.");
        return false;
    }
}
