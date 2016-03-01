import org.junit.*;
import static org.junit.Assert.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("To Do List");
  }
  @Test
  public void categoryIsAddableTest() {
   goTo("http://localhost:4567");
   click("a", withText("Add/View Category"));
    assertThat(pageSource()).contains("Add a new category");
  }

  @Test
  public void categoryIsViewableTest() {
   goTo("http://localhost:4567");
   click("a", withText("Add/View Category"));
   fill("#name").with("School");
   submit(".btn");
    assertThat(pageSource()).contains("School");

  }
}
