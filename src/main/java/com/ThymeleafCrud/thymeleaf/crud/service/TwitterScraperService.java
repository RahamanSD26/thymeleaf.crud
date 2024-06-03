package com.ThymeleafCrud.thymeleaf.crud.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@Service
public class TwitterScraperService {

    private static final Logger logger = Logger.getLogger(TwitterScraperService.class.getName());

    @Value("${twitter.username}")
    private String twitterUsername;

    @Value("${twitter.password}")
    private String twitterPassword;

    @Value("${pyproxy.apiKey}")
    private String pyProxyApiKey;

    private String currentProxyIp;

    public String[] fetchTrendingTopics() {
        String proxyIp = getProxy();
        currentProxyIp = proxyIp; // Store the current proxy IP
        WebDriver driver = setupDriver(proxyIp);

        try {
            logger.info("Navigating to Twitter login page");
            driver.get("https://twitter.com/login");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Log in to Twitter
            logger.info("Attempting to log in to Twitter");
            WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("text")));
            usernameField.sendKeys(twitterUsername);
            usernameField.submit();

            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
            passwordField.sendKeys(twitterPassword);
            passwordField.submit();

            logger.info("Navigating to Twitter trending topics");
            driver.get("https://twitter.com/explore/tabs/trending");

            List<WebElement> trendingTopics = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@data-testid='trend']")));
            String[] trends = new String[5];
            for (int i = 0; i < 5 && i < trendingTopics.size(); i++) {
                trends[i] = trendingTopics.get(i).getText();
            }

            return trends;

        } catch (Exception e) {
            logger.severe("Error fetching trending topics: " + e.getMessage());
            e.printStackTrace();
            return new String[0];
        } finally {
            driver.quit();
        }
    }

    public String getProxy() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet("https://api.pyproxy.com/get-proxy?apiKey=" + pyProxyApiKey);
            HttpResponse response = httpClient.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            // Assuming PYProxy API returns a list of proxies separated by newlines
            String[] proxies = responseBody.split("\n");
            String selectedProxy = proxies[new Random().nextInt(proxies.length)];
            logger.info("Selected proxy: " + selectedProxy);
            return selectedProxy;

        } catch (Exception e) {
            logger.severe("Error getting proxy: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private WebDriver setupDriver(String proxyIp) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver-win64\\chromedriver.exe");

        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxyIp)
                .setFtpProxy(proxyIp)
                .setSslProxy(proxyIp);

        ChromeOptions options = new ChromeOptions();

        options.setProxy(proxy);
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        return new ChromeDriver(options);
    }
}
