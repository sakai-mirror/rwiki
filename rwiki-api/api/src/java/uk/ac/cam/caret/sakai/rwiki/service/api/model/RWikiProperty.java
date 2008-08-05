/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright 2006 Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 *       http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *
 **********************************************************************************/

package uk.ac.cam.caret.sakai.rwiki.service.api.model;

/**
 * Represents RWiki properties tied to the db instance
 * 
 * @author ieb
 */
// FIXME: Service
public interface RWikiProperty
{
	/**
	 * The ID
	 * 
	 * @return
	 */
	String getId();

	/**
	 * The ID
	 * 
	 * @param id
	 */
	void setId(String id);

	/**
	 * The name of the property
	 * 
	 * @return
	 */
	String getName();

	/**
	 * The Name of the property
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * The Value of the property
	 * 
	 * @return
	 */
	String getValue();

	/**
	 * The Value of the property
	 * 
	 * @param value
	 */
	void setValue(String value);

}
