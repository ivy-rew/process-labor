package com.axonivy.demo.test;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.process.model.BaseElement;
import ch.ivyteam.ivy.process.model.element.SingleTaskCreator;
import ch.ivyteam.ivy.process.model.element.Tagable;
import ch.ivyteam.ivy.process.model.element.value.task.TaskConfig;

@SuppressWarnings("restriction")
public class TaskEstimate {

  public static String of(SingleTaskCreator element) {
    TaskConfig task = element.getTaskConfig();

    return desc(task)
      .or(()->tag(element))
      .orElse(null);
  }

  public static Optional<String> desc(TaskConfig task) {
    String description = task.getDescription().getRawMacro();
    if (description.contains("estimate:")) {
      var estimate = StringUtils.substringAfter(description, ":");
      return Optional.of(estimate);
    }
    return Optional.empty();
  }

  public static Optional<String> tag(BaseElement element) {
    if (element instanceof Tagable tagged) {
      return tagged.getTags().toList().stream()
        .filter(tag -> tag.contains("estimate-"))
        .map(tag -> StringUtils.substringAfter(tag, "-"))
        .findAny();
    }
    return Optional.empty();
  }

}
