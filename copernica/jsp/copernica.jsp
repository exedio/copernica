<%@
page import="com.exedio.cope.lib.Search" %><%@
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
page import="com.exedio.cope.lib.EnumerationAttribute" %><%@
page import="com.exedio.cope.lib.EnumerationValue" %><%@
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

	final User user = Util.checkAccess(provider, request.getHeader("Authorization"));
	if(user==null)
	{
		response.addHeader("WWW-Authenticate", "Basic realm=\"Copernica\"");
%><html>
	<head>
		<title>Copernica Unauthorized</title>
	</head>
	<body>
		<b>Access not authorized.</b><br>
	</body>
</html>
<%
		response.setStatus(response.SC_UNAUTHORIZED);
		return;
	}
	
	final CopernicaCop cop = CopernicaCop.getCop(
		provider, request.getParameterMap()
	);
	
	
	final Language language = cop.language;
	
%><html>
	<head>
		<title><%=cop.getTitle(provider)%></title>
		<link rel="STYLESHEET" type="text/css" href="copernica.css">
	</head>
	<body>
		<div id="langcontainer">
			<ul id="langlist"><%
			for(Iterator l = provider.getDisplayLanguages().iterator(); l.hasNext(); )
			{
				final Language currentLanguage = (Language)l.next();
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
			<u>select a type on the left</u><%
		}
		else
			throw new RuntimeException();
		%>
		</div>
	</body>
</html>
