package com.axonivy.demo.test;

import java.util.ArrayList;
import java.util.List;

import com.axonivy.demo.test.HappyPathFinder.PathColor;

import ch.ivyteam.ivy.process.model.NodeElement;
import ch.ivyteam.ivy.process.model.Process;
import ch.ivyteam.ivy.process.model.connector.SequenceFlow;
import ch.ivyteam.ivy.process.model.element.SingleTaskCreator;
import ch.ivyteam.ivy.process.model.element.event.start.RequestStart;

@SuppressWarnings("restriction")
public class ProcessGraph {

  public final Process process;

  public ProcessGraph(Process process) {
    this.process = process;
  }

  public RequestStart findStart() {
    return process.search().type(RequestStart.class).findOne();
  }

  public SingleTaskCreator nextTask(NodeElement from) {
    if (from == null) {
      return null;
    }

    var nexts = new ArrayList<NodeElement>();
    List<SequenceFlow> outs = from.getOutgoing();
    var happy = HappyPathFinder.happyOutput(outs)
      .map(SequenceFlow::getTarget);
    if (happy.isPresent()) {
      if (happy.get() instanceof SingleTaskCreator task) {
        return task;
      }
      nexts.add(happy.get());
    }
    var secondary = HappyPathFinder.happyColor(outs, PathColor.ALMOST)
      .map(SequenceFlow::getTarget);
    if (secondary.isPresent()) {
      if (secondary.get() instanceof SingleTaskCreator task) {
        return task;
      }
      nexts.add(secondary.get());
    }

    for(var next : nexts) {
      var found = nextTask(next);
      if (found != null) {
        return found;
      }
    }
    // embedded sub? div deep?
    // call-sub -> inspect callee?
    return null;
  }
}
