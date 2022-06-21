package org.generation.FinalProject.security;

//It is a package/module in the Spring framework that deals with the model-view-controller pattern
//Deal with the view component first

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    //because we have implemented WebMvcConfigurer, so we can do out own implementation of the method on the WebMvcConfigurer interface
    @Value("${image.folder}")
    private String imageFolder;


    public void addViewControllers(ViewControllerRegistry registry) {
        //Map the browser's URL to a specific View (HTML) inside resources/templates directory

        //we can register the view that create a direct mapping
        registry.addViewController("/").setViewName("Homepage");
        registry.addViewController("/Homepage").setViewName("Homepage");
        registry.addViewController("/aboutus").setViewName("aboutus");
        registry.addViewController("/shop").setViewName("shop");
        registry.addViewController("/addproduct").setViewName("addproduct");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("Homepage");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //expose the images, js, css resources to the client (browser) so that they can access the necessary files to display.
        registry.addResourceHandler("/static")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(0);

        //expose the productimages folder to allow external client to access the files from the server
        Path uploadDir = Paths.get(imageFolder);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/" + imageFolder + "/**")
                .addResourceLocations("file:" + uploadPath + "/")
                .setCachePeriod(0);


    }


}