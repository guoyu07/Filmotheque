<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h1 class="text-center">Serie</h1><br/>

<c:forEach begin="0" end ="${nbSeries/6}" var="i">	
	<div class="row">
		<c:forEach begin="0" end ="5" var="j">
			<div class="col-sm-4 col-md-2">
				<c:set var="k" scope="session" value="${(i*6)+j}"/>		
				<c:url value="/saisons" var="url">
					<c:param name="idSerie" value="${series[k].id}"/>
				</c:url>							
				<div class="thumbnail">
					<a href="${url}"><img src ="https://image.tmdb.org/t/p/w500${series[k].affiche}" class="img-thumbnail img-responsive"/></a> 
				</div>
				<div class="caption">  						
		    		<h4><a href="${url}" class="titreClickable">${series[k].titre}</a></h4>
		    		<p><a href="${url}" class="titreClickable">${series[k].resumeCourt}</a></p>
	    		</div>
	    	</div>	    
	    </c:forEach>
	</div>	    	   	 
	<hr/>
</c:forEach>


<table class="table table-hover">
	<thead>
	    <tr>
	        <th></th><th>Titre</th><th>R�sum�</th>
	    </tr>
	</thead>
	<tbody>    
	    <c:forEach items="${series}" var="serie">	   	
	        <tr> 
	        	<td  width="10%">				
	        		<c:url value="/saisons" var="url">
						<c:param name="idSerie" value="${serie.id}"/>
					</c:url>
					<a href="${url}">	
	        			<img src ="https://image.tmdb.org/t/p/w500${serie.affiche}" class="img-thumbnail img-responsive"/>
	        		</a>      		        		
	        	</td> 
	        	<td>${serie.titre}</td>
	        	<td>${serie.resumeCourt}</td> 
	        	<td>

				</td>     	
	        </tr>		       	     	
	    </c:forEach>
    </tbody>
</table>  

