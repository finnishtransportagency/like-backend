package fi.livi.like.backend;


import java.util.Date;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.web.client.RestTemplate;

import fi.livi.like.backend.domain.JourneyUpdate;
import fi.livi.like.backend.domain.LikeActivity;
import fi.livi.like.backend.domain.LikeLocation;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(LikeApplication.class)
@WebIntegrationTest({"server.port=0", "management.port=0"}) 
@TestPropertySource("/test.properties")
public class TestLocation {

    final String BASE_URL = "http://localhost:";
    final String CONTEXT = "/backend/";

    @Value("${local.server.port}")
    protected int serverPort;    
    
    @Autowired
    DataSource dataSource;

    @Test
    @Sql(scripts = {"/postlocation.sql"}, config = @SqlConfig(encoding = "UTF-8"))
    public void PostLocationNoActivity() {
        
        RestTemplate restTemplate = new TestRestTemplate();

        HttpEntity<JourneyUpdate[]> request = new HttpEntity<>(new JourneyUpdate[]{new JourneyUpdate("userid", 123, 123, new Date(), new LikeLocation(60.0, 23.1, 200, 32), null)});
        ResponseEntity<JourneyUpdate[]> response = restTemplate.exchange(BASE_URL+serverPort+CONTEXT+"journeyupdates", HttpMethod.POST, request, JourneyUpdate[].class);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(new JdbcTemplate(dataSource), "location"));
    }

    @Test
    @Sql(scripts = {"/postlocation.sql"}, config = @SqlConfig(encoding = "UTF-8"))
    public void PostLocationsWithActivity() {
        
        RestTemplate restTemplate = new TestRestTemplate();

        HttpEntity<JourneyUpdate[]> request = new HttpEntity<>(new JourneyUpdate[]{
                new JourneyUpdate("userid", 124, 123, new Date(), new LikeLocation(60.0, 23.1, 200, 32), new LikeActivity(LikeActivity.Type.WALKING, 90)),
                new JourneyUpdate("userid", 124, 124, new Date(), new LikeLocation(60.1, 23.2, 202, 34), new LikeActivity(LikeActivity.Type.IN_VEHICLE, 10)),
            });
        ResponseEntity<JourneyUpdate[]> response = restTemplate.exchange(BASE_URL+serverPort+CONTEXT+"journeyupdates", HttpMethod.POST, request, JourneyUpdate[].class);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Assert.assertEquals(2, JdbcTestUtils.countRowsInTable(new JdbcTemplate(dataSource), "location"));
    }
}
