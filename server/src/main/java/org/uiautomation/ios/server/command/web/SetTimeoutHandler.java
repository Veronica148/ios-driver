package org.uiautomation.ios.server.command.web;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.UnsupportedCommandException;
import org.uiautomation.ios.UIAModels.configuration.CommandConfiguration;
import org.uiautomation.ios.communication.WebDriverLikeCommand;
import org.uiautomation.ios.communication.WebDriverLikeRequest;
import org.uiautomation.ios.communication.WebDriverLikeResponse;
import org.uiautomation.ios.server.IOSDriver;
import org.uiautomation.ios.server.command.BaseWebCommandHandler;

public class SetTimeoutHandler extends BaseWebCommandHandler {

  public SetTimeoutHandler(IOSDriver driver, WebDriverLikeRequest request) {
    super(driver, request);
  }

  /**
   * type - {string} The type of operation to set the timeout for. Valid values
   * are: "script" for script timeouts, "implicit" for modifying the implicit
   * wait timeout and "page load" for setting a page load timeout.
   */
  @Override
  public WebDriverLikeResponse handle() throws Exception {
    JSONObject payload = getRequest().getPayload();
    String type = payload.optString("type");
    if ("page load".equals(type)){
      long timeout = payload.getLong("ms");
      // meant for driver.get command
      CommandConfiguration conf = getSession().configure(WebDriverLikeCommand.URL);
      conf.set("page load",timeout);
    }else {
      throw new UnsupportedCommandException("timeout "+payload+" NI");
    }
    System.out.println(payload);
    return new WebDriverLikeResponse(getRequest().getSession(), 0, new JSONObject());
  }

  @Override
  public JSONObject configurationDescription() throws JSONException {
    return new JSONObject();
  }

}