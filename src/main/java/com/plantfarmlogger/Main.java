package com.plantfarmlogger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.plantfarmlogger.view.MainWindow;

public class Main {
    public static void main(String[] args) {

        System.out.println("Farm Logger start");

        ensureCsvResources();

        SwingUtilities.invokeLater(() -> {
            try {

                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            MainWindow mainWindow = new MainWindow();

            mainWindow.setVisible(true);
        });
    }

    private static void ensureCsvResources() {
        Path csvDir = Path.of("src", "main", "resources", "csv");
        List<String> requiredFiles = List.of(
                "cropbeds.csv",
                "croplogs.csv",
                "crops.csv",
                "users.csv");

        try {
            if (Files.notExists(csvDir)) {
                Files.createDirectories(csvDir);
                System.out.println("Created directory: " + csvDir.toAbsolutePath());
            }

            for (String fileName : requiredFiles) {
                Path filePath = csvDir.resolve(fileName);
                if (Files.notExists(filePath)) {
                    Files.createFile(filePath);
                    System.out.println("Created file: " + filePath.toAbsolutePath());
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to prepare CSV resources: " + e.getMessage());
        }
    }
}