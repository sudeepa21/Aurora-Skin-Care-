public class Payment {
    private Appointment appointment;
    private static final double REGISTRATION_FEE = 500.00;

    public Payment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void generateInvoice() {
        double price = appointment.getTotalFee();
        double tax = price * 0.025; // Calculate 2.5% tax
        double totalPrice = Math.round((price + tax) * 100.0) / 100.0; // Round to two decimal places

        // Display invoice details
        System.out.println("\n--- Invoice ---");
        System.out.println("Appointment ID: " +appointment.getAppointmentId());
        System.out.println("Date: " + appointment.getDay());
        System.out.println("Time: " + appointment.getTimeSlot());
        System.out.println("Patient Name: " + appointment.getPatient().getName());
        System.out.println("Dermatologist: " + appointment.getDermatologist().getName());
        System.out.println("Treatment: " + appointment.getTreatment().getName());
        System.out.println("Price: LKR " + price);
        System.out.println("Tax (2.5%): LKR " + tax);
        System.out.println("Amount Charged: LKR " + REGISTRATION_FEE + " (Registration Fee)");
        System.out.println("Total Price: LKR " + totalPrice);
    }
}
