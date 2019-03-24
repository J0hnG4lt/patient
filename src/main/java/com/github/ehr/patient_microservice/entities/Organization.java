package com.github.ehr.patient_microservice.entities;

import java.util.List;
 
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
public class Organization
{
 
        @NonNull private List<String> alias;
        @NonNull private String name;
        @NonNull private List<String> type;
        @NonNull private List<ContactPoint> telecom;
        @NonNull private List<Address> address;

         
}