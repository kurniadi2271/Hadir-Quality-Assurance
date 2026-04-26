package com.juaracoding.kelompok1.utils;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;

public class ExcelReader {

    public static String getLatestDownloadFile(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".xlsx") || name.endsWith(".xls"));
        if (files == null || files.length == 0) return null;

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile.getAbsolutePath();
    }

    // METHOD VALIDASI SPESIFIK: Cek apakah NIK, Nama, dan Unit ada di BARIS YANG SAMA
    public static boolean verifyRowData(String filePath, String[] keywords) {
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                StringBuilder rowContent = new StringBuilder();
                for (Cell cell : row) {
                    rowContent.append(cell.toString().toLowerCase()).append(" ");
                }

                boolean allMatch = true;
                for (String keyword : keywords) {
                    if (!rowContent.toString().contains(keyword.toLowerCase())) {
                        allMatch = false;
                        break;
                    }
                }

                if (allMatch) return true; // Ketemu baris yang isinya cocok semua keyword
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Ngitung jumlah file di folder (buat mastiin nggak ada file nambah)
    public static int getFileCount(String directoryPath) {
        File dir = new File(directoryPath);
        File[] files = dir.listFiles();
        return (files != null) ? files.length : 0;
    }

    // Ngitung baris di Excel (buat cek data kosong)
    public static int getRowCount(String filePath) {
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);
            return sheet.getPhysicalNumberOfRows(); 
        } catch (Exception e) {
            return 0;
        }
    }
}