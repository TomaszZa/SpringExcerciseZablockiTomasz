package pl.spring.demo.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.IdAware;

@Component
public class Sequence {

	public long nextValue(Collection<? extends IdAware> existingIds) {
		long result = 1;
		for (IdAware nextExistingId : existingIds) {
			if (Long.compare(nextExistingId.getId(), result) > 0) {
				result = nextExistingId.getId() + 1; // Should be +1
			}
		}
		return result;
	}

	public List<BookEntity> checkTitle(String title, Iterator iterator) {
		List<BookEntity> bookEntityList = new ArrayList<BookEntity>();
		BookEntity bookEntity = null;
		String titleFromSet;

		while (iterator.hasNext()) {
			bookEntity = (BookEntity) iterator.next();
			titleFromSet = bookEntity.getTitle();
			if (title.contains(titleFromSet))
				bookEntityList.add(bookEntity);
		}
		if (bookEntityList.size() == 0)
			return null;
		return bookEntityList;
	}

	public List<BookEntity> checkAuthors(String author, Iterator iterator) {
		List<BookEntity> bookEntityList = new ArrayList<BookEntity>();
		BookEntity bookEntity = null;
		String firstNameFromSet;
		String lastNameFromSet;

		while (iterator.hasNext()) {
			bookEntity = (BookEntity) iterator.next();
			for (int i = 0; i < bookEntity.getAuthors().size(); i++) {
				firstNameFromSet = bookEntity.getAuthors().get(i).getFirstName();
				lastNameFromSet = bookEntity.getAuthors().get(i).getLastName();
				if (author.contains(firstNameFromSet) && author.contains(lastNameFromSet))
					bookEntityList.add(bookEntity);
			}
		}
		if (bookEntityList.size() == 0)
			return null;
		return bookEntityList;
	}

}
