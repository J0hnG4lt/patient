package com.github.ehr.patient_microservice.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
public class Communication
{
 
        @NonNull String language;
        @NonNull Boolean preffered;
 
         
}