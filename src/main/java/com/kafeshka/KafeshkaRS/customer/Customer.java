package com.kafeshka.KafeshkaRS.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class Customer {
private String name;
private String phoneNumber;
private String address;
private Date dateOfBirth;
}


