import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Collections;

public class AquariumGUI extends JFrame {
  // db connection
  private static final String URL = "jdbc:mysql://localhost:3306/AquariumSystem_MaY";
  private static final String USER = "root";
  private static final String PASSWORD = "my1998";

  private JTable staffTable;

  public AquariumGUI() {
    // main frame
    setTitle("Aquarium Management GUI");
    setSize(1650, 1080);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JTabbedPane tPane = new JTabbedPane();

    // add table tabs
    tPane.addTab("Staff Table", createStaffPanel());
    tPane.addTab("Tank Table", createTankPanel());
    tPane.addTab("Animal Table", createAnimalPanel());
    tPane.addTab("Care Task Table", createCareTaskPanel());
    tPane.addTab("Schedule Table", createSchedulePanel());
    tPane.addTab("Aquarium Event Table", createEventPanel());

    // add tabs to main frame
    add(tPane);
    pack();
    setVisible(true);
  }

  // staff table panel
  private JPanel createStaffPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    JPanel controlPanel = new JPanel(new GridLayout(4, 2));
    JPanel buttonPanel = new JPanel(new FlowLayout());

    JTextField idField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField roleField = new JTextField();
    JTextField contactField = new JTextField();

    // add to control panel
    controlPanel.add(new JLabel("ID:"));
    controlPanel.add(idField);
    controlPanel.add(new JLabel("Name:"));
    controlPanel.add(nameField);
    controlPanel.add(new JLabel("Role:"));
    controlPanel.add(roleField);
    controlPanel.add(new JLabel("Contact:"));
    controlPanel.add(contactField);

    // CRUD operation buttons
    JButton createButton = new JButton("Create");
    createButton.addActionListener(e -> {
      String[] values = {
        idField.getText().trim(),
        nameField.getText().trim(),
        roleField.getText().trim(),
        contactField.getText().trim()
      };
      createRow("CreateStaff", values);
      displayTable("GetStaff", staffTable);
    });

    JButton updateButton = new JButton("Update");
    updateButton.addActionListener(e -> {
      String[] values = {
        idField.getText().trim(),
        nameField.getText().trim(),
        roleField.getText().trim(),
        contactField.getText().trim()
      };
      updateRow("UpdateStaff", values);
      displayTable("GetStaff", staffTable);
    });

    JButton deleteButton = new JButton("Delete");
    deleteButton.addActionListener(e -> {
      String id = idField.getText().trim();
      deleteRow("DeleteStaff", id);
      displayTable("GetStaff", staffTable);
    });

    // add to button panel
    buttonPanel.add(createButton);
    buttonPanel.add(updateButton);
    buttonPanel.add(deleteButton);

    // set up table
    staffTable = new JTable();
    JScrollPane scrollPane = new JScrollPane(staffTable);

    // add to main panel
    panel.add(controlPanel, BorderLayout.NORTH);
    panel.add(scrollPane, BorderLayout.CENTER);
    panel.add(buttonPanel, BorderLayout.SOUTH);

    displayTable("GetStaff", staffTable);

    return panel;
  }

  // tank table panel
  private JPanel createTankPanel() {
      JPanel panel = new JPanel(new BorderLayout());
      JPanel controlPanel = new JPanel(new GridLayout(5, 2));
      JPanel buttonPanel = new JPanel(new FlowLayout());

      JTextField tankIdField = new JTextField();
      JTextField locationField = new JTextField();
      JTextField waterTypeField = new JTextField();
      JTextField temperatureField = new JTextField();
      JTextField capacityField = new JTextField();

      controlPanel.add(new JLabel("Tank ID:"));
      controlPanel.add(tankIdField);
      controlPanel.add(new JLabel("Location:"));
      controlPanel.add(locationField);
      controlPanel.add(new JLabel("Water Type:"));
      controlPanel.add(waterTypeField);
      controlPanel.add(new JLabel("Temperature:"));
      controlPanel.add(temperatureField);
      controlPanel.add(new JLabel("Capacity:"));
      controlPanel.add(capacityField);

      JTable tankTable = new JTable();
      JScrollPane scrollPane = new JScrollPane(tankTable);

      JButton createButton = new JButton("Create");
      createButton.addActionListener(e -> {
        String[] values = {
          tankIdField.getText().trim(),
          locationField.getText().trim(),
          waterTypeField.getText().trim(),
          temperatureField.getText().trim(),
          capacityField.getText().trim()
        };
        createRow("CreateTank", values);
        displayTable("GetTank", tankTable);
      });

      JButton updateButton = new JButton("Update");
      updateButton.addActionListener(e -> {
        String[] values = {
          tankIdField.getText().trim(),
          locationField.getText().trim(),
          waterTypeField.getText().trim(),
          temperatureField.getText().trim(),
          capacityField.getText().trim()
        };
        updateRow("UpdateTank", values);
        displayTable("GetTank", tankTable);
      });

      JButton deleteButton = new JButton("Delete");
      deleteButton.addActionListener(e -> {
        String id = tankIdField.getText().trim();
        deleteRow("DeleteTank", id);
        displayTable("GetTank", tankTable);
      });

      buttonPanel.add(createButton);
      buttonPanel.add(updateButton);
      buttonPanel.add(deleteButton);

      panel.add(controlPanel, BorderLayout.NORTH);
      panel.add(scrollPane, BorderLayout.CENTER);
      panel.add(buttonPanel, BorderLayout.SOUTH);

      displayTable("GetTank", tankTable);

      return panel;

  }

  // animal table panel
  private JPanel createAnimalPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    JPanel controlPanel = new JPanel(new GridLayout(6, 2));
    JPanel buttonPanel = new JPanel(new FlowLayout());

    JTextField animalIdField = new JTextField();
    JTextField scientificNameField = new JTextField();
    JTextField commonNameField = new JTextField();
    JTextField tankIdField = new JTextField();
    JTextField ageField = new JTextField();
    JTextField dietField = new JTextField();

    controlPanel.add(new JLabel("Animal ID:"));
    controlPanel.add(animalIdField);
    controlPanel.add(new JLabel("Scientific Name:"));
    controlPanel.add(scientificNameField);
    controlPanel.add(new JLabel("Common Name:"));
    controlPanel.add(commonNameField);
    controlPanel.add(new JLabel("Tank ID:"));
    controlPanel.add(tankIdField);
    controlPanel.add(new JLabel("Age:"));
    controlPanel.add(ageField);
    controlPanel.add(new JLabel("Diet:"));
    controlPanel.add(dietField);

    JTable AnimalTable = new JTable();
    JScrollPane scrollPane = new JScrollPane(AnimalTable);

    JButton createButton = new JButton("Create");
    createButton.addActionListener(e -> {
      String[] values = {
        animalIdField.getText().trim(),
        scientificNameField.getText().trim(),
        commonNameField.getText().trim(),
        tankIdField.getText().trim(),
        ageField.getText().trim(),
        dietField.getText().trim()
      };
      createRow("CreateAnimal", values);
      displayTable("GetAnimal", AnimalTable);
    });

    JButton updateButton = new JButton("Update");
    updateButton.addActionListener(e -> {
      String[] values = {
        animalIdField.getText().trim(),
        scientificNameField.getText().trim(),
        commonNameField.getText().trim(),
        tankIdField.getText().trim(),
        ageField.getText().trim(),
        dietField.getText().trim()
      };
      updateRow("UpdateAnimal", values);
      displayTable("GetAnimal", AnimalTable);
    });

    JButton deleteButton = new JButton("Delete");
    deleteButton.addActionListener(e -> {
      String id = animalIdField.getText().trim();
      deleteRow("DeleteAnimal", id);
      displayTable("GetAnimal", AnimalTable);
    });

    buttonPanel.add(createButton);
    buttonPanel.add(updateButton);
    buttonPanel.add(deleteButton);

    panel.add(controlPanel, BorderLayout.NORTH);
    panel.add(scrollPane, BorderLayout.CENTER);
    panel.add(buttonPanel, BorderLayout.SOUTH);

    displayTable("GetAnimal", AnimalTable);

    return panel;
  }

  // careTask table panel
  private JPanel createCareTaskPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    JPanel controlPanel = new JPanel(new GridLayout(4, 2));
    JPanel buttonPanel = new JPanel(new FlowLayout());

    JTextField taskIdField = new JTextField();
    JTextField taskDescriptionField = new JTextField();
    JTextField frequencyField = new JTextField();
    JTextField specialReqField = new JTextField();

    controlPanel.add(new JLabel("Task ID:"));
    controlPanel.add(taskIdField);
    controlPanel.add(new JLabel("Task Description:"));
    controlPanel.add(taskDescriptionField);
    controlPanel.add(new JLabel("Frequency:"));
    controlPanel.add(frequencyField);
    controlPanel.add(new JLabel("Special Requirement:"));
    controlPanel.add(specialReqField);

    JTable CareTaskTable = new JTable();
    JScrollPane scrollPane = new JScrollPane(CareTaskTable);

    JButton createButton = new JButton("Create");
    createButton.addActionListener(e -> {
      String[] values = {
        taskIdField.getText().trim(),
        taskDescriptionField.getText().trim(),
        frequencyField.getText().trim(),
        specialReqField.getText().trim()
      };
      createRow("CreateCareTask", values);
      displayTable("GetCareTask", CareTaskTable);
    });

    JButton updateButton = new JButton("Update");
    updateButton.addActionListener(e -> {
      String[] values = {
        taskIdField.getText().trim(),
        taskDescriptionField.getText().trim(),
        frequencyField.getText().trim(),
        specialReqField.getText().trim()
      };
      updateRow("UpdateCareTask", values);
      displayTable("GetCareTask", CareTaskTable);
    });

    JButton deleteButton = new JButton("Delete");
    deleteButton.addActionListener(e -> {
      String id = taskIdField.getText().trim();
      deleteRow("DeleteCareTask", id);
      displayTable("GetCareTask", CareTaskTable);
    });

    buttonPanel.add(createButton);
    buttonPanel.add(updateButton);
    buttonPanel.add(deleteButton);

    panel.add(controlPanel, BorderLayout.NORTH);
    panel.add(scrollPane, BorderLayout.CENTER);
    panel.add(buttonPanel, BorderLayout.SOUTH);

    displayTable("GetCareTask", CareTaskTable);

    return panel;
  }

  // schedule table panel
  private JPanel createSchedulePanel() {
    JPanel panel = new JPanel(new BorderLayout());
    JPanel controlPanel = new JPanel(new GridLayout(7, 2));
    JPanel buttonPanel = new JPanel(new FlowLayout());

    JTextField scheduleIdField = new JTextField();
    JTextField taskIdField = new JTextField();
    JTextField staffIdField = new JTextField();
    JTextField animalIdField = new JTextField();
    JTextField scientificNameField = new JTextField();
    JTextField scheduledDateField = new JTextField();
    JTextField scheduledTimeField = new JTextField();

    controlPanel.add(new JLabel("Schedule ID:"));
    controlPanel.add(scheduleIdField);
    controlPanel.add(new JLabel("Task ID:"));
    controlPanel.add(taskIdField);
    controlPanel.add(new JLabel("Staff ID:"));
    controlPanel.add(staffIdField);
    controlPanel.add(new JLabel("Animal ID:"));
    controlPanel.add(animalIdField);
    controlPanel.add(new JLabel("Scientific Name:"));
    controlPanel.add(scientificNameField);
    controlPanel.add(new JLabel("Scheduled Date:"));
    controlPanel.add(scheduledDateField);
    controlPanel.add(new JLabel("Scheduled Time:"));
    controlPanel.add(scheduledTimeField);

    JTable ScheduleTable = new JTable();
    JScrollPane scrollPane = new JScrollPane(ScheduleTable);

    JButton createButton = new JButton("Create");
    createButton.addActionListener(e -> {
      String[] values = {
        scheduleIdField.getText().trim(),
        taskIdField.getText().trim(),
        staffIdField.getText().trim(),
        animalIdField.getText().trim(),
        scientificNameField.getText().trim(),
        scheduledDateField.getText().trim(),
        scheduledTimeField.getText().trim()
      };
      createRow("CreateSchedule", values);
      displayTable("GetSchedule", ScheduleTable);
    });

    JButton updateButton = new JButton("Update");
    updateButton.addActionListener(e -> {
      String[] values = {
        scheduleIdField.getText().trim(),
        taskIdField.getText().trim(),
        staffIdField.getText().trim(),
        animalIdField.getText().trim(),
        scientificNameField.getText().trim(),
        scheduledDateField.getText().trim(),
        scheduledTimeField.getText().trim()
      };
      updateRow("UpdateSchedule", values);
      displayTable("GetSchedule", ScheduleTable);
    });

    JButton deleteButton = new JButton("Delete");
    deleteButton.addActionListener(e -> {
      String id = scheduleIdField.getText().trim();
      deleteRow("DeleteSchedule", id);
      displayTable("GetSchedule", ScheduleTable);
    });

    buttonPanel.add(createButton);
    buttonPanel.add(updateButton);
    buttonPanel.add(deleteButton);

    panel.add(controlPanel, BorderLayout.NORTH);
    panel.add(scrollPane, BorderLayout.CENTER);
    panel.add(buttonPanel, BorderLayout.SOUTH);

    displayTable("GetSchedule", ScheduleTable);

    return panel;
  }

  // event table panel
  private JPanel createEventPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    JPanel controlPanel = new JPanel(new GridLayout(9, 2));
    JPanel buttonPanel = new JPanel(new FlowLayout());

    JTextField eventIdField = new JTextField();
    JTextField eventNameField = new JTextField();
    JTextField eventDateField = new JTextField();
    JTextField eventTimeField = new JTextField();
    JTextField eventLocationField = new JTextField();
    JTextField eventDescriptionField = new JTextField();
    JTextField staffIdField = new JTextField();
    JTextField animalIdField = new JTextField();
    JTextField scientificNameField = new JTextField();

    controlPanel.add(new JLabel("Event ID:"));
    controlPanel.add(eventIdField);
    controlPanel.add(new JLabel("Event Name:"));
    controlPanel.add(eventNameField);
    controlPanel.add(new JLabel("Event Date:"));
    controlPanel.add(eventDateField);
    controlPanel.add(new JLabel("Event Time:"));
    controlPanel.add(eventTimeField);
    controlPanel.add(new JLabel("Event Location:"));
    controlPanel.add(eventLocationField);
    controlPanel.add(new JLabel("Event Description:"));
    controlPanel.add(eventDescriptionField);
    controlPanel.add(new JLabel("Staff ID:"));
    controlPanel.add(staffIdField);
    controlPanel.add(new JLabel("Animal ID:"));
    controlPanel.add(animalIdField);
    controlPanel.add(new JLabel("Scientific Name:"));
    controlPanel.add(scientificNameField);

    JTable EventTable = new JTable();
    JScrollPane scrollPane = new JScrollPane(EventTable);

    JButton createButton = new JButton("Create");
    createButton.addActionListener(e -> {
      String[] values = {
        eventIdField.getText().trim(),
        eventNameField.getText().trim(),
        eventDateField.getText().trim(),
        eventTimeField.getText().trim(),
        eventLocationField.getText().trim(),
        eventDescriptionField.getText().trim(),
        staffIdField.getText().trim(),
        animalIdField.getText().trim(),
        scientificNameField.getText().trim()
      };
      createRow("CreateAquariumEvent", values);
      displayTable("GetAquariumEvent", EventTable);
    });

    JButton updateButton = new JButton("Update");
    updateButton.addActionListener(e -> {
      String[] values = {
        eventIdField.getText().trim(),
        eventNameField.getText().trim(),
        eventDateField.getText().trim(),
        eventTimeField.getText().trim(),
        eventLocationField.getText().trim(),
        eventDescriptionField.getText().trim(),
        staffIdField.getText().trim(),
        animalIdField.getText().trim(),
        scientificNameField.getText().trim()
      };
      updateRow("UpdateAquariumEvent", values);
      displayTable("GetAquariumEvent", EventTable);
    });

    JButton deleteButton = new JButton("Delete");
    deleteButton.addActionListener(e -> {
      String id = eventIdField.getText().trim();
      deleteRow("DeleteAquariumEvent", id);
      displayTable("GetAquariumEvent", EventTable);
    });

    buttonPanel.add(createButton);
    buttonPanel.add(updateButton);
    buttonPanel.add(deleteButton);

    panel.add(controlPanel, BorderLayout.NORTH);
    panel.add(scrollPane, BorderLayout.CENTER);
    panel.add(buttonPanel, BorderLayout.SOUTH);

    displayTable("GetAquariumEvent", EventTable);

    return panel;
  }

  // display (READ operation)
  private void displayTable(String procedureName, JTable table) {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      CallableStatement stmt = connection.prepareCall("{CALL " + procedureName + "()}",
              ResultSet.TYPE_SCROLL_INSENSITIVE,
              ResultSet.CONCUR_READ_ONLY);

      ResultSet rs = stmt.executeQuery();

      ResultSetMetaData metaData = rs.getMetaData();
      int columnCount = metaData.getColumnCount();

      String[] columnNames = new String[columnCount];
      for (int i = 0; i < columnCount; i++) {
        columnNames[i] = metaData.getColumnName(i + 1);
      }

      rs.last();
      int rowCount = rs.getRow();
      rs.beforeFirst();

      String[][] tableData = new String[rowCount][columnCount];
      int i = 0;
      while (rs.next()) {
        for (int j = 0; j < columnCount; j++) {
          tableData[i][j] = rs.getString(j + 1);
        }
        i++;
      }

      table.setModel(new javax.swing.table.DefaultTableModel(tableData, columnNames));

    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error displaying table: " + e.getMessage(),
              "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  // create row
  private void createRow(String procedureName, String[] values) {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      String placeholders = String.join(", ", Collections.nCopies(values.length, "?"));
      CallableStatement stmt = connection.prepareCall("{CALL " + procedureName + "(" + placeholders + ")}");

      for (int i = 0; i < values.length; i++) {
        stmt.setString(i + 1, values[i]);
      }

      stmt.executeUpdate();
      JOptionPane.showMessageDialog(this, "Row created successfully!",
              "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error creating row: " + e.getMessage(),
              "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  // update row
  private void updateRow(String procedureName, String[] values) {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      String placeholders = String.join(", ", Collections.nCopies(values.length, "?"));
      CallableStatement stmt = connection.prepareCall("{CALL " + procedureName + "(" + placeholders + ")}");

      for (int i = 0; i < values.length; i++) {
        stmt.setString(i + 1, values[i]);
      }

      stmt.executeUpdate();
      JOptionPane.showMessageDialog(this, "Row updated successfully!",
              "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error updating row: " + e.getMessage(),
              "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  // delete row
  private void deleteRow(String procedureName, String id) {
    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
      CallableStatement stmt = connection.prepareCall("{CALL " + procedureName + "(?)}");
      stmt.setString(1, id);

      stmt.executeUpdate();
      JOptionPane.showMessageDialog(this, "Row deleted successfully!", "Success",
              JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Error deleting row: " + e.getMessage(),
              "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(AquariumGUI::new);
  }
}


