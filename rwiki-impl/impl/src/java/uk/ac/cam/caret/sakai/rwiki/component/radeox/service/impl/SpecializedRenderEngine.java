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

package uk.ac.cam.caret.sakai.rwiki.component.radeox.service.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.radeox.api.engine.ImageRenderEngine;
import org.radeox.api.engine.RenderEngine;
import org.radeox.api.engine.WikiRenderEngine;
import org.radeox.api.engine.context.RenderContext;

import uk.ac.cam.caret.sakai.rwiki.service.api.PageLinkRenderer;
import uk.ac.cam.caret.sakai.rwiki.service.api.RWikiObjectService;

/**
 * @author andrew
 */
// FIXME: Component
public class SpecializedRenderEngine implements ImageRenderEngine,
		WikiRenderEngine, RenderEngine
{

	// FIXME make this an ImageRenderEngine and an IncludeRenderEngine

	private RenderEngine renderEngine;

	private RWikiObjectService objectService;

	private PageLinkRenderer plr;

	private String space;

	private String externalImageLink;

	public SpecializedRenderEngine(RenderEngine renderEngine,
			RWikiObjectService objectService, PageLinkRenderer plr,
			String space, String externalImageLink)
	{
		this.externalImageLink = externalImageLink;
		this.plr = plr;
		this.space = space;
		this.objectService = objectService;
		this.renderEngine = renderEngine;
	}

	public void appendLink(StringBuffer buffer, String name, String view)
	{
		plr.appendLink(buffer, name, view);
	}

	public void appendLink(StringBuffer buffer, String name, String view,
			String anchor)
	{
		plr.appendLink(buffer, name, view, anchor);
	}

	public void appendCreateLink(StringBuffer buffer, String name, String view)
	{
		plr.appendCreateLink(buffer, name, view);
	}

	// SAK-2671: We need to know the Local Render Space to localize within
	public String getSpace()
	{
		return space;
	}

	public boolean exists(String name)
	{
		return (objectService.exists(name, space));
	}

	public boolean showCreate()
	{
		return true;
	}

	public String getName()
	{
		return renderEngine.getName();
	}

	public String render(Reader in, RenderContext context) throws IOException
	{
		return renderEngine.render(in, context);
	}

	public String render(String content, RenderContext context)
	{
		return renderEngine.render(content, context);
	}

	public void render(Writer out, String content, RenderContext context)
			throws IOException
	{
		renderEngine.render(out, content, context);
	}

	public String getExternalImageLink()
	{
		return externalImageLink;
	}

	public RenderEngine getRenderEngine()
	{
		return renderEngine;
	}

	public void setRenderEngine(RenderEngine deligate)
	{
		this.renderEngine = deligate;
	}

	public RWikiObjectService getObjectService()
	{
		return objectService;
	}

	public void setObjectService(RWikiObjectService objectService)
	{
		this.objectService = objectService;
	}

	public PageLinkRenderer getPageLinkRenderer()
	{
		return plr;
	}

}
