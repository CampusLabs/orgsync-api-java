<%include "header.gsp"%>

	<%include "menu.gsp"%>

	<div class="version">Version: $content.version</div>

	<div class="doc-nav">
		<% if (content.prev_title) { %>
		<a href="${content.prev_link}">${content.prev_title}</a>
		&larr;
		<% } %>
		<a href="index.html">Contents</a>
		<% if (content.next_title) { %>
		&rarr;
		<a href="${content.next_link}">${content.next_title}</a>
		<% } %>
	</div>

	<hr />

	<div class="page-header">
		<h1>${content.title}</h1>
	</div>

	<p>${content.body}</p>

	<hr />

<%include "footer.gsp"%>
