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
            DataFormatter formatter = new DataFormatter(); // Menyamakan tampilan Excel dengan String Java

            for (Row row : sheet) {
                StringBuilder rowContent = new StringBuilder();
                
                // Loop setiap cell, termasuk yang kosong (MissingCellPolicy)
                for (int cn = 0; cn < row.getLastCellNum(); cn++) {
                    Cell cell = row.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String cellValue = formatter.formatCellValue(cell).trim();
                    rowContent.append(cellValue.toLowerCase()).append(" ");
                }

                String fullRowText = rowContent.toString();
                
                // Log untuk debug (Hapus jika sudah jalan)
                // System.out.println("Memeriksa Baris " + row.getRowNum() + ": " + fullRowText);

                boolean allMatch = true;
                for (String keyword : keywords) {
                    if (keyword == null || !fullRowText.contains(keyword.toLowerCase().trim())) {
                        allMatch = false;
                        break;
                    }
                }

                if (allMatch) return true; 
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