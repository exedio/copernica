<%@
page import="com.exedio.cope.lib.Database" %><%@
page import="com.exedio.cope.lib.DatabaseReportable" %><%@
page import="com.exedio.cope.lib.Report" %><%@
page import="com.exedio.cope.lib.ReportTable" %><%@
page import="com.exedio.cope.lib.ReportLastAnalyzed" %><%@
page import="com.exedio.cope.lib.ReportColumn" %><%@
page import="com.exedio.cope.lib.ReportConstraint" %><%@
page import="com.exedio.cope.lib.SystemException" %><%@

page import="java.util.Date" %><%@

include file="copernica-provider.inc"

%><%
final boolean doReport = 
	request.getParameter("REPORT")!=null ||
	request.getParameter("APPLY")!=null ;
%>
<html>
	<head>
		<title>
			Copernica - Database Administration
		</title><%
		if(doReport)
		{
			%><%@ include file="admin-report-head.inc" %><%
		}%>
	</head>
	<body>
		<h1>Copernica</h1>
		<h2>Generic Backoffice for COPE</h2>
		<h3>Database Administration</h3>

		<form action="admin.jsp" method="POST">
			Database:
			<br>
			<input type="submit" name="CREATE" value="create" />
			<input type="submit" name="TEARDOWN" value="tear down"/>
			<input type="submit" name="DROP" value="drop"/>
			<%
			if(Database.theInstance instanceof DatabaseReportable)
			{
				%><input type="submit" name="REPORT" value="report"/><%
			}
			%>
			<br>
			<%
				if(request.getParameter("CREATE")!=null)
				{
					Database.theInstance.createDatabase();
					provider.initializeExampleSystem();
					%>Database successfully created!<%
				}
				else if(request.getParameter("TEARDOWN")!= null)
				{
					Database.theInstance.tearDownDatabase();
					%>Database successfully torn down!<%
				}
				else if(request.getParameter("DROP")!=null)
				{
					Database.theInstance.dropDatabase();
					%>Database successfully dropped!<%
				}
				else if(doReport)
				{
					%><hr><%@ include file="admin-report.inc" %><%
				}
			%>
		</form>
	</body>
</html>
