// package com.plantfarmlogger.controller.dao;

// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.ArrayList;

// import com.plantfarmlogger.model.CropLog;


// public class CropLogDao {
//     ArrayList<CropLog> CropLogs = new ArrayList<CropLog>();

//     private final String CropLogFile = "src/main/resources/csv/croplogs.csv";

//     public CropLogDao() {
//         fetch();
//     }

//     public ArrayList<CropLog> getCropLogs() {
//         return CropLogs;
//     }

//     private void fetch() {
//         try (
//                 BufferedReader br = new BufferedReader(new FileReader(CropLogFile));) {
//             String line;

//             while ((line = br.readLine()) != null) {
//                 String[] spl = line.split(",");
//                 String plantType = spl[0];
//                 String soilType = spl[1];
//                 String lastFertilized = spl[2];
//                 String datePlanted = spl[3];

//                 double width = Double.parseDouble(spl[4]);
//                 double height = Double.parseDouble(spl[5]);
//                 double length = Double.parseDouble(spl[6]);

//                 CropLog n = new CropLog(plantType, soilType, lastFertilized, datePlanted, width, height, length);
//                 CropLogs.add(n);
//             }

//         } catch (IOException e) {
//             System.out.println("IO_ERROR theres no file " + CropLogFile);
//         }
//         System.out.println("SUCCESS");
//     }

//     private void saveToCSV() {

//         try (BufferedWriter bw = new BufferedWriter(new FileWriter(CropLogFile));

//         ) {
//             for (CropLog c : CropLogs) {
//                 bw.write(String.join(",", c.getPlantType(),
//                         c.getSoilType(),
//                         c.getLastFertilized(),
//                         c.getDatePlanted(),
//                         String.valueOf(c.getWidth()),
//                         String.valueOf(c.getHeight()),
//                         String.valueOf(c.getLength())));
//                 bw.newLine();
//             }

//         } catch (IOException e) {
//             System.out.println("IO_ERROR");
//         }
//         System.out.println("OPEnEd " + CropLogFile);
//     }

//     public void create(CropLog t) {
//         if (t == null) {
//             System.out.println("no user ");
//         }
//         CropLogs.add(t);
//         saveToCSV();

//     }

//     public void delete(CropLog t) {
//         int index = 0;
//         for (CropLog u : CropLogs) {

//             if (t == u) {
//                 CropLogs.remove(index);
//             }

//             index++;
//         }
//         saveToCSV();
//     }

//     public void printU() {
//         for (CropLog c : CropLogs) {
//             System.out.println(c);
//         }
//     }

// }
