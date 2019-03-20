package com.github.ehr.patient_microservice.entities;
 
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document(collection="address")
public class Address
{
         
        @Id
        private long addressId;
         
        private String address;
         
        private String city;
         
        private String state;
         
        private long zipcode;
         
        public Address()
        {
                 
                System.out.println("CAlling default cons");
        }
 
        @PersistenceConstructor
        public Address(long addressId, String address, String city, String state, long zipcode)
        {
                super();
                this.addressId = addressId;
                this.address = address;
                this.city = city;
                this.state = state;
                this.zipcode = zipcode;
        }
 
 
 
 
        public String getAddress()
        {
                return address;
        }
 
        public void setAddress(String address)
        {
                this.address = address;
        }
 
        public String getCity()
        {
                return city;
        }
 
        public void setCity(String city)
        {
                this.city = city;
        }
 
        public String getState()
        {
                return state;
        }
 
        public void setState(String state)
        {
                this.state = state;
        }
 
        public long getZipcode()
        {
                return zipcode;
        }
 
        public void setZipcode(long zipcode)
        {
                this.zipcode = zipcode;
        }
 
        @Override
        public String toString()
        {
                return "Address [address=" + address + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode + "]";
        }       
         
}