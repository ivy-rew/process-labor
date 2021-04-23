package com.axon.ivy.template;

import java.util.List;
import java.util.stream.Collectors;

import com.axon.ivy.template.PatternConverter.ModelStrategy;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.model.EmbeddedProcess;
import ch.ivyteam.ivy.process.model.Process;
import ch.ivyteam.ivy.process.model.diagram.Diagram;
import ch.ivyteam.ivy.process.model.diagram.shape.DiagramShape;
import ch.ivyteam.ivy.process.model.element.EmbeddedProcessElement;
import ch.ivyteam.ivy.process.model.element.activity.UserTask;
import ch.ivyteam.ivy.process.model.element.event.end.TaskEnd;
import ch.ivyteam.ivy.process.model.element.event.start.RequestStart;

@SuppressWarnings("restriction")
public class EmbeddedTaskToSerial implements ModelStrategy
{
	public void convert(Process template, Process target) {
		List<EmbeddedProcess> embeddedProcesses = template.search().type(EmbeddedProcessElement.class)
		    .find().stream()
			.map(element -> element.getEmbeddedProcess())
			.collect(Collectors.toList());
		if (embeddedProcesses.isEmpty())
		{
			Ivy.log().info("no embedded processes in template "+template);
			return;
		}
		Diagram execDiagram = target.getDiagram();
		
		drawPattern(embeddedProcesses, execDiagram);
	}

	private static void drawPattern(List<EmbeddedProcess> embeddedProcesses, Diagram execDiagram) {
		int x = 50;
		int y = 0;
		
		for(EmbeddedProcess embedded : embeddedProcesses)
		{
			y+=100;
			DiagramShape start = execDiagram.add().shape(RequestStart.class).at(x, y);
			start.getLabel().setText(embedded.getName());
			RequestStart starter = start.getElement();
			makeExecutable(starter);
			
			DiagramShape previous = start;
			
			List<UserTask> tasks = embedded.search().type(UserTask.class).find();
			for(UserTask task : tasks)
			{
				x+=200;
				
				DiagramShape current = execDiagram.add().shape(UserTask.class).at(x, y);
				current.getLabel().setText(task.getName());
				UserTask execTask  = current.getElement();
				copyInscriptions(task, execTask);
				previous.edges().connectTo(current); // connect
				previous = current;
			}
			
			x+=200;
			DiagramShape end = execDiagram.add().shape(TaskEnd.class).at(x, y);
			previous.edges().connectTo(end);
		}
	}



	private static void makeExecutable(RequestStart starter) {
		starter.setLinkName("startExec.ivp");
		starter.setStartByHttpRequestAllowed(true);
	}

	private static void copyInscriptions(UserTask templateTask, UserTask execTask) {
		execTask.setCallTarget(templateTask.getCallTarget()); 
		// more inscriptions?
	}
}