package com.github.ehr.patient_microservice.entities;
 
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
public class ContactPoint
{
 
        @NonNull String system;
        @NonNull private String value;
        @NonNull private String use;
        @NonNull private Integer rank;
  
}