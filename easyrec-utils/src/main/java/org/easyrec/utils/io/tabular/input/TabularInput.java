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
package org.easyrec.utils.io.tabular.input;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Interface for importers of tabular (matrix-like) data.
 * <p/>
 * There are four ways of this class: via its Iterator methods, via its
 * readAll() method or by implementing an TabularInputObserver and calling
 * readAll() (or visisAll()). Finally, the content of a data row can be mapped
 * to an object by using a TabularInputRowMapper in the nextMappedObject() and
 * the readAllAndMap() methods.
 * <p/>
 * <p/>
 * <p>
 * <b>Company:&nbsp;</b> SAT, Research Studios Austria
 * </p>
 * <p/>
 * <p>
 * <b>Copyright:&nbsp;</b> (c) 2007
 * </p>
 * <p/>
 * <p>
 * <b>last modified:</b><br/>
 * $Author: szavrel $<br/>
 * $Date: 2011-03-22 15:26:04 +0100 (Di, 22 Mär 2011) $<br/>
 * $Revision: 17973 $
 * </p>
 *
 * @author Florian Kleedorfer
 */

public interface TabularInput {

    public void addObserver(TabularInputObserver observer);

    public void removeObserver(TabularInputObserver observer);

    public void clearObservers();

    /**
     * Returns the column names.
     *
     *
     */
    public List<String> getColumnNames();

    /**
     * Processes the data from the specified reader.
     *
     * @param stream
     * @param encoding
     */
    public void setSource(InputStream stream, String encoding);

    /**
     * Processes the data from the specified file.
     *
     * @param file
     * @param encoding
     */
    public void setSource(File file, String encoding);

    /**
     * Reads all data from the current position on into a List<List<String>>.
     *
     *
     */
    public List<List<String>> readAll();

    /**
     * Maps the whole data to a list of objects using the specified mapper. Note
     * that a row is omitted in the result if the mapper returns null for that
     * data row
     *
     * @param <T>
     * @param mapper
     *
     */
    public <T> List<T> readAll(TabularInputRowMapper<T> mapper);

    /**
     * Reads all data from the current position but does not return anything.
     * This method is only useful in connection with registering observers. In
     * this case the advantage over readAll() is that this method does not have
     * the overhead of creating a List for the whole data set.
     *
     *
     */
    public void visitAll();

    /**
     * Returns true if nextRow() will return another row.
     *
     *
     */
    public boolean hasNext();

    /**
     * Returns the next data row.
     *
     *
     */
    public List<String> next();

    /**
     * Returns the next data row, mapped to an object with the specified
     * TabularInputRowMapper. Note that this method may return null if the
     * mapper returns null for the next data row.
     *
     * @param <T>
     * @param mapper
     *
     */
    public <T> T next(TabularInputRowMapper<T> mapper);

    /**
     * Frees allocated resources. Should be called if iterating through the data
     * source is stopped before reaching its end. Note that calling this method
     * does not unregister any observers.
     */
    public void freeSourceSpecificResources();
}
