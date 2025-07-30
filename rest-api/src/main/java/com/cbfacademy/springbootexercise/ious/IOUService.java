package com.cbfacademy.springbootexercise.ious;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class IOUService {

    private final IOURepository repository;

    public IOUService(IOURepository repository) {
        this.repository = repository;
    }

    public List<IOU> getAllIOUs() {
        return repository.findAll();
    }

   public IOU getIOU(UUID id) throws NoSuchElementException {
    return repository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("IOU with id " + id + " not found"));
    }

    public IOU createIOU(IOU iou) throws IllegalArgumentException, OptimisticLockingFailureException {
        if (iou == null) {
            throw new IllegalArgumentException("IOU cannot be null");
        }
        return repository.save(iou);
    }

public IOU updateIOU(UUID id, IOU updatedIOU) throws NoSuchElementException {
        IOU existingIOU = getIOU(id); // throws exception if not found

        existingIOU.setBorrower(updatedIOU.getBorrower());
        existingIOU.setLender(updatedIOU.getLender());
        existingIOU.setAmount(updatedIOU.getAmount());
        existingIOU.setDateTime(updatedIOU.getDateTime());

        return repository.save(existingIOU);
    }

    public void deleteIOU(UUID id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("IOU not found with id: " + id);
        }
        repository.deleteById(id);
    }
}

