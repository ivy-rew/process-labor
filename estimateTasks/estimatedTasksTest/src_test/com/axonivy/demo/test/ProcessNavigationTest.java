package com.axonivy.demo.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.process.IProcessManager;
import ch.ivyteam.ivy.process.model.NodeElement;
import ch.ivyteam.ivy.process.model.element.gateway.Alternative;
import ch.ivyteam.ivy.workflow.businesscase.IBusinessCase;

@SuppressWarnings("restriction")
@IvyTest
public class ProcessNavigationTest {

  private ProcessGraph graph;

  @BeforeEach
  void setup() {
    var pmv = Ivy.request().getProcessModelVersion();
    var manager = IProcessManager.instance().getProjectDataModelFor(pmv);
    var process = manager.findProcessByPath("main").getModel();
    this.graph = new ProcessGraph(process);
  }

  @Test
  void processNavigation() {
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

  @Test
  void happyPath_byColor() {
    var gateway = graph.process.search().type(Alternative.class).findOne();
    assertThat(gateway.getOutgoing()).hasSize(3);
    var happyFlow = HappyPathFinder.happyOutOf(gateway);
    assertThat(happyFlow.get().getPid().getFieldIds()).endsWith("f14");
    NodeElement happyTarget = happyFlow.get().getTarget();
    assertThat(happyTarget.getName()).isEqualTo("happy end");
  }

}
