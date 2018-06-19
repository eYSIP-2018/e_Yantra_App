package com.example.rajneesh.googlesignin;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MapResponse {
//ArrayList<Response> responses;
//
//    public ArrayList<Response> getResponses() {
//        return responses;
//    }
//
//    public void setResponses(ArrayList<Response> responses) {
//        this.responses = responses;
//    }

   // class Response{
    @SerializedName("S. No.")
    int sno;
    @SerializedName("Region Name")
    String region;
    @SerializedName("College Name")
    String clg_name;
    @SerializedName("District")
    String district;
    @SerializedName("Address")
    String address;
    @SerializedName("State")
    String state;
    @SerializedName("Pincode")
    String pincode;

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getClg_name() {
            return clg_name;
        }

        public void setClg_name(String clg_name) {
            this.clg_name = clg_name;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public int getSno() {

            return sno;
        }

        public void setSno(int sno) {
            this.sno = sno;
        }
 //   }
}
