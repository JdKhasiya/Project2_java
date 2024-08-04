import java.util.Scanner;

public class MovieTicketBooking {
    private static final int ROWS = 5;
    private static final int COLUMNS = 10;
    private static final char AVAILABLE = 'O';
    private static final char BOOKED = 'X';

    private static String[] movies = {"Movie A", "Movie B", "Movie C"};
    private static String[] times = {"12:00 PM", "03:00 PM", "06:00 PM"};
    private static double[] prices = {10.00, 12.50, 15.00};
    private static char[][][] seats = new char[movies.length][ROWS][COLUMNS];

    public static void main(String[] args) {
        initializeSeats();
        Scanner scanner = new Scanner(System.in);
        boolean continueBooking = true;

        while (continueBooking) {
            displayMovies();
            System.out.print("Select a movie (1-3): ");
            int movieIndex = scanner.nextInt() - 1;

            if (movieIndex >= 0 && movieIndex < movies.length) {
                displaySeats(movieIndex);
                System.out.print("Enter row letter (A-E): ");
                char rowChar = scanner.next().toUpperCase().charAt(0);
                int row = rowChar - 'A';
                System.out.print("Enter column number (1-10): ");
                int column = scanner.nextInt() - 1;

                if (row >= 0 && row < ROWS && column >= 0 && column < COLUMNS && seats[movieIndex][row][column] == AVAILABLE) {
                    seats[movieIndex][row][column] = BOOKED;
                    System.out.println("Seat booked successfully!");
                    System.out.printf("You booked a ticket for %s at %s. Price: $%.2f%n", movies[movieIndex], times[movieIndex], prices[movieIndex]);
                } else {
                    System.out.println("Invalid seat selection or seat already booked. Please try again.");
                }
            } else {
                System.out.println("Invalid movie selection. Please try again.");
            }

            System.out.print("Do you want to book another seat? (yes/no): ");
            String response = scanner.next().toLowerCase();
            continueBooking = response.equals("yes");
        }

        System.out.println("Thank you for using the Movie Ticket Booking System!");
        scanner.close();
    }

    private static void initializeSeats() {
        for (int m = 0; m < movies.length; m++) {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    seats[m][i][j] = AVAILABLE;
                }
            }
        }
    }

    private static void displayMovies() {
        System.out.println("Available Movies:");
        for (int i = 0; i < movies.length; i++) {
            System.out.printf("%d. %s at %s - $%.2f%n", i + 1, movies[i], times[i], prices[i]);
        }
    }

    private static void displaySeats(int movieIndex) {
        System.out.println("Seating Arrangement for " + movies[movieIndex] + " at " + times[movieIndex] + ":");

        // Print column numbers
        System.out.print("   ");
        for (int c = 1; c <= COLUMNS; c++) {
            System.out.printf("%2d ", c);
        }
        System.out.println();

        // Print seat layout with row labels
        for (int i = 0; i < ROWS; i++) {
            System.out.printf("%c  ", 'A' + i);
            for (int j = 0; j < COLUMNS; j++) {
                System.out.printf("%2c ", seats[movieIndex][i][j]);
            }
            System.out.println();
        }
    }
}
