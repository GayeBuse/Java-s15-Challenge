package com.workintech.library.model;





public class Bill {

        private long bookId;// Faturada yer alan kitabın kimliği.
        private String bookName;//Faturada yer alan kitabın adı.
        private String customerName;//Faturayı alan müşterinin adı.

        private double totalPrice;//Faturanın toplam fiyatı.

    public Bill(long bookId, String bookName, String customerName, double totalPrice) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }




        public void printBill() {
            System.out.println("Fatura Detayları:");
            System.out.println("Kitap ID: " + bookId);
            System.out.println("Kitap İsmi: " + bookName);
            System.out.println("Alıcı Adı: " + customerName);
            System.out.println("Toplam Fiyat: " + totalPrice + " TL");

        }

        @Override
        public String toString() {
            return "Bill{" +
                    "bookId=" + bookId +
                    ", bookName='" + bookName + '\'' +
                    ", customerName='" + customerName + '\'' +
                    ", totalPrice=" + totalPrice +
                    '}';
        }
    }

//Bu şekilde, kullanıcılar işlem sonrası fatura bilgilerini görüntüleyebilir ve saklayabilirler.