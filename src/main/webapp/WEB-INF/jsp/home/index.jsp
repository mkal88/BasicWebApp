<%@include file="/WEB-INF/jsp/include.jsp" %>
<stripes:layout-render name="/WEB-INF/jsp/layout/standard.jsp">
	<stripes:layout-component name="html_head_css">
		<style type="text/css">
			#welcome-content {
				width: 960px;
				margin: 20px auto;
				background-color: #ffffff;
			}

			#welcome-content ul, #welcome-content ul li {
				list-style-type: none;
				margin-bottom: 10px;
			}

			#welcome-content ul {
				margin: 20px;
				padding: 0;
			}

		</style>
	</stripes:layout-component>
	<stripes:layout-component name="content">
		<div class="banner">
			<div class="banner-title"><h1>Basic App</h1></div>
		</div>
		<div id="welcome-content" class="mwt-corner-all">
			<ul>
				<li>
					<h3>Tools</h3>
					<ul>
						<li><stripes:link beanclass="co.ds.stripes.SubscriberAction">Subscribers</stripes:link></li>
						<li><stripes:link beanclass="co.ds.stripes.TopicAction">Topics</stripes:link></li>
					</ul>
				</li>
			</ul>
		</div>
	</stripes:layout-component>
</stripes:layout-render>