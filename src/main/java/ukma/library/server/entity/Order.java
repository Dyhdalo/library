package ukma.library.server.entity;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{

    private int id;
    private int userId;
    private int copyId;

    private Date issueDate;
    private Date returnDate;
    private Date complitingDate;

    public Order() {
        this.issueDate = new Date(System.currentTimeMillis());
        this.returnDate = new Date(issueDate.getTime() + (7 * 24 * 3600 * 1000));
    }

    public Order(int id, int userId, int copyId) {
        this.id = id;
        this.userId = userId;
        this.copyId = copyId;
        this.issueDate = new Date(System.currentTimeMillis());
        this.returnDate = new Date(issueDate.getTime() + (7 * 24 * 3600 * 1000));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCopyId() {
        return copyId;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getComplitingDate() {
        return complitingDate;
    }

    public void setComplitingDate(Date complitingDate) {
        this.complitingDate = complitingDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (userId != order.userId) return false;
        if (copyId != order.copyId) return false;
        if (issueDate != null ? !issueDate.equals(order.issueDate) : order.issueDate != null) return false;
        if (returnDate != null ? !returnDate.equals(order.returnDate) : order.returnDate != null) return false;
        return !(complitingDate != null ? !complitingDate.equals(order.complitingDate) : order.complitingDate != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + copyId;
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + (complitingDate != null ? complitingDate.hashCode() : 0);
        return result;
    }


}
