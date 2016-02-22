package ukma.library.server.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Queue implements Serializable {

    private int id;
    private int bookId;
    private int userId;
    private Date date;

    public Queue() {
    }

    public Queue(int id, int bookId, int userId) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Queue queue = (Queue) o;

        if (id != queue.id) return false;
        if (bookId != queue.bookId) return false;
        if (userId != queue.userId) return false;
        return !(date != null ? !date.equals(queue.date) : queue.date != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + bookId;
        result = 31 * result + userId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return "Queue{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", date=" + dateFormat.format(date) +
                '}';
    }
}
