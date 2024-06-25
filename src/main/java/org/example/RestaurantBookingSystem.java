package org.example;
import java.util.Scanner;

public class RestaurantBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        String[] tableTypes = {"Standard", "Window", "VIP"};
        int[] tableCapacities = {4, 2, 6};
        double[] hourlyRates = {20.0, 30.0, 50.0};
        int[] availableTables = {5, 3, 2};
        String[] bookedTableTypes = new String[20];
        int[] bookingHours = new int[20];
        double[] bookingCosts = new double[20];
        String[] customerNames = new String[20];
        int totalBookings = 0;

        while (running) {
            System.out.println("\nRestaurant Booking System");
            System.out.println("1. Make a booking");
            System.out.println("2. Cancel a booking");
            System.out.println("3. View available tables");
            System.out.println("4. View booking history");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.println("\nAvailable table types:");
                for (int i = 0; i < tableTypes.length; i++) {
                    if (availableTables[i] > 0) {
                        System.out.println((i + 1) + ". " + tableTypes[i] + " - Capacity: " + tableCapacities[i] + " - $" + hourlyRates[i] + " per hour");
                    }
                }

                System.out.print("Enter the number of the table type you want to book: ");
                int tableChoice = scanner.nextInt() - 1;
                scanner.nextLine(); // Consume newline

                if (tableChoice >= 0 && tableChoice < tableTypes.length) {
                    if (availableTables[tableChoice] > 0) {
                        System.out.print("Enter the number of hours for booking: ");
                        int hours = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (hours > 0) {
                            double totalCost = hourlyRates[tableChoice] * hours;
                            System.out.println("Total cost: $" + totalCost);
                            System.out.print("Enter your name: ");
                            String name = scanner.nextLine();
                            System.out.print("Confirm booking (y/n): ");
                            String confirm = scanner.nextLine();

                            if (confirm.equalsIgnoreCase("y")) {
                                availableTables[tableChoice]--;
                                bookedTableTypes[totalBookings] = tableTypes[tableChoice];
                                bookingHours[totalBookings] = hours;
                                bookingCosts[totalBookings] = totalCost;
                                customerNames[totalBookings] = name;
                                totalBookings++;
                                System.out.println("Table booked successfully!");
                            } else {
                                System.out.println("Booking cancelled.");
                            }
                        } else {
                            System.out.println("Invalid number of hours.");
                        }
                    } else {
                        System.out.println("Sorry, no " + tableTypes[tableChoice] + " tables available.");
                    }
                } else {
                    System.out.println("Invalid table type selection.");
                }
            } else if (choice == 2) {
                if (totalBookings > 0) {
                    System.out.println("\nCurrent bookings:");
                    for (int i = 0; i < totalBookings; i++) {
                        System.out.println((i + 1) + ". " + customerNames[i] + " - " + bookedTableTypes[i] + " - " + bookingHours[i] + " hours");
                    }
                    System.out.print("Enter the number of the booking to cancel: ");
                    int cancelChoice = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline
                    if (cancelChoice >= 0 && cancelChoice < totalBookings) {
                        String cancelledTable = bookedTableTypes[cancelChoice];
                        for (int i = 0; i < tableTypes.length; i++) {
                            if (tableTypes[i].equals(cancelledTable)) {
                                availableTables[i]++;
                                break;
                            }
                        }
                        System.out.println("Booking cancelled successfully!");
                        for (int i = cancelChoice; i < totalBookings - 1; i++) {
                            bookedTableTypes[i] = bookedTableTypes[i + 1];
                            bookingHours[i] = bookingHours[i + 1];
                            bookingCosts[i] = bookingCosts[i + 1];
                            customerNames[i] = customerNames[i + 1];
                        }
                        totalBookings--;
                    } else {
                        System.out.println("Invalid selection.");
                    }
                } else {
                    System.out.println("No active bookings to cancel.");
                }
            } else if (choice == 3) {
                System.out.println("\nAvailable tables:");
                for (int i = 0; i < tableTypes.length; i++) {
                    System.out.println(tableTypes[i] + ": " + availableTables[i] + " available");
                }
            } else if (choice == 4) {
                if (totalBookings > 0) {
                    System.out.println("\nBooking history:");
                    for (int i = 0; i < totalBookings; i++) {
                        System.out.println((i + 1) + ". " + customerNames[i] + " - " + bookedTableTypes[i] + " - " + bookingHours[i] + " hours - $" + bookingCosts[i]);
                    }
                } else {
                    System.out.println("No booking history available.");
                }
            } else if (choice == 5) {
                running = false;
                System.out.println("Thank you for using our Restaurant Booking System. Goodbye!");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}