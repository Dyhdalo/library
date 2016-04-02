package ukma.library.server.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import ukma.library.server.dao.SearchDao;
import ukma.library.server.entity.*;
import ukma.library.server.service.LibraryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcSearchDao implements SearchDao {

    private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement statement;

    private static final String QUEUE_TABLE_NAME = " acsm_b775c39c99325aa.queue ";
    private static final String COPY_TABLE_NAME = " acsm_b775c39c99325aa.queue ";

    public JdbcSearchDao() {
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(LibraryService.MYSQL_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addQueue(Queue queue) {
        String sql = "INSERT INTO " + QUEUE_TABLE_NAME + "(id_book, id_user, date) " +
                "VALUES (?, ?, ?)";
        try {
            connection = createConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, queue.getBookId());
            statement.setInt(2, queue.getUserId());
            statement.setDate(3, new Date(queue.getDate().getTime()));
            statement .executeUpdate();
        } catch (SQLException e) {
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }

    @Override
    public boolean deleteOueue(Queue queue) {
        String sql = "DELETE FROM " + QUEUE_TABLE_NAME + " WHERE Queue.id_queue = " + queue.getId();
        try {
            connection = createConnection();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }

    @Override
    public Copy getFreeCopy(int id) {
        return null;
    }

    @Override
    public List<Queue> getActiveQueue() {
        String sql = "SELECT * FROM" + QUEUE_TABLE_NAME;
        return selectQueueList(sql);
    }

    @Override
    public List<Queue> getQueueForBook(Book book) {
        String sql = "SELECT * FROM" + QUEUE_TABLE_NAME + "WHERE Queue.id_book = " + book.getId();
        return selectQueueList(sql);
    }

    private List<Queue> selectQueueList(String sql) {
        ArrayList<Queue> queues = new ArrayList<Queue>();
        try {
            connection = createConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            int row = 0;
            while (resultSet.next()) {
                queues.add(queueMapper.mapRow(resultSet, row++));
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e);
        } finally {
            closeConnection();
        }
        return queues;
    }

    private static final RowMapper<Queue> queueMapper = new RowMapper<Queue>() {

        public Queue mapRow(ResultSet rs, int rowNum) throws SQLException {
            Queue queue = new Queue();
            queue.setId(rs.getInt("id_queue"));
            queue.setBookId(rs.getInt("id_book"));
            queue.setUserId(rs.getInt("id_user"));
            queue.setDate(rs.getTimestamp("date"));
            return queue;
        }
    };
}
