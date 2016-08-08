package fi.livi.like.backend;


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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.web.client.RestTemplate;

import fi.livi.like.backend.data.HashUtils;
import fi.livi.like.backend.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(LikeApplication.class)
@WebIntegrationTest({"server.port=0", "management.port=0"}) 
@TestPropertySource("/test.properties")
public class TestUser {

    final String BASE_URL = "http://localhost:";
    final String CONTEXT = "/backend/";

    @Value("${local.server.port}")
    protected int serverPort;    
    
    @Autowired
    DataSource dataSource;

    @Test
    @Sql(scripts = {"/getnewuser.sql"}, config = @SqlConfig(encoding = "UTF-8"))
    public void GetNewUser() {
        Assert.assertEquals(0, JdbcTestUtils.countRowsInTable(new JdbcTemplate(dataSource), "user"));

        RestTemplate restTemplate = new TestRestTemplate();

        String username = "username";
        String password = "password";
        HttpEntity<User> request = new HttpEntity<>(new User(null, username, password));
        ResponseEntity<User> response = restTemplate.exchange(BASE_URL+serverPort+CONTEXT+"getuser", HttpMethod.POST, request, User.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE.toString(), response.getHeaders().getContentType().toString());
        User user = response.getBody();

        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(new JdbcTemplate(dataSource), "user"));

        Assert.assertEquals("249ba36000029bbe97499c03db5a9001f6b734ec5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", user.getId());
        Assert.assertEquals(HashUtils.encryptString(username), user.getUsername());
        Assert.assertEquals(HashUtils.encryptString(password), user.getPassword());
    }
    
    @Test
    @Sql(scripts = {"/getexistinguser.sql"}, config = @SqlConfig(encoding = "UTF-8"))
    public void GetExistingUser() {
        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(new JdbcTemplate(dataSource), "user"));

        RestTemplate restTemplate = new TestRestTemplate();

        String username = "username";
        String password = "password";
        HttpEntity<User> request = new HttpEntity<>(new User(null, username, password));
        ResponseEntity<User> response = restTemplate.exchange(BASE_URL+serverPort+CONTEXT+"getuser", HttpMethod.POST, request, User.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE.toString(), response.getHeaders().getContentType().toString());
        User user = response.getBody();

        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(new JdbcTemplate(dataSource), "user"));

        Assert.assertEquals(HashUtils.encryptString(username) + HashUtils.encryptString(password), user.getId());
        Assert.assertEquals(HashUtils.encryptString(username), user.getUsername());
        Assert.assertEquals(HashUtils.encryptString(password), user.getPassword());
    }
}
