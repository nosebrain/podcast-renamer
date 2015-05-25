package de.nosebrain.podcastrename.controller;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;

@Controller
public class BaseController {
  
  private static final Pattern DESCRIPTION_PATTERN = Pattern.compile("<div><span style=\"color:#666666;font-size:11px;\">Views:</span>.*", Pattern.DOTALL);

  @RequestMapping("/")
  public void renamePodcast(@RequestParam("url") final URL url, @RequestParam("title") final String title, final HttpServletResponse response) throws IOException, IllegalArgumentException, FeedException {
    response.setContentType("text/xml; charset=utf-8");
    final SyndFeedInput input = new SyndFeedInput();
    final SyndFeed feed = input.build(new XmlReader(url));
    feed.setTitle(title);
    
    for (final SyndEntry entry : feed.getEntries()) {
      final SyndContent description = entry.getDescription();
      if (description != null) {
        final String cleanedDescription = DESCRIPTION_PATTERN.matcher(description.getValue()).replaceAll("");
        description.setValue(cleanedDescription);
      }
    }
    
    final SyndFeedOutput output = new SyndFeedOutput();
    output.output(feed, response.getWriter());
  }
}
