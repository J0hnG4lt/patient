package com.github.ehr.patient_microservice.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.NonNull;

@Getter
@Setter
@NoArgsConstructor
public class PatientLink
{
 
        @NonNull public Long other;
        @NonNull public String type;
         
}