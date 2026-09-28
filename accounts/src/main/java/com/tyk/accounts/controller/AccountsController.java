package com.tyk.accounts.controller;

import com.tyk.accounts.constants.AccountsConstants;
import com.tyk.accounts.dto.CustomerDto;
import com.tyk.accounts.dto.ResponseDto;
import com.tyk.accounts.service.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService accountsService;

    @GetMapping("say-hello")
    public String getMessage(){
        return "HI Yedukondalu, Your Implemented the Hello World Get Rest API";
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount( @Valid @RequestBody CustomerDto customerDto){

        accountsService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.STATUS_201));
    }
    
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchCustomerDetails(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number  must be 10 digits")String mobileNumber){
    	
    	CustomerDto fetchCustomerDetails = accountsService.fetchCustomerDetails(mobileNumber);
    	return ResponseEntity.status(HttpStatus.OK).body(fetchCustomerDetails);
    }
    
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCustomerDetails( @Valid@RequestBody CustomerDto customerDto){
    	boolean updateCustomerDetails = accountsService.updateCustomerDetails(customerDto);
    	if(updateCustomerDetails) {
    		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.MESSAGE_200, AccountsConstants.MESSAGE_200));
    	}else {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
    	}
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCustomer(@RequestParam      @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits") String mobileNumber){
    	boolean isCustomerDeleted = accountsService.deleteCustomer(mobileNumber);
    	if(isCustomerDeleted) {
    		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.MESSAGE_200, AccountsConstants.MESSAGE_200));
    	}else {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
    	}
    }
}
