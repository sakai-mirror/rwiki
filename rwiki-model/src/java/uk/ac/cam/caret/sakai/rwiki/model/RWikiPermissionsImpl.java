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
package uk.ac.cam.caret.sakai.rwiki.model;

import uk.ac.cam.caret.sakai.rwiki.service.api.model.RWikiPermissions;

/**
 * @author ieb
 */
public class RWikiPermissionsImpl implements RWikiPermissions
{
	public static final int OWNER_READ_INDEX, OWNER_COMMENT_INDEX, OWNER_WRITE_INDEX,
			OWNER_ADMIN_INDEX;

	public static final int GROUP_READ_INDEX, GROUP_COMMENT_INDEX,GROUP_WRITE_INDEX,
			GROUP_ADMIN_INDEX;

	public static final int PUBLIC_READ_INDEX, PUBLIC_COMMENT_INDEX,PUBLIC_WRITE_INDEX;

	public static final int PERMISSIONS_LENGTH;

	static
	{
		int i = 0;
		OWNER_READ_INDEX = i++;
		OWNER_COMMENT_INDEX = i++;
		OWNER_WRITE_INDEX = i++;
		OWNER_ADMIN_INDEX = i++;
		GROUP_READ_INDEX = i++;
		GROUP_COMMENT_INDEX = i++;
		GROUP_WRITE_INDEX = i++;
		GROUP_ADMIN_INDEX = i++;
		PUBLIC_READ_INDEX = i++;
		PUBLIC_COMMENT_INDEX = i++;
		PUBLIC_WRITE_INDEX = i++;
		// end permissions
		PERMISSIONS_LENGTH = i;
	}

	private boolean[] permissions = new boolean[PERMISSIONS_LENGTH];

	public boolean[] getPermissions()
	{
		return permissions;
	}

	public void setPermissions(boolean[] permissions)
	{
		if (permissions != null && permissions.length != PERMISSIONS_LENGTH)
		{
			if (permissions != null && permissions.length != PERMISSIONS_LENGTH)
			{
				throw new IllegalArgumentException(
						"permissions must be a boolean array of length "
								+ PERMISSIONS_LENGTH + " or null");
			}
		}
		this.permissions = permissions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#isGroupAdmin()
	 */
	public boolean isGroupAdmin()
	{
		return permissions[GROUP_ADMIN_INDEX];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#setGroupAdmin(boolean)
	 */
	public void setGroupAdmin(boolean groupAdmin)
	{
		this.permissions[GROUP_ADMIN_INDEX] = groupAdmin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#isGroupRead()
	 */
	public boolean isGroupRead()
	{
		return permissions[GROUP_READ_INDEX];
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#setGroupRead(boolean)
	 */
	public void setGroupRead(boolean groupRead)
	{
		this.permissions[GROUP_READ_INDEX] = groupRead;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#isGroupComment()
	 */
	public boolean isGroupComment()
	{
		return permissions[GROUP_COMMENT_INDEX];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#setGroupComment(boolean)
	 */
	public void setGroupComment(boolean groupComment)
	{
		this.permissions[GROUP_COMMENT_INDEX] = groupComment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#isGroupWrite()
	 */
	public boolean isGroupWrite()
	{
		return permissions[GROUP_WRITE_INDEX];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#setGroupWrite(boolean)
	 */
	public void setGroupWrite(boolean groupWrite)
	{
		this.permissions[GROUP_WRITE_INDEX] = groupWrite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#isOwnerAdmin()
	 */
	public boolean isOwnerAdmin()
	{
		return permissions[OWNER_ADMIN_INDEX];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#setOwnerAdmin(boolean)
	 */
	public void setOwnerAdmin(boolean ownerAdmin)
	{
		this.permissions[OWNER_ADMIN_INDEX] = ownerAdmin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#isOwnerRead()
	 */
	public boolean isOwnerRead()
	{
		return permissions[OWNER_READ_INDEX];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#setOwnerRead(boolean)
	 */
	public void setOwnerRead(boolean ownerRead)
	{
		this.permissions[OWNER_READ_INDEX] = ownerRead;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#isOwnerComment()
	 */
	public boolean isOwnerComment()
	{
		return permissions[OWNER_COMMENT_INDEX];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#setOwnerComment(boolean)
	 */
	public void setOwnerComment(boolean ownerComment)
	{
		this.permissions[OWNER_COMMENT_INDEX] = ownerComment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#isOwnerWrite()
	 */
	public boolean isOwnerWrite()
	{
		return permissions[OWNER_WRITE_INDEX];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#setOwnerWrite(boolean)
	 */
	public void setOwnerWrite(boolean ownerWrite)
	{
		this.permissions[OWNER_WRITE_INDEX] = ownerWrite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#isPublicRead()
	 */
	public boolean isPublicRead()
	{
		return permissions[PUBLIC_READ_INDEX];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#setPublicRead(boolean)
	 */
	public void setPublicRead(boolean publicRead)
	{
		this.permissions[PUBLIC_READ_INDEX] = publicRead;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#isPublicComment()
	 */
	public boolean isPublicComment()
	{
		return permissions[PUBLIC_COMMENT_INDEX];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#setPublicComment(boolean)
	 */
	public void setPublicComment(boolean publicComment)
	{
		this.permissions[PUBLIC_COMMENT_INDEX] = publicComment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#isPublicWrite()
	 */
	public boolean isPublicWrite()
	{
		return permissions[PUBLIC_WRITE_INDEX];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.caret.sakai.rwiki.model.impl.RWikiPermissions#setPublicWrite(boolean)
	 */
	public void setPublicWrite(boolean publicWrite)
	{
		this.permissions[PUBLIC_WRITE_INDEX] = publicWrite;
	}

	public String toString()
	{
		char[] perms = "o---- s---- p---".toCharArray();
		if (isOwnerRead()) perms[1] = 'r';
		if (isOwnerComment()) perms[4] = 'c';
		if (isOwnerWrite()) perms[2] = 'w';
		if (isOwnerAdmin()) perms[3] = 'a';
		if (isGroupRead()) perms[7] = 'r';
		if (isGroupComment()) perms[10] = 'c';
		if (isGroupWrite()) perms[8] = 'w';
		if (isGroupAdmin()) perms[9] = 'a';
		if (isPublicRead()) perms[13] = 'r';
		if (isPublicWrite()) perms[14] = 'w';
		if (isPublicComment()) perms[15] = 'c';
		return new String(perms);
	}

}
