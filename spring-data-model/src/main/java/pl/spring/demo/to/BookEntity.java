package pl.spring.demo.to;

import java.util.List;

public class BookEntity implements IdAware {
	private List<AuthorTo> authors;
	private String title;

	public BookEntity() {
	}

	public BookEntity(Long id, String title, String authors) {
		this.id = id;
		this.title = title;
		this.authors = authors;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	private class AuthorTo {
		Long id;
		String firstName;
		String lastName;
	}
}
