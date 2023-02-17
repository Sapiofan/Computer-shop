package com.sapiofan.shop;

import com.sapiofan.shop.entities.Computer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Runner {

    private static final Scanner scanner = new Scanner(System.in);

    private static BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static final FileHandler fileHandler = new FileHandler();

    public static void main(String[] args) {
        System.out.println("\nWelcome to the computer stock.\n");
        while (true) {
            System.out.println("\nWhat would you like to do (input number)?\n" +
                    "1. See catalog of computers\n" +
                    "2. Find computers by name or brand\n" +
                    "3. Buy computer\n" +
                    "4. Quit program");
            byte choice = choice(4);
            switch (choice) {
                case 1:
                    showCatalog();
                    break;
                case 2:
                    findComputers();
                    break;
                case 3:
                    buyComputer();
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void showCatalog() {
        System.out.println("Choose category:\n" +
                "1. Laptops\n" +
                "2. Tablets\n" +
                "3. Return to previous step");
        byte choice = choice(3);
        switch (choice) {
            case 1 -> fileHandler.showCategory("Laptops");
            case 2 -> fileHandler.showCategory("Tablets");
        }
    }

    private static void findComputers() {
        List<Computer> computers = fileHandler.getAllComputers();
        while (true) {
            System.out.println("Input name or brand of computer");
            String name = null;
            try {
                name = reader.readLine();
            } catch (IOException e) {

            }
            List<Computer> foundList = new ArrayList<>();
            for (Computer computer : computers) {
                if(computer.getName().toLowerCase().contains(name.toLowerCase())
                        || computer.getBrand().toLowerCase().contains(name.toLowerCase())) {
                    foundList.add(computer);
                }
            }
            for (Computer computer : foundList) {
                System.out.println(computer);
            }

            System.out.println("Would you like to see details of one of them or search other computers?\n" +
                    "1. See details\n" +
                    "2. Continue searching\n" +
                    "3. Return to the beginning");

            byte choice = choice(3);
            switch (choice) {
                case 1 : {
                    System.out.println("Choose id of computer you would like to see?");
                    long id = -1;
                    Computer comp = null;
                    boolean flag = true;
                    while (id == -1) {
                        while (flag) {
                            try {
                                id = scanner.nextLong();
                                flag = false;
                            } catch (NumberFormatException e) {
                                System.out.println("Please, input number");
                            }
                        }
                        long finalId = id;
                        if(computers.stream().anyMatch(computer -> computer.getId() == finalId)) {
                            comp = fileHandler.getDetailsOfComputer(id);
                            break;
                        } else {
                            id = -1;
                            System.out.println("Sorry, there is no such id of product. Try again, please");
                        }
                        flag = true;
                    }
                    System.out.println(comp);
                    return;
                }
                case 2 : continue;
                case 3 : return;
            }
        }
    }

    private static void buyComputer() {
        List<Computer> computers = fileHandler.getAllComputers();
        while (true) {
            System.out.println("Choose id of product you would like to buy or '-1' to return to the start");
            long id = -2;
            Computer comp = null;
            boolean flag = true;
            while (id == -2) {
                while (flag) {
                    try {
                        id = Long.parseLong(reader.readLine());
                        if(id == -1) {
                            return;
                        }
                        flag = false;
                    } catch (NumberFormatException e) {
                        System.out.println("Please, input number");
                    } catch (IOException e) {
                        System.err.println("Something went wrong while reading from console: " + e);
                    }
                }
                long finalId = id;
                if (computers.stream().anyMatch(computer -> computer.getId() == finalId)) {
                    comp = fileHandler.getDetailsOfComputer(id);
                    break;
                } else {
                    id = -2;
                    System.out.println("Sorry, there is no such id of product. Try again, please");
                }
                flag = true;
            }
            System.out.println("Do you exactly this product would like to buy?\n" +
                    "1. Yes\n" +
                    "2. No");
            System.out.println(comp);
            byte choice = choice(2);
            switch (choice) {
                case 1 -> {
                    while (true) {
                        System.out.print("Your phone: ");
                        String phone = scanner.nextLine();
                        System.out.print("Your address: ");
                        String address = scanner.nextLine();
                        System.out.print("Your card (xxxx-xxxx-xxxx-xxxx): ");
                        String card = scanner.nextLine();
                        System.out.print("Date of card: ");
                        String date = scanner.nextLine();
                        System.out.print("CVV of card: ");
                        String cvv = scanner.nextLine();
                        String error = allDataAreCorrect(phone, address, card, date, cvv);
                        if (allDataAreCorrect(phone, address, card, date, cvv).isEmpty()) {
                            fileHandler.addNote(phone, address, id);
                            System.out.println("Transaction was successful. Wait for your computer");
                            return;
                        } else {
                            System.out.println(error);
                            System.out.println("Would you like to quit or try again?\n" +
                                    "1. Try\n" +
                                    "2. Quit");
                            byte quit = choice(2);
                            if (quit == 2) {
                                return;
                            }
                        }
                    }
                }
                case 2 -> {
                    System.out.println("Choose another product.");
                }
            }
        }
    }

    private static String allDataAreCorrect(String phone, String address, String card, String date, String cvv) {
        if((phone == null || phone.isEmpty()) || (address == null || address.isEmpty())) {
            return "Sorry. Phone and address can't be empty. Please fill in properly";
        }

        if((card == null || card.isEmpty()) || (date == null || date.isEmpty()) || (cvv == null || cvv.isEmpty())) {
            return "Sorry. Card, date and cvv can't be empty. Please fill similar to example:\n" +
                    "card - xxxx-xxxx-xxxx-xxxx\n" +
                    "date - mm/yyyy\n" +
                    "cvv - xxx";
        }

        boolean flag = false;
        for (char c : card.toCharArray()) {
            if(!Character.isDigit(c) && c != '-') {
                flag = true;
                break;
            }
        }

        for (String s : card.split("-")) {
            if(s.length() != 4) {
                flag = true;
                break;
            }
        }

        if(card.trim().length() != 19 || flag) {
            return "Sorry, your card number is not correct";
        }

        if(date.trim().length() != 7 || date.trim().charAt(2) != '/') {
            return "Sorry, date in your card is not correct";
        }

        try {
            int value = Integer.parseInt(cvv);
            if(value < 100 || value > 999) {
                return "Sorry, you cvv number isn't correct.";
            }
        } catch (NumberFormatException e) {
            return "Incorrect cvv. Please, input number from 100 to 999";
        }

        return "";
    }

    private static byte choice(int high) {
        byte choice;
        while (true) {
            try {
                choice = Byte.parseByte(reader.readLine());
                if(choice > 0 && choice <= high) {
                    return choice;
                }
            } catch (NumberFormatException e) {}
            catch (IOException e) {
                System.err.println("Something went wrong while reading from console: " + e);
            }
            System.out.println("Please, input number from " + 1 +" to " + high);
        }
    }
}
