package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.AuthorTo;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class) // create context for test (such in
										// normal spring application)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplTest {

	@Autowired
	private BookService bookService;

	@Test
	public void testShouldFindAllBooks() {
		List<BookTo> allBooks = bookService.findAllBooks();
		// when
		// then
		assertNotNull(allBooks);
		assertFalse(allBooks.isEmpty());
		assertEquals(7, allBooks.size()); // Because we added one book in test
											// testShouldSaveBook()
	}

	@Test
	// @Ignore
	public void testShouldFindAllBooksByTitle() {
		// given
		final String title = "Opium w rosole";
		// when
		List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
	}

	@Test
	// @Ignore
	public void testShouldFindAllBooksByAuthor() {
		// given
		final String author = "Edmund Niziurski";
		// when
		List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
		// then
		assertNotNull(booksByAuthor);
		assertFalse(booksByAuthor.isEmpty());
	}

	@Test(expected = BookNotNullIdException.class)
	public void testShouldThrowBookNotNullIdException() {
		// given
		final BookEntity bookToSave = new BookEntity();
		bookToSave.setId(22L);
		// when
		bookService.saveBook(bookToSave);
		// then
		fail("test should throw BookNotNullIdException");
	}

	@Test
	public void testShouldSaveBook() { // to correct
		// given
		List<AuthorTo> listAuthorTo = new ArrayList<AuthorTo>();
		AuthorTo authorTo = new AuthorTo(1L, "Maciek", "Kowalski");
		listAuthorTo.add(authorTo);
		BookEntity book = new BookEntity(null, "title", listAuthorTo);

		List<BookTo> allBooks = bookService.findAllBooks(); // only for its size
															// to assert

		BookTo result = bookService.saveBook(book);
		assertEquals((long) (allBooks.size() + 1), result.getId().longValue());
	}

}
