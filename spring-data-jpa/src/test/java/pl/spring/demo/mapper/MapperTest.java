package pl.spring.demo.mapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pl.spring.demo.entity.AuthorTo;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

public class MapperTest {

	@Autowired
	private Mapper mapper = new Mapper();

	@Test
	public void testChangeBookToIntoBookEntity() {
		BookEntity bookEntity;
		BookTo bookTo = new BookTo(1L, "Good Book", "Maciek Garlicki");
		bookEntity = mapper.getBookEntity(bookTo);

		assertEquals(1L, bookEntity.getId().longValue());
		assertEquals("Good Book", bookEntity.getTitle());
		assertEquals("Maciek", bookEntity.getAuthors().get(0).getFirstName());
		assertEquals("Garlicki", bookEntity.getAuthors().get(0).getLastName());
	}

	@Test
	public void testChangeBookEntityIntoBookTo() {
		BookTo bookTo;
		AuthorTo authorTo = new AuthorTo(2L, "Krzys", "Parch");
		List<AuthorTo> authors = new ArrayList<AuthorTo>();
		authors.add(authorTo);

		BookEntity bookEntity = new BookEntity(2L, "Middle Book", authors);
		bookTo = mapper.getBookTo(bookEntity);

		assertEquals(2L, bookTo.getId().longValue());
		assertEquals("Middle Book", bookTo.getTitle());
		assertEquals("Krzys Parch", bookTo.getAuthors());

	}

}