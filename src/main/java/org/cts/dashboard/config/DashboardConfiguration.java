package org.cts.dashboard.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Contains User Portal DB configuration
 * @author Dhiman Mondal
 *
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="org.cts.dashboard")
public class DashboardConfiguration {

	
}
