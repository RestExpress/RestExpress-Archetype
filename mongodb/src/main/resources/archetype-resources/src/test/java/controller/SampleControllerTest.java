#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restexpress.RestExpress;
import ${package}.Main;

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
        HttpGet getRequest = new HttpGet(BASE_URL + "/samples/uuid/123.json");
        final HttpResponse response = httpClient.execute(getRequest);
        assertEquals(400, response.getStatusLine().getStatusCode());
    }
}
