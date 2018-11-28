package org.neo4j.boot.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.Scanner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootExampleApplicationTests {

    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test_create_user() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/test/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\":\"aa@bb.com\"}"))
            .andExpect(status().isOk())
            .andExpect(content().json("{\n" +
                "  \"email\": \"aa@bb.com\"\n" +
                "}")).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        String userId = extractUserIdFromResponseContent(contentAsString);

        mockMvc.perform(post("/test/users/{userId}/hobbies", userId)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Tennis\"}"))
            .andExpect(status().isOk());
    }

    /**
     * Extracts the user id from a "create user" response in a quick and dirty way.
     *
     * @param content content of a "create user" response body
     * @return user id
     */
    private String extractUserIdFromResponseContent(String content) {
        Scanner scanner = new Scanner(content);
        scanner.useDelimiter("\"");
        scanner.next();
        scanner.next();
        scanner.next();
        return scanner.next();
    }
}
