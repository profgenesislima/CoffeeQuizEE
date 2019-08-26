package edu.genesislima.coffeequiz.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contador {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int total;
	private Date data;
	public int getId() {
		return id;
	}
	
	
	
	public Contador() {
		super();
	}



	public Contador(int total, Date data) {
		super();
		this.total = total;
		this.data = data;
	}



	public int getTotal() {
		return total;
	}
	
	public Date getData() {
		return data;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contador other = (Contador) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
