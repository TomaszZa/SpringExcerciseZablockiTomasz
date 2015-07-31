package pl.spring.demo.entity;

import java.util.List;

import pl.spring.demo.to.IdAware;

public class BookEntity implements IdAware {
	private Long id;
	private List<AuthorTo> authors;
	private String title;

	public BookEntity() {
	}

	public BookEntity(Long id, String title, List<AuthorTo> authors) {
		this.title = title;
		this.authors = authors;
		this.id = id;
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

	public List<AuthorTo> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorTo> authors) {
		this.authors = authors;
	}
}
