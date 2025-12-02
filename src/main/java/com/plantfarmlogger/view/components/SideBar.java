package com.plantfarmlogger.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import com.plantfarmlogger.model.User;

// Change from 'extends JFrame' to 'extends JPanel'
public class SideBar extends JPanel {
    private JButton addCropBed, changeName, changeAddress, changePass, logOut;

    // DESIGN STUFF
    private static final Color BG_COLOR = new Color(113, 165, 84);
    // private static final Color BUTTON_COLOR = new Color(54, 85, 59);
    // private static final Color BUTTON_HOVER_COLOR = new Color(64, 95, 69);
    // private static final Color TEXT_FIELD_BG = new Color(220, 220, 220);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color SUB_TEXT_COLOR = new Color(214, 223, 197);

    private static final Font defaultFont = new Font("SansSerif", Font.PLAIN, 16);

    public SideBar(User user, Runnable onLogout) {

        // edit to get user
        // this.user = new User("John Doe", "johndoe", "Cebu Institute of
        // Technology-University, Cebu City, Cebu, Region VII", "123", 24, "123");
        // this.farm = new Farm("myFarm", 0);
        // farm.setCropBed(new ArrayList<>());
        // farm.addCropBed(new CropBed(new Plant("Tree", 1), "Loamy", "120125",
        // 1.1, 1.2, 1.3,
        // "110125"));
        // farm.addCropBed(new CropBed(new Plant("Tree", 1), "Loamy", "120125",
        // 1.1, 1.2, 1.3,
        // "110125"));
        // farm.addCropBed(new CropBed(new Plant("Tree", 1), "Loamy", "120125",
        // 1.1, 1.2, 1.3,
        // "110125"));

        // The contentPane setup is now the setup for the SideBar JPanel itself
        // (this) refers to the SideBar JPanel
        setLayout(new GridBagLayout());
        setBackground(BG_COLOR);
        setMaximumSize(new Dimension(300, 800));
        setFont(Font.getFont("SansSerif"));
        setUIFont(new FontUIResource(defaultFont));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setSize(230, 200);
        userInfoPanel.setOpaque(false);
        JLabel logoLabel = new JLabel("ANICore LITE");
        logoLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        logoLabel.setForeground(TEXT_COLOR);

        JLabel uName = new JLabel(user.getName());
        uName.setForeground(TEXT_COLOR);
        uName.setBorder(new EmptyBorder(new Insets(20, 0, 5, 0)));
        uName.setFont(new Font("SansSerif", Font.BOLD, 32));
        userInfoPanel.add(uName);

        JLabel uUsername = new JLabel("@" + user.getUsername());
        uUsername.setForeground(SUB_TEXT_COLOR);
        uUsername.setBorder(new EmptyBorder(new Insets(0, 0, 30, 0)));
        uUsername.setFont(new Font("SansSerif", Font.BOLD, 16));
        userInfoPanel.add(uUsername);

        JLabel uAddress = new JLabel("<html>" + user.getAddress() + "</html>");
        uAddress.setForeground(SUB_TEXT_COLOR);
        uAddress.setFont(new Font("SansSerif", Font.BOLD, 16));
        userInfoPanel.add(uAddress);

        JPanel cropBeds = new JPanel();
        cropBeds.setLayout(new BoxLayout(cropBeds, BoxLayout.Y_AXIS));
        cropBeds.setMinimumSize(new Dimension(230, 100));
        cropBeds.setPreferredSize(new Dimension(230, 100));
        cropBeds.setOpaque(false);
        cropBeds.setFont(new Font("SansSerif", Font.BOLD, 32));

        // edit to make loop for number of crop beds
        // edit this nalang soon to make it white with for loop (THESE SHOULD BE
        // JBUTTONS)
        // for(CropBed i : farm.getCropBeds()){
        // JButton crop = new JButton(i.getPlantType().getName());
        // crop.setBorderPainted(false);
        // crop.setContentAreaFilled(false);
        // crop.setFocusPainted(false);
        // crop.setFocusable(true);
        // crop.setForeground(SUB_TEXT_COLOR);

        // // add color shi
        // crop.addMouseListener(new MouseAdapter() {
        // @Override
        // public void mouseEntered(MouseEvent e) {
        // crop.setForeground(TEXT_COLOR);
        // }

        // @Override
        // public void mouseExited(MouseEvent e) {
        // crop.setForeground(SUB_TEXT_COLOR);
        // }
        // });
        // cropBeds.add(crop);
        // }

        JScrollPane cropBedsList = new JScrollPane(cropBeds);
        cropBedsList.setOpaque(false);
        cropBedsList.getViewport().setOpaque(false);
        // Setting the preferred size of the scroll pane itself controls its visible
        // area
        cropBedsList.setMinimumSize(new Dimension(230, 100));
        cropBedsList.setPreferredSize(new Dimension(230, 100));
        cropBedsList.setBorder(BorderFactory.createEmptyBorder());

        JPanel cropsTitleRow = new JPanel(new GridLayout(1, 2));
        cropsTitleRow.setSize(230, 100);
        JLabel cropBedsLabel = new JLabel("Crop Beds");
        cropBedsLabel.setForeground(SUB_TEXT_COLOR);
        cropsTitleRow.add(cropBedsLabel);
        cropsTitleRow.setOpaque(false);

        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addCropBed = new JButton("+ Add Crop Bed");
        addCropBed.setBorderPainted(false);
        addCropBed.setContentAreaFilled(false);
        addCropBed.setFocusPainted(false);
        addCropBed.setFocusable(true);
        addCropBed.setForeground(SUB_TEXT_COLOR);

        // add color shi
        addCropBed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addCropBed.setForeground(TEXT_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addCropBed.setForeground(SUB_TEXT_COLOR);
            }
        });

        buttonWrapper.add(this.addCropBed);
        buttonWrapper.setOpaque(false);
        addCropBed.setMaximumSize(new Dimension(20, 20));
        cropsTitleRow.add(buttonWrapper);

        JPanel cropsPanel = new JPanel();
        cropsPanel.setLayout(new BoxLayout(cropsPanel, BoxLayout.Y_AXIS));
        cropsPanel.add(cropsTitleRow);
        cropsPanel.add(Box.createVerticalStrut(10));
        cropsPanel.add(cropBedsList);
        cropsPanel.setOpaque(false);

        // SETTINGS PANEL
        this.changeName = new JButton("Change Name");
        changeName.setBorderPainted(false);
        changeName.setContentAreaFilled(false);
        changeName.setFocusPainted(false);
        changeName.setFocusable(true);
        changeName.setForeground(SUB_TEXT_COLOR);
        changeName.setHorizontalAlignment(SwingConstants.LEFT);
        changeName.setMargin(new Insets(0, 0, 0, 0));

        changeName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                changeName.setForeground(TEXT_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                changeName.setForeground(SUB_TEXT_COLOR);
            }
        });

        this.changePass = new JButton("Change Password");
        changePass.setBorderPainted(false);
        changePass.setContentAreaFilled(false);
        changePass.setFocusPainted(false);
        changePass.setFocusable(true);
        changePass.setForeground(SUB_TEXT_COLOR);
        changePass.setHorizontalAlignment(SwingConstants.LEFT);
        changePass.setMargin(new Insets(0, 0, 0, 0));
        changePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                changePass.setForeground(TEXT_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                changePass.setForeground(SUB_TEXT_COLOR);
            }
        });

        this.changeAddress = new JButton("Change Address");
        changeAddress.setBorderPainted(false);
        changeAddress.setContentAreaFilled(false);
        changeAddress.setFocusPainted(false);
        changeAddress.setFocusable(true);
        changeAddress.setForeground(SUB_TEXT_COLOR);
        changeAddress.setHorizontalAlignment(SwingConstants.LEFT);
        changeAddress.setMargin(new Insets(0, 0, 0, 0));
        changeAddress.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                changeAddress.setForeground(TEXT_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                changeAddress.setForeground(SUB_TEXT_COLOR);
            }
        });

        JPanel settingsPanel = new JPanel();
        settingsPanel.setOpaque(false);
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setSize(200, 500);
        JLabel settingsLabel = new JLabel("Settings");
        settingsLabel.setForeground(SUB_TEXT_COLOR);
        settingsPanel.add(settingsLabel);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(changeName);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(changeAddress);
        settingsPanel.add(Box.createVerticalStrut(10));
        settingsPanel.add(changePass);

        logOut = new JButton("Log Out");
        logOut.setBorderPainted(false);
        logOut.setContentAreaFilled(false);
        logOut.setFocusPainted(false);
        logOut.setFocusable(true);
        logOut.setForeground(SUB_TEXT_COLOR);
        logOut.setHorizontalAlignment(SwingConstants.LEFT);
        logOut.setMargin(new Insets(0, 0, 0, 0));
        logOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logOut.setForeground(TEXT_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logOut.setForeground(SUB_TEXT_COLOR);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (onLogout != null) {
                    onLogout.run();
                }
            }
        });

        gbc.gridy = 0;
        add(logoLabel, gbc);
        gbc.gridy = 1;
        add(userInfoPanel, gbc);
        gbc.gridy = 2;
        add(cropsPanel, gbc);
        gbc.gridy = 3;
        add(settingsPanel, gbc);
        gbc.gridy = 4;
        add(logOut, gbc);

    }

    public static void setUIFont(FontUIResource f) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                FontUIResource originalFont = (FontUIResource) value;
                // CORRECTED LINE: Use the original font's style, but apply the new size and
                // family name.
                Font newFont = new Font(f.getFontName(), originalFont.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(newFont));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call superclass method to ensure proper painting

        Graphics2D g2d = (Graphics2D) g;

        // Define start and end colors
        Color startColor = new Color(76, 139, 63); // Steel Blue
        Color endColor = new Color(102, 177, 75); // Light Blue

        // Create a vertical gradient from top to bottom
        GradientPaint gp = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);

        // Set the paint and fill the rectangle
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(() -> {
    // JFrame frame = new JFrame("AniCore Lite Sidebar");
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //
    // // Create the new SideBar JPanel instance
    // SideBar sideBarPanel = new SideBar();
    //
    // // Add the JPanel to the JFrame's content pane
    // frame.getContentPane().add(sideBarPanel);
    //
    // frame.setSize(300, 800);
    // frame.setMinimumSize(new Dimension(300, 600));
    // frame.setVisible(true);
    // });
    // }

}