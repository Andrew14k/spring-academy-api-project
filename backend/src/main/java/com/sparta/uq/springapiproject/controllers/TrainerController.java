package com.sparta.uq.springapiproject.controllers;


import com.sparta.uq.springapiproject.dtos.TrainerDTO;
import com.sparta.uq.springapiproject.dtos.TrainerMapper;
import com.sparta.uq.springapiproject.entities.Trainer;
import com.sparta.uq.springapiproject.services.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")  // clients can access api endpoints
@Tag(name = "trainer-controller", description = "Operations on trainer table")
@RestController //@Controller and @ResponseBody
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    @Operation(summary = "Get all trainers", description = "Retrieve a list of all trainers")
    @GetMapping
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        List<TrainerDTO> trainers = trainerService.getAllTrainers();
        return ResponseEntity.ok(trainers);
    }


    @Operation(summary = "Get a trainer by ID", description = "Retrieve a trainer from the database using their unique ID")
    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO> getTrainerById(@PathVariable Integer id) {
        TrainerDTO trainer = trainerService.getTrainerByID(id);
        if (trainer != null) {
            return ResponseEntity.ok(trainer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Add a new trainer", description = "Create a new trainer in the database")
    @PostMapping
    public ResponseEntity<TrainerDTO> addTrainer(@RequestBody TrainerDTO trainerDTO) {
        TrainerDTO addedTrainer = trainerService.saveTrainer(trainerDTO);
        return ResponseEntity.status(201).body(addedTrainer);
    }

    @Operation(summary = "Update a trainer", description = "Update an existing trainer in the database")
    @PutMapping("/{id}")
    public ResponseEntity<TrainerDTO> updateTrainer(@PathVariable Integer id, @RequestBody TrainerDTO trainerDTO) {
        trainerDTO.setId(id);
        try {
            TrainerDTO updatedTrainer = trainerService.updateTrainer(trainerDTO);
            return ResponseEntity.ok(updatedTrainer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a trainer", description = "Delete a trainer from the database using their unique ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Integer id) {
        boolean deletedTrainer = trainerService.deleteTrainer(id);
        if (deletedTrainer) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
