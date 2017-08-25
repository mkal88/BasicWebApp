<%@include file="/WEB-INF/jsp/include.jsp" %>
<%--@elvariable id="actionBean" type="co.ds.stripes.TopicAction"--%>
<stripes:layout-render name="/WEB-INF/jsp/layout/standard.jsp">
	<stripes:layout-component name="html_head_css">
		<style type="text/css">

			#name {
				width: 360px;
			}

			DIV.form-wrapper {
				width: 720px;
				margin: 0 auto;
			}

			DIV.row {
				width: 720px;
			}

			DIV.label {
				padding: 10px 5px 0 0;
				width: 200px;
			}

			DIV.field {
				padding: 5px;
				width: 500px;
			}

		</style>
	</stripes:layout-component>
	<stripes:layout-component name="html_head_js">
		<script type="text/javascript">
			$(function () {
				$("#name").focus();
			});
		</script>
	</stripes:layout-component>
	<stripes:layout-component name="content">
		<stripes:form beanclass="co.ds.stripes.TopicAction">
			<stripes:hidden name="topic.id"/>
			<div class="banner">
				<div class="banner-title"><h1>Topic</h1></div>
			</div>
			<div class="clear"></div>
			<div class="button-bar">
				<div class="box-right">
					<stripes:submit name="save" value="Save" id="save-button"/>
					<stripes:submit name="cancelForm" value="Cancel" id="cancel-button"/>
				</div>
				<div class="clear"></div>
			</div>
			<div class="form-wrapper">
				<div class="row form-top-padding">
					<div class="label">Name:</div>
					<div class="field">
						<stripes:text name="topic.name" id="name"/>
						<stripes:errors field="topic.name">
							<div class="form-error" id="recipient-error"><stripes:individual-error/></div>
						</stripes:errors>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>