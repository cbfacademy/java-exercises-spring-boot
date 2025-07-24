package com.cbfacademy.springbootexercise.ious;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Controller class to implement IOU API endpoints.
 */
@RestController
@RequestMapping("/api/ious")
public class IOUController {

    private final IOUService iouService;

    public IOUController(IOUService iouService) {
        this.iouService = iouService;
    }

    /**
     * Retrieve a list of (optionally filtered) IOUs.
     *
     * @param borrower The borrower's name.
     * @param lender   The lender's name.
     * @param value    The value of the IOU.
     * @return A list of all IOUs and HttpStatus OK if successful.
     */
    @GetMapping
    public List<IOU> getIOUs(@RequestParam(required = false) String borrower,
            @RequestParam(required = false) String lender) {
        try {
            if (StringUtils.hasText(borrower)) {
                System.out.println("Borrower = " + borrower);
                return iouService.getIOUsByBorrower(borrower);
            } else if (StringUtils.hasText(lender)) {
                System.out.println("Lender = " + lender);
                return iouService.getIOUsByLender(lender);
            } else {
                return iouService.getAllIOUs();
            }
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }

    /**
     * Retrieve a list of high value IOUs.
     *
     * @return A list of all IOUs and HttpStatus OK if successful.
     */
    @GetMapping("/high")
    public List<IOU> getHighValueIOUs() {
        try {
            return iouService.getHighValueIOUs();
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }

    /**
     * Retrieve a list of low value IOUs.
     *
     * @return A list of all IOUs and HttpStatus OK if successful.
     */
    @GetMapping("/low")
    public List<IOU> getLowValueIOUs() {
        try {
            return iouService.getLowValueIOUs();
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }

    /**
     * Retrieve a specific IOU by its ID.
     *
     * @param id The ID of the IOU to retrieve.
     * @return The requested IOU and HttpStatus OK if found, or HttpStatus NOT_FOUND
     *         if the ID is not found.
     */
    @GetMapping("/{id}")
    public IOU getIOU(@PathVariable UUID id) {
        try {
            IOU iou = iouService.getIOU(id);

            return iou;
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "IOU Not Found", exception);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }

    /**
     * Create a new IOU.
     *
     * @param iou The IOU object to create.
     * @return The created IOU and HttpStatus CREATED if successful.
     */
    @PostMapping
    public ResponseEntity<IOU> createIOU(@RequestBody IOU iou) {
        try {
            IOU createdIOU = iouService.createIOU(iou);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdIOU);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }

    /**
     * Update an existing IOU by ID.
     *
     * @param id         The ID of the IOU to update.
     * @param updatedIOU The updated IOU object.
     * @return The updated IOU and HttpStatus OK if successful, or HttpStatus
     *         NOT_FOUND if the ID is not found.
     */
    @PutMapping("/{id}")
    public IOU updateIOU(@PathVariable UUID id, @RequestBody IOU updatedIOU) {
        try {
            IOU iou = iouService.updateIOU(id, updatedIOU);

            return iou;
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "IOU Not Found", exception);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }

    /**
     * Delete an IOU by ID.
     *
     * @param id The ID of the IOU to delete.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIOU(@PathVariable UUID id) {
        try {
            iouService.deleteIOU(id);

            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "IOU Not Found", exception);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }
}
