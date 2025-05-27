// GUI
// Akash Howlader (24-56432-1)
// Hrishov (23-55660-3)

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactBookGUI extends JFrame {
    private ContactManager contactManager;
    private DefaultListModel<Contact> contactListModel;
    private JList<Contact> contactJList;

    private JTextField nameTextField;
    private JTextField phoneTextField;
    private JTextField emailTextField;
    private JTextField searchTextField;

    private JButton addButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton viewAllButton;

    public ContactBookGUI() {
        contactManager = new ContactManager();
        contactListModel = new DefaultListModel<>();



        setTitle("Akash & Hrishov's Contact Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLayout(new BorderLayout(10, 10));




        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5)); // Rows, Cols, hgap, vgap
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add New Contact"));

        inputPanel.add(new JLabel("Name:"));
        nameTextField = new JTextField();
        inputPanel.add(nameTextField);

        inputPanel.add(new JLabel("Phone:"));
        phoneTextField = new JTextField();
        inputPanel.add(phoneTextField);

        inputPanel.add(new JLabel("Email (Optional):"));
        emailTextField = new JTextField();
        inputPanel.add(emailTextField);

        addButton = new JButton("Add Contact");
        inputPanel.add(new JLabel());
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);



        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Contacts"));
        contactJList = new JList<>(contactListModel);
        contactJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPanel.add(new JScrollPane(contactJList), BorderLayout.CENTER);

        add(listPanel, BorderLayout.CENTER);



        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        actionPanel.setBorder(BorderFactory.createTitledBorder("Actions & Search"));

        deleteButton = new JButton("Delete Selected");
        actionPanel.add(deleteButton);

        actionPanel.add(new JSeparator(SwingConstants.VERTICAL)); //separator

        actionPanel.add(new JLabel("Search:"));
        searchTextField = new JTextField(15);
        actionPanel.add(searchTextField);
        searchButton = new JButton("Search");
        actionPanel.add(searchButton);
        viewAllButton = new JButton("View All");
        actionPanel.add(viewAllButton);


        add(actionPanel, BorderLayout.SOUTH);


        addButton.addActionListener(e -> addContactAction());
        deleteButton.addActionListener(e -> deleteContactAction());
        searchButton.addActionListener(e -> searchContactsAction());
        viewAllButton.addActionListener(e -> refreshContactDisplayList(contactManager.getAllContacts()));


        refreshContactDisplayList(contactManager.getAllContacts());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addContactAction() {
        String name = nameTextField.getText().trim();
        String phone = phoneTextField.getText().trim();
        String email = emailTextField.getText().trim();

        if (name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and Phone cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        contactManager.addContact(name, phone, email);

        refreshContactDisplayList(contactManager.getAllContacts());


        nameTextField.setText("");
        phoneTextField.setText("");
        emailTextField.setText("");
        JOptionPane.showMessageDialog(this, "Contact added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteContactAction() {
        int selectedIndex = contactJList.getSelectedIndex();
        if (selectedIndex != -1) {

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete " + contactListModel.getElementAt(selectedIndex).getName() + "?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                contactManager.removeContact(selectedIndex);
				
                refreshContactDisplayList(contactManager.getAllContacts());
                searchTextField.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a contact to delete.", "Selection Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void searchContactsAction() {
        String searchTerm = searchTextField.getText().trim();

        Contact[] searchResults = contactManager.searchContacts(searchTerm);

        refreshContactDisplayList(searchResults);

        if (searchResults.length == 0 && !searchTerm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No contacts found matching your search.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void refreshContactDisplayList(Contact[] contactsToDisplay) {
		contactListModel.clear();
		if (contactsToDisplay != null) {
			for (Contact contact : contactsToDisplay) {
				contactListModel.addElement(contact);
			}
		}
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ContactBookGUI();
            }
        });
    }
}
