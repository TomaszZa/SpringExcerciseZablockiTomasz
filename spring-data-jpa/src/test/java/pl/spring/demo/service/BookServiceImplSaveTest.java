package pl.spring.demo.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class) // Niezbedne do polaczenia testow
@ContextConfiguration(locations = "CommonServiceTest-context.xml") // ze
																	// Springiem
public class BookServiceImplSaveTest {

	@Autowired
	private BookDao bookDao;

	@Test
	public void testToMethodSave() {
		// inicjal
		BookTo book = new BookTo(null, "title", "author");
		// use save
		BookTo result = bookDao.save(book);
		// check ID
		assertNotNull(result.getId().longValue());
	}
}
