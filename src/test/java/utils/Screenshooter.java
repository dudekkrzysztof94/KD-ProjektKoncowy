package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Screenshooter {

    private static final String SCREENSHOT_FOLDER = "screenshots/";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    public static void takeScreenshot(WebDriver driver) {
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(FORMATTER);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path screenshotDirectory = Paths.get(SCREENSHOT_FOLDER);

        try {
            if (Files.notExists(screenshotDirectory)) {
                Files.createDirectories(screenshotDirectory);
            }
            File destinationFile = new File(screenshotDirectory + "/screenshot" + formattedDate + ".png");
            FileUtils.copyFile(screenshot, destinationFile);
        } catch (IOException e) {
            System.err.println("Error during saving screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
