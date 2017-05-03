package com.vural;

import com.vural.controller.ImageController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


/**
 * Created by vural on 02-May-17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ImageControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(new ImageController()).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testDefaultPage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("upload"))
                .andDo(print());
    }

    @Test
    public void testUpload() throws Exception {
        this.mockMvc.perform(get("/upload"))
                .andExpect(status().isOk())
                .andExpect(view().name("upload"))
                .andDo(print());
    }

    @Test
    public void testImages() throws Exception {
        this.mockMvc.perform(get("/images"))
                .andExpect(status().isOk())
                .andExpect(view().name("images"))
                .andDo(print());
    }

    @Test
    public void testUploadImage() throws Exception {
        this.mockMvc.perform(post("/upload"))
                .andExpect(status().isOk())
                .andExpect(view().name("/upload"))
                .andDo(print());
    }
}
