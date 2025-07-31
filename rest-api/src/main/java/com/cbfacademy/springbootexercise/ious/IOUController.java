package com.cbfacademy.springbootexercise.ious;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/ious")

public class IOUController {

   private final IOUService service;

    public IOUController(IOUService service) {
        this.service = service;
    }

    @GetMapping
    public List<IOU> getAllIOUs() {
        return service.getAllIOUs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IOU> getIOU(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(service.getIOU(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ious")
    public List<IOU> getIOUs(@RequestParam(required = false) String borrower) {
        if (borrower != null)  {
            return service.getIOUsByBorrower(borrower);
        } else {
            return service.getAllIOUs();
        }
    }

    @PostMapping
    public ResponseEntity<IOU> createIOU(@RequestBody IOU iou) {
        try {
            IOU created = service.createIOU(iou);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<IOU> updateIOU(@PathVariable UUID id, @RequestBody IOU updatedIOU) {
        try {
            return ResponseEntity.ok(service.updateIOU(id, updatedIOU));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIOU(@PathVariable UUID id) {
        service.deleteIOU(id);
        return ResponseEntity.noContent().build();
    } 

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFound() {
        // No body needed, just returns 404
    }
}
