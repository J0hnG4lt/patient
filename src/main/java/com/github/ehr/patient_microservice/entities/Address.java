package com.github.ehr.patient_microservice.entities;
 
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
public class Address
{
              
        @NonNull private String address;
         
        @NonNull private String city;
         
        @NonNull private String state;

        @NonNull private String countryCode;
         
        @NonNull private long zipcode;
         
}