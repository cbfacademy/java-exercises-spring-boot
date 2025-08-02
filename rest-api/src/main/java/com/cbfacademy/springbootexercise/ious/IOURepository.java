package com.cbfacademy.springbootexercise.ious;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;
import java.util.List;

public interface IOURepository extends ListCrudRepository<IOU, UUID> {
    List<IOU> findByBorrower(String borrower);
    // No additional methods needed for now
    @Query(value = "SELECT * FROM ious WHERE amount > (SELECT AVG(amount) FROM ious)", nativeQuery = true)
    List<IOU>findHighValueIOUs(); 

    @Query("Select i from IOU i where i.amount <= (select avg(i2.amount) from IOU i2)")
    List<IOU> findLowValueIOUs(); // Example of a method to find low value IOUs
}
