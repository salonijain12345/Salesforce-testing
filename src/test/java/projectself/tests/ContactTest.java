package projectself.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import projectself.base.BaseTest;
import projectself.pages.ContactPage;
import projectself.pages.HomePage;
import projectself.pages.LoginPage;

public class ContactTest extends BaseTest {

    @Test(description = "Create contact directly from Contacts tab",enabled=false)
    public void createContactFromTabTest() throws InterruptedException {
        driver.get(prop.getProperty("url"));

        LoginPage login = new LoginPage(driver);
        login.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(3000);

        HomePage home = new HomePage(driver);
        home.goToSales();

        ContactPage contactPage = new ContactPage(driver);
        contactPage.goToContactsTab();

        String lastName = "Doe" + System.currentTimeMillis();
        String accountName = "TestCompany";
        contactPage.createContactFromContactsTab(lastName, accountName);

        String header = contactPage.getContactHeaderText();
        Assert.assertTrue(header.contains("Doe"), "Contact creation failed or header mismatch");
    }
    
    @Test(description = "Edit an existing contact",enabled=false)
    public void testEditContact() throws InterruptedException {
        driver.get(prop.getProperty("url"));

        LoginPage login = new LoginPage(driver);
        login.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(4000);

        HomePage home = new HomePage(driver);
        home.goToSales();

        ContactPage contactPage = new ContactPage(driver);
        contactPage.goToContactsTab();

        String originalLastName = "EditTest" + System.currentTimeMillis();
        String accountName = "TestCompany";
        contactPage.createContactFromContactsTab(originalLastName, accountName);

        Thread.sleep(3000); // Wait before editing

        String newLastName = "UpdatedName" + System.currentTimeMillis();
        contactPage.editContact(originalLastName, newLastName);

        Thread.sleep(2000);
        Assert.assertTrue(contactPage.getContactHeaderText().contains("UpdatedName"), "Contact edit failed");
    }
    
    @Test(description = "Delete an existing contact",enabled=false)
    public void testDeleteContact() throws InterruptedException {
        driver.get(prop.getProperty("url"));

        LoginPage login = new LoginPage(driver);
        login.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(4000);

        HomePage home = new HomePage(driver);
        home.goToSales();

        ContactPage contactPage = new ContactPage(driver);
        contactPage.goToContactsTab();

        String contactToDelete = "DeleteTest" + System.currentTimeMillis();
        String accountName = "TestCompany";
        contactPage.createContactFromContactsTab(contactToDelete, accountName);

        Thread.sleep(3000);
        contactPage.deleteContact(contactToDelete);

        Thread.sleep(2000);
        boolean found = contactPage.searchContact(contactToDelete);
        Assert.assertFalse(found, "Contact still visible after deletion");
    }
    @Test(description = "Search for a contact")
    public void testSearchContact() throws InterruptedException {
        driver.get(prop.getProperty("url"));

        LoginPage login = new LoginPage(driver);
        login.login(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(4000);

        HomePage home = new HomePage(driver);
        home.goToSales();

        ContactPage contactPage = new ContactPage(driver);
        contactPage.goToContactsTab();

        String contactToSearch = "SearchTest" + System.currentTimeMillis();
        String accountName = "TestCompany";
        contactPage.createContactFromContactsTab(contactToSearch, accountName);

        Thread.sleep(3000);
        boolean found = contactPage.searchContact(contactToSearch);
        Assert.assertTrue(found, "Contact not found in global search");
    }

}
