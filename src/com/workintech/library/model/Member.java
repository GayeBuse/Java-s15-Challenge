package com.workintech.library.model;

import com.workintech.library.enums.Status;
import com.workintech.library.interfaces.PersonBookManagment;
import com.workintech.library.interfaces.SearchBookManagment;

import java.util.LinkedList;
import java.util.List;

public class Member extends Person implements PersonBookManagment, SearchBookManagment { //Bu satırın açıklamasıyla birlikte, Member sınıfı, Person sınıfından kalıtılan özelliklere ve PersonBookManagement arayüzünün metodlarına erişim sağlar. Bu sayede, Member sınıfı, kütüphane üyelerinin hem kişisel bilgilerini hem de kitap yönetimi ile ilgili işlemlerini yönetebilir.
    private String id;
    private String password;
    private double money;
    private List<Book> borrowedBooks; //üyenin ödünç aldığı kitapları içeren bir listeye sahip olacaktır.
    private Library library;//Member sınıfı kütüphanedeki kitapları ödünç alabilir veya iade edebilir. Bu sayede Member sınıfı, kütüphanedeki kitaplarla etkileşime geçebilir ve kütüphane işlemlerini gerçekleştirebilir.
    private List<Bill> bills;
    public Member(String name, String surname, String email, String address, double idNumber, String phoneNumber, String id, String password, double money, Library library) {
        super(name, surname, email, address, idNumber, phoneNumber);
        this.id = id;
        this.password = password;
        this.money = money;
        this.borrowedBooks = new LinkedList<>();
        this.library = library;
        this.bills = new LinkedList<>();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
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

    public Library getLibrary() {//sadece ilgili özelliğin değerini dışarıya vermek için kullanılır ve özelliğin değerini değiştirme
        return library;
    }


    @Override
    public void borrow(String book) {
        if (borrowedBooks.size() >= 5) {
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
                //targetbook  kitap listesinden her bir kitabı temsil eder.
                Bill bill = new Bill(targetBook.getID(), targetBook.getName(),this.getName(),targetBook.getPrice());
                // Faturayı yazdırma işlemi
                bill.printBill();

                // Kitabı ödünç al
                borrowedBooks.add(targetBook);
                targetBook.setStock(targetBook.getStock() - 1);

                System.out.println(targetBook.getName() + " kitabınız alınmıştır. Faturanız oluşturulmuştur."+"Kalan stok :"+targetBook.getStock());
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
                    double moneyBack = borrowedBook.getPrice() / 1.75;//kütüphanede kazansın
                    this.setMoney(this.getMoney() + moneyBack);
                    System.out.println(returnBook + " kitabınız başarıyla iade edildi. Ücret iadesi yapıldı: " + moneyBack + " TL");

                }
                return;
            }
        }
        System.out.println("Üzgünüz, " + returnBook + " adında bir kitap ödünç alınmamış durumda veya zaten iade edilmiş.");
    }

    @Override
    public boolean login(String inputName, String inputSurname, String inputEmail, double inputIdNumber, String inputPassword) {
        if (password.equals(inputPassword) && inputIdNumber == getIdNumber() && inputEmail == getEmail()) {
            System.out.println("Kütüphanemize Hoşgeldin"+" " + inputName + " " + inputSurname);
            return true;
        }
        System.out.println("Bu bilgilerde kayıt bulunamadı .Lütfen tekrar deneyiniz.");
        return false;
    }



    @Override
    public boolean searchBook(String bookName) {
        if (library == null) {
            System.out.println("Hata: Kütüphane bulunamadı.");
            return false;
        }

        for (Book book : library.getBookList().values()) {
            if (book.getName().equalsIgnoreCase(bookName)) {
                System.out.println("Aradığınız kitap sistemimizde bulunmuştur: "  + book.getID() + "- " + book.getName() + "  (" + book.getAuthor() + ")" + "  Stock:" + book.getStock());
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
            if (book.getAuthor().equalsIgnoreCase(authorName)) {
                System.out.println("Aradığınız yazarın kitaplarından biri sistemimizde bulunmuştur: " + book.getID() + "- " + book.getName() + "  (" + book.getAuthor() + ")" + "  Stock:" + book.getStock());
                return true;
            }
        }
        System.out.println("Aradığınız yazarın kitapları sistemimizde bulunmamaktadır.");
        return false;
    }
    }


