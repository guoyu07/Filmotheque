<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class ="row">		
	<h1 class="text-center">${titre}</h1>
	<h1 class="text-center">Saison ${numSaison} Episode ${episode.numero}</h1>	
</div>
<hr/>
<div class ="row">
	<div class ="col-md-6 text-center">
		<img src ="https://image.tmdb.org/t/p/w500${episode.affiche}" class="img-thumbnail img-responsive"/>
	</div>
	<div class ="col-md-6">
		<c:url value="/download" var="urlEpisode">
			<c:param name="typeFolder" value="serie"/>
			<c:param name="pathFile" value="${episode.fichier.chemin}"/>
		</c:url>
		<div class="row">
			<div class ="col-md-2 col-md-offset-2">
				<a  href="${urlEpisode}"><h3 class="titreClickable">Télécharger:</h3></a>
			</div>
			<div class ="col-md-2 col-md-offset-2">
				<a class="btn text-right " href="${urlEpisode}" role="button">
					<img src="/Filmotheque/resources/images/icone/download.png" />
				</a>
			</div>
		</div>
		<hr/>
		<h4 class ="text-center">Titre épisode: </h4>
		<p> ${episode.titre}</p>		
		<hr/>
		<h4 class ="text-center">Date de sortie: </h4>
			<p><fmt:formatDate type="date" value="${episode.dateSortie}" /></p> 
		<hr/>
		<h4 class ="text-center">Résumé: </h4>
		<p> ${episode.resume}</p>	
	</div>
</div>
<hr/>	
<h1 class="text-center">Bande Annonce</h1>
	<c:if test="${videos.size() > 0}">
		<c:forEach begin="0" end ="${videos.size()/4}" var="i">
			<div class="row">
				<c:forEach begin="0" end ="3" var="j">
					<div class="col-sm-6 col-md-3">
						<c:set var="k" scope="session" value="${(i*4)+j}"/>
						<c:if test="${k<videos.size()}">
							<h4>${videos[k].nom}</h4>
							<iframe  width="80%" height="20%" src="https://www.youtube.com/embed/${videos[k].cleVideo}" frameborder="0" allowfullscreen></iframe>
						</c:if>
					</div>
				</c:forEach>			
			</div>			
		</c:forEach>
	</c:if>	
	<c:if test="${videos.size() == 0}">		
		<p>Aucune bande annonce disponible pour cet épisode</p>
	</c:if>	
<hr/>
