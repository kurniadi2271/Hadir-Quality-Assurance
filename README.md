# Hadir Quality Assurance Project

Proyek ini adalah implementasi pengujian Behavior-Driven Development (BDD) untuk aplikasi Hadir (sistem manajemen kehadiran) menggunakan Cucumber, Selenium WebDriver, dan TestNG.

## Fitur yang Diuji

### 1. Login
- Login dengan kredensial valid
- Login gagal dengan kredensial tidak valid

### 2. Dashboard
- Akses dan navigasi dashboard

### 3. Laporan Kehadiran
- Melihat laporan kehadiran karyawan

### 4. Laporan Cuti
- Mengelola dan melihat laporan cuti

### 5. Laporan Izin Terlambat
- Mengelola izin terlambat

### 6. Laporan Izin Pulang Cepat
- Mengelola izin pulang cepat

### 7. Laporan Sakit
- Mengelola laporan sakit

### 8. Laporan Lembur
- Mengelola laporan lembur

### 9. Laporan Koreksi
- Mengelola koreksi kehadiran

### 10. Download Absen
- Mengunduh data absensi

### 11. Reports All
- Laporan keseluruhan

## Struktur Proyek

```
src/
├── main/
│   ├── java/
│   │   └── com/juaracoding/kelompok1/
│   │       ├── drivers/
│   │       │   ├── DriverSingleton.java (Pola Singleton untuk WebDriver)
│   │       │   └── strategies/
│   │       ├── pages/
│   │       │   ├── BasePage.java (Kelas dasar untuk halaman)
│   │       │   ├── DashboardPage.java
│   │       │   ├── DownloadAbsen.java
│   │       │   ├── LaporanCuti.java
│   │       │   ├── LaporanIzinPulangCepat.java
│   │       │   ├── LaporanIzinTerlambat.java
│   │       │   ├── LaporanKehadiran.java
│   │       │   ├── LaporanKoreksi.java
│   │       │   ├── LaporanLembur.java
│   │       │   ├── LaporanSakit.java
│   │       │   └── ...
│   │       └── utils/
│   └── resources/
│       └── features/
│           ├── Dashboard.feature
│           ├── DownloadAbsen.feature
│           ├── LaporanCuti.feature
│           ├── LaporanIzinPulangCepat.feature
│           ├── LaporanIzinTerlambat.feature
│           ├── LaporanKehadiran.feature
│           ├── LaporanKoreksi.feature
│           ├── LaporanLembur.feature
│           ├── LaporanSakit.feature
│           ├── Login.feature
│           └── ReportsAll.feature
└── test/
    └── java/
        └── com/juaracoding/kelompok1/
            ├── DashboardSteps.java
            ├── DownloadAbsenTest.java
            ├── LaporanCutiTest.java
            ├── LaporanIzinPulangCepatTest.java
            ├── LaporanIzinTerlambatTest.java
            ├── LaporanKehadiranTest.java
            ├── LaporanKoreksiTest.java
            ├── LaporanLemburTest.java
            ├── LaporanSakitTest.java
            ├── LoginTest.java
            ├── ReportsTest.java
            └── TestRunner.java
```

## Prasyarat

- Java 8 atau lebih tinggi
- Maven 3.x
- WebDriver (ChromeDriver, GeckoDriver, dll.)
- Browser (Chrome, Firefox, dll.)

## Setup

1. Clone repositori ini:
   ```
   git clone <repository-url>
   cd Hadir-Quality-Assurance-main
   ```

2. Install dependencies:
   ```
   mvn clean install
   ```

3. Pastikan WebDriver sesuai dengan versi browser Anda.

## Menjalankan Test

Jalankan semua test menggunakan Maven:
```
mvn test
```

Atau jalankan TestRunner spesifik:
```
mvn test -Dtest=TestRunner
```

## Pelaporan

- Laporan Extent: `test-output/SparkReport/Index.html`
- Screenshot: `test-output/Screenshots/`
- Cucumber JSON: `target/cucumber.json`

## Teknologi yang Digunakan

- **Java**: Bahasa pemrograman utama
- **Maven**: Build tool
- **Cucumber**: BDD framework
- **Selenium WebDriver**: Automation tool
- **TestNG**: Testing framework
- **ExtentReports**: Reporting tool

## Kontribusi

1. Fork repositori
2. Buat branch fitur baru (`git checkout -b feature/AmazingFeature`)
3. Commit perubahan (`git commit -m 'Add some AmazingFeature'`)
4. Push ke branch (`git push origin feature/AmazingFeature`)
5. Buat Pull Request

## Documentation & Traceability

Detailed test cases and execution results can be found in the System Integration Testing (SIT) document:
- 📑 **[SIT Test Cases & Execution Report](https://docs.google.com/spreadsheets/d/1D2XC_ET07kQNebInsT2YS6l1CDmGYTHh3oYyxU3wIE8/edit?gid=1444309931#gid=1444309931)**

## Collaborators

- anekekarina99
- raraindiragani
- Syafiqb

```