/*
 * Copyright 2011 Research Studios Austria Forschungsgesellschaft mBH
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
package org.easyrec.store.dao.core;

import org.easyrec.model.core.transfer.TimeConstraintVO;
import org.easyrec.model.core.web.Item;
import org.easyrec.model.core.web.RemoteTenant;
import org.easyrec.model.core.web.statistic.ItemDetails;
import org.easyrec.store.dao.BasicDAO;

import javax.annotation.Nullable;
import java.util.List;


/**
 * This class manages items that are retrieved from the tenants.
 * <p/>
 * <p><b>Company:&nbsp;</b>
 * SAT, Research Studios Austria</p>
 * <p/>
 * <p><b>Copyright:&nbsp;</b>
 * (c) 2007</p>
 * <p/>
 * <p><b>last modified:</b><br/>
 * $Author: fsalcher $<br/>
 * $Date: 2012-03-08 12:29:09 +0100 (Do, 08 Mär 2012) $<br/>
 * $Revision: 18751 $</p>
 *
 * @author <AUTHOR>
 */
public interface ItemDAO extends BasicDAO {

    public enum SortColumn {
        NONE,
        ITEM_ID,
        ITEM_TYPE,
        DESCRIPTION
    }

    public static final String DEFAULT_ID_COLUMN_NAME = "ID";
    public static final String DEFAULT_TENANTID_COLUMN_NAME = "TENANTID";
    public static final String DEFAULT_ITEMID_COLUMN_NAME = "ITEMID";
    public static final String DEFAULT_ITEMTYPE_COLUMN_NAME = "ITEMTYPE";
    public static final String DEFAULT_DESCRIPTION_COLUMN_NAME = "DESCRIPTION";
    public static final String DEFAULT_URL_COLUMN_NAME = "URL";
    public static final String DEFAULT_IMAGEURL_COLUMN_NAME = "IMAGEURL";
    public static final String DEFAULT_ACTIVE_COLUMN_NAME = "ACTIVE";
    public static final String DEFAULT_VALUE_COLUMN_NAME = "VALUE";
    public static final String DEFAULT_CREATION_DATE_COLUMN_NAME = "CREATIONDATE";
    public static final String DEFAULT_PROFILEDATA_COLUMN_NAME = "PROFILEDATA";

    public static final String DEFAULT_TABLE_NAME = "item";
    public static final String DEFAULT_TABLE_KEY = "id";


    /**
     * This Function saves an Item.
     *
     * @param tenantId
     * @param itemId
     * @param itemType
     * @param itemDescription
     * @param url
     * @param imageurl
     *
     * @return the added item
     */
    public Item add(Integer tenantId, String itemId, String itemType, String itemDescription, String url,
                    String imageurl);

    /**
     * This Function insert or updates an Item.
     *
     * @param tenantId
     * @param itemId
     * @param itemType
     * @param itemDescription
     * @param url
     * @param imageurl
     *
     * @return the inserted or updated item
     */
    public Item insertOrUpdate(Integer tenantId, String itemId, String itemType, String itemDescription, String url,
                               String imageurl);


    /**
     * This function returns an item for the given key.
     *
     * @param remoteTenant
     * @param itemId
     * @param itemType
     *
     * @return the item
     */
    @Nullable
    public Item get(RemoteTenant remoteTenant, String itemId, String itemType);

    /**
     * This function returns an item (as it is) for the given id.
     * Note: don't forget to update item URLs!
     *
     * @param id
     * @return the item
     */
    public Item get(Integer id);

    public Item get(Integer tenantId, String itemId, String itemType);
    
    /**
     * This function returns true,
     * if an item for the given key exists.
     *
     * @param remoteTenant
     * @param itemId
     * @param itemType
     *
     * @return <code>true</code> if the item exists
     */
    public boolean exists(RemoteTenant remoteTenant, String itemId, String itemType);

    /**
     * This function activates an item.
     *
     * @param tenantId
     * @param itemId
     * @param itemType
     */
    public void activate(Integer tenantId, String itemId, String itemType);

    /**
     * This function deactivates an item.
     *
     * @param tenantId
     * @param itemId
     * @param itemType
     */
    public void deactivate(Integer tenantId, String itemId, String itemType);

    /**
     * This function removes an item with the given key.
     *
     * @param tenantId
     * @param itemId
     * @param itemType
     */
    public void remove(Integer tenantId, String itemId, String itemType);
    
    /**
     * This function removes an item with the given key.
     *
     * @param tenantId
     * @param itemType
     */
    public void remove(Integer tenantId, String itemType) throws Exception;

    /**
     * This function returns all items of a tenant in a given range(LIMIT ?,?) and
     * for a matching item description. This is used for paging and search.
     *
     * @param remoteTenant
     * @param itemDescription
     * @param start the offset of the SQL result
     * @param end   the number of items you want to load
     *
     * @return list with the requested items
     */
    public List<Item> getItems(RemoteTenant remoteTenant, String itemDescription, int start, int end);

    /**
     * This functions removes all items to a tenant.
     *
     * @param tenantId
     */
    public void removeItems(Integer tenantId);

    /**
     * Get Details for a given items.
     * - When did the first action occured?
     * - When did the last action occured?
     * - How many actions were on this item?
     * - How many users were on this item?
     *
     * @param tenantId
     * @param itemId
     * @param itemType
     *
     * @return a ItemDetails object with the details of the item
     */
    public ItemDetails getItemDetails(Integer tenantId, String itemId, String itemType);

    /**
     * Get a list of items that have rules. The result can be restricted by a description search term
     * and by a LIMIT values.
     *
     * @param tenantId
     * @param description Return only items with the given description. If an empty string is provided
     * no restriction is applied.
     * @param start offset parameter for the LIMIT statement
     * @param end row_count parameter for the LIMIT statement
     *
     * @return list of items
     */
    public List<Item> getItemsWithRules(Integer tenantId, String description, int start, int end);

    /**
     * Return the number of items with rules
     *
     * @param tenantId
     * @param description
     *
     * @return number of items
     */
    public int getNumberOfItemsWithRules(Integer tenantId, String description);

    /**
     * returns the number of items for a given tenant.
     *
     * @param tenantId
     *
     * @return number of items
     */
    public Integer count(Integer tenantId);

    /**
     * returns the number of items for a given tenant and a matching
     * item Descritpion
     *
     * @param tenantId
     * @param description
     *
     * @return number of items
     */
    public Integer count(Integer tenantId, String description);

    /**
     * Get Items that have recommendations that where clicked most by users.
     *
     * @param remoteTenant
     * @param start offset value of the LIMIT clause
     * @param end row_number value of the LIMIT clause
     *
     * @return list of items
     */
    public List<Item> getHotItems(RemoteTenant remoteTenant, Integer start, Integer end);

    /**
     * empties the item cache. For example if a tenant url was
     * changed all item urls need to be changed too.
     */
    public void emptyCache();

    /**
     * Returns a list of items that match the search criteria. It returns a List of
     * matching ITEMS based on the filters you provide - you can use NULL to ignore the filter.
     * The last few parameters are used for paging and sorting. You need to specify the TABLENAME
     * for sorting. If you want to know the total count of found items use the searchItemsTotalCount
     * function which uses the same parameters as here.
     *
     * @param tenantId               The id of the tenant the items belong to.
     * @param itemId                 if not {@code null} matches all items with a wildcard id.
     * @param itemTypes              if not {@code null} matches all items with a wildcard item type.
     * @param description            if not {@code null} matches all items with a wildcard description.
     * @param url                    if not {@code null} matches all items with a wildcard url.
     * @param imageUrl               if not {@code null} matches all items with a wildcard imageUrl.
     * @param active                 if not {@code null} matches all items with the active flag set to the specified value.
     * @param creationDateConstraint if not {@code null} matches all items matching the time constraint on the
     *                               creationDate.
     * @param hasRules              if true only items with rules are returned otherwise the number of rules is ignored.
     * @param rulesOfType
     * @param sortColumn             this UNSAFE parameter hold the tablename for sorting set it to NULL for no sorting (don't pass unchecked user input here)
     * @param sortDESC               Set this parameter to true if you want to sort your result DESCENDING
     * @param offset                 The offset you want to use on your result set - e.g., ITEMS PER PAGE * PAGENUMBER
     * @param itemCount              How many items you want to load from the database.
     *
     * @return List of all items matching the search criteria.
     */
    public List<Item> searchItems(int tenantId, String itemId, Iterable<String> itemTypes, String description,
                                  String url, String imageUrl, Boolean active, TimeConstraintVO creationDateConstraint,
                                  Boolean hasRules, String rulesOfType, SortColumn sortColumn, boolean sortDESC,
                                  Integer offset, Integer itemCount);

    /**
     * Returns the number of items that match the search criteria. It returns the Number of
     * matching ITEMS based on the filters you provide - you can use NULL to ignore the filter.
     *
     * @param tenantId               The id of the tenant the items belong to.
     * @param itemId                 if not {@code null} matches all items with a wildcard id.
     * @param itemTypes              if not {@code null} matches all items with a wildcard item type.
     * @param description            if not {@code null} matches all items with a wildcard description.
     * @param url                    if not {@code null} matches all items with a wildcard url.
     * @param imageUrl               if not {@code null} matches all items with a wildcard imageUrl.
     * @param active                 if not {@code null} matches all items with the active flag set to the specified value.
     * @param creationDateConstraint if not {@code null} matches all items matching the time constraint on the
     *                               creationDate.
     * @param hasRules
     * @param rulesOfType
     * @return List of all items matching the search criteria.
     */
    public int searchItemsTotalCount(int tenantId, String itemId, Iterable<String> itemTypes, String description,
                                     String url, String imageUrl, Boolean active,
                                     TimeConstraintVO creationDateConstraint, Boolean hasRules, String rulesOfType);


    /**
     * This function returns the int of the type id of a given item id.
     *
     * @param tenantId the tenantId of the item
     * @param itemId   the itemId of the item
     *
     * @return the itemType of the item
     */
    public int getItemTypeIdOfItem(Integer tenantId, Integer itemId);

}
