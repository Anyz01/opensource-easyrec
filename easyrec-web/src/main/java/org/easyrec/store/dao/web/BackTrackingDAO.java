package org.easyrec.store.dao.web;

/**
 * This DAO manages the backtracking mechanism for recommended items.
 * <p/>
 * <p><b>Company:&nbsp;</b>
 * SAT, Research Studios Austria</p>
 * <p/>
 * <p><b>Copyright:&nbsp;</b>
 * (c) 2007</p>
 * <p/>
 * <p><b>last modified:</b><br/>
 * $Author: phlavac $<br/>
 * $Date: 2009-12-16 21:13:01 +0100 (Mi, 16 Dez 2009) $<br/>
 * $Revision: 15249 $</p>
 *
 * @author phlavac
 */
public interface BackTrackingDAO {
    
    /**
     * This tracks an item clicked by a user from a recommendation
     * @param userId
     * @param tenantId
     * @param itemFromId
     * @param itemFromType
     * @param itemToId
     * @param itemToType
     * @param recType
     */
    public void track(Integer userId, Integer tenantId, Integer itemFromId, Integer itemFromType, Integer itemToId, Integer itemToType, Integer recType);

    /**
     * Counts the occurence of clicks on an item in a recommendation.
     *
     * @param tenantId
     * @param itemFromId
     * @param itemFromTypeId
     * @param itemToId
     * @param itemToTypeId
     * @param recType
     *
     */
    public Integer getItemCount(Integer tenantId, Integer itemFromId, Integer itemFromTypeId, Integer itemToId, Integer itemToTypeId, Integer recType);

    /**
     * deletes tenant specific backtracking data
     *
     * @param tenantId
     */
    public void clear(Integer tenantId);
}
