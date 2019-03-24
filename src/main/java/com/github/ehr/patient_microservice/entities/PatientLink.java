package com.github.ehr.patient_microservice.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
public class PatientLink
{
 
        @NonNull Long other;
        @NonNull String type;
         
}