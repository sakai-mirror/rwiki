/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006, 2007, 2008 The Sakai Foundation
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

package uk.ac.cam.caret.sakai.rwiki.component.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.sakaiproject.component.cover.ServerConfigurationService;

import uk.ac.cam.caret.sakai.rwiki.service.api.RWikiObjectService;
import uk.ac.cam.caret.sakai.rwiki.utils.NameHelper;

/**
 * @author ieb
 */
public class PageVisits
{
	private static final int MAX_SIZE = 10;

	private Stack<String> s = new Stack<String>();

	public PageVisits()
	{

	}

	public void addPage(String globalPageName)
	{
		s.remove(globalPageName);
		s.push(globalPageName);
		while (s.size() > MAX_SIZE)
		{
			s.remove(0);
		}
	}

	public List<String[]> getPageNames(String type)
	{
		List<String[]> l = new ArrayList<String[]>();
		if (s.size() > 0)
		{
			String localSpace = NameHelper.localizeSpace(s.peek());
			for (String pagename : s)
			{
				String[] pagespec = new String[2];
				pagespec[0] = ServerConfigurationService.getAccessUrl() + RWikiObjectService.REFERENCE_ROOT + encode(pagename)
						+ "." + type;
				pagespec[1] = NameHelper.localizeName(pagename, localSpace);
				l.add(pagespec);
			}
		}
		return l;
	}

	private String encode(String toEncode)
	{
		try
		{
			String encoded = URLEncoder.encode(toEncode, "UTF-8");
			encoded = encoded.replaceAll("\\+", "%20").replaceAll("%2F", "/");

			return encoded;

		}
		catch (UnsupportedEncodingException e)
		{
			throw new IllegalStateException("UTF-8 Encoding is not supported when encoding: " + toEncode + ": " + e.getMessage());
		}
	}

}
