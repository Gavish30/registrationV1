package com.api.controller;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.services.RegistrationService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations(){
        List<RegistrationDto> dtos = registrationService.getRegistrations();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> saveRegistration(
            @Valid @RequestBody RegistrationDto registrationDto, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.CREATED);
        }
        RegistrationDto createdReg = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(createdReg, HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity<String> deleteReg(@RequestParam long id){
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("Record Deleted", HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RegistrationDto> updateReg
            (@PathVariable long id, @RequestBody RegistrationDto registrationDto){
        RegistrationDto updatedReg = registrationService.updateReg(id, registrationDto);
        return new ResponseEntity<>(updatedReg, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getById(@PathVariable long id){
        RegistrationDto registrationById = registrationService.getRegistrationById(id);
        return new ResponseEntity<>(registrationById,HttpStatus.OK);
    }
}
