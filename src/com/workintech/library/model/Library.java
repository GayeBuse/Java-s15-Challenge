package com.workintech.library.model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Library {
    //Her okuyucunun veya kitabın bir benzersiz kimliği vardır. Map kullanarak, her okuyucunun veya kitabın kimliğiyle ilişkilendirilmiş olan nesneleri saklayabiliriz. Bu, her bir okuyucu veya kitabın eşsiz bir şekilde tanımlanmasını sağlar.Buda bize hız kazandırır
private Map<Long,Book> bookList;
private Map<Long,Member>memberList;
    //Long türü, çok geniş bir aralıkta tam sayıları temsil edebilir, bu nedenle benzersiz kimliklerin saklanması için uygundur.
    public Library() {
        // HashMap, verilerin hızlı bir şekilde saklanması ve erişilmesi için kullanılabilir.search işlemi hızlııdr.maplerin keyleri hascode üzerinde tutulur.
        this.bookList = new TreeMap<>();//SIRALI GELİR
        this.memberList = new HashMap<>();//SIRALI GELMEZ
    }

    public Map<Long, Book> getBookList() {
        return bookList;
    }

    public void setBookList(Map<Long, Book> bookList) {
        this.bookList = bookList;
    }

    public Map<Long, Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(Map<Long, Member> memberList) {
        this.memberList = memberList;
    }

    @Override
    public String toString() {
        return "Library{" +
                "bookList=" + bookList +
                ", memberList=" + memberList +
                '}';
    }
}
