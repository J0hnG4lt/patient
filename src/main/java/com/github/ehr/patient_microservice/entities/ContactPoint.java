package com.github.ehr.patient_microservice.entities;
 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.NonNull;

@Getter
@Setter
@NoArgsConstructor
public class ContactPoint
{
 
        @NonNull public String system;
        @NonNull public String value;
        @NonNull public String use;
        @NonNull public Integer rank;
  
}