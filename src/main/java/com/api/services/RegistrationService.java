package com.api.services;

import com.api.entity.Registration;
import com.api.exceptions.ResourecNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;
    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }

    public List<RegistrationDto> getRegistrations(){
        List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDto> dtos = registrations.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return dtos;
    }
    public RegistrationDto createRegistration(RegistrationDto registrationDto){
        Registration registration = mapToEntity(registrationDto);
        Registration save = registrationRepository.save(registration);
        RegistrationDto regDto = mapToDto(save);
        return regDto;
    }
   public RegistrationDto updateReg(long id, RegistrationDto registrationDto){
       Optional<Registration> reg = registrationRepository.findById(id);
       Registration r = reg.get();
        r.setName(registrationDto.getName());
        r.setMobile(registrationDto.getMobile());
        r.setEmail(registrationDto.getEmail());
       Registration registration = mapToEntity(registrationDto);
       Registration saved = registrationRepository.save(r);
       RegistrationDto registrationDto1 = mapToDto(registration);

       return registrationDto1;
   }
   public RegistrationDto getRegistrationById(long id){
       Registration registration = registrationRepository.findById(id).orElseThrow(
               () -> new ResourecNotFoundException("Resource not found")
       );
       RegistrationDto registrationDto = mapToDto(registration);
       return registrationDto;
   }

    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }
    Registration mapToEntity(RegistrationDto registrationDto){
        Registration registration = modelMapper.map(registrationDto, Registration.class);
//        Registration  registration = new Registration();
//       registration.setName(registrationDto.getName());
//       registration.setEmail(registrationDto.getEmail());
//       registration.setMobile(registrationDto.getMobile());
       return registration;
    }
    RegistrationDto mapToDto(Registration registration) {
        RegistrationDto registrationDto = modelMapper.map(registration, RegistrationDto.class);
//        RegistrationDto registrationDto = new RegistrationDto();
//        registrationDto.setName(registration.getName());
//        registrationDto.setMobile(registrationDto.getMobile());
//        registrationDto.setEmail(registration.getEmail());
        return registrationDto;
    }

}
