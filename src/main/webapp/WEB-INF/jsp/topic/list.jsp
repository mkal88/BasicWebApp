<%@include file="/WEB-INF/jsp/include.jsp" %>
<%--@elvariable id="actionBean" type="co.ds.stripes.TopicAction"--%>
<stripes:layout-render name="/WEB-INF/jsp/layout/standard.jsp">
	<stripes:layout-component name="html_head_css">
		<style type="text/css">
			#edit-button {
				display: none;
			}
		</style>
	</stripes:layout-component>
	<stripes:layout-component name="html_head_js">
		<script type="text/javascript">
 			$(function(){
				$("TR.topic-row").click(function(){
					$("#topic-id").val($(this).attr("data-id"));
					$("#edit-button").click();
				});
			});
		</script>
	</stripes:layout-component>
	<stripes:layout-component name="content">
		<stripes:form beanclass="co.ds.stripes.TopicAction">
			<div class="banner">
				<div class="banner-title"><h1>Topics</h1></div>
			</div>
			<div class="clear"></div>
			<div class="button-bar">
				<div class="box-right">
					<stripes:submit name="edit" value="Edit" id="edit-button"/>
					<stripes:hidden name="topic.id" value="" id="topic-id"/>
					<stripes:submit name="cancelList" value="Cancel" id="cancel-button"/>
				</div>
				<div class="clear"></div>
			</div>
			<table class="grid-list" cellspacing="0">
				<thead>
				<tr>
					<th><div>Name</div></th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${actionBean.topics}" var="topic" varStatus="status">
					<c:set var="stateClass" value=""/>
					<tr data-id="${topic.id}" class="topic-row">
						<td class="${stateClass}">${topic.name}</td>
					</tr>
				</c:forEach>
				</tbody>
				<tfoot>
				<tr>
					<td colspan="2"><div>&nbsp;</div></td>
				</tr>
				</tfoot>
			</table>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>