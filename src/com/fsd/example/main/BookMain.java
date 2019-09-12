package com.fsd.example.main;

import java.util.Scanner;

import com.fsd.example.helper.BookHelper;
import com.fsd.example.helper.SessionFactoryHelper;
import com.fsd.example.helper.SubjectHelper;

public class BookMain {

	public static void main(String[] args) throws Exception {
		SessionFactoryHelper.buildSessionFactory();
		execute();		
	}

	private static void execute() {
		Scanner input = null;
		int menuChoice = 0;
		try {
			do {
				input = new Scanner(System.in);
				System.out.println(
						"\n***********************" + "\n**********MENU*********" + "\n***********************");
				System.out.println("\n1. Add a Subject" + "\n2. Add a Book" + "\n3. Delete a Subject"
						+ "\n4. Delete a book" + "\n5. Search for a book" + "\n6. Search for a subject"
						+ "\n7. Sort Book By Title" + "\n8. Sort Books by publish Date" + "\n9. Exit"
						+ "\n\nPlease enter your selection and then press enter : ");

				menuChoice = input.nextInt();
				switch (menuChoice) {
				case 1:
					SubjectHelper.addSubject();
					System.out.println("Press enter to continue");
					break;
				case 2:
					BookHelper.addBook();
					System.out.println("Press enter to continue");
					break;
				case 3:
					SubjectHelper.deleteSubject();
					System.out.println("Press enter to continue");
					break;
				case 4:
					BookHelper.deleteBook();
					System.out.println("Press enter to continue");
					break;
				case 5:
					BookHelper.searchByBook();
					System.out.println("Press enter to continue");
					break;
				case 6:
					SubjectHelper.searchBySubject();
					System.out.println("Press enter to continue");
					break;
				case 7:
					BookHelper.sortBookByTitle();
					System.out.println("Press enter to continue");
					break;
				case 8:
					BookHelper.sortBookByPublishDate();
					System.out.println("Press enter to continue");
					break;
				default:
					break;
				}
			} while (menuChoice != 9);
		} catch (Exception e) {
			System.out.println("\nInvalid input " + e.getMessage());
			execute();
		}
		//input.close();
		System.out.println("Exiting Application");
		System.exit(0);
	}

}
