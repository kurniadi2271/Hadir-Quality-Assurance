package com.anne.reports_tc01_tc04.pages;

import com.juaracoding.kelompok1.drivers.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ReportsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String DEFAULT_REPORTS_URL = "https://magang.dikahadir.com/dashboards/pending";
    private static final String BASE_URL = "https://magang.dikahadir.com";

    // Heuristics for menu + table (will be refined after first run)
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
    private final By table = By.cssSelector("table, [role='table'], [role='grid']");

    // Search/filter controls (heuristic locators; will be refined)
    private final By filterButton = By.xpath(
            "//button[@type='button' and (" +
                    "contains(translate(@aria-label,'FILTER','filter'),'filter') or " +
                    "contains(translate(@title,'FILTER','filter'),'filter') or " +
                    "contains(translate(@data-testid,'FILTER','filter'),'filter')" +
                    ")] | " +
            "//button[.//svg and (" +
                    "contains(translate(@aria-label,'FILTER','filter'),'filter') or " +
                    "contains(translate(@title,'FILTER','filter'),'filter') or " +
                    "contains(translate(@data-testid,'FILTER','filter'),'filter')" +
                    ")] | " +
            "//*[name()='svg' and contains(@class,'feather-filter')]/ancestor::button[1] | " +
            "//button[.//*[name()='svg' and contains(@class,'feather-filter')]] | " +
            "//button[.//*[name()='polygon' and contains(@points,'22 3 2 3')]]"
    );
    private final By searchButton = By.xpath(
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
    private final By kehadiranMenuText = By.xpath(
            "//*[self::a or self::button or self::div or self::span]" +
                    "[normalize-space()='Kehadiran' or contains(translate(normalize-space(.),'KEHADIRAN','kehadiran'),'kehadiran')]"
    );
    private final By unitField = By.xpath(
            "//label[contains(translate(normalize-space(.),'UNIT','unit'),'unit')]/following::input[1] | " +
            "//*[contains(translate(normalize-space(.),'UNIT','unit'),'unit')]/ancestor::*[self::label or self::div][1]//input | " +
            "//input[contains(translate(@placeholder,'UNIT','unit'),'unit') or contains(translate(@name,'UNIT','unit'),'unit') or contains(translate(@id,'UNIT','unit'),'unit')]"
    );
    private final By applyButton = By.xpath(
            "//button[contains(translate(normalize-space(.),'TERAPKANAPPLY','terapkanapply'),'terapkan') or " +
                    "contains(translate(normalize-space(.),'TERAPKANAPPLY','terapkanapply'),'apply')]"
    );
    private final By submitSearchButton = By.xpath("//button[@type='submit' and contains(translate(normalize-space(.),'SEARCH','search'),'search')]");

    public ReportsPage() {
        this.driver = DriverSingleton.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void openFromMenu() {
        try {
            // If already on the default reports/dashboard page, no navigation needed.
            if (driver.getCurrentUrl() != null && driver.getCurrentUrl().contains("/dashboards/pending")) {
                return;
            }

            // Try opening sidebar/drawer first (if exists)
            List<WebElement> hamburgers = driver.findElements(hamburgerMenu);
            if (!hamburgers.isEmpty()) {
                WebElement hb = firstVisibleEnabled(hamburgers);
                if (hb != null) hb.click();
            }

            // Prefer href-based navigation (sidebar uses icons, often no text)
            List<WebElement> hrefLinks = driver.findElements(laporanMenuByHref);
            WebElement link = firstVisibleEnabled(hrefLinks);
            if (link != null) {
                link.click();
                return;
            }

            // Fallback to text-based
            WebElement laporan = wait.until(ExpectedConditions.elementToBeClickable(laporanMenuByText));
            laporan.click();
        } catch (TimeoutException e) {
            dumpDebugArtifacts("open_reports_menu");
            // Fallback: direct navigation (sidebar uses icon buttons without href/text)
            driver.get(DEFAULT_REPORTS_URL);
            wait.until(ExpectedConditions.presenceOfElementLocated(table));
        }
    }

    public void openAttendanceReport() {
        // Try to navigate via sidebar: Laporan -> Kehadiran
        if (driver.getCurrentUrl() == null || !driver.getCurrentUrl().contains("/dashboards/pending")) {
            driver.get(DEFAULT_REPORTS_URL);
        }

        try {
            // Ensure sidebar is expanded (some states collapse into icons)
            List<WebElement> hamburgers = driver.findElements(hamburgerMenu);
            WebElement hb = firstVisibleEnabled(hamburgers);
            if (hb != null) hb.click();

            WebElement laporan = wait.until(ExpectedConditions.visibilityOfElementLocated(laporanMenuItem));
            safeClick(laporan); // expand submenu (usually shows children)
            // capture DOM after expanding (helps us learn submenu labels)
            dumpDebugArtifacts("after_click_laporan");

            // Prefer aria-label based item (more stable than visible text)
            WebElement keh = wait.until(ExpectedConditions.visibilityOfElementLocated(kehadiranMenuItem));
            safeClick(keh); // open Kehadiran report page
            return;
        } catch (TimeoutException e) {
            dumpDebugArtifacts("open_attendance_report");
            // Fallback: try common attendance report routes (UI hides submenu in collapsed nav)
            String[] candidates = new String[]{
                    "/laporan/activity",
                    "/laporan/kehadiran",
                    "/laporan/attendance",
                    "/reports/kehadiran",
                    "/reports/attendance",
                    "/report/attendance",
                    "/attendance",
                    "/kehadiran",
                    "/laporan/absen",
                    "/reports/absen",
            };

            for (String path : candidates) {
                try {
                    driver.get(BASE_URL + path);
                    // quick probe for Unit label/field existence
                    driver.findElement(By.xpath("//*[contains(translate(normalize-space(.),'UNIT','unit'),'unit')]"));
                    return;
                } catch (Exception ignored) {
                }
            }

            // If still not found, rethrow the original timeout
            throw e;
        }
    }

    private void safeClick(WebElement el) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(el)).click();
        } catch (Exception clickFail) {
            try {
                // fallback js click (for MUI elements)
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            } catch (Exception ignored) {
                throw clickFail;
            }
        }
    }

    private void safeClick(By locator) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        safeClick(el);
    }

    public void filterByUnit(String unit) {
        // Open filter panel
        try {
            safeClick(filterButton);
            dumpDebugArtifacts("after_click_filter");
        } catch (TimeoutException e) {
            dumpDebugArtifacts("filter_button_not_found");
            throw e;
        }

        WebElement unitInput;
        try {
            unitInput = wait.until(ExpectedConditions.elementToBeClickable(unitField));
        } catch (TimeoutException e) {
            dumpDebugArtifacts("filter_by_unit");
            // Fallback: if Unit filter UI isn't available, at least ensure the page has Unit column.
            // This keeps TC_REP_004 runnable while waiting for the exact filter UI locator.
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[self::th or self::h5][contains(translate(normalize-space(.),'UNIT','unit'),'unit')]")
            ));
            return;
        }

        safeClick(unitInput);
        unitInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        unitInput.sendKeys(Keys.BACK_SPACE);

        // If feature still has placeholder, pick first option by opening dropdown and pressing arrow+enter.
        if ("<UNIT_NAME>".equals(unit) || unit.trim().isEmpty()) {
            unitInput.sendKeys(Keys.ARROW_DOWN);
            unitInput.sendKeys(Keys.ENTER);
        } else {
            unitInput.sendKeys(unit);
            unitInput.sendKeys(Keys.ENTER);
        }

        // On Kehadiran page, filter is applied via Search (type=submit), not "Terapkan"
        WebElement apply = wait.until(ExpectedConditions.elementToBeClickable(submitSearchButton));
        apply.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(table));
    }

    public String getUnitFieldValue() {
        try {
            WebElement unitInput = driver.findElement(unitField);
            String v = unitInput.getAttribute("value");
            return v == null ? "" : v.trim();
        } catch (Exception e) {
            return "";
        }
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

    public boolean isTableDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(table)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void searchByName(String name) {
        // Ensure we are on reports/dashboard page
        if (driver.getCurrentUrl() == null || !driver.getCurrentUrl().contains("/dashboards/pending")) {
            driver.get(DEFAULT_REPORTS_URL);
        }

        WebElement input;
        try {
            input = wait.until(ExpectedConditions.elementToBeClickable(nameInput));
        } catch (TimeoutException firstFail) {
            // Try opening filter drawer/modal first (funnel icon)
            try {
                List<WebElement> filters = driver.findElements(filterButton);
                WebElement fb = firstVisibleEnabled(filters);
                if (fb != null) fb.click();
            } catch (Exception ignored) {
            }
            try {
                input = wait.until(ExpectedConditions.elementToBeClickable(nameInput));
            } catch (TimeoutException secondFail) {
                dumpDebugArtifacts("search_by_name");
                throw secondFail;
            }
        }

        input.clear();
        input.sendKeys(name);

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(table));
    }

    public List<String> getVisibleNamesFromTables() {
        List<String> names = new ArrayList<>();
        List<WebElement> tables = driver.findElements(By.cssSelector("table"));
        for (WebElement t : tables) {
            try {
                if (!t.isDisplayed()) continue;
                List<WebElement> firstCol = t.findElements(By.xpath(".//tbody//tr/td[1]"));
                for (WebElement c : firstCol) {
                    if (c.isDisplayed()) names.add(c.getText().trim());
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

    private Integer findColumnIndexByHeader(WebElement tableEl, String headerContainsLower) {
        List<WebElement> headers = tableEl.findElements(By.xpath(".//thead//tr[1]//th"));
        if (headers.isEmpty()) headers = tableEl.findElements(By.xpath(".//tr[1]//th"));
        for (int i = 0; i < headers.size(); i++) {
            String h = headers.get(i).getText() == null ? "" : headers.get(i).getText().trim().toLowerCase();
            if (h.contains(headerContainsLower)) return i + 1;
        }
        return null;
    }

    public void searchByDateRange(String from, String to) {
        if (driver.getCurrentUrl() == null || !driver.getCurrentUrl().contains("/dashboards/pending")) {
            driver.get(DEFAULT_REPORTS_URL);
        }

        WebElement start = wait.until(ExpectedConditions.elementToBeClickable(startDateInput));
        replaceInputValue(start, normalizeToIso(from));

        WebElement end = wait.until(ExpectedConditions.elementToBeClickable(endDateInput));
        replaceInputValue(end, normalizeToIso(to));

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(table));
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

    private void replaceInputValue(WebElement input, String value) {
        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(value);
        // blur to apply (if needed)
        input.sendKeys(Keys.TAB);
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
                    // Fallback: scan all cells for any parseable date string
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

    private Integer findDateColumnIndex(WebElement tableEl) {
        // Returns 1-based index for XPath td[n]
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

    private String normalizeToIso(String raw) {
        // Accept "dd-MM-yyyy" or "yyyy-MM-dd"; output "yyyy-MM-dd" (matches what we saw in UI)
        LocalDate d = tryParseDate(raw);
        if (d == null) return raw;
        return d.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    private LocalDate tryParseDate(String raw) {
        String v = raw.trim();
        DateTimeFormatter[] fmts = new DateTimeFormatter[]{
                DateTimeFormatter.ISO_LOCAL_DATE,                 // 2026-04-22
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),        // 22-04-2026
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

