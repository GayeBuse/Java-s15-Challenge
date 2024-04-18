package com.workintech.library.interfaces;

import com.workintech.library.model.Book;
import com.workintech.library.enums.Category;
import com.workintech.library.enums.Genre;
import com.workintech.library.enums.Status;

public interface LibraryBookManagement {
    void addBook(Book book);//Bu metod, kütüphaneye yeni bir kitap eklemek için gerekli parametre olan Book nesnesini alır.
    //metodunda ise yeni bir kitap oluşturulduğu için bu tür bir kimlik belirleme ihtiyacı yoktur.
    void deleteBook(Long ID);//: Belirli bir kitabı kütüphaneden siler. Bu metod, silinecek kitabın kimliğini (ID) alır.
    //updateBook metodunun daha fazla parametre alması, güncellenmek istenen kitabın belirlenmesi ve güncellenecek yeni bilgilerin sağlanması için gereklidir.
    void updateBook(Book book, String author, String name, double price, int stock, Genre genre, Status status, Category category);

}
