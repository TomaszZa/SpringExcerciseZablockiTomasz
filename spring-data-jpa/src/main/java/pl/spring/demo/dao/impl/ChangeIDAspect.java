package pl.spring.demo.dao.impl;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;

@Component
public class ChangeIDAspect implements MethodBeforeAdvice {
	@Autowired
	BookServiceImpl bookServiceImpl;
	@Autowired
	BookDaoImpl bookDaoImpl;

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		BookTo book = bookServiceImpl.bookk;
		if (book.getId() == null) {
			book.setId(bookDaoImpl.getSequence().nextValue(bookDaoImpl.getALL_BOOKS()));
		}
		bookDaoImpl.getALL_BOOKS().add(book);

	}
}
