package org.nmcpye.store.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.nmcpye.store.StoreApp;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = StoreApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
