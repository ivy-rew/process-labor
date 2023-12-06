package com.axonivy.demo.test;

import java.util.List;
import java.util.Optional;

import ch.ivyteam.ivy.process.model.NodeElement;
import ch.ivyteam.ivy.process.model.connector.SequenceFlow;

@SuppressWarnings("restriction")
public class HappyPathFinder {

  public static enum PathColor {
    HAPPY("happyPath"),
    ALMOST("almostHappy");

    private final String name;

    PathColor(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }

  public static Optional<SequenceFlow> happyOutOf(NodeElement element) {
    return happyOutput(element.getOutgoing());
  }

  public static Optional<SequenceFlow> happyOutput(List<SequenceFlow> outs) {
    return happyColor(outs, PathColor.HAPPY);
  }

  public static Optional<SequenceFlow> happyColor(List<SequenceFlow> outs, HappyPathFinder.PathColor color) {
    if (outs.size() == 1) {
      var first = outs.get(0);
      return Optional.of(first);
    }
    for(var out : outs) {
      var colorName = out.getEdge().color().getReference();
      if (colorName.contains(color.getName())) {
        return Optional.of(out);
      }
    }
    return Optional.empty();
  }
}