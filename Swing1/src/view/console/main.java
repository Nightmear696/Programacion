/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.console;
import model.validations.UserDataValidations;
import java.util.Scanner;
import static model.validations.UserDataValidations.checkPostalCode;

/**
 *
 * @author martilopbel
 */
public class main {
      static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String option;

        do {

            System.out.println("MENU: ");
            System.out.println("1. - Tester checkId");
            System.out.println("2. - Tester checkFormatDate");
            System.out.println("3. - Tester calculateAge");
            System.out.println("4. - Tester checkPostalCode");
            System.out.println("5. - Tester isNumeric");
            System.out.println("6. - Tester isAlphabetic");
            System.out.println("7. - Tester checkEmail");
            System.out.println("8. - Tester checkName");
            System.out.println("Z. - Salir");
            System.out.print("Option: ");
            option = sc.next().toUpperCase();

            switch (option) {
                case "1":
                    testCheckId();
                    break;
                case "2":
                    testCheckFormatDate();
                    break;
                case "3":
                    testCalculateAge();
                    break;
                case "4":
                    testCheckPostalCode();
                    break;
                case "5":
                    testIsNumeric();
                    break;
                case "6":
                    testIsAlphabetic();
                    break;
                case "7":
                    testCheckEmail();
                    break;
                case "8":
                    testCheckName();
                    break;
                case "Z":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion incorrecta, por favor intente de nuevo.");
            }
        } while (!option.equals("Z"));
    }

    public static void testCheckId() {
        System.out.println("NIF Validator");
        System.out.print("Enter your ID: ");
        String id = sc.next();

        boolean idOK = UserDataValidations.checkID(1, id);
        if (idOK) {
            System.out.println("Correct ID");
        } else {
            System.out.println("Incorrect ID");
        }
    }

    public static void testCheckFormatDate() {
        System.out.println("DATE Validator");
        System.out.print("Enter a date (dd/mm/yyyy): ");
        String date = sc.next();
        boolean dateOK = UserDataValidations.checkFormatDate(date);
        if (dateOK) {
            System.out.println("Correct date ");
        } else {
            System.out.println("Incorrect date ");
        }
    }

    public static void testCalculateAge() {
        System.out.println("Age Validator");
        System.out.print("Enter your birth date(dd/mm/yyyy): ");
        String birthDate = sc.next();

        int age = UserDataValidations.checkCalculateDate(birthDate);

        if (age == -1) {
            System.out.println("Incorrect date, please enter your birth date.");
        } else {
            System.out.println("Your age is: " + age);
        }
    }

    public static void testCheckPostalCode() {
        System.out.println("Postal Code Validator");
        System.out.print("Enter your postal code: ");
        String zip = sc.next();

        boolean postalCodeValid = checkPostalCode(zip);

        if (postalCodeValid) {
            System.out.println("Correct postal code.");
        } else {
            System.out.println("Incorrect postal code.");
        }
    }

    static void testIsNumeric() {

        System.out.println("Enter a numeric String");
        String str = sc.next();
        boolean numericoOk = UserDataValidations.isNumeric(str);
        if (numericoOk) {
            System.out.println("Correct format.");

        } else {
            System.out.println("Incorrect format.");
        }

    }

    static void testIsAlphabetic() {

        System.out.println("Enter a String");
        String str = sc.next();
        boolean numericoOk = UserDataValidations.isAlphabetic(str);
        if (numericoOk) {
            System.out.println("Correct format.");

        } else {
            System.out.println("Incorrect format.");
        }

    }

    static void testCheckEmail() {

        System.out.println("Enter a valid email");
        String email = sc.next();
        boolean emailSirve = UserDataValidations.checkEmail(email);
        if (!emailSirve) {
            System.out.println("Invalid Email");
        } else {
            System.out.println("Valid Email");
        }

    }

    static void testCheckName() {

        System.out.println("Enter your name to see if it is valid: ");
        String name = sc.next();

        boolean nameValid = UserDataValidations.checkName(name);
        if (!nameValid) {
            System.out.println("Invalid Name");
        } else {
            System.out.println("Valid Name");
        }

    }

}
