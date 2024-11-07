public class Patient {
    private String nic;
    private String name;
    private String email;
    private String contactNumber;

    public Patient(String nic, String name, String email, String contactNumber) {
        this.nic = nic;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    // Getter and Setter methods
    public String getNic() { return nic; }
    public void setNic(String nic) { this.nic = nic; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
}
