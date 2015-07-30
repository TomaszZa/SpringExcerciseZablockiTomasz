package pl.spring.demo.dao.impl;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import pl.spring.demo.to.BookTo;

@Component
public class ChangeIDAspect implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		((BookTo) args[0]).setId(((BookDaoImpl) target).getNextIdFromSequence());
		((BookDaoImpl) target).getALL_BOOKS().add((BookTo) args[0]);
	}
}
