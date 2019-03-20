package com.github.ehr.patient_microservice.entities;

 
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document(collection="patient")
public class Patient
{
 
        @Id
        private Long personId;
         
        private String name;
         
        private int age;
         
        @DBRef(db="address")
        private List<Address> addresses = new ArrayList<>();
         
        public Patient()
        {}
         
         
        @PersistenceConstructor
        public Patient(Long personId, String name, int age)
        {
                super();
                this.personId = personId;
                this.name = name;
                this.age = age;
        }
 
        public Long getPersonId()
        {
                return personId;
        }
 
        public void setPersonId(Long personId)
        {
                this.personId = personId;
        }
 
        public String getName()
        {
                return name;
        }
 
        public void setName(String name)
        {
                this.name = name;
        }
 
        public int getAge()
        {
                return age;
        }
 
        public void setAge(int age)
        {
                this.age = age;
        }
 
        public List<Address> getAddresses()
        {
                return addresses;
        }
 
 
        public void setAddresses(List<Address> addresses)
        {
                this.addresses = addresses;
        }
 
 
        @Override
        public String toString()
        {
                return "Person [personId=" + personId + ", name=" + name + ", age=" + age + ", addresses=" + addresses + "]";
        }
         
}