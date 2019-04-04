package com.github.ehr.patient_microservice.entities;

import java.util.List;
import java.math.BigInteger;
import java.util.Date; 

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat; 

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

@Document(collection="patient")
@Getter
@Setter
public class Patient
{
 
        @Id
        private BigInteger identity;

        private Boolean active;
        @NonNull private List<String> name;
        private List<ContactPoint> telecom;
        private String gender;
        
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private Date birthDate;
        
        private Boolean deceasedBoolean;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private Date deceBooleanDateTime;
         
        private List<Address> addresses;
        
        private String maritalStatus;

        private List<Photo> photo;

        private List<Contact> contact;

        private List<Communication> communication;

        private Organization managingOrganization;

        private PatientLink link;

        public Patient()
        {}
         
         
        @PersistenceConstructor
        public Patient(List<String> name)
        {
                super();
                this.name = name;
        }
         
}