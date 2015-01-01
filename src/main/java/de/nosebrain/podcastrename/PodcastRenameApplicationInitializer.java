package de.nosebrain.podcastrename;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import de.nosebrain.podcastrename.config.PodcastRenameConfig;

public class PodcastRenameApplicationInitializer implements WebApplicationInitializer {

  public static final String SERVLET_NAME = "podcastRename";

  @Override
  public void onStartup(final ServletContext servletContext) throws ServletException {
    final WebApplicationContext context = createContext();
    servletContext.addListener(new ContextLoaderListener(context));
    final ServletRegistration.Dynamic dispatcher = servletContext.addServlet(SERVLET_NAME, new DispatcherServlet(context));
    dispatcher.setLoadOnStartup(1);
    dispatcher.setAsyncSupported(true);
    dispatcher.addMapping("/*");
  }
  
  private static AnnotationConfigWebApplicationContext createContext() {
    final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.setConfigLocation(PodcastRenameConfig.class.getPackage().getName());
    return context;
  }

}
