package hr.tvz.miholic.hardwareapp.Hardware.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.miholic.hardwareapp.Hardware.Classes.Hardware;
import hr.tvz.miholic.hardwareapp.Hardware.Commands.HardwareCommand;
import hr.tvz.miholic.hardwareapp.Hardware.Enums.HardwareTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HardwareControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDc2MzkyMywiaWF0IjoxNjU0MTU5MTIzLCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.2KP_q--vj49v3yY68wQdnu5ylfZGMTrlmX3YJQEZivtG4LPPmwRIP6p9JA84vQei4WuM2uwFxbSXsp2qwPcrdw";

    final String TEST_NAME = "placeholderName";
    final String TEST_CODE = "0518009405";
    final Double TEST_PRICE = new Double(1000);
    final HardwareTypeEnum TEST_TYPE = HardwareTypeEnum.valueOf("CPU");
    final int TEST_AMOUNT = 1000;
    HardwareCommand hardwareCommand = new HardwareCommand(TEST_NAME, TEST_CODE, TEST_PRICE, TEST_TYPE, TEST_AMOUNT);

    @Test
    void getAllHardware() throws Exception{
        this.mockMvc.perform(
                        get("/hardware")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ adminToken)
                )

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
    @Test
    void getHardwareByCode() throws Exception {
        this.mockMvc.perform(
                        get("/hardware/123456789")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ adminToken)
                )

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
    @Test
    void getHardwareByBadCode() throws Exception {
        this.mockMvc.perform(
                        get("/hardware/12345678911")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ adminToken)
                )

                .andExpect(status().isNotFound());
    }
    @Test
    void getHardwareByReallyBadCode() throws Exception {
        this.mockMvc.perform(
                        get("/hardware/XDDDDDDDDD")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ adminToken)
                )

                .andExpect(status().isNotFound());
    }
    @Test
    void getHardwareByType() throws Exception {
        this.mockMvc.perform(
                        get("/hardware/?type=GPU")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ adminToken)
                )

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    @Transactional
    void save() throws Exception {
        this.mockMvc.perform(
                        post("/hardware")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ adminToken)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(hardwareCommand))
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value(TEST_NAME))
                .andExpect(jsonPath("$.code").value(TEST_CODE))
                .andExpect(jsonPath("$.price").value(TEST_PRICE));
    }

    @Test
    @Transactional
    void deleteDelete() throws  Exception {
        this.mockMvc.perform(
                        delete("/hardware/0518009405")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional
    void update() throws Exception{
        this.mockMvc.perform(
                        put("/hardware/123456789")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(hardwareCommand))
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }


}