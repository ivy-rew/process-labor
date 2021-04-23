package com.axon.ivy.template;

import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.components.ProcessKind;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.IProcess;
import ch.ivyteam.ivy.process.IProcessManager;
import ch.ivyteam.ivy.process.IProjectProcessManager;
import ch.ivyteam.ivy.process.model.Process;
import ch.ivyteam.ivy.process.model.element.ProcessElement;
import ch.ivyteam.ivy.process.resource.ProcessCreator;
import ch.ivyteam.ivy.process.resource.ProcessCreator.ProcessCreationParameters;
import ch.ivyteam.ivy.resource.datamodel.ResourceDataModelException;

@SuppressWarnings("restriction")
public class PatternConverter {

	public static interface ModelStrategy
	{
		public void convert(Process template, Process target);	
	}
	
	public static void convert() throws ResourceDataModelException
	{
		IProjectProcessManager manager = IProcessManager.instance().getProjectDataModelFor(IProcessModelVersion.current());
		Set<Process> templates = manager.search().description("TEMPLATE").find().stream()
			.map(start -> start.getRootProcess())
			//.map(process -> process.search().type(EmbeddedProcessElement.class).findOne().getEmbeddedProcess())
			.collect(Collectors.toSet());
		
		IProject project = IProcessModelVersion.current().getProject();
		
		
		ModelStrategy strategy = new EmbeddedTaskToSerial();
		for(Process template : templates)
		{
			Ivy.log().info("converting: "+template);
			IProcess process = getOrCreate(manager, project, template.getName()+"Exec");
			strategy.convert(template, process.getModel());
			process.save();
		}
		
		refreshTree(project);
	}

	private static IProcess getOrCreate(IProjectProcessManager manager, IProject project, String name)
			throws ResourceDataModelException {
		IProcess existing = manager.findProcessByPath("Business Processes/"+name, false);
		if (existing != null)
		{
			Ivy.log().error("exist already: cleaning old model");
			Process model = existing.getModel();
			for(ProcessElement el : model.getProcessElements())
			{
				model.remove().element(el);
			}
			return existing;
		}
		
		ProcessCreationParameters createParameters = ProcessCreator.create(project, name)
		        .kind(ProcessKind.NORMAL)
		        .namespace("Business Processes")
		        .createDefaultContent(false)
		        .toCreator().getCreateParameters();
		IProcess process = manager.createProcess(createParameters, new NullProgressMonitor());
		return process;
	}
	
	private static void refreshTree(IProject project) {
		try {
			project.getFolder("processes").refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
}
