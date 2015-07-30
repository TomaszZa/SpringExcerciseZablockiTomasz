package pl.spring.demo.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.IdAware;

@Component
public class BookDaoAdvisor implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] objects, Object o) throws Throwable {

		if (hasAnnotation(method, o, NullableId.class)) {
			checkNotNullId(o, method, objects[0]);
		}
	}

	private void checkNotNullId(Object o, Method method, Object objects) {
		if (o instanceof IdAware && ((IdAware) o).getId() != null) {
			throw new BookNotNullIdException();
		
			((BookTo) objects).setId(((BookDaoImpl) o).getNextIdFromSequence());
			((BookDaoImpl) o).getALL_BOOKS().add((BookTo) objects);
		}
	}

	private boolean hasAnnotation(Method method, Object o, Class annotationClazz) throws NoSuchMethodException {
		boolean hasAnnotation = method.getAnnotation(annotationClazz) != null;

		if (!hasAnnotation && o != null) {
			hasAnnotation = o.getClass().getMethod(method.getName(), method.getParameterTypes())
					.getAnnotation(annotationClazz) != null;
		}
		return hasAnnotation;
	}
}
