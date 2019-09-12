package com.fsd.example.helper;

import com.fsd.example.dao.BookDao;
import com.fsd.example.dao.SubjectDao;
import com.fsd.example.entity.Book;
import com.fsd.example.entity.Subject;

import java.io.IOException;
import java.util.*;

public class SubjectHelper {
    public static void addSubject() throws Exception {
        List<Book> bookList = BookDao.getAllBooks();
        if (bookList.isEmpty()) {
            System.out.println("There are no books to add to subject. Please add at least one book first, then try again");
            return;
        }
        System.out.println("\n*******************************"
                + "\n**********ADD A SUBJECT********"
                + "\n*******************************");
        Scanner input = new Scanner(System.in);
        System.out.println("\nEnter a Subject Title :");
        String inputSubTitle = input.nextLine();
        int durTime = enterDurationInHours();
        List<Book> bookSet = selectBooks(bookList);

        Subject newSubject = new Subject();
        newSubject.setSubtitle(inputSubTitle);
        newSubject.setDurationInHours(durTime);
        newSubject.setReferences(bookSet);
        boolean status = SubjectDao.addSubject(newSubject);
        System.out.println("\nSubject Added "+status+" Id="+newSubject.getSubjectId());
    }

    private static List<Book> selectBooks(List<Book> bookList) throws IOException, ClassNotFoundException {
        List<Book> bookSet = new ArrayList<>();
        if (!bookList.isEmpty()) {
            bookSet = enterBookIds(bookList);
        }
        return bookSet;
    }

    private static List<Book> enterBookIds(List<Book> bookList) {
        List<Book> bookSet = new ArrayList<>();
        try {
            System.out.println("Select books from the following books to add to the subject");
            System.out.println(bookList);
            Scanner input = new Scanner(System.in);
            System.out.println("Enter book ids as comma separated values : ");
            String bookIds = input.nextLine();
            if (bookIds != null && bookIds != "") {
                List<String> bookIdList = Arrays.asList(bookIds.split(","));
                for (Book book : bookList) {
                    for (String bookId : bookIdList) {
                        if (book.getBookId().equals(Long.valueOf(bookId))) {
                            bookSet.add(book);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("\nInvalid input " + e.getMessage());
            enterBookIds(bookList);
        }
        return bookSet;
    }

    private static int enterDurationInHours() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nEnter duration in Hours :");
        int durTime = 0;
        try {
            durTime = input.nextInt();
        } catch (Exception e) {
            System.out.println("\nInvalid input " + e.getMessage());
            enterDurationInHours();
        }
        return durTime;
    }

    public static void deleteSubject() throws Exception {
        int SubCount = SubjectDao.getAllSubjectCount();
        if (SubCount==0) {
            System.out.println("There are no subjects in the system");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("\nEnter subject by which you want to delete : ");
        String subtitle = input.nextLine();
        int count = SubjectDao.deleteSubject(subtitle);
        System.out.println("Number of records deleted : "+count);
    }


    public static void searchBySubject() throws Exception {
        int SubCount = SubjectDao.getAllSubjectCount();
        List<Book> bookDetailsList  = new ArrayList<Book>();
        if (SubCount==0) {
            System.out.println("There are no subjects in the system");
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("\nEnter subject by which you want to search : ");
        String subtitle = input.nextLine();
        bookDetailsList=SubjectDao.searchForSubjects(subtitle);
        if(bookDetailsList.isEmpty()){
            System.out.println("no books found for your search : "+subtitle);
        }else{
            System.out.println("Matching Books :\n"+bookDetailsList);
        }
    }
}
