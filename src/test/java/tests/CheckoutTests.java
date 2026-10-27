package tests;

import core.BaseTest;
import core.DriverManager;
import core.Log;
import org.testng.Assert;
import org.testng.annotations.Test;
import saucedemo.CartPage;
import saucedemo.CheckoutPage;
import saucedemo.DashboardPage;
import saucedemo.LoginPage;

public class CheckoutTests extends BaseTest {

    @Test(priority = 1, groups = {"smoke"}, description = "Verifikasi alur lengkap checkout produk dari keranjang hingga pesanan selesai")
    public void testSuccessfulCheckoutFlow() {
        Log.info("Memulai test: End-to-End Successful Checkout Flow");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        Log.info("User login menggunakan credential standard user");
        loginPage.login(config.getProperty("standardUser"), config.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        Log.info("Menambahkan produk ke keranjang belanja");
        dashboardPage.clickAddToCart();

        Log.info("Mengklik ikon keranjang untuk masuk ke halaman Cart");
        dashboardPage.clickCartIcon();

        CartPage cartPage = new CartPage(DriverManager.getDriver());
        Log.info("Memverifikasi keberadaan produk di dalam keranjang");
        Assert.assertTrue(cartPage.isProductInCart(), "Produk seharusnya muncul di dalam keranjang belanja!");

        Log.info("Mengklik tombol Checkout di halaman Cart");
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());
        Log.info("Mengisi data informasi pelanggan pada formulir checkout");
        checkoutPage.inputCustomerInfo("Lailatul", "Eky", "68131");

        Log.info("Mengklik tombol Continue");
        checkoutPage.clickContinue();

        Log.info("Mengklik tombol Finish untuk menyelesaikan pesanan");
        checkoutPage.clickFinish();

        Log.info("Memverifikasi pesan sukses checkout muncul");
        Assert.assertTrue(checkoutPage.isCheckoutSuccessful(),
                "Pesan sukses 'Thank you for your order' seharusnya tampil di layar!");

        Log.info("Test Berhasil: Skenario End-to-End Checkout sukses dijalankan!");
    }
}