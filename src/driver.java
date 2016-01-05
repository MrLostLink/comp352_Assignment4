import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class driver {

	public static void main(String[] args) throws IOException {

		Scanner key = new Scanner(System.in);

		System.out.println("HashTable Creator v1");
		System.out.println("(Assume perfect user)");
		System.out.println("--------------------");
		HashTable test1 = new HashTable();

		boolean run = true;

		while (run) {
			System.out.println("Press 1 to enter a new string to table \n"
					+ "Press 2 to read strings from preset text file \n" + "Press 3 to move on to next menu \n"
					+ "Press 4 to print keys and table \n" + "Press 5 to remove \n" + "Press 6 to search \n"
					+ "Press 7 to pick x number of lines to read(Part C of assignment) \n"
					+ "Press 8 to remove 10,000 lines(not working) \n"
					+"Press 9 to exit program ");

			switch (key.nextInt()) {

			case 1:
				do {
					System.out.println("Enter new string: \n" + "(type done when finished inputing strings)");
					String str = key.next();
					if (str.equals("done"))
						break;
					test1.put(str, str);

				} while (true);

				break;

			case 2:
				System.out.println("Please enter the text file you wish to read(enter x.txt):");
				String text = key.next();

				File file = new File(text);

				Scanner scanner = new Scanner(file);
				while (scanner.hasNext()) {

					String str = scanner.next();
					test1.put(str, str);

				}
				System.out.println("----------------------");
				scanner.close();

				break;

			case 3:

				test1.printHashtableStatistics();
				break;
			case 4:

				test1.keySet();

				test1.entrySet();
				break;

			case 5:
				System.out.println("Enter value to remove: ");
				System.out.println("(If taken back to menu value was not removed)");
				String str = key.next();

				test1.remove(str);

				break;

			case 6:
				System.out.println("Enter value you wish to find:");
				System.out.println("(If taken back to menu value was not found)");
				str = key.next();
				test1.getString(str);
				break;

			case 7:

				System.out.println("Please enter the text file you wish to read(enter x.txt):");
				String text2 = key.next();

				System.out.println("Print amount of lines to read from text: ");
				int b = key.nextInt();

				File file2 = new File(text2);

				Scanner scanner2 = new Scanner(file2);

				for (int i = 0; i < b; i++) {

					str = scanner2.next();
					test1.put(str, str);

				}

				System.out.println("----------------------");
				scanner2.close();

				break;
			case 8:
				test1.remove10k();
				System.out.println("10 000 strings removed");
				break;
			case 9:
				System.out.println("Smell ya later");
				run = false;
				break;

			}
		}

	}
	}	 