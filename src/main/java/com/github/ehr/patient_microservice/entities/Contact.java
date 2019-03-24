package com.github.ehr.patient_microservice.entities;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
public class Contact
{
 
        @NonNull List<String> relationship;
        @NonNull private String name;
        @NonNull private List<ContactPoint> telecom;
        @NonNull private Address address;
        @NonNull private String gender;
 
         
}