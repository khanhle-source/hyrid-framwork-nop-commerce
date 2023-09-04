package nopCommerce.userpageUIs;

public class UserBasePageUI {
    public static final String ADDRESS_LINK = "xpath=//div[@class='listbox']//li[contains(@class, 'customer-addresses')]";
    public static final String REWARD_POINT_LINK = "xpath=//div[@class='listbox']//li[contains(@class, 'reward-points')]";
    public static final String PRODUCT_REVIEW_LINK = "xpath=//div[@class='listbox']//li[contains(@class, 'customer-review')]";
    public static final String LOGOUT_LINK_AT_USER = "xpath=//a[@class='ico-logout']";
    public static final String LOGOUT_LINK_AT_ADMIN = "xpath=//a[text()='Logout']";
    public static final String DYNAMIC_LINK = "xpath=//div[@class='listbox']//li/a[text()='%s']";

    //Pattern Object
    public static final String DYNAMIC_PAGES_AT_MY_ACCOUNT_AREA = "xpath=//div[contains(@class,'account-navigation')]//a[text()='%s']";
    public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
    public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
    public static final String DYNAMIC_DROPDOWN_BY_NAME = "xpath=//select[@name='%s']";
    public static final  String DYNAMIC_RADIO_BUTTON_BY_NAME = "xpath=//label[text()='%s']/preceding-sibling::input";

}
