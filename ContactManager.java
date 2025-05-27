// Manages a list of contacts
// Akash Howlader (24-56432-1)
// Hrishov (23-55660-3)

public class ContactManager {

    private Contact[] contactsArray;
    private int contactCount;
    private static final int MAX_CONTACTS = 100;

    public ContactManager() {
        this.contactsArray = new Contact[MAX_CONTACTS];
        this.contactCount = 0;
    }


    public void addContact(String name, String phone, String email) {
        if (contactCount < MAX_CONTACTS) {
            Contact newContact = new Contact(name, phone, email);
            this.contactsArray[contactCount] = newContact;
            this.contactCount++;
        } else {
            System.err.println("Contact book is full! Cannot add '" + name + "'. Max capacity: " + MAX_CONTACTS);
        }
    }

    public void removeContact(int index) {
        if (index >= 0 && index < contactCount) {
            for (int i = index; i < contactCount - 1; i++) {
                contactsArray[i] = contactsArray[i + 1];
            }
            contactsArray[contactCount - 1] = null;
            contactCount--;
        } else {
            System.err.println("Error: Cannot remove contact. Invalid index: " + index);
        }
    }

    public Contact[] getAllContacts() {
        Contact[] currentContacts = new Contact[contactCount];
        for (int i = 0; i < contactCount; i++) {
            currentContacts[i] = this.contactsArray[i];
        }
        return currentContacts;
    }

    public Contact[] searchContacts(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllContacts();
        }

        Contact[] tempResults = new Contact[contactCount];
        int matchesFound = 0;
        String lowerCaseSearchTerm = searchTerm.toLowerCase();

        for (int i = 0; i < contactCount; i++) {
            Contact contact = this.contactsArray[i];
            boolean nameMatches = contact.getName().toLowerCase().contains(lowerCaseSearchTerm);
            boolean phoneMatches = contact.getPhoneNumber().toLowerCase().contains(lowerCaseSearchTerm);
            boolean emailMatches = false;
            if (contact.getEmailAddress() != null && !contact.getEmailAddress().isEmpty()) {
                emailMatches = contact.getEmailAddress().toLowerCase().contains(lowerCaseSearchTerm);
            }

            if (nameMatches || phoneMatches || emailMatches) {
                tempResults[matchesFound] = contact;
                matchesFound++;
            }
        }

        Contact[] finalResults = new Contact[matchesFound];
        for (int i = 0; i < matchesFound; i++) {
            finalResults[i] = tempResults[i];
        }
        return finalResults;
    }

    public Contact getContact(int index) {
        if (index >= 0 && index < contactCount) {
            return contactsArray[index];
        }
        return null;
    }
}
