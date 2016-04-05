package ukma.library.server.entity;

import java.io.Serializable;

public class Copy implements Serializable{

    private int id;

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStatus() {
        return status.equals(Status.FREE)? 1:2;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status == 1? Status.FREE:Status.RESERVED;
    }

    public int getId() {
        return id;
    }


    public enum Status{
        RESERVED,
        FREE
    }

    private int isbn;
    private int bookId;
    private Status status = Status.FREE;

    public Copy(){}

    public Copy(int isbn, int bookId){
        this.isbn = isbn;
        this.bookId = bookId;
    }

    public static boolean validateIsbn13( String isbn )
    {
        return true;
//        if ( isbn == null )
//        {
//            return false;
//        }
//
//        //remove any hyphens
//        isbn = isbn.replaceAll( "-", "" );
//
//        //must be a 13 digit ISBN
//        if ( isbn.length() != 13 )
//        {
//            return false;
//        }
//
//        try
//        {
//            int tot = 0;
//            for ( int i = 0; i < 12; i++ )
//            {
//                int digit = Integer.parseInt( isbn.substring( i, i + 1 ) );
//                tot += (i % 2 == 0) ? digit * 1 : digit * 3;
//            }
//
//            //checksum must be 0-9. If calculated as 10 then = 0
//            int checksum = 10 - (tot % 10);
//            if ( checksum == 10 )
//            {
//                checksum = 0;
//            }
//
//            return checksum == Integer.parseInt( isbn.substring( 12 ) );
//        }
//        catch ( NumberFormatException nfe )
//        {
//            //to catch invalid ISBNs that have non-numeric characters in them
//            return false;
//        }
    }
}

