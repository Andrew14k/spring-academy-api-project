package com.sparta.uq.springapiproject.controllers;

import com.sparta.uq.springapiproject.dtos.TraineeDTO;
import com.sparta.uq.springapiproject.entities.Trainee;
import com.sparta.uq.springapiproject.services.TraineeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "trainee-controller", description = "Operations on trainee table")
@RestController //@Controller and @ResponseBody
@RequestMapping("/trainees")
public class TraineeController {
    private final TraineeService service;
    public TraineeController(TraineeService service){
        this.service = service;
    }

    @Operation(summary = "Get all trainees", description = "Retrieve a list of all trainees.")
    @GetMapping(value = "/") //maps http get requests to the getAllTrainees method
    public ResponseEntity<List<TraineeDTO>> getALlTrainees(){
        List<TraineeDTO> trainees = service.getAllTrainees();
        return ResponseEntity.ok(trainees);
    }

    @Operation(summary = "Get trainee by ID", description = "Retrieve a trainee from the database using their unique ID.")
    @GetMapping("/{id}")
    public ResponseEntity<TraineeDTO> getTraineeById(@PathVariable Integer id) {
        TraineeDTO trainee = service.getTraineeById(id);
        if (trainee != null) {
            return ResponseEntity.ok(trainee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Add a new trainee", description = "Create a new trainee in the database")
    @PostMapping
    public ResponseEntity<TraineeDTO> addTrainee(@RequestBody TraineeDTO trainee){
        TraineeDTO addedTrainee = service.addTrainee(trainee);
        return ResponseEntity.status(201).body(addedTrainee);
    }

    @Operation(summary = "Update a trainee", description = "Update a trainee by searching for their ID")
    @PutMapping
    public ResponseEntity<TraineeDTO> updateTrainee(@RequestBody TraineeDTO trainee) {
        TraineeDTO updatedTrainee = service.updateTrainee(trainee);
        return ResponseEntity.status(201).body(updatedTrainee);
    }

    @Operation(summary = "Delete a trainee", description = "Finds a trainee by their ID and deletes them")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainee(@PathVariable Integer id) {
        var deletedTrainee = service.deleteTrainee(id);
        if (!deletedTrainee) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.noContent().build();
    }
}
