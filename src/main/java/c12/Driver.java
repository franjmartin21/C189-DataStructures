package c12;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver
{
    private static final Name  INPUT_ERROR = new Name("error", "error");
    private static final Name QUIT = new Name("quit", "quit");

    public static void main(String[] args)
    {
        TelephoneDirectory directory = new TelephoneDirectory();
        String fileName = "data.txt"; // or file name could be read

        try
        {
            Scanner data = new Scanner(new File(fileName));
            directory.readFile(data);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found: " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("I/O error " + e.getMessage());
        }

        Name nextName = getName(); // get name for search from user
        while (!nextName.equals(QUIT))
        {
            if (nextName.equals(INPUT_ERROR))
                System.out.println("Error in entering name. Try again.");
            else
            {
                String phoneNumber = directory.getPhoneNumber(nextName);

                if (phoneNumber == null)
                    System.out.println(nextName + " is not in the directory.");
                else
                    System.out.println("The phone number for " + nextName +
                            " is " + phoneNumber);
            } // end if

            nextName = getName();
        } // end while

        System.out.println("Bye!");
    } // end main

    // Returns either the name read from user, INPUT_ERROR, or QUIT
    private static Name getName()
    {
        Name result = null;
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter first name and last name, " +
                "or quit to end: ");
        String line = keyboard.nextLine();

        if (line.trim().toLowerCase().equals("quit"))
            result = QUIT;
        else
        {
            String firstName = null;
            String lastName = null;
            Scanner scan = new Scanner(line);

            if (scan.hasNext())
            {
                firstName = scan.next();

                if (scan.hasNext())
                    lastName = scan.next();
                else
                    result = INPUT_ERROR;
            }
            else
                result = INPUT_ERROR;

            if (result == null)
                result = new Name(firstName, lastName);
        } // end if

        return result;
    } // end getName
} // end Driver