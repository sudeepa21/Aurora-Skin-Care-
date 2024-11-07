import java.util.ArrayList;
import java.util.HashMap;

public class Dermatologist {
    private String name;
    private HashMap<String, ArrayList<String>> availableSlots;

    public Dermatologist(String name) {
        this.name = name;
        this.availableSlots = new HashMap<>();
        initializeAvailableSlots();
    }

    private void initializeAvailableSlots() {
        availableSlots.put("Monday", new ArrayList<>());
        availableSlots.put("Wednesday", new ArrayList<>());
        availableSlots.put("Friday", new ArrayList<>());
        availableSlots.put("Saturday", new ArrayList<>());

        for (int i = 0; i < 12; i++) {
            int hour = 10 + i / 4;
            int minute = (i % 4) * 15;
            if (hour < 13) {
                availableSlots.get("Monday").add(hour + ":" + String.format("%02d", minute));
                availableSlots.get("Wednesday").add((hour + 2) + ":" + String.format("%02d", minute));
            }
            if (hour >= 4) {
                availableSlots.get("Friday").add(hour + ":" + String.format("%02d", minute));
            }
            if (hour == 9) {
                availableSlots.get("Saturday").add(hour + ":" + String.format("%02d", minute));
            }
        }
    }

    public String getName() { return name; }

    public ArrayList<String> getAvailableTimeSlots(String day) {
        ArrayList<String> slots = availableSlots.get(day);
        ArrayList<String> availableSlotsList = new ArrayList<>();
        if (slots != null) {
            for (String slot : slots) {
                if (!slot.contains("(already booked)")) {
                    availableSlotsList.add(slot);
                } else {
                    availableSlotsList.add(slot);
                }
            }
        }
        return availableSlotsList;
    }

    public boolean bookTimeSlot(String day, String timeSlot) {
        ArrayList<String> slots = availableSlots.get(day);
        if (slots != null && slots.contains(timeSlot)) {
            slots.remove(timeSlot);
            slots.add(timeSlot + " (already booked)");
            return true;
        }
        return false;
    }
}
