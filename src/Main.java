import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Appointment> appointments = new ArrayList<>();
    private static int appointmentIdCounter = 1; // Counter to assign unique IDs to appointments

    public static void main(String[] args) {
        // Initialize dermatologists
        Dermatologist drRamani = new Dermatologist("Dr. Ramani");
        Dermatologist drSrilal = new Dermatologist("Dr. Srilal");

        System.out.println("Welcome to Aurora Skin Care Clinic");

        boolean continueRunning = true;
        while (continueRunning) {
            // Display menu
            System.out.println("\n1. Make Appointment");
            System.out.println("2. Update Appointment");
            System.out.println("3. View Appointments by Date");
            System.out.println("4. Search Appointment");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    makeAppointment(drRamani, drSrilal);
                    break;
                case 2:
                    updateAppointment();
                    break;
                case 3:
                    viewAppointmentsByDate();
                    break;
                case 4:
                    searchAppointment();
                    break;
                case 5:
                    continueRunning = false;
                    System.out.println("Thank you for visiting Aurora Skin Care Clinic. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void makeAppointment(Dermatologist drRamani, Dermatologist drSrilal) {
        System.out.println("\nEnter Patient Details:");
        System.out.print("NIC: ");
        String nic = scanner.next();
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Email: " );
        String email = scanner.next();
        
        System.out.print("Contact Number: ");
        String contactNumber = scanner.next();

        // Create the patient object
        Patient patient = new Patient(nic, name, email, contactNumber);

        // Select Dermatologist
        System.out.println("\nSelect Dermatologist:");
        System.out.println("1. Dr. Ramani");
        System.out.println("2. Dr. Srilal");
        int doctorChoice = scanner.nextInt();
        Dermatologist selectedDermatologist = (doctorChoice == 1) ? drRamani : drSrilal;

        // Select Day
      // System.out.println("\nSelect a day for the appointment (Monday, Wednesday, Friday, Saturday):");
      // scanner.nextLine(); // consume newline
      // String selectedDay = scanner.nextLine();
      
      System.out.println("\nSelect a day for the appointment:");
        System.out.println("1. Monday");
        System.out.println("2. Wednesday");
        System.out.println("3. Friday");
        System.out.println("4. Saturday");
        int dayChoice = scanner.nextInt();
        String selectedDay = "";
        switch (dayChoice) {
            case 1: selectedDay = "Monday"; break;
            case 2: selectedDay = "Wednesday"; break;
            case 3: selectedDay = "Friday"; break;
            case 4: selectedDay = "Saturday"; break;
            default: 
                System.out.println("Invalid choice. Please select a valid day.");
                return;
        }  


        boolean validSlotSelected = false;
        String selectedTimeSlot = null;

        // Loop until a valid slot is selected
        while (!validSlotSelected) {
            // Get available time slots for the selected day
            ArrayList<String> availableTimeSlots = selectedDermatologist.getAvailableTimeSlots(selectedDay);

            if (availableTimeSlots == null || availableTimeSlots.isEmpty()) {
                System.out.println("No available slots for the selected day.");
                return;
            }

            // Display available time slots
            System.out.println("\nAvailable time slots:");
            for (int i = 0; i < availableTimeSlots.size(); i++) {
                System.out.println((i + 1) + ". " + availableTimeSlots.get(i));
            }

            // Select a time slot
            System.out.println("Select a time slot by number:");
            int timeSlotChoice = scanner.nextInt();
            if (timeSlotChoice < 1 || timeSlotChoice > availableTimeSlots.size()) {
                System.out.println("Invalid choice. Please select a valid time slot.");
                continue;
            }

            selectedTimeSlot = availableTimeSlots.get(timeSlotChoice - 1);

            // Check if the selected time slot is already booked
            if (selectedTimeSlot.contains("(already booked)")) {
                System.out.println("Sorry, you selected a time that is already booked. Please select another time slot.");
            } else {
                validSlotSelected = true;
            }
        }

        // Book the time slot
        boolean bookingSuccess = selectedDermatologist.bookTimeSlot(selectedDay, selectedTimeSlot);
        if (!bookingSuccess) {
            System.out.println("The time slot was already booked. Please try again.");
        } else {
            // Select Treatment
            System.out.println("\nSelect Treatment:");
            System.out.println("1. Acne Treatment (LKR 2750)");
            System.out.println("2. Skin Whitening (LKR 7650)");
            System.out.println("3. Mole Removal (LKR 3850)");
            System.out.println("4. Laser Treatment (LKR 12500)");

            int treatmentChoice = scanner.nextInt();
            Treatment treatment = null;

            switch (treatmentChoice) {
                case 1:
                    treatment = new Treatment("Acne Treatment", 2750.00);
                    break;
                case 2:
                    treatment = new Treatment("Skin Whitening", 7650.00);
                    break;
                case 3:
                    treatment = new Treatment("Mole Removal", 3850.00);
                    break;
                case 4:
                    treatment = new Treatment("Laser Treatment", 12500.00);
                    break;
                default:
                    System.out.println("Invalid treatment selected.");
                    return;
            }

            // Present payment options
            System.out.println("\nYou have selected " + treatment.getName() + " for LKR " + treatment.getPrice());
            System.out.println("To confirm your appointment, please select an option:");
            System.out.println("1. Pay LKR 500 registration fee to confirm the appointment.");
            System.out.println("2. Exit to the main menu.");

            int paymentChoice = scanner.nextInt();

            if (paymentChoice == 1) {
                // Process the registration fee payment
                System.out.println("Thank you! Your appointment has been successfully confirmed.");
                Appointment appointment = new Appointment(patient, selectedDermatologist, selectedDay, selectedTimeSlot, treatment, appointmentIdCounter++);
                appointments.add(appointment);

                // Generate Payment and Invoice
                Payment payment = new Payment(appointment);
                payment.generateInvoice();
            } else {
                System.out.println("Exiting to main menu.");
            }
        }
    }

    public static void updateAppointment() {
        System.out.print("Enter appointment ID to update: ");
        int appointmentId = scanner.nextInt();
        
        if (appointmentId < 1 || appointmentId > appointments.size()) {
            System.out.println("Invalid appointment ID.");
            return;
        }
        
        Appointment appointment = appointments.get(appointmentId - 1);
        System.out.println("Updating Appointment for: " + appointment.getPatient().getName());
        
        // Update Patient Details
        System.out.print("Update NIC (or press enter to skip):  ");
        String newNic = scanner.nextLine();
        if (!newNic.isEmpty()) {
            appointment.getPatient().setNic(newNic);
        }
                
        
        System.out.print("Update Name (or press enter to skip): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            appointment.getPatient().setName(newName);
        }

        System.out.print("Update Email (or press enter to skip): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            appointment.getPatient().setEmail(newEmail);
        }

        System.out.print("Update Contact Number (or press enter to skip): ");
        String newContact = scanner.nextLine();
        if (!newContact.isEmpty()) {
            appointment.getPatient().setContactNumber(newContact);
        }

        System.out.println("Appointment updated successfully!");
    }

    public static void viewAppointmentsByDate() {
        System.out.print("Enter date to view appointments (e.g., Monday): ");
        String date = scanner.next();
        boolean found = false;

        System.out.println("\nAppointments on " + date + ":");
        for (Appointment appointment : appointments) {
            if (appointment.getDay().equalsIgnoreCase(date)) {
                System.out.println("Appointment ID: " + (appointments.indexOf(appointment) + 1));
                System.out.println("Patient: " + appointment.getPatient().getName());
                System.out.println("Time Slot: " + appointment.getTimeSlot());
                System.out.println("Treatment: " + appointment.getTreatment().getName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No appointments found for " + date);
        }
    }

    public static void searchAppointment() {
        System.out.print("Enter Patient Name or Appointment ID to search: ");
        String searchQuery = scanner.next();
        boolean found = false;

        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getName().equalsIgnoreCase(searchQuery) || 
                String.valueOf(appointments.indexOf(appointment) + 1).equals(searchQuery)) {
                System.out.println("Appointment ID: " + (appointments.indexOf(appointment) + 1));
                System.out.println("Patient: " + appointment.getPatient().getName());
                System.out.println("Dermatologist: " + appointment.getDermatologist().getName());
                System.out.println("Day: " + appointment.getDay());
                System.out.println("Time Slot: " + appointment.getTimeSlot());
                System.out.println("Treatment: " + appointment.getTreatment().getName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No appointments found for the search query: " + searchQuery);
        }
    }
}
