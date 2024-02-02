package br.com.mmtechnology.postjob.enumerated;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum Department {
  INFANTIL(1),
  FITNESS(2);

  private final int id;

  Department(int i) {
    this.id = i;
  }

  public static Department getDepartmentById(int id) {
    return Arrays.stream(Department.values())
        .filter(department -> department.getId() == id)
        .findFirst()
        .orElse(null);
  }
}
