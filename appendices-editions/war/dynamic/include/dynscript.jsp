<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="<c:url value="/js/ajax.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/audio.min.js" />"></script>
<script type="text/javascript">
	
	var sequenceLeft = [];
	var sequenceRight = [];
	
	var leftTime = 0;
	var rightTime = 0;

	var leftId = 0;
	var rightId = 0;
	
	var token = '';
	
	function startRequest() {
		<%		
		String token = request.getParameter("s");
		if ( token != null) {
		%>
		token = '<%=token%>';
		fillSequence('/play?s=<%=token%>', false);
		<% } else { %>
		launch('/work');
		<% } %>
	}
	
	$(document).ready(function() {
		startRequest();
	});
	
</script>
