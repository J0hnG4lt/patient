package com.github.ehr.patient_microservice.entities;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.NonNull;

@Getter
@Setter
@NoArgsConstructor
public class Contact
{
 
        @NonNull public List<String> relationship;
        @NonNull public List<String> name;
        @NonNull public List<ContactPoint> telecom;
        public Address address;
        @NonNull public String gender;
 
}