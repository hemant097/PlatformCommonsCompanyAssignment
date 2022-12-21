package com.platformcommons.Entities;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platformcommons.enums.AddressType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer addressid;
	String area;
	String state;
	String district;
	Integer pincode;
	AddressType addressType;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	Student stud;
}
