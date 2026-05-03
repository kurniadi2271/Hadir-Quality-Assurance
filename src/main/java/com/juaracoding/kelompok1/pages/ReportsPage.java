package com.juaracoding.kelompok1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReportsPage extends BasePage {

    private static final String DEFAULT_REPORTS_URL = "https://magang.dikahadir.com/laporan/activity";
    private static final String BASE_URL = "https://magang.dikahadir.com";

    // ── Sidebar / Menu locators (By-based, karena heuristic) ──
    private final By hamburgerMenu = By.cssSelector(
            "button[aria-label*='menu' i], button[aria-label*='navigation' i], " +
            "button[aria-label*='sidebar' i], button[aria-label*='drawer' i]"
    );
    private final By laporanMenuByText = By.xpath(
            "//*[self::a or self::button or self::span]" +
                    "[contains(translate(normalize-space(.),'LAPORAN','laporan'),'laporan') or " +
                    " contains(translate(normalize-space(.),'REPORTS','reports'),'report')]"
    );
    private final By laporanMenuByHref = By.xpath(
            "//a[@href and (" +
                    "contains(translate(@href,'LAPORAN','laporan'),'laporan') or " +
                    "contains(translate(@href,'REPORTS','reports'),'report') or " +
                    "contains(translate(@href,'REPORTS','reports'),'reports')" +
                    ")]"
    );
    private final By tableLocator = By.cssSelector("table, [role='table'], [role='grid'], .MuiTableContainer-root");

    // ── Filter / Search locators ──
    private final By filterButton = By.xpath(
            "//button[contains(@class,'MuiButton-containedSecondary')] | " +
            "//button[@type='button' and (" +
                    "contains(translate(@aria-label,'FILTER','filter'),'filter') or " +
                    "contains(translate(@title,'FILTER','filter'),'filter') or " +
                    "contains(translate(@data-testid,'FILTER','filter'),'filter')" +
                    ")] | " +
            "//*[name()='svg' and contains(@class,'feather-filter')]/ancestor::button[1] | " +
            "//button[.//*[name()='svg' and contains(@class,'feather-filter')]] | " +
            "//button[.//*[name()='polygon' and contains(@points,'22 3 2 3')]]"
    );
    private final By searchButton = By.xpath(
            "//button[contains(@class,'MuiButton-containedPrimary') and contains(normalize-space(.),'Search')] | " +
            "//button[" +
                    "contains(translate(normalize-space(.),'SEARCHCARI','searchcari'),'search') or " +
                    "contains(translate(normalize-space(.),'SEARCHCARI','searchcari'),'cari') or " +
                    "contains(translate(@aria-label,'SEARCH','search'),'search')" +
                    "]"
    );
    private final By startDateInput = By.xpath(
            "//*[normalize-space()='Start Date' or contains(translate(normalize-space(.),'START DATE','start date'),'start date')]" +
                    "/following::input[1]"
    );
    private final By endDateInput = By.xpath(
            "//*[normalize-space()='End Date' or contains(translate(normalize-space(.),'END DATE','end date'),'end date')]" +
                    "/following::input[1]"
    );
    private final By nameInput = By.xpath(
            "//input[@id='search'] | " +
            "//input[contains(@placeholder,'Cari berdasarkan nama')] | " +
            "//input[(" +
                    "@type='text' or @type='search'" +
                    ") and (" +
                    "contains(translate(@placeholder,'NAMA','nama'),'nama') or " +
                    "contains(translate(@name,'NAMA','nama'),'nama') or " +
                    "contains(translate(@id,'NAMA','nama'),'nama') or " +
                    "contains(translate(@placeholder,'NAME','name'),'name')" +
                    ")]"
    );
    private final By laporanMenuItem = By.xpath(
            "//*[@aria-label='Laporan' or contains(translate(@aria-label,'LAPORAN','laporan'),'laporan')]"
    );
    private final By kehadiranMenuItem = By.xpath(
            "//*[@aria-label='Kehadiran' or contains(translate(@aria-label,'KEHADIRAN','kehadiran'),'kehadiran')]"
    );
    private final By unitField = By.xpath(
            "//input[@id='job_departement'] | " +
            "//input[contains(@placeholder,'Cari Departemen')] | " +
            "//label[contains(translate(normalize-space(.),'UNIT','unit'),'unit')]/following::input[1] | " +
            "//*[contains(translate(normalize-space(.),'UNIT','unit'),'unit')]/ancestor::*[self::label or self::div][1]//input | " +
            "//input[contains(translate(@placeholder,'UNIT','unit'),'unit') or contains(translate(@name,'UNIT','unit'),'unit') or contains(translate(@id,'UNIT','unit'),'unit')]"
    );
    private final By submitSearchButton = By.xpath(
            "//button[contains(normalize-space(.),'Terapkan')] | " +
            "//button[@type='submit' and contains(translate(normalize-space(.),'SEARCH','search'),'search')]"
    );

    public ReportsPage(WebDriver driver) {
        super(driver);
    }

    // ── Navigation ──

    public void openFromMenu() {
        try {
            String url = driver.getCurrentUrl();
            if (url != null && url.contains("/laporan/activity")) {
                return;
            }

            // Try sidebar navigation first
            List<WebElement> hamburgers = driver.findElements(hamburgerMenu);
            WebElement hb = firstVisibleEnabled(hamburgers);
            if (hb != null) hb.click();

            List<WebElement> hrefLinks = driver.findElements(laporanMenuByHref);
            WebElement link = firstVisibleEnabled(hrefLinks);
            if (link != null) {
                link.click();
                // Wait for the Kehadiran submenu and click it
                try {
                    WebElement keh = wait.until(ExpectedConditions.visibilityOfElementLocated(kehadiranMenuItem));
                    safeClick(keh);
                } catch (TimeoutException ignored) {
                }
                return;
            }

            WebElement laporan = wait.until(ExpectedConditions.elementToBeClickable(laporanMenuByText));
            laporan.click();
        } catch (TimeoutException e) {
            dumpDebugArtifacts("open_reports_menu");
            driver.get(DEFAULT_REPORTS_URL);
            wait.until(ExpectedConditions.presenceOfElementLocated(tableLocator));
        }
    }

    public void openAttendanceReport() {
        String url = driver.getCurrentUrl();
        if (url != null && url.contains("/laporan/activity")) {
            return; // Already on the attendance report page
        }

        // Navigate directly to the attendance report page
        driver.get(DEFAULT_REPORTS_URL);
        wait.until(ExpectedConditions.presenceOfElementLocated(tableLocator));
    }

    // ── Filter & Search ──

    public void filterByUnit(String unit) {
        // Click the orange filter (funnel) button to open the filter modal
        try {
            safeClick(filterButton);
            dumpDebugArtifacts("after_click_filter");
        } catch (TimeoutException e) {
            dumpDebugArtifacts("filter_button_not_found");
            throw e;
        }

        // Wait for the filter modal/dialog to appear and find the unit/department input
        WebElement unitInput;
        try {
            unitInput = wait.until(ExpectedConditions.presenceOfElementLocated(unitField));
        } catch (TimeoutException e) {
            dumpDebugArtifacts("filter_by_unit");
            wait.until(ExpectedConditions.presenceOfElementLocated(tableLocator));
            return;
        }

        // Focus the input via JS first, then use real sendKeys to trigger MUI Autocomplete
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true); arguments[0].focus();", unitInput);
        try { Thread.sleep(300); } catch (InterruptedException ignored) {}

        String searchText = ("<UNIT_NAME>".equals(unit) || unit.trim().isEmpty()) ? "a" : unit;

        try {
            unitInput.clear();
            unitInput.sendKeys(searchText);
        } catch (Exception e) {
            // Fallback to JS if sendKeys fails
            js.executeScript(
                "var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                "setter.call(arguments[0], arguments[1]);" +
                "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
                "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                unitInput, searchText
            );
        }

        // Try to find listbox options with a short timeout (3s max)
        // Using findElements (non-blocking) to avoid browser crash from 30s timeout
        WebElement firstOption = null;
        long deadline = System.currentTimeMillis() + 3000;
        while (System.currentTimeMillis() < deadline) {
            List<WebElement> options = driver.findElements(
                    By.xpath("//ul[@role='listbox']//li | //li[contains(@id,'job_departement-option')]"));
            if (!options.isEmpty()) {
                firstOption = options.get(0);
                break;
            }
            try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        }

        if (firstOption != null) {
            safeClick(firstOption);
        } else {
            // Fallback: keyboard navigation
            try {
                unitInput.sendKeys(Keys.ARROW_DOWN);
                try { Thread.sleep(200); } catch (InterruptedException ignored) {}
                unitInput.sendKeys(Keys.ENTER);
            } catch (Exception ignored) {}
        }

        // Click "Terapkan" using safeClick (JS fallback) to handle MUI Dialog overlay
        WebElement apply = wait.until(ExpectedConditions.presenceOfElementLocated(submitSearchButton));
        safeClick(apply);
        wait.until(ExpectedConditions.presenceOfElementLocated(tableLocator));
    }

    public String getUnitFieldValue() {
        try {
            WebElement input = driver.findElement(unitField);
            String v = input.getAttribute("value");
            return v == null ? "" : v.trim();
        } catch (Exception e) {
            return "";
        }
    }

    public void searchByName(String name) {
        // Ensure we are on the reports/laporan page
        String url = driver.getCurrentUrl();
        if (url == null || !url.contains("/laporan/activity")) {
            driver.get(DEFAULT_REPORTS_URL);
            wait.until(ExpectedConditions.presenceOfElementLocated(tableLocator));
        }

        WebElement input;
        try {
            input = wait.until(ExpectedConditions.elementToBeClickable(nameInput));
        } catch (TimeoutException firstFail) {
            dumpDebugArtifacts("search_by_name");
            throw firstFail;
        }

        input.clear();
        input.sendKeys(name);

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(tableLocator));
        // Give React time to re-render results, then dump for debugging
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        dumpDebugArtifacts("after_search_by_name");
    }

    public void searchByDateRange(String from, String to) {
        String url = driver.getCurrentUrl();
        if (url == null || !url.contains("/laporan/activity")) {
            driver.get(DEFAULT_REPORTS_URL);
        }

        WebElement start = wait.until(ExpectedConditions.elementToBeClickable(startDateInput));
        replaceInputValue(start, normalizeToIso(from));

        WebElement end = wait.until(ExpectedConditions.elementToBeClickable(endDateInput));
        replaceInputValue(end, normalizeToIso(to));

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(tableLocator));
    }

    // ── Data Getters ──

    public boolean isTableDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(tableLocator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public List<String> getVisibleNamesFromTables() {
        List<String> names = new ArrayList<>();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> tables = driver.findElements(By.cssSelector("table"));
        for (WebElement t : tables) {
            try {
                if (!t.isDisplayed()) continue;
                List<WebElement> rows = t.findElements(By.xpath(".//tbody//tr"));
                for (WebElement row : rows) {
                    List<WebElement> cells = row.findElements(By.xpath(".//td"));
                    if (cells.isEmpty()) continue;
                    WebElement firstCell = cells.get(0);
                    if (!firstCell.isDisplayed()) continue;
                    // Use JS innerText to get visible text regardless of nested structure
                    String text = (String) js.executeScript(
                            "return arguments[0].innerText;", firstCell);
                    if (text != null && !text.trim().isEmpty()) {
                        names.add(text.trim());
                    }
                }
            } catch (Exception ignored) {
            }
        }
        return names;
    }

    public List<String> getVisibleUnitsFromTable() {
        List<String> units = new ArrayList<>();
        try {
            WebElement t = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table")));
            Integer unitCol = findColumnIndexByHeader(t, "unit");
            if (unitCol == null) return units;
            List<WebElement> cells = t.findElements(By.xpath(".//tbody//tr/td[" + unitCol + "]"));
            for (WebElement c : cells) {
                if (c.isDisplayed()) units.add(c.getText().trim());
            }
        } catch (Exception ignored) {
        }
        return units;
    }

    public String getStartDateValue() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(startDateInput)).getAttribute("value");
        } catch (Exception e) {
            return null;
        }
    }

    public String getEndDateValue() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(endDateInput)).getAttribute("value");
        } catch (Exception e) {
            return null;
        }
    }

    public List<LocalDate> getVisibleDatesFromTables(LocalDate from, LocalDate to) {
        List<LocalDate> dates = new ArrayList<>();
        List<WebElement> tables = driver.findElements(By.cssSelector("table"));
        for (WebElement t : tables) {
            try {
                if (!t.isDisplayed()) continue;
                Integer dateColIndex = findDateColumnIndex(t);
                if (dateColIndex != null) {
                    List<WebElement> dateCells = t.findElements(By.xpath(".//tbody//tr/td[" + dateColIndex + "]"));
                    for (WebElement c : dateCells) {
                        if (!c.isDisplayed()) continue;
                        String txt = c.getText() == null ? "" : c.getText().trim();
                        if (txt.isEmpty()) continue;
                        LocalDate parsed = tryParseDate(txt);
                        if (parsed != null) dates.add(parsed);
                    }
                } else {
                    List<WebElement> cells = t.findElements(By.xpath(".//tbody//tr/td"));
                    for (WebElement c : cells) {
                        if (!c.isDisplayed()) continue;
                        String txt = c.getText() == null ? "" : c.getText().trim();
                        if (txt.isEmpty()) continue;
                        LocalDate parsed = tryParseDate(txt);
                        if (parsed != null) dates.add(parsed);
                    }
                }
            } catch (Exception ignored) {
            }
        }
        if (dates.isEmpty()) {
            dumpDebugArtifacts("date_range_no_dates");
        }
        return dates;
    }

    // ── Private helpers ──

    private void safeClick(WebElement el) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(el)).click();
        } catch (Exception clickFail) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            } catch (Exception ignored) {
                throw clickFail;
            }
        }
    }

    private void safeClick(By locator) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        safeClick(el);
    }

    private WebElement firstVisibleEnabled(List<WebElement> els) {
        for (WebElement el : els) {
            try {
                if (el.isDisplayed() && el.isEnabled()) return el;
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    private Integer findColumnIndexByHeader(WebElement tableEl, String headerContainsLower) {
        List<WebElement> headers = tableEl.findElements(By.xpath(".//thead//tr[1]//th"));
        if (headers.isEmpty()) headers = tableEl.findElements(By.xpath(".//tr[1]//th"));
        for (int i = 0; i < headers.size(); i++) {
            String h = headers.get(i).getText() == null ? "" : headers.get(i).getText().trim().toLowerCase();
            if (h.contains(headerContainsLower)) return i + 1;
        }
        return null;
    }

    private Integer findDateColumnIndex(WebElement tableEl) {
        List<WebElement> headers = tableEl.findElements(By.xpath(".//thead//tr[1]//th"));
        if (headers.isEmpty()) headers = tableEl.findElements(By.xpath(".//tr[1]//th"));
        for (int i = 0; i < headers.size(); i++) {
            String h = headers.get(i).getText() == null ? "" : headers.get(i).getText().trim().toLowerCase();
            if (h.contains("tanggal") || h.contains("date")) {
                return i + 1;
            }
        }
        return null;
    }

    private void replaceInputValue(WebElement input, String value) {
        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(value);
        input.sendKeys(Keys.TAB);
    }

    private String normalizeToIso(String raw) {
        LocalDate d = tryParseDate(raw);
        if (d == null) return raw;
        return d.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    private LocalDate tryParseDate(String raw) {
        String v = raw.trim();
        DateTimeFormatter[] fmts = new DateTimeFormatter[]{
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("d-M-yyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH),
                DateTimeFormatter.ofPattern("dd MMM yyyy", new Locale("id", "ID")),
                DateTimeFormatter.ofPattern("d MMM yyyy", new Locale("id", "ID"))
        };
        for (DateTimeFormatter f : fmts) {
            try {
                return LocalDate.parse(v, f);
            } catch (DateTimeParseException ignored) {
            }
        }
        return null;
    }

    private void dumpDebugArtifacts(String prefix) {
        try {
            System.out.println("[DEBUG] URL: " + driver.getCurrentUrl());
            System.out.println("[DEBUG] Title: " + driver.getTitle());

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File out = new File("target/" + prefix + "_screenshot.png");
            FileHandler.createDir(out.getParentFile());
            FileHandler.copy(src, out);
            System.out.println("[DEBUG] Screenshot saved: " + out.getAbsolutePath());

            File htmlOut = new File("target/" + prefix + "_page.html");
            FileHandler.createDir(htmlOut.getParentFile());
            Files.writeString(htmlOut.toPath(), driver.getPageSource(), StandardCharsets.UTF_8);
            System.out.println("[DEBUG] Page source saved: " + htmlOut.getAbsolutePath());
        } catch (Exception ignored) {
        }
    }
}
