package ukma.library.server.entity;

import java.io.Serializable;

public class Book implements Serializable{

	public String test;
	
	public Book(){
		test = "Bad!";
	}
	
	public void ok(){
		test = "Ok!";
	}
}
