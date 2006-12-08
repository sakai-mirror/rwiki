<?xml version="1.0" encoding="UTF-8" ?>
<!--
/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006 The Sakai Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/
-->
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0" 
  xmlns:c="http://java.sun.com/jsp/jstl/core"
  xmlns:fn="http://java.sun.com/jsp/jstl/functions"
  ><jsp:directive.page language="java"
    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    errorPage="/WEB-INF/command-pages/errorpage.jsp" 
    /><jsp:text><![CDATA[<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
</jsp:text>
  
	<c:set var="permissionsBean" value="${requestScope.rsacMap.permissionsBean}"/>
	<c:if test="${!permissionsBean.updateAllowed}">
	<jsp:scriptlet>
		if ( true ) {
			throw new uk.ac.cam.caret.sakai.rwiki.service.exception.UpdatePermissionException("You are not allowed to edit this page");
		}
	</jsp:scriptlet>
	</c:if>
  <c:set var="currentRWikiObject" value="${requestScope.rsacMap.currentRWikiObject}"/>
  <div class="rwiki_help_popup" >
	    <form action="?#" method="post">
	    <!--.AJAX based edit.-->
	    <!--<form action="?#" method="post" onsubmit="ajaxRefPopupPost(this,'?#',2,this); return false;" >-->
	    Edit Comment<br/>
		<textarea cols="40" rows="10" name="content" id="content" ><c:out value="${currentRWikiObject.content}"/></textarea>
		<input type="hidden" name="action" value="commenteditsave"/>
		<input type="hidden" name="panel" value="Main"/>
		<input type="hidden" name="version" value="${currentRWikiObject.version.time}"/>
		<input type="hidden" name="pageName" value="${currentRWikiObject.name}" />
		<input type="hidden" name="realm" value="${currentRWikiObject.realm }"/>
		<br/>
		<span class="act">
		  <input type="submit" name="save" value="save" />
		  <input type="button" value="cancel" onclick="popupClose(-1);"/>
		</span>
	    </form>
	</div>
</jsp:root>
