package pl.spring.demo.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.AuthorTo;
import pl.spring.demo.entity.BookEntity;

public class BookDaoImpl implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	private Sequence sequence;

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookEntity> findAll() {
		return new ArrayList<>(ALL_BOOKS);
	}

	@Override
	public List<BookEntity> findBookByTitle(String title) {
		Iterator iterator = ALL_BOOKS.iterator();
		return sequence.checkTitle(title, iterator);
	}

	@Override
	public List<BookEntity> findBooksByAuthor(String author) { // notready !!

		return null;
	}

	@Override
	@NullableId
	public BookEntity save(BookEntity book) {
		return book;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	public long getNextIdFromSequence() {
		return sequence.nextValue(ALL_BOOKS);
	}

	public Set<BookEntity> getALL_BOOKS() {
		return ALL_BOOKS;
	}

	private void addTestBooks() {
		List<AuthorTo> one = new ArrayList<AuthorTo>();
		one.add(new AuthorTo(1L, "Wiliam", "Szekspir"));
		List<AuthorTo> two = new ArrayList<AuthorTo>();
		two.add(new AuthorTo(2L, "Hanna", "Ożogowska"));
		List<AuthorTo> three = new ArrayList<AuthorTo>();
		three.add(new AuthorTo(3L, "Jan", "Parandowski"));
		List<AuthorTo> four = new ArrayList<AuthorTo>();
		four.add(new AuthorTo(4L, "Edmund", "Niziurski"));
		List<AuthorTo> five = new ArrayList<AuthorTo>();
		five.add(new AuthorTo(5L, "Zbigniew", "Nienacki"));
		List<AuthorTo> six = new ArrayList<AuthorTo>();
		six.add(new AuthorTo(6L, "Aleksander", "Fredro"));

		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", one));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", two));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", three));
		ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", four));
		ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", five));
		ALL_BOOKS.add(new BookEntity(6L, "Zemsta", six));
	}
}
