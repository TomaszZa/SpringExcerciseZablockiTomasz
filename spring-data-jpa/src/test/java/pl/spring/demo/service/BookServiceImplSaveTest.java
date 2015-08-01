package pl.spring.demo.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.AuthorTo;
import pl.spring.demo.entity.BookEntity;

@RunWith(SpringJUnit4ClassRunner.class) // Niezbedne do polaczenia testow
@ContextConfiguration(locations = "CommonServiceTest-context.xml") // ze
																	// Springiem
public class BookServiceImplSaveTest {

	@Autowired
	private BookDao bookDao;

	@Test
	public void testToMethodSave() {
		List<AuthorTo> listAuthorTo = new ArrayList<AuthorTo>();
		AuthorTo authorTo = new AuthorTo(1L, "Maciek", "Kowalski");
		listAuthorTo.add(authorTo);
		// inicjal
		BookEntity book = new BookEntity(null, "title", listAuthorTo);
		// use save
		BookEntity result = bookDao.save(book);
		// check ID
		assertNotNull(result.getId().longValue());
	}
}
