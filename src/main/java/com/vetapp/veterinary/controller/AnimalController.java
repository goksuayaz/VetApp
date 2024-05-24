package com.vetapp.veterinary.controller;

import com.vetapp.veterinary.business.abs.IAnimalService;
import com.vetapp.veterinary.business.abs.ICustomerService;
import com.vetapp.veterinary.core.config.modelMapper.IModelMapperService;
import com.vetapp.veterinary.core.result.Result;
import com.vetapp.veterinary.core.result.ResultData;
import com.vetapp.veterinary.core.utilies.ResultHelper;
import com.vetapp.veterinary.dto.CursorResponse;
import com.vetapp.veterinary.dto.request.AnimalSaveRequest;
import com.vetapp.veterinary.dto.request.AnimalUpdateRequest;
import com.vetapp.veterinary.dto.response.AnimalResponse;
import com.vetapp.veterinary.dto.response.CustomerResponse;
import com.vetapp.veterinary.dto.response.VaccineResponse;
import com.vetapp.veterinary.entity.Animal;
import com.vetapp.veterinary.entity.Customer;
import com.vetapp.veterinary.entity.Vaccine;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/animals")

public class AnimalController {

    private final IAnimalService animalService;
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;


    public AnimalController(IAnimalService animalService,
                            ICustomerService customerService,
                            IModelMapperService modelMapper) {
        this.animalService = animalService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }


    //endpoint that retrieves details of a specific animal
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get (@PathVariable("id") Long id) {
        Animal animal = this.animalService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(animal,AnimalResponse.class));
    }


    //Adding a new animal endpoint
    @PostMapping("/createdNew")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest ){
        Animal saveAnimal = this.modelMapper.forRequest().map(animalSaveRequest,Animal.class);

        //Customer customer =this.customerService.get(animalSaveRequest.getCustomerId());
        //saveAnimal.setCustomer(customer);

        this.animalService.save(saveAnimal);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAnimal,AnimalResponse.class));
    }




    //Animal update endpoint
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest ){
        Animal updateAnimal = this.modelMapper.forRequest().map(animalUpdateRequest,Animal.class);
        this.animalService.update(updateAnimal);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAnimal,AnimalResponse.class));
    }


    //Animal delete endpoint
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.animalService.delete(id);
        return ResultHelper.Ok();
    }

    //Endpoint that fetches the animal's vaccines
    @GetMapping("/{id}/vaccines")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getVaccinesForAnimal(@PathVariable("id") Long id) {
        Animal animal = this.animalService.get(id);


        List<Vaccine> vaccines = animal.getVaccines();


        List<VaccineResponse> vaccineResponses = vaccines.stream()
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(vaccineResponses);
    }
    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAnimalsByName(@RequestParam("name") String name) {
        List<Animal> animals = this.animalService.getAnimalsByName(name);


        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(animalResponses);
    }
    @GetMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAnimalsByCustomerId(@PathVariable("customerId") Long customerId) {
        List<Animal> animals = this.animalService.getAnimalsByCustomerId(customerId);


        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(animalResponses);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false,defaultValue = "2") int pageSize
    ){

        Page<Animal> animalPage = this.animalService.cursor(page,pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage.map(animal -> this.modelMapper.forResponse().map(animal,AnimalResponse.class));

        return  ResultHelper.cursor(animalResponsePage);
    }


}

