// Manages a list of contacts
// Akash Howlader (24-56432-1)
// Hrishov (23-55660-3)

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactManager {
    private List<Contact> contacts;

    public ContactManager() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public void addContact(String name, String phone, String email) {
        Contact newContact = new Contact(name, phone, email);
        this.contacts.add(newContact);
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(this.contacts);
    }

    public Contact getContact(int index) {
        if (index >= 0 && index < contacts.size()) {
            return contacts.get(index);
        }
        return null;
    }

    public void removeContact(int index) {
        if (index >= 0 && index < contacts.size()) {
            this.contacts.remove(index);
        }
    }

    public List<Contact> searchContacts(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllContacts();
        }
        String lowerCaseSearchTerm = searchTerm.toLowerCase();
        return contacts.stream()
                .filter(contact -> contact.getName().toLowerCase().contains(lowerCaseSearchTerm) ||
                                   contact.getPhoneNumber().contains(lowerCaseSearchTerm) ||
                                   contact.getEmailAddress().toLowerCase().contains(lowerCaseSearchTerm))
                .collect(Collectors.toList());
    }
}