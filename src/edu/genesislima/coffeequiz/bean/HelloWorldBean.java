package edu.genesislima.coffeequiz.bean;

import java.io.Serializable;

import javax.ejb.Startup;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

//@ManagedBean(name="helloWorld", eager=true)
@Named("helloWorld")
@RequestScoped
@Startup
public class HelloWorldBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HelloWorldBean() {
	      System.out.println("HelloWorld started!");
	   }
		
	   public String getMessage() {
	      return "Hello World!";
	   }
	   
}
