<%
try
{
%><%@
page import="com.exedio.cope.lib.Feature" %><%@
page import="com.exedio.cope.lib.Function" %><%@
page import="com.exedio.cope.lib.Attribute" %><%@
page import="com.exedio.cope.lib.ObjectAttribute" %><%@
page import="com.exedio.cope.lib.StringAttribute" %><%@
page import="com.exedio.cope.lib.BooleanAttribute" %><%@
page import="com.exedio.cope.lib.IntegerAttribute" %><%@
page import="com.exedio.cope.lib.LongAttribute" %><%@
page import="com.exedio.cope.lib.DoubleAttribute" %><%@
page import="com.exedio.cope.lib.DateAttribute" %><%@
page import="com.exedio.cope.lib.MediaAttribute" %><%@
page import="com.exedio.cope.lib.EnumAttribute" %><%@
page import="com.exedio.cope.lib.EnumValue" %><%@
page import="com.exedio.cope.lib.ItemAttribute" %><%@
page import="com.exedio.cope.lib.Type" %><%@
page import="com.exedio.cope.lib.Item" %><%@
page import="com.exedio.cope.lib.NestingRuntimeException" %><%@
page import="com.exedio.cope.lib.NoSuchIDException" %><%@
page import="java.io.PrintWriter" %><%@
page import="java.util.Date" %><%@
page import="java.util.Iterator" %><%@
page import="java.util.Collection" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %><%@
page import="java.text.SimpleDateFormat" %><%@

include file="provider.inc"

%><%
	try
	{
		provider.getModel().checkDatabase();
	}
	catch(NestingRuntimeException e)
	{
		%><html>
	<head>
		<title>Copernica Error</title>
	</head>
	<body>
		<b>Database not initialized.</b><br>
		<a href="<%=new AdminCop()%>">Administration</a>
		<hr>
		<pre>
<%e.printStackTrace(new PrintWriter(out));%>
		</pre>
		<hr>
	</body>
</html>
<%
		return;
	}

	final CopernicaUser user = Util.checkAccess(provider, request, response);
	if(user==null)
	{
%><html>
	<head>
		<title>Copernica Unauthorized</title>
	</head>
	<body>
		<b>Access not authorized.</b><br>
	</body>
</html>
<%
		return;
	}
	
	final CopernicaCop cop = CopernicaCop.getCop(provider, request);
	
	final CopernicaLanguage language = cop.language;
	
%><html>
	<head>
		<title><%=cop.getTitle()%></title><%
		
		final CopernicaCop home = cop.toHome();
		%>
		<link rel="home" href="<%=home%>"><%
		final CopernicaCop prev = cop.toPrev();
		if(prev!=null)
		{
		%>
		<link rel="prev" href="<%=prev%>"><%
		}
		final CopernicaCop next = cop.toNext();
		if(next!=null)
		{
		%>
		<link rel="next" href="<%=next%>"><%
		}
		
		%>
		<link rel="STYLESHEET" type="text/css" href="copernica.css">
	</head>
	<body>
		<div id="langcontainer">
			<ul id="langlist"><%
			for(Iterator l = provider.getDisplayLanguages().iterator(); l.hasNext(); )
			{
				final CopernicaLanguage currentLanguage = (CopernicaLanguage)l.next();
				final String name = currentLanguage.getCopernicaName(language);
				final boolean active = currentLanguage.equals(language);
				%>
				<li<%if(active){%> id="active"<%}%>>
					<a href="<%=cop.switchLanguage(currentLanguage)%>"<%
						if(active){%> id="current"<%}%>><%=name%></a>
				</li><%
			}
			%>
			<li>
				<%=user.getCopernicaName()%>
			</li>
			</ul>
		</div><%@
		include file="copernica-typelist.inc" %>
		<div id="main"><%
		if(cop instanceof TypeCop)
		{
			final TypeCop typeCop = ((TypeCop)cop);
			%><%@ include file="copernica-type.inc" %><%
		}
		else if(cop instanceof ItemCop)
		{
			final ItemCop itemCop = (ItemCop)cop;
			%><%@ include file="copernica-item.inc" %><%
		}
		else if(cop instanceof EmptyCop)
		{
			%>
			<h1>Copernica</h1>
			<h2>Generic Backoffice for COPE</h2>
			<img src="exedio.png" width="200" height="66"><%
		}
		else
			throw new RuntimeException();
		//cop.log();
		%>
		</div>
	</body>
</html><%
}
catch(Throwable e)
{
	response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
%><html>
	<head>
		<title>Copernica System Error</title>
	</head>
	<body>
		<b>Sorry, an internal error occurred.</b><br><%
		if("jo-man".equals(request.getParameter("display_error")))
		{
		%>
		<hr>
		<font color="#ff0000">
		<pre>
<%e.printStackTrace(new PrintWriter(out));%>
		</pre>
		</font>
		<hr><%
		}
		else
		{
			final String id = Util.createErrorId();
			System.out.println("--------I"+id+"-----");
			e.printStackTrace();
			System.out.println("--------O"+id+"-----");
		%>
		Please report the id <%=id%> to the webmaster.<%
		}
		%>
	</body>
</html><%
}
%>
