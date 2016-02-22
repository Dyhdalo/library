package ukma.library.server.entity;

import java.io.Serializable;

public class Book implements Serializable{

	private int id;

	private String title;

	private String author;

	private String edition;

	private int year;

	public Book(){
		//test = "Bad!";
	}
	
	public Book(int id, String title, String author, String edition, int year) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.edition = edition;
		this.year = year;
	}

	public void ok(){
		//test = "Ok!";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Book book = (Book) o;

		if (getId() != book.getId()) return false;
		if (getYear() != book.getYear()) return false;
		if (!getTitle().equals(book.getTitle())) return false;
		if (!getAuthor().equals(book.getAuthor())) return false;
		return getEdition().equals(book.getEdition());

	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + getTitle().hashCode();
		result = 31 * result + getAuthor().hashCode();
		result = 31 * result + getEdition().hashCode();
		result = 31 * result + getYear();
		return result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
