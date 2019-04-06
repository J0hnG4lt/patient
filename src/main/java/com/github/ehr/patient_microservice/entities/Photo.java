package com.github.ehr.patient_microservice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.NonNull;

@Getter
@Setter
@NoArgsConstructor
public class Photo
{
 
        @NonNull public String name;
        @NonNull public String uri;
        @NonNull public Long rank;
 
}