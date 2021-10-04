package com.ty.hibernet.Demo2;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Student {
	
	@Id
	private int s_id;
	private String s_name;
	private int phone_no;
	private String s_branch;

}

