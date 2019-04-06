package com.github.ehr.patient_microservice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.NonNull;

@Getter
@Setter
@NoArgsConstructor
public class Communication
{
 
        @NonNull public String language;
        public Boolean preffered;
 
         
}