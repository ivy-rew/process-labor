package com.axonivy.demo.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.process.IProcessManager;
import ch.ivyteam.ivy.process.model.NodeElement;
import ch.ivyteam.ivy.process.model.Process;
import ch.ivyteam.ivy.process.model.element.SingleTaskCreator;
import ch.ivyteam.ivy.process.model.element.event.start.RequestStart;
import ch.ivyteam.ivy.workflow.businesscase.IBusinessCase;

@SuppressWarnings("restriction")
@IvyTest
public class ProcessNavigationTest {

  @Test
  public void processNavigation() {
    var pmv = Ivy.request().getProcessModelVersion();
    var manager = IProcessManager.instance().getProjectDataModelFor(pmv);
    var process = manager.findProcessByPath("main").getModel();

    var graph = new ProcessGraph(process);

    var start = graph.findStart();
    var first = graph.nextTask(start);
    assertThat(first.getName()).isEqualTo("first");
    var desc = first.getTaskConfig().getDescription().getRawMacro();
    assertThat(desc).contains("estimate:");

    var second = graph.nextTask(first);
    assertThat(second.getName()).isEqualTo("second");

    var optional = graph.nextTask(second);
    assertThat(optional.getName()).isEqualTo("optional");

    var more = graph.nextTask(optional);
    assertThat(more)
      .as("all tasks done")
      .isNull();

    // custom-fields
    //-> bad performance in case of many tasks
    //-> cleanup job required
    //
    // default paths:
    //-> yet empty condition is treated as default
    //-> process colors can be used to model own defaults

    IBusinessCase rootCase = Ivy.wfCase().getBusinessCase();
    rootCase.customFields().stringField("estimate");
  }

  private static class ProcessGraph {

    private Process process;

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
      for(var flow : from.getOutgoing()) {
        var next = flow.getTarget();
        nexts.add(next);
        if (next instanceof SingleTaskCreator task) {
          return task;
        }
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
}
