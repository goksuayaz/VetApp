package com.vetapp.veterinary.controller;

import com.vetapp.veterinary.business.abs.ICustomerService;
import com.vetapp.veterinary.core.config.modelMapper.IModelMapperService;
import com.vetapp.veterinary.core.result.Result;
import com.vetapp.veterinary.core.result.ResultData;
import com.vetapp.veterinary.core.utilies.ResultHelper;
import com.vetapp.veterinary.dto.CursorResponse;
import com.vetapp.veterinary.dto.request.CustomerSaveRequest;
import com.vetapp.veterinary.dto.request.CustomerUpdateRequest;
import com.vetapp.veterinary.dto.response.CustomerResponse;
import com.vetapp.veterinary.entity.Customer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/customers")

public class CustomerController {

    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;


    public CustomerController(ICustomerService customerService, IModelMapperService modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    //Endpoint to register a new customer
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest ){
        Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest,Customer.class);
        this.customerService.save(saveCustomer);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveCustomer,CustomerResponse.class));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get (@PathVariable("id") long id) {
        Customer customer = this.customerService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(customer,CustomerResponse.class));
    }


    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest ){
        Customer updateCustomer = this.modelMapper.forRequest().map(customerUpdateRequest,Customer.class);
        this.customerService.update(updateCustomer);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateCustomer,CustomerResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        this.customerService.delete(id);
        return ResultHelper.Ok();
    }


    //To list customers as a page
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(name = "page", required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false,defaultValue = "2") int pageSize
    ){

        Page<Customer> categoryPage = this.customerService.cursor(page,pageSize);
        Page<CustomerResponse> customerResponsePage = categoryPage
                .map(category -> this.modelMapper.forResponse().map(category,CustomerResponse.class));

        return  ResultHelper.cursor(customerResponsePage);
    }

    //Endpoint that filters customers by name
    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CustomerResponse>> getCustomersByName(@RequestParam("name") String name) {
        List<Customer> customers = this.customerService.getCustomersByName(name);


        List<CustomerResponse> customerResponses = customers.stream()
                .map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(customerResponses);
    }

}

