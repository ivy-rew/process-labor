package com.axonivy.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.axonivy.demo.test.HappyPathFinder.PathColor;

import ch.ivyteam.ivy.process.rdm.IProcessManager;
import ch.ivyteam.ivy.process.model.EmbeddedProcess;
import ch.ivyteam.ivy.process.model.NodeElement;
import ch.ivyteam.ivy.process.model.Process;
import ch.ivyteam.ivy.process.model.connector.SequenceFlow;
import ch.ivyteam.ivy.process.model.element.EmbeddedProcessElement;
import ch.ivyteam.ivy.process.model.element.SingleTaskCreator;
import ch.ivyteam.ivy.process.model.element.activity.ProcessCaller;
import ch.ivyteam.ivy.process.model.element.activity.TriggerCall;
import ch.ivyteam.ivy.process.model.element.activity.value.ProcessCallSignature;
import ch.ivyteam.ivy.process.model.element.event.EmbeddedEvent;
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
      if (happy.get() instanceof EmbeddedProcessElement embedee) {
        EmbeddedProcess inner = embedee.getEmbeddedProcess();
        Optional<EmbeddedEvent> innerStart = outs.get(0).getEmbeddedTarget();
        List<SequenceFlow> innerFlows = innerStart.get().getOutgoing();

      }
      if (happy.get() instanceof ProcessCaller callee) {
        ProcessCallSignature target = callee.getCallTarget();
        if (happy.get() instanceof TriggerCall trigger) {
          // we're not waiting for that
        }
        IProcessManager.instance();
        // call-sub should not have any tasks :)
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
