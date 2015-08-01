package pl.spring.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.spring.demo.entity.AuthorTo;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

@Component
public class Mapper {
	private BookTo bookTo = new BookTo();
	private BookEntity bookEntity = new BookEntity();
	private List<AuthorTo> authors = new ArrayList<AuthorTo>();
	String authorsBookTo = "";
	long authorID = 1;

	public BookTo getBookTo(BookEntity bookEntity) {
		changeListAuthorToToStringAuthors(bookEntity);

		bookTo.setTitle(bookEntity.getTitle());
		bookTo.setId(bookEntity.getId());
		bookTo.setAuthors(authorsBookTo);

		return bookTo;
	}

	public BookEntity getBookEntity(BookTo bookTo) {
		changeStringAuthorsToListAuthorTo(bookTo);

		bookEntity.setTitle(bookTo.getTitle());
		bookEntity.setId(bookTo.getId());
		bookEntity.setAuthors(authors);

		return bookEntity;
	}

	public List<BookTo> getListBookTo(List<BookEntity> bookEntity) {
		List<BookTo> tempBookToList = new ArrayList<BookTo>();
		for (int i = 0; i < bookEntity.size(); i++)
			tempBookToList.add(getBookTo(bookEntity.get(i)));
		return tempBookToList;
	}

	public List<BookEntity> getListBookEntity(List<BookTo> bookTo) {
		List<BookEntity> tempBookEntityList = new ArrayList<BookEntity>();
		for (int i = 0; i < bookTo.size(); i++)
			tempBookEntityList.add(getBookEntity(bookTo.get(i)));
		return tempBookEntityList;
	}

	private void changeStringAuthorsToListAuthorTo(BookTo bookTo) {
		char charTemp = 0;
		boolean changeWord = true;
		String firstName = "";
		String lastName = "";

		for (int i = 0; i < bookTo.getAuthors().length(); i++) {
			if (i < bookTo.getAuthors().length())
				charTemp = bookTo.getAuthors().charAt(i);

			if (changeWord && charTemp != ' ')
				firstName += charTemp;
			if (!changeWord && charTemp != ' ')
				lastName += charTemp;
			if (charTemp == ' ' || i == bookTo.getAuthors().length() - 1)
				changeWord ^= true;

			if (changeWord && firstName.length() != 0 && lastName.length() != 0) {

				authors.add(new AuthorTo(new Long(authorID++), firstName, lastName));
				firstName = "";
				lastName = "";
			}
		}
	}

	private void changeListAuthorToToStringAuthors(BookEntity bookEntity) {
		for (int i = 0; i < bookEntity.getAuthors().size(); i++) {
			authorsBookTo += bookEntity.getAuthors().get(i).getFirstName();
			authorsBookTo += ' ';
			authorsBookTo += bookEntity.getAuthors().get(i).getLastName();
			if (i + 1 != bookEntity.getAuthors().size())
				authorsBookTo += ' ';
		}
	}
}
