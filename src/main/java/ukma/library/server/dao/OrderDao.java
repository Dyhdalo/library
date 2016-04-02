package ukma.library.server.dao;

import ukma.library.server.entity.Order;

import java.util.List;

public interface OrderDao {

    public List<Order> getAllOrders();

    public List<Order> getOpenOrder();

    public List<Order> getOrderByUser(int id);

    public List<Order> getOrderByBook(int id);

    public Order getOrderById(int id);

    public boolean addOrder(Order order);

}
