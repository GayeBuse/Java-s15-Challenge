package com.workintech.library.interfaces;

public interface GuestBookManagment {
    boolean login(String inputEmail);
    void borrow (String book);// ödünç
    void returnBook(String returnBook,boolean isDamaged);//Okuyucunun ödünç aldığı bir kitabı geri vermesini sağlar. isDamaged parametresi, kitabın hasarlı olup olmadığını belirtir.

    boolean searchBook(String bookName);

}
