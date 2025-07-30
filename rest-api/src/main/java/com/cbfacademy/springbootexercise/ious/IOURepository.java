package com.cbfacademy.springbootexercise.ious;

import org.springframework.data.repository.ListCrudRepository;
import java.util.UUID;

public interface IOURepository extends ListCrudRepository<IOU, UUID> {
    // No additional methods needed for now
}
