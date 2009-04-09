/**********************************************************************************
 * $URL:$
 * $Id:$
 ***********************************************************************************
 *
 * Copyright (c) 2008 Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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

package uk.ac.cam.caret.sakai.rwiki.bean.test;

import junit.framework.TestCase;

import org.easymock.MockControl;

import uk.ac.cam.caret.sakai.rwiki.service.api.RWikiObjectService;
import uk.ac.cam.caret.sakai.rwiki.service.api.model.RWikiObject;
import uk.ac.cam.caret.sakai.rwiki.tool.api.ToolRenderService;
import uk.ac.cam.caret.sakai.rwiki.tool.bean.RenderBean;

public class RenderBeanTest extends TestCase
{

	String localName = "Foo";

	String realm = "bar";

	String globalName = "bar.Foo";

	String otherRealm = "realm";

	String value = "value";

	ToolRenderService mockToolRenderService;

	RWikiObjectService mockObjectService;

	RWikiObject mockObject;

	RenderBean rb;

	MockControl renderServiceControl, objectServiceControl, rwikiObjectControl;

	public RenderBeanTest(String test)
	{
		super(test);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		renderServiceControl = MockControl
				.createControl(ToolRenderService.class);
		objectServiceControl = MockControl
				.createControl(RWikiObjectService.class);
		rwikiObjectControl = MockControl.createControl(RWikiObject.class);

		mockToolRenderService = (ToolRenderService) renderServiceControl
				.getMock();
		mockObjectService = (RWikiObjectService) objectServiceControl.getMock();
		mockObject = (RWikiObject) rwikiObjectControl.getMock();
		// mockObject = new RWikiObjectImpl();

		mockObjectService.checkUpdate(mockObject);
		objectServiceControl.setReturnValue(false);
		objectServiceControl.replay();

		rb = new RenderBean(mockObject, mockToolRenderService,
				mockObjectService, true);

	}

	/*
	 * Test method for
	 * 'uk.ac.cam.caret.sakai.rwiki.tool.bean.RenderBean.renderPage()'
	 */
	public void testRenderPage()
	{
		mockToolRenderService.renderPage(mockObject);
		renderServiceControl.setReturnValue(value);
		rwikiObjectControl.replay();
		renderServiceControl.replay();

		assertTrue(value.equals(rb.renderPage()));
		objectServiceControl.verify();
		renderServiceControl.verify();
		rwikiObjectControl.verify();
	}

	/*
	 * Test method for
	 * 'uk.ac.cam.caret.sakai.rwiki.tool.bean.RenderBean.renderPage(String,
	 * String)'
	 */
	public void testRenderPageStringString()
	{
		return;
		/*
		 * try { mockObjectService.getRWikiObject(globalName,user,realm); }
		 * catch (PermissionException e) { // EMPTY }
		 * objectServiceControl.setReturnValue(mockObject);
		 * mockRenderService.renderPage(mockObject,user,otherRealm);
		 * renderServiceControl.setReturnValue(value);
		 * objectServiceControl.replay(); rwikiObjectControl.replay();
		 * renderServiceControl.replay();
		 * assertTrue(value.equals(rb.renderPage(globalName, otherRealm)));
		 * objectServiceControl.verify(); renderServiceControl.verify();
		 * rwikiObjectControl.verify();
		 */
	}

}
