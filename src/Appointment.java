public class Appointment {
    private Patient patient;
    private Dermatologist dermatologist;
    private String day;
    private String timeSlot;
    private Treatment treatment;
    private int appointmentId; // New attribute

    public Appointment(Patient patient, Dermatologist dermatologist, String day, String timeSlot, Treatment treatment, int appointmentId) {
        this.patient = patient;
        this.dermatologist = dermatologist;
        this.day = day;
        this.timeSlot = timeSlot;
        this.treatment = treatment;
        this.appointmentId = appointmentId; // Set appointment ID
    }

    // Getter methods
    public Patient getPatient() { return patient; }
    public Dermatologist getDermatologist() { return dermatologist; }
    public String getDay() { return day; }
    public String getTimeSlot() { return timeSlot; }
    public Treatment getTreatment() { return treatment; }
    public int getAppointmentId() { return appointmentId; } // New getter for appointment ID

    public double getTotalFee() {
        return treatment.getPrice();
    }
}
