package dev.matheuspereira.dxc_customer_service.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum ActionType {
  CREATE,
  UPDATE,
  PARTIAL_UPDATE,
  DELETE;

  public static ActionType fromKey(String key) {
    for (ActionType type : ActionType.values()) {
      if (type.name().equalsIgnoreCase(key)) {
        return type;
      }
    }

    throw new IllegalArgumentException("No valid identifier type found for key: " + key);
  }
}
