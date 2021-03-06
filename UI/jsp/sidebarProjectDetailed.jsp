<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Sidebar -->
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<li class="sidebar-brand"><a href="./projectdetail?projectId=${project.id}" >Project details</a></li>
		<li><a href="./allsprinttasks?projectId=${project.id}">Active sprint</a></li>
		<li><a href="./allTasksProject?projectId=${project.id}&backLog=1">Backlog<!-- all tasks that are not taken and not finished --></a></li>
		<li><a href="./allTasksProject?projectId=${project.id}&backLog=0">All tasks history</a></li>
		<li><a href="./burnDownChartviewer?projectId=${project.id}">Burndown chart</a></li>
		<hr>
		<li><a href="./createsprint?projectId=${project.id}&all=0" class="noReLoad">Start sprint</a></li>
		<li><a href="./addWorkers?projectId=${project.id}" class="noReLoad">Add workers</a></li>
		<li><a href="./createtask?projectId=${project.id}" class="noReLoad">Add task</a></li>
		<li><a href="./createepic?projectId=${project.id}" class="noReLoad">Add epic</a></li>
	</ul>
</div>
