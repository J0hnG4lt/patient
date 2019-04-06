package com.github.ehr.patient_microservice.entities;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Organization
{
 
        @NonNull public List<String> alias;
        @NonNull public String name;
        @NonNull public List<String> type;
        @NonNull public List<ContactPoint> telecom;
        @NonNull public List<Address> address;

         
}