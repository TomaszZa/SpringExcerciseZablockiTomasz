package pl.spring.demo.mock;
/**
 * @COPYRIGHT (C) 2015 Schenker AG
 *
 * All rights reserved
 */

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.AuthorTo;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;

//PRZENIOSLEM TEN TEST PONIEWAZ POTRZEBA TERAZ WSTRZYKNAC MAPPERA (CZYLI ODPALIC GO W KONTEKSCIE!!)
/**
 * TODO The class BookServiceImplTest is supposed to be documented...
 *
 * @author AOTRZONS
 */

public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService;
	@Mock // dla javy ze mock
	private BookDao bookDao;

	@Before
	public void setUpt() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Ignore
	public void testShouldSaveBook() {
		// given
		List<AuthorTo> listAuthorTo = new ArrayList<AuthorTo>();
		AuthorTo authorTo = new AuthorTo(1L, "Maciek", "Kowalski");
		listAuthorTo.add(authorTo);
		BookEntity book = new BookEntity(null, "title", listAuthorTo);
		// mock
		Mockito.when(bookDao.save(book)).thenReturn(new BookEntity(1L, "title", listAuthorTo)); // metoda
		// when
		// then
		System.out.println(bookService + "   " + book);
		BookTo result = bookService.saveBook(book);
		Mockito.verify(bookDao).save(book);
		assertEquals(1L, result.getId().longValue());
	}

}
