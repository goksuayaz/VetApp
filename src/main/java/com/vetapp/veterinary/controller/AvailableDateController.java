package com.vetapp.veterinary.controller;

import com.vetapp.veterinary.business.abs.IAvailableDateService;
import com.vetapp.veterinary.business.abs.IDoctorService;
import com.vetapp.veterinary.core.config.modelMapper.IModelMapperService;
import com.vetapp.veterinary.core.result.Result;
import com.vetapp.veterinary.core.result.ResultData;
import com.vetapp.veterinary.core.utilies.ResultHelper;
import com.vetapp.veterinary.dto.CursorResponse;
import com.vetapp.veterinary.dto.request.AnimalUpdateRequest;
import com.vetapp.veterinary.dto.request.AvailableDateSaveRequest;
import com.vetapp.veterinary.dto.request.AvailableDateUpdateRequest;
import com.vetapp.veterinary.dto.response.AnimalResponse;
import com.vetapp.veterinary.dto.response.AvailableDateResponse;
import com.vetapp.veterinary.entity.Animal;
import com.vetapp.veterinary.entity.AvailableDate;
import com.vetapp.veterinary.entity.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/available-dates")


public class AvailableDateController {

    private  final IAvailableDateService availableDateService;
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    public AvailableDateController(IAvailableDateService availableDateService,
                                   IDoctorService doctorService,
                                   IModelMapperService modelMapper) {
        this.availableDateService = availableDateService;
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> get (@PathVariable("id") Long id) {
        AvailableDate availableDate = this.availableDateService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(availableDate,AvailableDateResponse.class));
    }

    @PostMapping("/createdNew")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest ){

        AvailableDate saveAvailableDate = this.modelMapper.forRequest().map(availableDateSaveRequest,AvailableDate.class);

        Doctor doctor =this.doctorService.get(availableDateSaveRequest.getDoctorId());
        saveAvailableDate.setDoctor(doctor);

        this.availableDateService.save(saveAvailableDate);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAvailableDate, AvailableDateResponse.class));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest ){
        AvailableDate updateAvailableDate = this.modelMapper.forRequest().map(availableDateUpdateRequest,AvailableDate.class);
        this.availableDateService.update(updateAvailableDate);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAvailableDate,AvailableDateResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.availableDateService.delete(id);
        return ResultHelper.Ok();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(
            @RequestParam(name = "page", required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false,defaultValue = "2") int pageSize
    ){

        Page<AvailableDate> availableDatePage = this.availableDateService.cursor(page,pageSize);
        Page<AvailableDateResponse> availableDateResponsePage = availableDatePage.map(availableDate -> this.modelMapper.forResponse().map(availableDate,AvailableDateResponse.class));

        return  ResultHelper.cursor(availableDateResponsePage);
    }





}

