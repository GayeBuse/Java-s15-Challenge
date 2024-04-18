import com.workintech.library.enums.Category;
import com.workintech.library.enums.Genre;
import com.workintech.library.enums.Roles;
import com.workintech.library.enums.Status;
import com.workintech.library.model.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Book book1=new Book(1L,"George Orwell","1984",29.99,50, Genre.DYSTOPIA, Status.NODAMAGED, Category.NOVEL);
        Book book2=new Book(2L,"J.K. Rowling","Harry Potter and the Philosopher's Stone", 24.99,100, Genre.FANTASY, Status.NODAMAGED, Category.CHILDRENS_BOOKS);
        Book book3=new Book(3L,"Harper Lee","To Kill a Mockingbird", 19.99,30, Genre.CLASSICS, Status.NODAMAGED, Category.NOVEL);
        Book book4=new Book(4L,"J.R.R. Tolkien","The Lord of the Rings", 39.99,20, Genre.FANTASY, Status.NODAMAGED, Category.FICTION);
        Book book5=new Book(5L,"Jane Austen","Pride and Prejudice", 17.99,40, Genre.ROMANCE, Status.NODAMAGED, Category.CLASSICAL);
        Book book6=new Book(6L,"Sabahattin Ali","Kürk Mantolu Madonna",  24.99,30, Genre.ROMANCE, Status.NODAMAGED, Category.NOVEL);
        Book book7=new Book(7L,"Orhan Pamuk","Beyaz Kale",  34.99,20, Genre.HISTORY, Status.NODAMAGED, Category.NOVEL);
        Book book8=new Book(8L,"Orhan Kemal","Bereketli Topraklar Üzerinde",   22.99,25, Genre.SOCIAL, Status.NODAMAGED, Category.NOVEL);
// Kütüphane oluştur
        Library library=new Library();
        library.getBookList().put(book1.getID(), book1);//getID() metodunu kullanarak kitabın kimliğini alıp, bu kimlik ile birlikte kitabı kütüphane koleksiyonuna ekledik.
        library.getBookList().put(book2.getID(), book2);
        library.getBookList().put(book3.getID(), book3);
        library.getBookList().put(book4.getID(), book4);
        library.getBookList().put(book5.getID(), book5);
        library.getBookList().put(book6.getID(), book6);
        library.getBookList().put(book7.getID(), book7);
        library.getBookList().put(book8.getID(), book8);

        System.out.println("Kütüphanede bulunan kitaplar: " + library.getBookList());

        System.out.println("*********************** ");

        // Kütüphaneci oluştur
        Librarian librarian=new Librarian("Gaye","Özkan",123456789, Roles.LIBRARIAN,library);
        System.out.println("Kütüphaneci: " + librarian.getName()+" "+ librarian.getSurname());
        System.out.println("*********************** ");
        librarian.addBook(book1);
        librarian.addBook(book2);
        librarian.addBook(book3);
        librarian.addBook(book4);
        librarian.addBook(book5);
        librarian.addBook(book6);
        librarian.addBook(book7);
        librarian.addBook(book8);


        System.out.println("Kütüphanede bulunan kitaplar: " + library.getBookList());

        System.out.println("*********************** ");
        librarian.searchBook("To Kill a Mockingbird");
        librarian.searchBook("İçimizdeki Şeytan ");//bulunamadı hatası
        librarian.searchBookByAuthor("Orhan Pamuk");
        System.out.println("*********************** ");
        librarian.searchBook(3L);


        System.out.println("*********************** ");

        librarian.deleteBook(5L);

        System.out.println("**********Güncel kitap listesi************* ");
        for (Book book : library.getBookList().values()) {
            System.out.println(book);
        }
        System.out.println("*********************** ");


        librarian.updateBook(book4,"Ali","gece kuşları ",12.99,200,Genre.FANTASY,Status.DAMAGED,Category.NOVEL);
        System.out.println("***********Güncellenmiş kitap listesi************ ");
        for (Book book : library.getBookList().values()) {
            System.out.println(book);


        }
        System.out.println("*********************** ");
        //üye oluşturma
        Member member1=new Member("Sibel","Özkan","sibelozkan@gmail.com","test mah. sınav apartmanı no56 Bursa",123456788,"5559897855","1","852456951",250,library);
        //Üye girşi
        member1.login("Sibel","Özkan","sibelozkan@gmail.com",123456788,"852456951");
        //hatalı üye girşi
        member1.login("Sibel","Özkan","sibelozkan@gmail.com",123456778,"852456951");
    //isme göre üye kitap araması
        System.out.println("*********************** ");
        member1.searchBook(8L);
        member1.searchBook("Kürk Mantolu Madonna");
        ;

        System.out.println("*********************** ");
        member1.borrow("Bereketli Topraklar Üzerinde");
        System.out.println("*********************** ");
        member1.borrow("Beyaz Kale");
        System.out.println("*********************** ");
        member1.borrow("Harry Potter and the Philosopher's Stone");
        System.out.println("*********************** ");
        member1.borrow("Kürk Mantolu Madonna");
        System.out.println("*********************** ");
        member1.borrow("1984");
        System.out.println("*********************** ");
        member1.borrow("To Kill a Mockingbird");//maksimum kitap limiti.

        System.out.println("************Geri İade*********** ");
        member1.returnBook("1984",true);
        member1.returnBook("Kürk Mantolu Madonna",false);
        System.out.println("*********************** ");
//Misafir Girişi
        Guest guest1 = new Guest("Ali", "Veli", "ali.veli@example.com", "5551234567",library);
guest1.login("ali.veli@example.com");
        System.out.println("*********************** ");
        // Misafir kitap araması yapar
        guest1.searchBook("Harry Potter and the Philosopher's Stone");
        System.out.println("*********************** ");
        // Misafir bir kitap ödünç alır
        guest1.borrow("Harry Potter and the Philosopher's Stone");
        System.out.println("*********************** ");
        // Misafir bir kitap iade eder
        guest1.returnBook("Harry Potter and the Philosopher's Stone", false);


        System.out.println("************************** ");

    }

}
