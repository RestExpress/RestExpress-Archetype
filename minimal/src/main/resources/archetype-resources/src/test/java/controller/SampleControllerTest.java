#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import static org.junit.Assert.*;

import ${package}.Main;

import com.strategicgains.restexpress.RestExpress;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.*;

import java.io.IOException;

public class SampleControllerTest {

    /**
     * The REST server that handles the test calls.
     */
    private static RestExpress server;
    private HttpClient httpClient;
    private static final String BASE_URL = "http://localhost:8081";


    @BeforeClass
    public static void beforeClass() throws Exception {
        String[] env = { "dev" };
        server = Main.initializeServer(env);
    }

    @AfterClass
    public static void afterClass() {
        server.shutdown();
    }


    @Before
    public void beforeEach() {
        httpClient = new DefaultHttpClient();
    }


    @After
    public void afterEach() {
        httpClient = null;
    }

    @Test
    public void postDirectiveReplayRequest() throws IOException {
        HttpGet getRequest = new HttpGet(BASE_URL + "/your/route/here/123.json");
        final HttpResponse response = httpClient.execute(getRequest);
        assertEquals(200, response.getStatusLine().getStatusCode());
    }
}
