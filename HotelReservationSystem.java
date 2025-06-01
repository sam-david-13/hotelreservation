import java.util.*;

class Room {
    int roomNumber;
    String category; // Single, Double, Suite
    boolean isBooked;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isBooked = false;
    }

    public String toString() {
        return "Room " + roomNumber + " [" + category + "] - " + (isBooked ? "Booked" : "Available");
    }
}

class Reservation {
    String customerName;
    Room room;
    double paymentAmount;

    public Reservation(String customerName, Room room, double paymentAmount) {
        this.customerName = customerName;
        this.room = room;
        this.paymentAmount = paymentAmount;
    }

    public void displayReservation() {
        System.out.println("Customer: " + customerName);
        System.out.println("Room: " + room.roomNumber + " (" + room.category + ")");
        System.out.println("Payment: $" + paymentAmount);
    }
}

public class HotelReservationSystem {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        initializeRooms();
        int choice;

        do {
            System.out.println("\n=== Hotel Reservation System ===");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // clear newline

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    System.out.println("Thank you for using the system!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 4);
    }

    static void initializeRooms() {
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Double"));
        rooms.add(new Room(103, "Suite"));
        rooms.add(new Room(104, "Single"));
        rooms.add(new Room(105, "Double"));
    }

    static void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println(room);
            }
        }
    }

    static void makeReservation() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter desired room category (Single/Double/Suite): ");
        String category = scanner.nextLine();

        Room availableRoom = null;
        for (Room room : rooms) {
            if (!room.isBooked && room.category.equalsIgnoreCase(category)) {
                availableRoom = room;
                break;
            }
        }

        if (availableRoom == null) {
            System.out.println("Sorry, no available rooms in that category.");
            return;
        }

        double price = calculatePrice(category);
        System.out.println("Room " + availableRoom.roomNumber + " is available. Price: $" + price);
        System.out.print("Proceed with payment? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            availableRoom.isBooked = true;
            Reservation reservation = new Reservation(name, availableRoom, price);
            reservations.add(reservation);
            System.out.println("Reservation successful!");
        } else {
            System.out.println("Reservation cancelled.");
        }
    }

    static void viewBookings() {
        if (reservations.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        for (Reservation r : reservations) {
            System.out.println("\n--- Booking ---");
            r.displayReservation();
        }
    }

    static double calculatePrice(String category) {
        switch (category.toLowerCase()) {
            case "single":
                return 100.0;
            case "double":
                return 150.0;
            case "suite":
                return 250.0;
            default:
                return 0.0;
        }
    }
}
