package pl.spring.demo.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Service;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.IdAware;

@Service
public class BookDaoAdvisor implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] objects, Object o) throws Throwable {

		if (hasAnnotation(method, o, NullableId.class)) {
			checkNotNullId(objects[0]);
			saveInAspect(method, objects[0], o);
		}
	}

	private void checkNotNullId(Object o) {
		if (o instanceof IdAware && ((IdAware) o).getId() != null) {
			throw new BookNotNullIdException();
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

	public void saveInAspect(Method method, Object objects, Object o) {
		((BookEntity) objects).setId(((BookDaoImpl) o).getNextIdFromSequence());
		((BookDaoImpl) o).getALL_BOOKS().add((BookEntity) objects);
	}
}
