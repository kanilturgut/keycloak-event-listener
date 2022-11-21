package org.kanilturgut;

import java.util.Map;
import org.jboss.logging.Logger;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

public class CustomEventListenerProvider implements EventListenerProvider {

  private static final Logger log = Logger.getLogger(CustomEventListenerProvider.class);

  @Override
  public void onEvent(Event event) {
    log.info(toString(event));
  }

  @Override
  public void onEvent(AdminEvent adminEvent, boolean b) {
    log.info(toString(adminEvent));
  }

  @Override
  public void close() {

  }

  private String toString(Event event) {
    StringBuilder sb = new StringBuilder();
    sb.append("type=").append(event.getType());
    sb.append(", realmId=").append(event.getRealmId());
    sb.append(", clientId=").append(event.getClientId());
    sb.append(", userId=").append(event.getUserId());
    sb.append(", ipAddress=").append(event.getIpAddress());

    if (event.getError() != null) {
      sb.append(", error=").append(event.getError());
    }

    if (event.getDetails() != null) {
      for (Map.Entry<String, String> e : event.getDetails().entrySet()) {
        sb.append(", ").append(e.getKey());
        if (e.getValue() == null || e.getValue().indexOf(' ') == -1) {
          sb.append("=").append(e.getValue());
        } else {
          sb.append("='").append(e.getValue()).append("'");
        }
      }
    }

    return sb.toString();
  }

  private String toString(AdminEvent adminEvent) {
    StringBuilder sb = new StringBuilder();
    sb.append("operationType=").append(adminEvent.getOperationType());
    sb.append(", realmId=").append(adminEvent.getAuthDetails().getRealmId());
    sb.append(", clientId=").append(adminEvent.getAuthDetails().getClientId());
    sb.append(", userId=").append(adminEvent.getAuthDetails().getUserId());
    sb.append(", ipAddress=").append(adminEvent.getAuthDetails().getIpAddress());
    sb.append(", resourcePath=").append(adminEvent.getResourcePath());

    if (adminEvent.getError() != null) {
      sb.append(", error=").append(adminEvent.getError());
    }

    return sb.toString();
  }
}
