package com.example.demoweb;

import com.example.demoweb.controller.PostsViewController;
import com.example.demoweb.model.Post;
import com.example.demoweb.repository.PostRepository;
import com.example.demoweb.service.LikesService;
import com.example.demoweb.service.PostService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc

public class RedirectCheck {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void postRedirectTest() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/new");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    @MockBean
    private PostRepository postRepository;

    @Autowired
    PostService postService;

    @Test
    public void postCreateTest() throws Exception {
        Post post =  postService.create("Test text");
        Assert.assertNotNull(post.getCreationDate());
        Assert.assertEquals(java.util.Optional.ofNullable(post.getLikes()), java.util.Optional.ofNullable(0));
    }

    @Autowired
    LikesService likesService;
    @Test
    public void likeSetTest() throws Exception {
        Post post = new Post(0L, "hjh",new Date());
        post.setLikes(2);
        post.setLikes( likesService.like(post));
        Assert.assertEquals(java.util.Optional.ofNullable(post.getLikes()), java.util.Optional.ofNullable(3));
    }
    @Test
    public void contextLoads() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Доска постов")));
    }

    @Test
    public void mocks() throws Exception {
        Post post =  postService.create("Test text");
        Mockito.verify(postRepository, Mockito.times(1)).save(post);
    }
}
