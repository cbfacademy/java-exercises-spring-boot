package com.cbfacademy.springbootexercise.ious;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Service class to manage IOU objects.
 */
@Service
public class IOUService {

    private final IOURepository repository;

    /**
     * Constructor for IOUService.
     *
     * @param repository the IOURepository to be used for data persistence
     */
    public IOUService(IOURepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all IOUs from the repository.
     *
     * @return a list of all IOU entities
     */
    public List<IOU> getAllIOUs() {
        return repository.findAll();
    }

    /**
     * Retrieves an IOU by its unique identifier.
     *
     * @param id the unique identifier of the IOU
     * @return the IOU entity if found
     * @throws NoSuchElementException if no IOU is found with the specified id
     */
    public IOU getIOU(UUID id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow();
    }

    /**
     * Creates a new IOU entity and saves it to the repository.
     *
     * @param iou the IOU entity to be created
     * @return the saved IOU entity
     * @throws IllegalArgumentException if the provided IOU is invalid
     * @throws OptimisticLockingFailureException if the entity could not be saved due to a version conflict
     */
    public IOU createIOU(IOU iou) throws IllegalArgumentException, OptimisticLockingFailureException {
        return repository.save(iou);
    }

    /**
     * Updates an existing IOU entity with new values.
     *
     * @param id the unique identifier of the IOU to be updated
     * @param updatedIOU the IOU entity containing updated values
     * @return the updated IOU entity
     * @throws NoSuchElementException if no IOU is found with the specified id
     */
    public IOU updateIOU(UUID id, IOU updatedIOU) throws NoSuchElementException {
        IOU iou = repository.findById(id).orElseThrow();

        iou.setBorrower(updatedIOU.getBorrower());
        iou.setLender(updatedIOU.getLender());
        iou.setAmount(updatedIOU.getAmount());

        return repository.save(iou);
    }

    /**
     * Deletes an IOU by its unique identifier.
     *
     * @param id the unique identifier of the IOU to be deleted
     * @throws NoSuchElementException if no IOU is found with the specified id
     */
    public void deleteIOU(UUID id) throws NoSuchElementException {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Finds all IOUs associated with a specific borrower.
     *
     * @param borrower the name of the borrower whose IOUs are to be retrieved
     * @return a list of IOU entities associated with the given borrower
     */
    public List<IOU> getIOUsByBorrower(String borrower) {
        return repository.findByBorrowerIgnoreCase(borrower);
    }

    /**
     * Finds all IOUs associated with a specific lender.
     *
     * @param lender the name of the lender whose IOUs are to be retrieved
     * @return a list of IOU entities associated with the given lender
     */
    public List<IOU> getIOUsByLender(String lender) {
        return repository.findByLenderIgnoreCase(lender);
    }

    /**
     * Retrieves IOUs that are considered high value.
     *
     * @return a list of IOU entities classified as high value
     */
    public List<IOU> getHighValueIOUs() {
        return repository.findHighValueIOUs();
    }

    /**
     * Retrieves IOUs that are considered low value.
     *
     * @return a list of IOU entities classified as low value
     */
    public List<IOU> getLowValueIOUs() {
        return repository.findLowValueIOUs();
    }
}
