package ukma.library.server.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import ukma.library.server.dao.OrderDao;
import ukma.library.server.entity.Order;
import ukma.library.server.service.LibraryService;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcOrderDao implements OrderDao {

    private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement statement;

    private static final String Order_TABLE_NAME = " acsm_b775c39c99325aa.Order ";

    public JdbcOrderDao() {
        if (connection == null)
            try {
                connection = DriverManager.getConnection(LibraryService.MYSQL_URL);
            } catch (SQLException e) {
                System.out.println("Connection failed: " + e);
            }
    }

    @Override
    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM" + Order_TABLE_NAME;
        return selectOrderList(sql);
    }

    @Override
    public List<Order> getOpenOrder() {
        String sql = "SELECT * FROM" + Order_TABLE_NAME + "WHERE date_compliting = null";
        return selectOrderList(sql);
    }

    @Override
    public List<Order> getOrderByUser(int id) {
        String sql = "SELECT * FROM" + Order_TABLE_NAME + "WHERE id_user = " + id;
        return selectOrderList(sql);
    }

    @Override
    public List<Order> getOrderByBook(int id) {
        String sql = "SELECT * FROM" + Order_TABLE_NAME + "WHERE id_instance = " + id;
        return selectOrderList(sql);
    }

    @Override
    public Order getOrderById(int id) {
        String sql = "SELECT * FROM" + Order_TABLE_NAME + "WHERE id_iorder = " + id;
        List<Order> orders = selectOrderList(sql);
        return orders == null ? null : orders.get(0);
    }

    private List<Order> selectOrderList(String sql) {
        ArrayList<Order> orders = new ArrayList<Order>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            int row = 0;
            while (resultSet.next()) {
                orders.add(orderMapper.mapRow(resultSet, row++));
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e);
        }
        return orders;
    }

    private static final RowMapper<Order> orderMapper = new RowMapper<Order>() {

        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setId(resultSet.getInt("id_order"));
            order.setUserId(resultSet.getInt("id_user"));
            order.setCopyId(resultSet.getInt("id_instance"));
            order.setIssueDate(resultSet.getTimestamp("date_issue"));
            order.setReturnDate(resultSet.getTimestamp("date_return"));
            order.setComplitingDate(resultSet.getTimestamp("date_compliting"));
            return order;
        }
    };

}
