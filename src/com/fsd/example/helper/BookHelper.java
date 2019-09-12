package com.fsd.example.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.fsd.example.dao.BookDao;
import com.fsd.example.entity.Book;

public class BookHelper {

	static Comparator<Book> byTitle = Comparator.comparing(Book::getTitle);
	static Comparator<Book> byPublishDate = Comparator.comparing(Book::getPublishDate);

	public static void searchByBook() throws Exception {
		int count = BookDao.getAllBooks().size();
		List<Book> bookDetailsList = new ArrayList<Book>();
		if (count == 0) {
			System.out.println("There are no books in the system");
			return;
		}
		Scanner input = new Scanner(System.in);
		System.out.println("\nEnter title by which you want to search : ");
		String bookTitle = input.nextLine();
		bookDetailsList = BookDao.searchForBooks(bookTitle);
		if (bookDetailsList.isEmpty()) {
			System.out.println("no books found for your search : " + bookTitle);
		} else {
			System.out.println("Matching Books :\n" + bookDetailsList);
		}
		// input.close();
	}

	public static void deleteBook() throws Exception {
		int count = BookDao.getAllBooks().size();
		if (count == 0) {
			System.out.println("There are no books in the system");
			return;
		}
		Scanner input = new Scanner(System.in);
		System.out.println("\nEnter title by which you want to delete : ");
		String bookTitle = input.nextLine();
		int rowsDeleted = BookDao.deleteBook(bookTitle);
		System.out.println("Number of records deleted : " + rowsDeleted);
	}

	public static void addBook() throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println(
				"\n****************************" + "\n**********ADD A BOOK********" + "\n****************************");
		System.out.println("\nEnter Book Title :");
		String bookTitle = input.nextLine();
		double price = enterBookPrice();
		int volume = enterBookVolume();
		Date publishDate = parsePublishDate();
		// Long bookId = BookDao.getNextBookId();
		// Book newBook = new Book(bookId,bookTitle,price,volume, publishDate);
		Book newBook = new Book();
		newBook.setTitle(bookTitle);
		newBook.setPrice(price);
		newBook.setVolume(volume);
		newBook.setPublishDate(publishDate);
		boolean status = BookDao.addBook(newBook);
		System.out.println("\nBook Added " + status + " Id=" + newBook.getBookId());
	}

	private static int enterBookVolume() {
		Scanner input = new Scanner(System.in);
		System.out.println("\nEnter volume of the book :");
		int inputPrice = 0;
		try {
			inputPrice = input.nextInt();
		} catch (Exception e) {
			System.out.println("\nInvalid input " + e.getMessage());
			enterBookVolume();
		}
		return inputPrice;
	}

	private static double enterBookPrice() {
		Scanner input = new Scanner(System.in);
		System.out.println("\nEnter price of the book :");
		double inputPrice = 0;
		try {
			inputPrice = input.nextDouble();
		} catch (Exception e) {
			System.out.println("\nInvalid input " + e.getMessage());
			enterBookPrice();
		}
		return inputPrice;
	}

	private static Date parsePublishDate() {
		Scanner input = new Scanner(System.in);
		Date publishDate = null;
		System.out.println("\nAdd published date in (mm/dd/yy) format : ");
		String inputDate = input.nextLine();
		try {
			publishDate = new SimpleDateFormat("mm/dd/yy").parse(inputDate);
		} catch (Exception e) {
			System.out.println("\nInvalid input format " + e.getMessage());
			parsePublishDate();
		}
		return publishDate;
	}

	public static void sortBookByPublishDate() throws Exception {
		List<Book> sortedBooksList = BookDao.getAllBooks().stream().sorted(byPublishDate).collect(Collectors.toList());
		System.out.println("Sorted By Published Books :\n" + sortedBooksList);
	}

	public static void sortBookByTitle() throws Exception {
		List<Book> sortedBooksList = BookDao.getAllBooks().stream().sorted(byTitle).collect(Collectors.toList());
		System.out.println("Sorted By Title Books :\n" + sortedBooksList);
	}
}
