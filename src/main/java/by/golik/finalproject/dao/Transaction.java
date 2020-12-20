package by.golik.finalproject.dao;

/**
 * @author Nikita Golik
 */
public interface Transaction {
    void createDao();
    void commit();
    void rollback();
}
