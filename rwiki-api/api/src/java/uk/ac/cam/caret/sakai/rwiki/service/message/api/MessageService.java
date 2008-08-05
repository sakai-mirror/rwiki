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

package uk.ac.cam.caret.sakai.rwiki.service.message.api;

import java.util.List;

/**
 * @author ieb
 */
public interface MessageService
{

	/**
	 * Updates the session presence record in the wiki
	 * 
	 * @param session
	 * @param user
	 * @param page
	 * @param space
	 */
	void updatePresence(String session, String user, String page, String space);

	void addMessage(String session, String user, String page, String space,
			String message);

	/**
	 * returns a List of the Messages associated with the session
	 * 
	 * @param session
	 * @return
	 */
	List getSessionMessages(String session);

	/**
	 * Returns List of the Messages in the space
	 * 
	 * @param space
	 * @return
	 */
	List getMessagesInSpace(String space);

	/**
	 * Returns List of Messages in the page
	 * 
	 * @param space
	 * @param page
	 * @return
	 */
	List getMessagesInPage(String space, String page);

	/**
	 * Returns List representation of the users in the space
	 * 
	 * @param space
	 * @return
	 */
	List getUsersInSpace(String space);

	/**
	 * Returns List representation of the users on the page
	 * 
	 * @param space
	 * @param page
	 * @return
	 */
	List getUsersOnPage(String space, String page);

	/**
	 * @param pageSpace
	 * @param pageName
	 * @return
	 */
	List getUsersInSpaceOnly(String pageSpace, String pageName);

}
