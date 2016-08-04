package fi.livi.like.backend;


import java.time.LocalDateTime;

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

import fi.livi.like.backend.domain.Activity;
import fi.livi.like.backend.domain.Location;

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

        HttpEntity<Location[]> request = new HttpEntity<>(new Location[]{new Location(60.0, 23.1, 200, 32, LocalDateTime.now(), "userid", null)});
        ResponseEntity<Location[]> response = restTemplate.exchange(BASE_URL+serverPort+CONTEXT+"locations", HttpMethod.POST, request, Location[].class);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(new JdbcTemplate(dataSource), "location"));
        Assert.assertEquals(0, JdbcTestUtils.countRowsInTable(new JdbcTemplate(dataSource), "activity"));
    }

    @Test
    @Sql(scripts = {"/postlocation.sql"}, config = @SqlConfig(encoding = "UTF-8"))
    public void PostLocationsWithActivity() {
        
        RestTemplate restTemplate = new TestRestTemplate();

        HttpEntity<Location[]> request = new HttpEntity<>(new Location[]{
                new Location(60.0, 23.1, 200, 32, LocalDateTime.now(), "userid", new Activity("WALKING", 90)),
                new Location(60.1, 23.2, 202, 34, LocalDateTime.now(), "userid", new Activity("DRIVING", 10)),
            });
        ResponseEntity<Location[]> response = restTemplate.exchange(BASE_URL+serverPort+CONTEXT+"locations", HttpMethod.POST, request, Location[].class);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Assert.assertEquals(2, JdbcTestUtils.countRowsInTable(new JdbcTemplate(dataSource), "location"));
        Assert.assertEquals(2, JdbcTestUtils.countRowsInTable(new JdbcTemplate(dataSource), "activity"));
    }
}
