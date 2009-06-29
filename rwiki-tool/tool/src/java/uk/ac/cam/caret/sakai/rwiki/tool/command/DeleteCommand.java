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

package uk.ac.cam.caret.sakai.rwiki.tool.command;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.component.api.ComponentManager;
import org.sakaiproject.content.api.FilePickerHelper;
import org.sakaiproject.tool.api.SessionManager;
import org.sakaiproject.tool.api.Tool;
import org.sakaiproject.tool.api.ToolSession;

import uk.ac.cam.caret.sakai.rwiki.service.api.RWikiObjectService;
import uk.ac.cam.caret.sakai.rwiki.service.exception.PermissionException;
import uk.ac.cam.caret.sakai.rwiki.service.exception.VersionException;
import uk.ac.cam.caret.sakai.rwiki.tool.RWikiServlet;
import uk.ac.cam.caret.sakai.rwiki.tool.RequestScopeSuperBean;
import uk.ac.cam.caret.sakai.rwiki.tool.api.HttpCommand;
import uk.ac.cam.caret.sakai.rwiki.tool.bean.EditBean;
import uk.ac.cam.caret.sakai.rwiki.tool.bean.ErrorBean;
import uk.ac.cam.caret.sakai.rwiki.tool.bean.ResourceLoaderBean;
import uk.ac.cam.caret.sakai.rwiki.tool.bean.ViewBean;
import uk.ac.cam.caret.sakai.rwiki.tool.bean.helper.ResourceLoaderHelperBean;
import uk.ac.cam.caret.sakai.rwiki.tool.bean.helper.ViewParamsHelperBean;
import uk.ac.cam.caret.sakai.rwiki.tool.command.helper.ErrorBeanHelper;
import uk.ac.cam.caret.sakai.rwiki.tool.util.WikiPageAction;
import uk.ac.cam.caret.sakai.rwiki.utils.NameHelper;

import org.sakaiproject.component.cover.ServerConfigurationService;

/**
 * @author savitha
 */
public class DeleteCommand implements HttpCommand{

	private static Log log = LogFactory.getLog(SaveCommand.class);

	//private static final String ATTACHMENT_HELPER = "sakai.filepicker";

	protected RWikiObjectService objectService;

	private String contentChangedPath;

	private String noDeletePath;

	private String successfulPath;

	//private String previewPath;

	//private String cancelPath;

	private SessionManager sessionManager;

	public void init()
	{
		ComponentManager cm = org.sakaiproject.component.cover.ComponentManager
		.getInstance();
		sessionManager = (SessionManager) load(cm, SessionManager.class
				.getName());
		objectService = (RWikiObjectService) load(cm, RWikiObjectService.class
				.getName());
	}

	private Object load(ComponentManager cm, String name)
	{
		Object o = cm.get(name);
		if (o == null)
		{
			log.error("Cant find Spring component named " + name);
		}
		return o;
	}

	public void execute(Dispatcher dispatcher, HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{

		RequestScopeSuperBean rssb = RequestScopeSuperBean
		.getFromRequest(request);

		ResourceLoaderBean rlb = rssb.getResourceLoaderBean();

		ViewParamsHelperBean vphb = (ViewParamsHelperBean) rssb
		.getNameHelperBean();
		String tid = org.sakaiproject.tool.cover.ToolManager.getCurrentPlacement().getId();
		String content = vphb.getContent();
		//String save = vphb.getSaveType();
		String name = vphb.getGlobalName();
		String realm = vphb.getLocalSpace();
		/*
		 if (save == null)
		{
			save = EditBean.SAVE_VALUE;
		}
		if (save.equals(EditBean.OVERWRITE_VALUE))
		{
			content = vphb.getSubmittedContent();
			// Set the content as the submitted content in case we have a
			// version exception
			vphb.setContent(content);
		}
		else if (save.equals(EditBean.PREVIEW_VALUE))
		{
			vphb.setSaveState(ViewParamsHelperBean.SAVE_PREVIEW);
			this.previewDispatch(dispatcher,request, response);
			return;
		}
		else if (save.equals(EditBean.LINK_ATTACHMENT_VALUE)
				|| save.equals(EditBean.EMBED_ATTACHMENT_VALUE))
		{

			ToolSession session = sessionManager.getCurrentToolSession();

			Map parameterMap = request.getParameterMap();
			session.setAttribute("STORED_PARAMETERS", parameterMap);

			ViewBean vb = rssb.getViewBean();

			// FIXME Knowledge of URL structure assumed!
			WikiPageAction returnAction = WikiPageAction.LINK_ATTACHMENT_RETURN_ACTION;
			if (save.equals(EditBean.EMBED_ATTACHMENT_VALUE))
			{
				returnAction = WikiPageAction.EMBED_ATTACHMENT_RETURN_ACTION;
			}

			// SAK-13408 - Tomcat and WAS have different URL structures; Attempting to add a 
			// link or image would lead to site unavailable errors in websphere if the tomcat
			// URL structure is used.
			if("websphere".equals(ServerConfigurationService.getString("servlet.container"))){
				session.setAttribute(ATTACHMENT_HELPER + Tool.HELPER_DONE_URL,
						request.getContextPath() + request.getServletPath()
						+ "/tool/" + tid + vb.getActionUrl(returnAction, true));
			}
			else {
				session.setAttribute(ATTACHMENT_HELPER + Tool.HELPER_DONE_URL,
						request.getContextPath() + request.getServletPath()
						+ vb.getActionUrl(returnAction, true));
			}

			session.setAttribute(FilePickerHelper.FILE_PICKER_ATTACH_LINKS,
					FilePickerHelper.FILE_PICKER_ATTACH_LINKS);

			String fromText;


			if (returnAction
					.equals(WikiPageAction.LINK_ATTACHMENT_RETURN_ACTION))
			{
				fromText = vb.getLocalName() + rlb.getString("save.as_link"," as link");
			}
			else
			{
				fromText = vb.getLocalName() + rlb.getString("save.as_embed"," as embed");
			}

			session.setAttribute(FilePickerHelper.FILE_PICKER_FROM_TEXT,
					fromText);

			if("websphere".equals(ServerConfigurationService.getString("servlet.container"))){
				//WS-57 - In Websphere the URL which the user is directed to contains a duplicate tool id. This fix will remove the 
				//dulpicate tool id that is passed in to the method sendRedirect() as the url being built
				response.sendRedirect("helper/" + ATTACHMENT_HELPER + "/tool");
			}
			else{
				response.sendRedirect(request.getContextPath()
						+ request.getServletPath() + "/helper/" + ATTACHMENT_HELPER
						+ "/tool");
			}

			return;
		}
		else if (save.equals(EditBean.CANCEL_VALUE))
		{
			vphb.setSaveState(ViewParamsHelperBean.SAVE_CANCEL);
			this.cancelDispatch(dispatcher,request, response);
			ViewBean vb = new ViewBean(name, realm);
			String requestURL = request.getRequestURL().toString();
			sessionManager.getCurrentToolSession().setAttribute(
					RWikiServlet.SAVED_REQUEST_URL,
					requestURL + vb.getViewUrl());
			return;
		}
		*/
		
		String version = vphb.getSubmittedVersion();
		Date versionDate = new Date(Long.parseLong(version));

		try
		{
			doDelete(name, realm, versionDate, content);
		}
		catch (VersionException e)
		{
			// The page has changed underneath us...

			// redirect probably back to the edit page
			//vphb.setSaveState(ViewParamsHelperBean.SAVE_VERSION_EXCEPTION);
			this.contentChangedDispatch(dispatcher,request, response);
			return;
		}
		catch (PermissionException e)
		{
			// Redirect back to a no permission page...
			this.noDeleteAllowed(dispatcher,request, response);
			return;
		}
		
		// Successful deletion
		//vphb.setSaveState(ViewParamsHelperBean.SAVE_OK);
		this.successfulUpdateDispatch(dispatcher,request, response);
		ViewBean vb = new ViewBean(name, realm);
		String requestURL = request.getRequestURL().toString();
		sessionManager.getCurrentToolSession().setAttribute(
				RWikiServlet.SAVED_REQUEST_URL, requestURL + vb.getViewUrl());
		return;

	}

	protected void doDelete(String name, String realm, Date versionDate,
			String content)
	{
		objectService.delete(name, realm, versionDate, content);
	}

	/*
	private void cancelDispatch(Dispatcher dispatcher,HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
			{
		dispatcher.dispatch(cancelPath,request, response);
			}

	private void previewDispatch(Dispatcher dispatcher,HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
			{
		dispatcher.dispatch(previewPath,request, response);
			}
	*/



	protected void successfulUpdateDispatch(Dispatcher dispatcher,HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		RequestScopeSuperBean rssb = RequestScopeSuperBean
				.getFromRequest(request);

		ViewParamsHelperBean vphb = (ViewParamsHelperBean) rssb
				.getNameHelperBean();

		String localName = NameHelper.localizeName(vphb.getGlobalName(), vphb
				.getPageSpace());
		String globalName = vphb.getGlobalName();
		int baseNameI = localName.indexOf(".");
		String baseName = localName;
		if (baseNameI > 0)
		{
			int nextI = localName.indexOf(".",baseNameI+1);
			baseName = null;
			while (nextI > 0 && baseName == null)
			{
				try
				{
					String test = localName.substring(baseNameI + 1, nextI);
					Integer.parseInt(localName.substring(baseNameI + 1, nextI));
					baseName = localName.substring(0, baseNameI);
				}
				catch (NumberFormatException e)
				{
					baseNameI = nextI;
					nextI = localName.indexOf(".", baseNameI + 1);
				}
			}
			if ( baseName == null ) {
				try
				{
					String test = localName.substring(baseNameI + 1);
					Integer.parseInt(localName.substring(baseNameI + 1));
					baseName = localName.substring(0, baseNameI);
				}
				catch (NumberFormatException e)
				{
					baseName = localName;
				}
			}
		}
		globalName = NameHelper.globaliseName(baseName, vphb.getPageSpace());
		vphb.setGlobalName(globalName);
		// force a refresh
		rssb.getCurrentPageName(true);
		rssb.getCurrentRWikiObject(true);
		
		dispatcher.dispatch(successfulPath,request, response);
	}


	private void contentChangedDispatch(Dispatcher dispatcher,HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
			{
		ErrorBean errorBean = ErrorBeanHelper.getErrorBean(request);
		ResourceLoaderBean rlb = ResourceLoaderHelperBean.getResourceLoader(request);
		errorBean
		.addError(rlb.getString("save.content_changed","Content has changed since you last viewed it. Please update the new content or overwrite it with the submitted content."));
		dispatcher.dispatch(contentChangedPath,request, response);
			}

	private void noDeleteAllowed(Dispatcher dispatcher,HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
			{
		ErrorBean errorBean = ErrorBeanHelper.getErrorBean(request);
		ResourceLoaderBean rlb = ResourceLoaderHelperBean.getResourceLoader(request);
		errorBean.addError(rlb.getString("save.noupdate_permission","You do not have permission to update this page."));
		dispatcher.dispatch(noDeletePath,request, response);
			}

	public String getSuccessfulPath()
	{
		return successfulPath;
	}

	public void setSuccessfulPath(String successfulPath)
	{
		this.successfulPath = successfulPath;
	}

	public String getContentChangedPath()
	{
		return contentChangedPath;
	}

	public void setContentChangedPath(String contentChangedPath)
	{
		this.contentChangedPath = contentChangedPath;
	}

	public String getNoDeletePath()
	{
		return noDeletePath;
	}

	public void setNoDeletePath(String noUpdatePath)
	{
		this.noDeletePath = noDeletePath;
	}

	/*
	public String getPreviewPath()
	{
		return previewPath;
	}

	public void setPreviewPath(String previewPath)
	{
		this.previewPath = previewPath;
	}

	public String getCancelPath()
	{
		return cancelPath;
	}

	public void setCancelPath(String cancelPath)
	{
		this.cancelPath = cancelPath;
	}
	*/

}
