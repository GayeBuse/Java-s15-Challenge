package com.workintech.library.interfaces;

public interface SearchBookManagment {
    boolean searchBook(String bookName);
    boolean searchBook(Long ID);
    boolean searchBookByAuthor(String authorName);

}
