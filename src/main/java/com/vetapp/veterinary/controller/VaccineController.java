package com.vetapp.veterinary.controller;

import com.vetapp.veterinary.business.abs.IVaccineService;
import com.vetapp.veterinary.core.config.modelMapper.IModelMapperService;
import com.vetapp.veterinary.core.result.Result;
import com.vetapp.veterinary.core.result.ResultData;
import com.vetapp.veterinary.core.utilies.ResultHelper;
import com.vetapp.veterinary.dto.request.VaccineSaveRequest;
import com.vetapp.veterinary.dto.request.VaccineUpdateRequest;
import com.vetapp.veterinary.dto.response.VaccineResponse;
import com.vetapp.veterinary.entity.Vaccine;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/vaccines")

public class VaccineController {

    private final IVaccineService vaccineService;
    private final IModelMapperService modelMapper;

    public VaccineController(IVaccineService vaccineService, IModelMapperService modelMapper) {
        this.vaccineService = vaccineService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest ){
        Vaccine saveVaccine = this.modelMapper.forRequest().map(vaccineSaveRequest, Vaccine.class);
        Long animalId = vaccineSaveRequest.getAnimalId();
        Vaccine savedVaccine = this.vaccineService.save(saveVaccine, animalId);
        return ResultHelper.created(this.modelMapper.forResponse().map(savedVaccine, VaccineResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> get (@PathVariable("id") Long id) {
        Vaccine vaccine = this.vaccineService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(vaccine,VaccineResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.vaccineService.delete(id);
        return ResultHelper.Ok();
    }
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest ){
        Vaccine updateVaccine = this.modelMapper.forRequest().map(vaccineUpdateRequest, Vaccine.class);
        this.vaccineService.update(updateVaccine);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateVaccine, VaccineResponse.class));
    }
    @GetMapping("/animal/{animalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getVaccinesByAnimalId(@PathVariable("animalId") Long animalId) {
        List<Vaccine> vaccines = this.vaccineService.getVaccinesByAnimalId(animalId);

        List<VaccineResponse> vaccineResponses = vaccines.stream()
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(vaccineResponses);
    }


    //Fetching vaccination records from a specific date range
    @GetMapping("/date-range")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getVaccinesByDateRange(
            @RequestParam("start-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("end-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Vaccine> vaccines = vaccineService.getByProtectionStartDateBetween(startDate, endDate);
        List<VaccineResponse> vaccineResponses = vaccines.stream()
                .map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(vaccineResponses);
    }
    @GetMapping("/animal/filter/date")
    public ResultData<List<VaccineResponse>> getVaccinesByAnimalAndDateRange(
            @RequestParam("animalId") Long animalId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Vaccine> vaccines = vaccineService.findByAnimalIdAndProtectionStartDateBetween(animalId, startDate, endDate);
        List<VaccineResponse> vaccineResponses = vaccines.stream()
                .map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(vaccineResponses);
    }
}

