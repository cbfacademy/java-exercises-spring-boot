package com.cbfacademy.springbootexercise.ious;

import org.springframework.data.repository.ListCrudRepository;
import java.util.UUID;
import java.util.List;

public interface IOURepository extends ListCrudRepository<IOU, UUID> {
    List<IOU> findByBorrower(String borrower);
    // No additional methods needed for now
}
