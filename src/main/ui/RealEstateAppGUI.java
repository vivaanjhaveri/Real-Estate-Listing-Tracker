package ui;

import model.Listing;
import model.Portfolio;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RealEstateAppGUI extends JFrame implements ActionListener {

    public static final int WIDTH = 750;
    public static final int HEIGHT = 750;

    private static final String JSON_STORE_LOCATION = "./data/propertyPortfolio.json";
    private static final String BACKGROUND_IMAGE_SRC = "./data/gui_background_image.png";
    private static final String saveMessage = "Saved portfolio to " + JSON_STORE_LOCATION;
    private static final String loadMessage = "Loaded portfolio from " + JSON_STORE_LOCATION;
    private static final Font titleFont = new Font("Arial", Font.BOLD, 24);

    private JFrame guiFrame;

    private JPanel startPanel;
    private JPanel mainMenuPanel;
    private JPanel addListingPanel;
    private JPanel sellListingPanel;
    private JPanel viewValuePanel;
    private JPanel viewPortfolioPanel;
    private JPanel viewInDemandPortfolioPanel;

    private JButton addListingButton;
    private JButton sellListingButton;
    private JButton viewAllButton;
    private JButton viewDemandButton;
    private JButton viewPortfolioValueButton;
    private JButton saveToFileButton;
    private JButton loadFromFileButton;
    private JButton enterButton;
    private JButton mainMenuButton;
    private JButton createListingButton;
    private JButton sellListingFromPortfolioButton;

    private JTextField listingIdField;
    private JTextField listingNameField;
    private JTextField listingTypeField;
    private JTextField listingPriceField;
    private JTextField listingSizeField;
    private JTextField listingIDSellField;
    private JTextField listingPriceSellField;

    private JComboBox<Boolean> listingDemandComboBox;

    private JLabel welcomeLabel;

    private GridBagConstraints labelConstraints;
    private GridBagConstraints fieldConstraints;

    private Portfolio portfolio;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Constructs GUI
    RealEstateAppGUI() {
        guiFrame = new JFrame("Vivaan's Real Estate Portfolio Tracker");
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setSize(WIDTH, HEIGHT);
        guiFrame.setLayout(new BorderLayout());

        jsonWriter = new JsonWriter(JSON_STORE_LOCATION);
        jsonReader = new JsonReader(JSON_STORE_LOCATION);

        portfolio = new Portfolio(); // sets up empty portfolio

        welcomeScreen();
        guiFrame.add(startPanel);
        guiFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates welcome panel
    private void welcomeScreen() {
        startPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintBackgroundImage(this, g);
            }
        };

        startPanel.setLayout(new BorderLayout());

        welcomeLabel = new JLabel("Welcome to Vivaan's Real Estate Portfolio Tracker");
        welcomeLabel.setFont(titleFont);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        enterButton = new JButton("Enter Portfolio");
        enterButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(enterButton);

        startPanel.add(welcomeLabel, BorderLayout.CENTER);
        startPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: creates main menu panel
    private void mainMenu() {
        guiFrame.getContentPane().removeAll(); // Clear previous content

        mainMenuPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintBackgroundImage(this, g);
            }
        };

        GridBagConstraints gbc = createGridBagConstraints(0,0,2);

        JLabel titleLabel = new JLabel("Portfolio Main Menu");
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainMenuPanel.add(titleLabel, gbc);

        GridBagConstraints buttonConstraints = createGridBagConstraints(0,1,1);

        addListingButton = new JButton("Add new listing to portfolio");
        addListingButton.addActionListener(this);
        mainMenuPanel.add(addListingButton, buttonConstraints);

        buttonConstraints.gridy++; // Move to the next column
        sellListingButton = new JButton("Sell current listing");
        sellListingButton.addActionListener(this);
        mainMenuPanel.add(sellListingButton, buttonConstraints);

        buttonConstraints.gridy++; // Move to the next row
        viewAllButton = new JButton("View all listings");
        viewAllButton.addActionListener(this);
        mainMenuPanel.add(viewAllButton, buttonConstraints);

        buttonConstraints.gridy++; // Move to the next row
        viewDemandButton = new JButton("View listings in demand");
        viewDemandButton.addActionListener(this);
        mainMenuPanel.add(viewDemandButton, buttonConstraints);

        buttonConstraints.gridy++; // Move to the next row
        viewPortfolioValueButton = new JButton("View portfolio value");
        viewPortfolioValueButton.addActionListener(this);
        mainMenuPanel.add(viewPortfolioValueButton, buttonConstraints);

        JPanel saveLoadPanel = new JPanel();
        saveToFileButton = new JButton("Save portfolio to file");
        saveToFileButton.addActionListener(this);
        saveLoadPanel.add(saveToFileButton, buttonConstraints);

        loadFromFileButton = new JButton("Load portfolio from file");
        loadFromFileButton.addActionListener(this);
        saveLoadPanel.add(loadFromFileButton, buttonConstraints);

        guiFrame.add(mainMenuPanel, BorderLayout.CENTER);
        guiFrame.add(saveLoadPanel, BorderLayout.SOUTH);
        guiFrame.revalidate(); // Refresh the frame to display changes
    }

    // MODIFIES: this
    // EFFECTS: GUI page for adding a new listing
    private void addListingToPortfolio() {
        guiFrame.getContentPane().removeAll(); // Clear previous content

        addListingPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintBackgroundImage(this, g);
            }
        };

        GridBagConstraints gbc = createGridBagConstraints(0,0,2);

        JLabel titleLabel = new JLabel("Create New Listing");
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        addListingPanel.add(titleLabel, gbc);

        gbc.gridy++;
        addLabelAndTextField(addListingPanel, "Listing ID:", listingIdField = new JTextField());
        addLabelAndTextField(addListingPanel, "Listing Name:", listingNameField = new JTextField());
        addLabelAndTextField(addListingPanel, "Listing Type:", listingTypeField = new JTextField());
        addLabelAndTextField(addListingPanel, "Listing Price (CAD $):", listingPriceField = new JTextField());
        addLabelAndTextField(addListingPanel, "Listing Size (sq.ft):", listingSizeField = new JTextField());
        addLabelAndTextField(addListingPanel, "Listing in Demand?:", listingDemandComboBox = new JComboBox<>(
                new Boolean[]{Boolean.TRUE, Boolean.FALSE}));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        createListingButton = new JButton("Create Listing");
        createListingButton.addActionListener(this);
        buttonPanel.add(createListingButton);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(this);
        buttonPanel.add(mainMenuButton);

        guiFrame.add(addListingPanel, BorderLayout.CENTER);
        guiFrame.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the bottom
        guiFrame.revalidate(); // Refresh the frame to display changes
    }

    // EFFECTS: Helper method to add label and text field to panel
    private void addLabelAndTextField(JPanel panel, String labelText, JComponent textField) {
        labelConstraints = createGridBagConstraints(0,GridBagConstraints.RELATIVE,1);
        fieldConstraints = createGridBagConstraints(1,GridBagConstraints.RELATIVE,1);

        panel.add(new JLabel(labelText), labelConstraints);
        panel.add(textField, fieldConstraints);
    }

    // MODIFIES: this
    // EFFECTS: GUI page for selling a listing
    private void sellListingFromPortfolio() {
        guiFrame.getContentPane().removeAll(); // Clear previous content

        sellListingPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintBackgroundImage(this, g);
            }
        };

        GridBagConstraints gbc = createGridBagConstraints(0,0,2);

        JLabel titleLabel = new JLabel("Sell Current Listing");
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        sellListingPanel.add(titleLabel, gbc);

        gbc.gridy++;
        addLabelAndTextField(sellListingPanel, "Listing ID to sell:", listingIDSellField = new JTextField());
        addLabelAndTextField(sellListingPanel, "Price sold at (CAD$):", listingPriceSellField = new JTextField());

        JPanel sellButtonPanel = new JPanel();
        sellListingFromPortfolioButton = new JButton("Sell Listing");
        sellListingFromPortfolioButton.addActionListener(this);
        sellButtonPanel.add(sellListingFromPortfolioButton);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(this);
        sellButtonPanel.add(mainMenuButton);

        guiFrame.add(sellListingPanel, BorderLayout.CENTER);
        guiFrame.add(sellButtonPanel, BorderLayout.SOUTH); // Add the button panel to the bottom
        guiFrame.revalidate(); // Refresh the frame to display changes
    }

    // MODIFIES: this
    // EFFECTS: GUI page for showing all listings in portfolio
    private void viewAllListingsInPortfolio() {
        guiFrame.getContentPane().removeAll(); // Clear previous content

        viewPortfolioPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintBackgroundImage(this, g);
            }
        };

        GridBagConstraints gbc = createGridBagConstraints(0,0,2);
        JLabel titleLabel = new JLabel("Current Portfolio");
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        viewPortfolioPanel.add(titleLabel, gbc);

        gbc.gridy++;
        if (portfolio.isPortfolioEmpty()) {
            JLabel noListings = new JLabel("There are currently no listings in portfolio");
            viewPortfolioPanel.add(noListings, gbc);
        } else {
            generateTableForPortfolio(gbc);
        }

        JPanel buttonPanel = new JPanel();
        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(this);
        buttonPanel.add(mainMenuButton);

        guiFrame.add(viewPortfolioPanel, BorderLayout.CENTER);
        guiFrame.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the bottom
        guiFrame.revalidate(); // Refresh the frame to display changes
    }

    // MODIFIES: this
    // EFFECTS: Generates a table with the portfolio contents
    private void generateTableForPortfolio(GridBagConstraints gbc) {
        String[] columnNames = {"ID","Name", "Type", "Price (CAD $)",
                "Size (sq.ft)", "In demand?"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);
        JTable listings = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(listings);
        scrollPane.setBounds(10, 90, 800, 200);
        for (int i = 0; i < portfolio.getAllUnsoldListings().size(); i++) {
            int id = portfolio.getAllUnsoldListings().get(i).getListingID();
            String name = portfolio.getAllUnsoldListings().get(i).getListingName();
            String type = portfolio.getAllUnsoldListings().get(i).getListingType();
            double price = portfolio.getAllUnsoldListings().get(i).getListingPrice();
            double size = portfolio.getAllUnsoldListings().get(i).getListingSize();
            boolean demand = portfolio.getAllUnsoldListings().get(i).getListingDemand();
       //     boolean status = portfolio.getAllUnsoldListings().get(i).getListingStatus();

            Object[] data = {id, name, type, price, size, demand};
            tableModel.addRow(data);
        }
        viewPortfolioPanel.add(scrollPane, gbc);
    }

    // MODIFIES: this
    // EFFECTS: GUI page for all listings in portfolio that are in demand
    private void viewAllInDemandInPortfolio() {
        guiFrame.getContentPane().removeAll(); // Clear previous content

        viewInDemandPortfolioPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintBackgroundImage(this, g);
            }
        };

        GridBagConstraints gbc = createGridBagConstraints(0,0,2);
        JLabel titleLabel = new JLabel("Current Listings in Demand");
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        viewInDemandPortfolioPanel.add(titleLabel, gbc);

        gbc.gridy++;
        if (portfolio.isPortfolioEmpty()) {
            JLabel noListings = new JLabel("There are currently no listings in portfolio");
            viewInDemandPortfolioPanel.add(noListings, gbc);
        } else if (portfolio.getAllInDemandListings().size() == 0) {
            JLabel noDemandListings = new JLabel("There are currently no listings in demand");
            viewInDemandPortfolioPanel.add(noDemandListings, gbc);
        } else {
            generateDemandTableForPortfolio(gbc);
        }

        JPanel buttonPanel = new JPanel();
        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(this);
        buttonPanel.add(mainMenuButton);

        guiFrame.add(viewInDemandPortfolioPanel, BorderLayout.CENTER);
        guiFrame.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the bottom
        guiFrame.revalidate(); // Refresh the frame to display changes
    }

    // MODIFIES: this
    // EFFECTS: Generates table of all in demand listings in portfolio
    private void generateDemandTableForPortfolio(GridBagConstraints gbc) {
        String[] columnNames = {"ID","Name", "Type", "Price (CAD $)",
                "Size (sq.ft)", "In demand?"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);
        JTable listings = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(listings);
        scrollPane.setBounds(10, 90, 800, 200);
        for (int i = 0; i < portfolio.getAllInDemandListings().size(); i++) {
            int id = portfolio.getAllInDemandListings().get(i).getListingID();
            String name = portfolio.getAllInDemandListings().get(i).getListingName();
            String type = portfolio.getAllInDemandListings().get(i).getListingType();
            double price = portfolio.getAllInDemandListings().get(i).getListingPrice();
            double size = portfolio.getAllInDemandListings().get(i).getListingSize();
            boolean demand = portfolio.getAllInDemandListings().get(i).getListingDemand();

            Object[] data = {id, name, type, price, size, demand};
            tableModel.addRow(data);
        }
        viewInDemandPortfolioPanel.add(scrollPane, gbc);
    }

    // EFFECTS: displays current portfolio value
    private void viewPortfolioValue() {
        guiFrame.getContentPane().removeAll(); // Clear previous content

        viewValuePanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintBackgroundImage(this, g);
            }
        };

        GridBagConstraints gbc = createGridBagConstraints(0,0,2);

        JLabel titleLabel = new JLabel("Current Portfolio Value");
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        viewValuePanel.add(titleLabel, gbc);

        gbc.gridy++;
        JLabel numCountValueText = new JLabel("There are currently " + portfolio.getAllUnsoldListings().size()
                + " listings in portfolio with a total value of $ " + portfolio.checkPortfolioValue());
        viewValuePanel.add(numCountValueText, gbc);

        JPanel buttonPanel = new JPanel();
        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(this);
        buttonPanel.add(mainMenuButton);

        guiFrame.add(viewValuePanel, BorderLayout.CENTER);
        guiFrame.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the bottom
        guiFrame.revalidate(); // Refresh the frame to display changes
    }

    // EFFECTS: maps buttons to their respective actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addListingButton) {
            addListingToPortfolio();
        } else if (e.getSource() == sellListingButton) {
            sellListingFromPortfolio();
        } else if (e.getSource() == viewAllButton) {
            viewAllListingsInPortfolio();
        } else if (e.getSource() == viewDemandButton) {
            viewAllInDemandInPortfolio();
        } else if (e.getSource() == viewPortfolioValueButton) {
            viewPortfolioValue();
        } else if (e.getSource() == saveToFileButton) {
            savePortfolio();
        } else if (e.getSource() == loadFromFileButton) {
            loadPortfolio();
        } else if (e.getSource() == mainMenuButton) {
            mainMenu();
        } else if (e.getSource() == enterButton) {
            mainMenu();
        } else if (e.getSource() == createListingButton) {
            createListing();
        } else if (e.getSource() == sellListingFromPortfolioButton) {
            sellListing();
        }
    }

    // MODIFIES: this, portfolio
    // EFFECTS: adds new listing from user to portfolio
    private void createListing() {
        int listingId = Integer.parseInt(listingIdField.getText());
        String listingName = listingNameField.getText();
        String listingType = listingTypeField.getText();
        double listingPrice = Double.parseDouble(listingPriceField.getText());
        int listingSize = Integer.parseInt(listingSizeField.getText());
        boolean isListingInDemand = (boolean) listingDemandComboBox.getSelectedItem();

        Listing listingToAdd = new Listing(listingId, listingName, listingType, listingPrice,
                listingSize, isListingInDemand, false);
        portfolio.addListingToPortfolio(listingToAdd);
        JOptionPane.showMessageDialog(null, "Listing ID " + listingId + " added successfully!");
    }

    // MODIFIES: this, portfolio
    // EFFECTS: sells listing from portfolio
    private void sellListing() {
        int listingId = Integer.parseInt(listingIDSellField.getText());

        if (portfolio.isPortfolioEmpty()) {
            JOptionPane.showMessageDialog(null, "Portfolio is empty!");
        } else {
            if (portfolio.portfolioContainsListing(listingId)) {
                portfolio.sellListing(listingId);
                JOptionPane.showMessageDialog(null, "Listing ID " + listingId + " sold successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Listing ID " + listingId + " not found!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Saves content of the portfolio to the file JSON_STORE.
    //          Throws exception if unable to write to the desired file.
    private void savePortfolio() {
        try {
            jsonWriter.open();
            jsonWriter.write(portfolio);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, saveMessage);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_LOCATION);
        }
    }

    // MODIFIES: this, portfolio
    // EFFECTS: loads portfolio from file. Throws exception if file to load from cannot be found.
    private void loadPortfolio() {
        try {
            portfolio = jsonReader.read();
            JOptionPane.showMessageDialog(null, loadMessage);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_LOCATION);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets background image
    private void paintBackgroundImage(JPanel panel, Graphics g) {
        ImageIcon imageIcon = new ImageIcon(BACKGROUND_IMAGE_SRC);
        Image image = imageIcon.getImage();

        int x = (panel.getWidth() - image.getWidth(null)) / 2;
        int y = (panel.getHeight() - image.getHeight(null)) / 2;
        g.drawImage(image, x, y, panel);
    }

    // MODIFIES: this
    // EFFECTS: sets gridbag constraints
    private GridBagConstraints createGridBagConstraints(int x, int y, int gridWidth) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gridWidth;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        return gbc;
    }
}



