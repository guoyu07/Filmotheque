<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row text-center">
	<h3 class="titreAccueil">Bienvenue ${user}</h3>
</div>
<div class="row">
	<div class="col-md-10 col-md-offset-1">
		<p>
			Ce site vous permet de consulter ma filmo/serieth�que personelle class�e par:
		</p>
		<ul>
			<li>Films</li>
			<li>Series / saisons / �pisodes</li>			
		</ul>
		<p>
			Vous pouvez �galement retrouver les biographies et filmographies disponible de vos acteurs et r�alisateurs pr�f�r�s.
		</p>
		<p>
			Et le petit plus de la maison, vous pouvez <strong>t�l�charger</strong> les films et les �pisodes de chaque s�rie. <br/>
			Un petit clique sur le nuage dans la fiche correspondante, un peu de patience (plus ou moins selon la taille du fichier) et hop, vous pouvez le regarder tranquillement! <br/>
			Pour les s�ries, il suffit d'aller sur l'�pisode desir� pour le t�l�charger.
			
		</p>
		<p>
			Des remarques, des suggestions pour des films/series qui vous int�ressent, envoyez moi un mail :<a href="mailto:skalva50@gmail.com">skalva50@gmail.com</a>, je verrai ce que je peux faire pour vous.		   
		</p>
		<p>
			Pour les d�veloppeurs dans l'�me ou si vous souhaitez cr�er votre propre site, le code source est disponible sur <a href="https://github.com/skalva50/Filmotheque" title="D�pot GitHub">GitHub</a> 
		</p>
		<h4>Bonne visite et bon film !</h4> 
	</div>
</div>
<hr/>
<div class="row text-center">
	<h3 class="titreAccueil">Actualit�s</h3>
</div>
<div class="col-md-10 col-md-offset-1">
	<h3>Derniers films ajout�s</h3><br/>
</div>
<div class="row">
	<c:forEach items="${films}" var="film">
		<div class="col-sm-2 col-md-4S">
			<c:url value="/films/filmDetails" var="url">
				<c:param name="idFilm" value="${film.id}"/>
			</c:url>
			<div class="thumbnail">
				<a href="${url}"><img src ="https://image.tmdb.org/t/p/w500${film.affiche}" class="img-thumbnail img-responsive"/></a> 
			</div>
			<div class="caption">  						
	    		<h4><a href="${url}" class="titreClickable">${film.titre}</a></h4>
	    		<p><a href="${url}" class="titreClickable">${film.resumeCourt}</a></p>
	    		<c:forEach begin="0" end ="${film.genres.size()}" var="i">
	    			<span>${film.genres[i].libelle}</span>	
	    			<c:if test="${i<film.genres.size()-1}">, </c:if>    			
	    		</c:forEach>		    		
		    </div>
		</div>
	</c:forEach>
</div>
<hr/>
<div class="row">
<div class="col-md-10 col-md-offset-1">
	<h3>Derni�res s�ries ajout�es</h3><br/>
</div>
	<c:forEach items="${series}" var="serie">
		<div class="col-sm-2 col-md-4S">
			<c:url value="/series/saisons" var="url">
				<c:param name="idSerie" value="${serie.id}"/>
			</c:url>
			<div class="thumbnail">
				<a href="${url}"><img src ="https://image.tmdb.org/t/p/w500${serie.affiche}" class="img-thumbnail img-responsive"/></a> 
			</div>
			<div class="caption">  						
	    		<h4><a href="${url}" class="titreClickable">${serie.titre}</a></h4>
	    		<p><a href="${url}" class="titreClickable">${serie.resumeCourt}</a></p>
	    		<c:forEach begin="0" end ="${serie.genres.size()}" var="i">
	    			<span>${serie.genres[i].libelle}</span>	
	    			<c:if test="${i<serie.genres.size()-1}">, </c:if>    			
	    		</c:forEach>		    		
		    </div>
		</div>
	</c:forEach>
</div>


