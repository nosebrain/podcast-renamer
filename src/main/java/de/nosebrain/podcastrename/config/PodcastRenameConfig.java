package de.nosebrain.podcastrename.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"de.nosebrain.podcastrename.controller"})
@EnableWebMvc
public class PodcastRenameConfig {

}
