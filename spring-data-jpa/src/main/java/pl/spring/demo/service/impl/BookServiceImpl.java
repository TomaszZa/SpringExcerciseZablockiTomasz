package pl.spring.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.mapper.Mapper;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private Mapper mapper;

	@Override
	public List<BookTo> findAllBooks() {
		return mapper.getListBookTo(bookDao.findAll());
	}

	@Override
	public List<BookTo> findBooksByTitle(String title) {
		return mapper.getListBookTo(bookDao.findBookByTitle(title));
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		return mapper.getListBookTo(bookDao.findBooksByAuthor(author));
	}

	@Override
	public BookTo saveBook(BookEntity book) {
		return mapper.getBookTo(bookDao.save(book));
	}
}
