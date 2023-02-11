package jQuery.pageUIs;

public class HomePageUI {
    public static final String PAGINATION_PAGE_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String INPUT_TEXTBOX = "xpath=//div[@class='qgrd-header-text' and text()='%s']/parent::div/following-sibling::input";
    public static final String ACTIVE_PAGE_NUMBER = "xpath=//a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String TOTAL_PAGINATION = "xpath=//ul[@class='qgrd-pagination-ul']/li";
    public static final String EACH_ROW_OF_EACH_PAGE = "xpath=//table[@class='qgrd']//tr";
    public static final String COUNTRY_OF_EACH_ROW = "xpath=//table[@class='qgrd']//tr[%s]/td[@data-key='country']";
    public static final String ALL_DATA_OF_EACH_ROW = "xpath=//table[@class='qgrd']//tr[%s]/td";
    public static final String COLUMN_INDEX_BY_NAME = "xpath=//tr/th[text()='%s']/preceding-sibling::th";
    public static final String TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody/tr[%s]/td[%s]/input";
}
