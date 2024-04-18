package com.workintech.library.interfaces;

public interface PersonBookManagment {
    void borrow (String book);// ödünç

    void returnBook(String returnBook,boolean isDamaged);//Okuyucunun ödünç aldığı bir kitabı geri vermesini sağlar. isDamaged parametresi, kitabın hasarlı olup olmadığını belirtir.
    boolean login(String inputName,String inputSurname,String inputEmail,double inputIdNumber, String inputPassword);//Okuyucunun sisteme giriş yapmasını sağlar. Kullanıcı adı, soyadı, e-posta, TC Kimlik Numarası ve şifre gibi bilgilerle giriş yapılmasını sağlar.



}
