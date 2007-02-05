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

package uk.ac.cam.caret.sakai.rwiki.utils;

import java.io.StringReader;

import org.sakaiproject.util.ResourceLoader;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Digests XHTML into a string representation
 * 
 * @author ieb
 */
public class DigestHtml
{

	public static String digest(String todigest)
	{
		Digester d = new Digester();
		try
		{
			XMLReader reader = XMLReaderFactory
					.createXMLReader("org.apache.xerces.parsers.SAXParser");
			reader.setContentHandler(d);
			reader.parse(new InputSource(new StringReader("<content>" //$NON-NLS-1$
					+ todigest + "</content>"))); //$NON-NLS-1$
			return d.toString();
		}
		catch (Exception ex)
		{
			return d.toString() + Messages.getString("DigestHtml.3") + ex.getMessage(); //$NON-NLS-1$
		}
	}

}
