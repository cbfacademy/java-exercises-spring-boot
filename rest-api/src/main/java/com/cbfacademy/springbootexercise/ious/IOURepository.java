package com.cbfacademy.springbootexercise.ious;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

/**
 * The IOURepository interface defines the operations for managing IOUs in the system.
 * It provides methods for retrieving, saving, updating, and deleting IOU records.
 */
public interface IOURepository extends ListCrudRepository<IOU, UUID> {

    /**
     * Searches for IOUs where the borrower's name matches the provided string.
     *
     * @param borrower the name of the borrower
     * @return a list of IOUs that match the borrower's name
     */
    List<IOU> findByBorrowerIgnoreCase(String borrower);

    /**
     * Searches for IOUs where the lender's name matches the provided string.
     *
     * @param lender the name of the lender
     * @return a list of IOUs that match the lender's name
     */
    List<IOU> findByLenderIgnoreCase(String lender);

    /**
     * Searches for IOUs where the amount is more than the average amount of all IOUs.
     *
     * @return a list of IOUs that have a high value
     */
    // @Query("SELECT i FROM IOU i WHERE i.amount > (SELECT AVG(i.amount) FROM IOU i) ORDER BY i.createdAt DESC")
    @Query(value = "SELECT * FROM ious WHERE amount > (SELECT AVG(amount) FROM ious) ORDER BY created_at DESC", nativeQuery = true)
    List<IOU> findHighValueIOUs();

    /**
     * Searches for IOUs where the amount is less than the average amount of all IOUs.
     *
     * @return a list of IOUs that have a low value
     */
    // @Query(value = "SELECT * FROM ious WHERE amount <= (SELECT AVG(amount) FROM ious) ORDER BY created_at DESC", nativeQuery = true)
    @Query("SELECT i FROM IOU i WHERE i.amount <= (SELECT AVG(i.amount) FROM IOU i) ORDER BY i.createdAt DESC")
    List<IOU> findLowValueIOUs();
}
