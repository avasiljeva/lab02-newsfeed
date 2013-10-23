package lv.lu.newsfeed.http;

import org.junit.Ignore;
import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.TableCell;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;

import junit.framework.Assert;

/**
 * Sample HttpUnit test.
 * Precondition: users are imported imported into database using UsersImportTool program
 * Application must be running on localhost:8080.
 */
@Ignore
public class TestLogin {

    private static final String USERNAME = "ben_armstrong";
    private static final String PASSWORD = "ben_armstrong";
    
    @Test
    public void testLogin(){
        WebConversation wc = new WebConversation();

        // [1] Send first request to reach application
        WebRequest request = new GetMethodWebRequest("http://localhost:8080/lab02-newsfeed-portal/");		
        try {
            WebResponse response = wc.getResponse(request);
            Assert.assertEquals("Wrong title of the page received", "NewsFeed Portal", response.getTitle());

            // [2] Go to "Login" screen
            WebLink link = response.getLinkWith("login");
            Assert.assertNotNull("'Login' link not found", link);
            link.click();
            WebForm[] forms = wc.getCurrentPage().getForms();
            Assert.assertEquals("Wrong number of forms received after click on 'login'", 1, forms.length);
            WebForm addPersonForm = forms[0];
            addPersonForm.setParameter("username", USERNAME);
            addPersonForm.setParameter("password", PASSWORD);
            addPersonForm.submit(); 

            // [3] Go to "People" screen and check anything is present in a table
            link = wc.getCurrentPage().getLinkWith("People");
            Assert.assertNotNull("'People' link not found", link);
            link.click();

            // check that there is one table on the screen
            WebTable[] tables = wc.getCurrentPage().getTables();
            Assert.assertEquals("Wrong number of tables received after click on 'People'", 1, tables.length);

            // search for test Person index in the table
            WebTable peopleTable = tables[0];
            Assert.assertEquals("Wrong number of columns in People table", 2, peopleTable.getColumnCount());
            Assert.assertEquals("Wrong number of rows in People table", 5, peopleTable.getRowCount());
            Assert.assertFalse("Current user is present in People table", isCurrentUserNotPresent(peopleTable));

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed");
        }
    }

    private boolean isCurrentUserNotPresent(WebTable peopleTable){
        for (int i=0; i<peopleTable.getRowCount(); i++){
            TableCell nameCell = peopleTable.getTableCell(i, 1);
            if (USERNAME.equals(nameCell.getText())){
               return true;
            }
        }
        return false;
    }
}
