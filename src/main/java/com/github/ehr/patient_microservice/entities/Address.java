package com.github.ehr.patient_microservice.entities;
 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Address
{
              
        public String address;
         
        public String city;
         
        public String state;

        public String countryCode;
         
        public long zipcode;
         
}