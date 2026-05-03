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

## Lisensi

Proyek ini menggunakan lisensi MIT. Lihat file `LICENSE` untuk detail lebih lanjut.
            ├── LoginTest.java (Login and common step definitions)
            └── TestRunner.java (Cucumber test runner)
```

## Test Scenarios

### Login Feature (Login.feature)
- **Positive Test**: Successful login with valid credentials (standard_user / secret_sauce)
- **Negative Test**: Login failure with invalid credentials

### Product Sorting Feature (Sorting.feature)
- **Positive Test 1**: Sort products by price low to high
- **Positive Test 2**: Sort products by price high to low
- **Negative Test**: Invalid sort option handling
- All scenarios include reset app state before logout

### Add to Cart Feature (AddToCart.feature)
- **Positive Test 1**: Add single product to cart (Sauce Labs Backpack)
- **Positive Test 2**: Add all products to cart (6 products)
- **Negative Test**: Add non-existent product (cart remains unchanged)
- Positive tests include reset app state before logout

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Chrome or Firefox browser
- WebDriver for selected browser (ChromeDriver or GeckoDriver)

## Setup

1. Clone or download the project
2. Navigate to the project directory
3. Install dependencies:
   ```bash
   mvn clean install
   ```

## Running Tests

### Run all tests
```bash
mvn clean test
```

### Run specific test class
```bash
mvn test -Dtest=TestRunner
```

### Run tests with detailed output
```bash
mvn test -X
```

## Test Execution Flow

Each test scenario follows this pattern:
1. **Given**: Navigate to login page
2. **When**: Enter credentials and click login
3. **When**: Perform action (sort products / add to cart)
4. **Then**: Verify expected result
5. **And**: Reset app state (clears changes from inventory)
6. **And**: Logout (returns to login page)

The reset app state step ensures clean state between tests by clearing the cart and any applied filters.

## Reports

Test reports are generated in:
- `target/cucumber-report.html` - Cucumber HTML report
- `target/surefire-reports/` - TestNG reports with details
- `target/cucumber.json` - Cucumber JSON report for CI/CD integration

## Documentation & Traceability

Detailed test cases and execution results can be found in the System Integration Testing (SIT) document:
- 📑 **[SIT Test Cases & Execution Report](https://docs.google.com/spreadsheets/d/18dvOcYLqLHMY9V1MUqPc7gGYaX9_6Fr4/edit?usp=drive_link&ouid=102556497230646308513&rtpof=true&sd=true)**

## Key Technologies

| Technology | Version | Purpose |
|---|---|---|
| Selenium WebDriver | 4.43.0 | Browser automation |
| Cucumber | 7.34.3 | BDD framework |
| TestNG | 7.12.0 | Test execution |
| ExtentReports | 5.1.2 | Advanced reporting |
| Java | 17 | Programming language |

## Design Patterns Used

1. **Page Object Model (POM)**: Separates test logic from page interactions
2. **Singleton Pattern**: Single instance of WebDriver across tests
3. **Strategy Pattern**: Different browser implementations (Chrome/Firefox)

## Test Data

- **Valid Credentials**: standard_user / secret_sauce
- **Base URL**: https://www.saucedemo.com/
- **Browser**: Firefox (configurable)</content>