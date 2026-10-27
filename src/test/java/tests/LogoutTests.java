package tests;

import core.BaseTest;
import core.DriverManager;
import core.Log;
import org.testng.Assert;
import org.testng.annotations.Test;
import saucedemo.DashboardPage;
import saucedemo.LoginPage;

public class LogoutTests extends BaseTest {

    @Test(priority = 1, groups = {"smoke"}, description = "Verifikasi user berhasil logout melalui sidebar menu")
    public void testSuccessfulLogout() {
        Log.info("Memulai test: Logout dari sistem");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        Log.info("User melakukan login dengan standard user");
        loginPage.login(config.getProperty("standardUser"), config.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        Log.info("Mengklik ikon burger menu di dashboard");
        dashboardPage.clickBurgerMenu();

        Log.info("Mengklik opsi Logout pada sidebar");
        dashboardPage.clickLogout();

        Log.info("Memverifikasi user telah berada di halaman login");

        // Ubah menjadi assertTrue karena kita memverifikasi user SUDAH di halaman login
        Assert.assertTrue(loginPage.isAtLoginPage(), "User seharusnya sudah berada di halaman login!");

        Log.info("Test Berhasil: User sukses melakukan logout!");
    }

    @Test(priority = 2, groups = {"smoke"}, description = "Verifikasi user tidak bisa kembali ke dashboard menggunakan tombol Back browser setelah logout")
    public void testPreventAccessAfterLogout() {
        Log.info("Memulai test: Keamanan navigasi Back browser setelah Logout");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login(config.getProperty("standardUser"), config.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickBurgerMenu();
        dashboardPage.clickLogout();

        Log.info("Simulasi menekan tombol Back di browser");
        DriverManager.getDriver().navigate().back();

        Log.info("Memverifikasi bahwa sesi telah berakhir");

        boolean isProductsStillDisplayed;
        try {
            isProductsStillDisplayed = dashboardPage.isProductsTitleDisplayed();
        } catch (Exception e) {
            isProductsStillDisplayed = false;
        }

        Assert.assertFalse(isProductsStillDisplayed, "User tidak boleh melihat halaman Products setelah logout dan tekan Back!");

        Log.info("Test Berhasil: Sesi aman, user tidak bisa kembali ke dashboard!");
    }
}