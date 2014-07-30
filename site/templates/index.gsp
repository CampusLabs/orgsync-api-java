<%include "header.gsp"%>

	<%include "menu.gsp"%>

	<div class="page-header">
		<h1>Blog</h1>
	</div>
	<ul>

	<% config.versions.each { v -> %>
		<li>$v</li>
	<% } %>
	</ul>

<%include "footer.gsp"%>
