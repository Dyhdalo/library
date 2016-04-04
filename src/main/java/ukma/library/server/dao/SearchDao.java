package ukma.library.server.dao;

import ukma.library.server.entity.Copy;
import ukma.library.server.entity.Queue;
import ukma.library.server.entity.User;

import java.util.List;

public interface SearchDao {

    public boolean addQueue(Queue queue);

    public boolean deleteOueue(int id_book, int id_user);

    public Copy getFreeCopy(int id);

    public List<Queue> getActiveQueue();

    public List<User> getQueueForBook(int book);

    public void addCopy(Copy c);
}