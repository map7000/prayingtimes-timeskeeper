package ru.mfilatov.prayingtimes.timeskeeper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.mfilatov.prayingtimes.timeskeeper.controller.TimesRestController;

@WebMvcTest(TimesRestController.class)
public class TimesRestControllerTest {
  // @MockBean
  // private TutorialRepository tutorialRepository;

  @Autowired private MockMvc mockMvc;

  @Test
  void simpleTest() throws Exception {
    mockMvc
        .perform(get("/{country}/{city}/{method}", "Russia", "Moscow", "14"))
        .andExpect(status().isOk())
        .andDo(print());
  }
}
