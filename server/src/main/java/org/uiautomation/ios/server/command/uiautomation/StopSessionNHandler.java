/*
 * Copyright 2012 ios-driver committers.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.uiautomation.ios.server.command.uiautomation;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.remote.Response;
import org.uiautomation.ios.client.uiamodels.impl.NoOpNativeDriver;
import org.uiautomation.ios.communication.WebDriverLikeRequest;
import org.uiautomation.ios.server.IOSServerManager;
import org.uiautomation.ios.server.command.UIAScriptHandler;

public class StopSessionNHandler extends UIAScriptHandler {

  public StopSessionNHandler(IOSServerManager driver, WebDriverLikeRequest request) {
    super(driver, request);
    setJS("stop");
  }

  public Response handle() throws Exception {
    String opaqueKey = getRequest().getSession();
    if (getDriver().getSession(opaqueKey)
        .getNativeDriver() instanceof NoOpNativeDriver) {
      // safari real
    } else {
      super.handle();
    }
      getDriver().stop(opaqueKey);

    Response resp = new Response();
    resp.setSessionId(opaqueKey);
    resp.setStatus(0);
    resp.setValue(new JSONObject());
    return resp;
  }

  @Override
  public JSONObject configurationDescription() throws JSONException {
    return noConfigDefined();
  }

}
