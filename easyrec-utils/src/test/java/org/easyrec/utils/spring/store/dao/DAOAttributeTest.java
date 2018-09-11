/**Copyright 2010 Research Studios Austria Forschungsgesellschaft mBH
 *
 * This file is part of easyrec.
 *
 * easyrec is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * easyrec is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with easyrec.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.easyrec.utils.spring.store.dao;

import org.easyrec.utils.spring.store.dao.annotation.DAO;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Tests for the {@link at.researchstudio.sat.util.store.dao.DAOAttribute} class.
 * <p>
 * <b>Company:&nbsp;</b> SAT, Research Studios Austria
 * </p>
 * <p>
 * <b>Copyright:&nbsp;</b> (c) 2007
 * </p>
 * <p>
 * <b>last modified:</b><br/> $Author: pmarschik $<br/> $Date: 2011-02-11 11:30:46 +0100 (Fr, 11 Feb 2011) $<br/> $Revision: 17662 $
 * </p>
 *
 * @author Florian Kleedorfer
 */
public class DAOAttributeTest {

    @Test
    public void testDAOAttribute() {
        TestClass testClass = new TestClass();
        assertNotNull(testClass.getClass().getAnnotation(DAO.class));
    }

    @DAO
    private static class TestClass {

    }
}
