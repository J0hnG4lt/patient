package com.github.ehr.patient_microservice.entities;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

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
        
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private Date birthDate;
        
        private Boolean deceasedBoolean;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private Date deceasedBooleanDateTime;
         
        private List<Address> addresses;
        
        private String maritalStatus;

        private List<Photo> photos;

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