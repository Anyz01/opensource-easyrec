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
package org.easyrec.service.domain.impl;

import org.easyrec.model.core.*;
import org.easyrec.model.core.transfer.IAConstraintVO;
import org.easyrec.service.domain.TypeMappingService;
import org.easyrec.store.dao.core.types.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Implementation of the {@link org.easyrec.service.domain.TypeMappingService} interface.
 * <p/>
 * <p><b>Company:&nbsp;</b>
 * SAT, Research Studios Austria</p>
 * <p/>
 * <p><b>Copyright:&nbsp;</b>
 * (c) 2007</p>
 * <p/>
 * <p><b>last modified:</b><br/>
 * $Author: szavrel $<br/>
 * $Date: 2012-02-02 18:32:45 +0100 (Do, 02 Feb 2012) $<br/>
 * $Revision: 18689 $</p>
 *
 * @author Roman Cerny
 */
public class TypeMappingServiceImpl implements TypeMappingService {
    private final ActionTypeDAO actionTypeDAO;
    private final AggregateTypeDAO aggregateTypeDAO;
    private final AssocTypeDAO assocTypeDAO;
    private final ItemTypeDAO itemTypeDAO;
    private final SourceTypeDAO sourceTypeDAO;
    private final ViewTypeDAO viewTypeDAO;

    public TypeMappingServiceImpl(ActionTypeDAO actionTypeDAO, AggregateTypeDAO aggregateTypeDAO,
                                  AssocTypeDAO assocTypeDAO, ItemTypeDAO itemTypeDAO, SourceTypeDAO sourceTypeDAO,
                                  ViewTypeDAO viewTypeDAO) {
        this.actionTypeDAO = actionTypeDAO;
        this.aggregateTypeDAO = aggregateTypeDAO;
        this.assocTypeDAO = assocTypeDAO;
        this.itemTypeDAO = itemTypeDAO;
        this.sourceTypeDAO = sourceTypeDAO;
        this.viewTypeDAO = viewTypeDAO;
    }

    // convert single model objects
    @Override
    public ActionVO<Integer, String> convertActionVO(Integer tenantId,
                                                                               ActionVO<Integer, Integer> action) {
        if (action == null) {
            return null;
        }

        return new ActionVO<>(
                action.getId(), action.getTenant(), action.getUser(), action.getSessionId(), action.getIp(),
                convertItemVO(tenantId, action.getItem()), actionTypeDAO.getTypeById(tenantId, action.getActionType()),
                action.getRatingValue(),
                action.getActionInfo(), action.getActionTime());
    }

    @Override
    public ActionVO<Integer, Integer> convertTypedActionVO(Integer tenantId,
                                                                                      ActionVO<Integer, String> typedAction) {
        if (typedAction == null) {
            return null;
        }

        return new ActionVO<>(
                typedAction.getId(), typedAction.getTenant(), typedAction.getUser(), typedAction.getSessionId(),
                typedAction.getIp(), convertTypedItemVO(tenantId, typedAction.getItem()),
                actionTypeDAO.getIdOfType(tenantId, typedAction.getActionType()), typedAction.getRatingValue(),
                typedAction.getActionInfo(),
                typedAction.getActionTime());
    }

    @Override
    public AssociatedItemVO<Integer, String> convertAssociatedItemVO(Integer tenantId,
                                                                                      AssociatedItemVO<Integer, Integer> associatedItem) {
        if (associatedItem == null) {
            return null;
        }

        return new AssociatedItemVO<>(
                convertItemVO(tenantId, associatedItem.getItem()), associatedItem.getAssocValue(),
                associatedItem.getItemAssocId(), assocTypeDAO.getTypeById(tenantId, associatedItem.getAssocType()));
    }

    @Override
    public AssociatedItemVO<Integer, Integer> convertTypedAssociatedItemVO(Integer tenantId,
                                                                                             AssociatedItemVO<Integer, String> typedAssociatedItem) {
        if (typedAssociatedItem == null) {
            return null;
        }

        return new AssociatedItemVO<>(
                convertTypedItemVO(tenantId, typedAssociatedItem.getItem()), typedAssociatedItem.getAssocValue(),
                typedAssociatedItem.getItemAssocId(),
                assocTypeDAO.getIdOfType(tenantId, typedAssociatedItem.getAssocType()));
    }

    @Override
    public IAConstraintVO<Integer, String> convertIAConstraintVO(Integer tenantId,
                                                                         IAConstraintVO<Integer, Integer> constraint) {
        if (constraint == null) {
            return null;
        }

        return new IAConstraintVO<>(
                constraint.getNumberOfResults(), viewTypeDAO.getTypeById(tenantId, constraint.getViewType()),
                sourceTypeDAO.getTypeById(tenantId, constraint.getSourceType()), constraint.getSourceInfo(),
                constraint.getTenant(), constraint.isActive(), constraint.getSortAsc());
    }

    @Override
    public IAConstraintVO<Integer, Integer> convertTypedIAConstraintVO(Integer tenantId,
                                                                                IAConstraintVO<Integer, String> typedConstraint) {
        if (typedConstraint == null) {
            return null;
        }

        return new IAConstraintVO<>(
                typedConstraint.getNumberOfResults(), viewTypeDAO.getIdOfType(tenantId, typedConstraint.getViewType()),
                sourceTypeDAO.getIdOfType(tenantId, typedConstraint.getSourceType()), typedConstraint.getSourceInfo(),
                typedConstraint.getTenant(), typedConstraint.isActive(), typedConstraint.getSortAsc());
    }

    @Override
    public ItemAssocVO<Integer, String> convertItemAssocVO(Integer tenantId,
                                                                                            ItemAssocVO<Integer,Integer> itemAssoc) {
        if (itemAssoc == null) {
            return null;
        }

        return new ItemAssocVO<>(
                itemAssoc.getId(), itemAssoc.getTenant(), convertItemVO(tenantId, itemAssoc.getItemFrom()),
                assocTypeDAO.getTypeById(tenantId, itemAssoc.getAssocType()), itemAssoc.getAssocValue(),
                convertItemVO(tenantId, itemAssoc.getItemTo()),
                sourceTypeDAO.getTypeById(tenantId, itemAssoc.getSourceType()), itemAssoc.getSourceInfo(),
                viewTypeDAO.getTypeById(tenantId, itemAssoc.getViewType()), itemAssoc.isActive(),
                itemAssoc.getChangeDate());
    }

    @Override
    public ItemAssocVO<Integer,Integer> convertTypedItemAssocVO(Integer tenantId,
                                                                                                     ItemAssocVO<Integer, String> typedItemAssoc) {
        if (typedItemAssoc == null) {
            return null;
        }

        return new ItemAssocVO<>(
                typedItemAssoc.getId(), typedItemAssoc.getTenant(),
                convertTypedItemVO(tenantId, typedItemAssoc.getItemFrom()),
                assocTypeDAO.getIdOfType(tenantId, typedItemAssoc.getAssocType()), typedItemAssoc.getAssocValue(),
                convertTypedItemVO(tenantId, typedItemAssoc.getItemTo()),
                sourceTypeDAO.getIdOfType(tenantId, typedItemAssoc.getSourceType()), typedItemAssoc.getSourceInfo(),
                viewTypeDAO.getIdOfType(tenantId, typedItemAssoc.getViewType()), typedItemAssoc.isActive(),
                typedItemAssoc.getChangeDate());
    }

    @Override
    public ItemVO<Integer, String> convertItemVO(Integer tenantId, ItemVO<Integer, Integer> item) {
        if (item == null) {
            return null;
        }

        return new ItemVO<>(item.getTenant(),
                item.getItem(), itemTypeDAO.getTypeById(tenantId, item.getType()));
    }

    @Override
    public ItemVO<Integer, Integer> convertTypedItemVO(Integer tenantId,
                                                                ItemVO<Integer, String> typedItem) {
        if (typedItem == null) {
            return null;
        }

        return new ItemVO<>(typedItem.getTenant(),
                typedItem.getItem(), itemTypeDAO.getIdOfType(tenantId, typedItem.getType()));
    }

    @Override
    public RankedItemVO<Integer, String> convertRankedItemVO(Integer tenantId,
                                                                              RankedItemVO<Integer, Integer> rankedItem) {
        if (rankedItem == null) {
            return null;
        }

        return new RankedItemVO<>(
                convertItemVO(tenantId, rankedItem.getItem()),
                actionTypeDAO.getTypeById(tenantId, rankedItem.getActionType()), rankedItem.getRank(),
                rankedItem.getCount());
    }

    @Override
    public RankedItemVO<Integer, Integer> convertTypedRankedItemVO(Integer tenantId,
                                                                                     RankedItemVO<Integer, String> typedRankedItem) {
        if (typedRankedItem == null) {
            return null;
        }

        return new RankedItemVO<>(
                convertTypedItemVO(tenantId, typedRankedItem.getItem()),
                actionTypeDAO.getIdOfType(tenantId, typedRankedItem.getActionType()), typedRankedItem.getRank(),
                typedRankedItem.getCount());
    }

    @Override
    public RatingVO<Integer, String> convertRatingVO(Integer tenantId,
                                                                       RatingVO<Integer, Integer> rating) {
        if (rating == null) {
            return null;
        }

        return new RatingVO<>(
                convertItemVO(tenantId, rating.getItem()), rating.getRatingValue(), rating.getCount(),
                rating.getLastActionTime(), rating.getUser(), rating.getSessionId());
    }

    @Override
    public RatingVO<Integer, Integer> convertTypedRatingVO(Integer tenantId,
                                                                             RatingVO<Integer, String> typedRating) {
        if (typedRating == null) {
            return null;
        }

        return new RatingVO<>(
                convertTypedItemVO(tenantId, typedRating.getItem()), typedRating.getRatingValue(),
                typedRating.getCount(), typedRating.getLastActionTime(), typedRating.getUser(),
                typedRating.getSessionId());
    }

    @Override
    public RecommendationVO<Integer, String> convertRecommendationVO(Integer tenantId,
        RecommendationVO<Integer, Integer> recommendation) {
        if (recommendation == null) {
            return null;
        }

        return new RecommendationVO<>(
                recommendation.getId(), recommendation.getTenant(), recommendation.getUser(),
                recommendation.getQueriedItem(), itemTypeDAO.getTypeById(tenantId, recommendation.getQueriedItemType()),
                assocTypeDAO.getTypeById(tenantId, recommendation.getQueriedAssocType()),
                actionTypeDAO.getTypeById(tenantId, recommendation.getRelatedActionType()),
                recommendation.getRecommendationStrategy(), recommendation.getExplanation(),
                recommendation.getRecommendationTime(),
                convertListOfRecommendedItemVOs(tenantId, recommendation.getRecommendedItems()));
    }

    @Override
    public RecommendationVO<Integer, Integer> convertTypedRecommendationVO(
            Integer tenantId, RecommendationVO<Integer, String> typedRecommendation) {
        if (typedRecommendation == null) {
            return null;
        }

        return new RecommendationVO<>(
                typedRecommendation.getId(), typedRecommendation.getTenant(), typedRecommendation.getUser(),
                typedRecommendation.getQueriedItem(),
                itemTypeDAO.getIdOfType(tenantId, typedRecommendation.getQueriedItemType()),
                assocTypeDAO.getIdOfType(tenantId, typedRecommendation.getQueriedAssocType()),
                actionTypeDAO.getIdOfType(tenantId, typedRecommendation.getRelatedActionType()),
                typedRecommendation.getRecommendationStrategy(), typedRecommendation.getExplanation(),
                typedRecommendation.getRecommendationTime(),
                convertListOfTypedRecommendedItemVOs(tenantId, typedRecommendation.getRecommendedItems()));
    }

    @Override
    public RecommendedItemVO<Integer, String> convertRecommendedItemVO(Integer tenantId,
                                                                                RecommendedItemVO<Integer, Integer> recommendedItem) {
        if (recommendedItem == null) {
            return null;
        }

        return new RecommendedItemVO<>(
                recommendedItem.getId(), convertItemVO(tenantId, recommendedItem.getItem()),
                recommendedItem.getPredictionValue(), recommendedItem.getRecommendationId(),
                recommendedItem.getItemAssocId(), recommendedItem.getExplanation());
    }

    @Override
    public RecommendedItemVO<Integer, Integer> convertTypedRecommendedItemVO(Integer tenantId,
                                                                                      RecommendedItemVO<Integer, String> typedRecommendedItem) {
        if (typedRecommendedItem == null) {
            return null;
        }

        return new RecommendedItemVO<>(
                typedRecommendedItem.getId(), convertTypedItemVO(tenantId, typedRecommendedItem.getItem()),
                typedRecommendedItem.getPredictionValue(), typedRecommendedItem.getRecommendationId(),
                typedRecommendedItem.getItemAssocId(), typedRecommendedItem.getExplanation());
    }

    // convert lists of model objects
    @Override
    public List<ActionVO<Integer, String>> convertListOfActionVOs(Integer tenantId,
                                                                                            List<ActionVO<Integer, Integer>> inList) {
        if (inList == null) {
            return null;
        }
        List<ActionVO<Integer, String>> outList = new ArrayList<>();

        for (ActionVO<Integer, Integer> action : inList) {
            outList.add(convertActionVO(tenantId, action));
        }
        return outList;
    }

    @Override
    public List<ActionVO<Integer, Integer>> convertListOfTypedActionVOs(Integer tenantId,
                                                                                                   List<ActionVO<Integer, String>> inList) {
        if (inList == null) {
            return null;
        }
        List<ActionVO<Integer, Integer>> outList = new ArrayList<>();

        for (ActionVO<Integer, String> typedAction : inList) {
            outList.add(convertTypedActionVO(tenantId, typedAction));
        }
        return outList;
    }

    @Override
    public List<AssociatedItemVO<Integer, String>> convertListOfAssociatedItemVOs(Integer tenantId,
                                                                                                   List<AssociatedItemVO<Integer, Integer>> inList) {
        if (inList == null) {
            return null;
        }
        List<AssociatedItemVO<Integer, String>> outList = new ArrayList<>();

        for (AssociatedItemVO<Integer, Integer> associatedItem : inList) {
            outList.add(convertAssociatedItemVO(tenantId, associatedItem));
        }

        return outList;
    }

    @Override
    public List<AssociatedItemVO<Integer, Integer>> convertListOfTypedAssociatedItemVOs(
            Integer tenantId, List<AssociatedItemVO<Integer, String>> inList) {
        if (inList == null) {
            return null;
        }
        List<AssociatedItemVO<Integer, Integer>> outList = new ArrayList<>();

        for (AssociatedItemVO<Integer, String> typedAssociatedItem : inList) {
            outList.add(convertTypedAssociatedItemVO(tenantId, typedAssociatedItem));
        }

        return outList;
    }

    @Override
    public List<IAConstraintVO<Integer, String>> convertListOfIAConstraintVOs(Integer tenantId,
                                                                                      List<IAConstraintVO<Integer, Integer>> inList) {
        if (inList == null) {
            return null;
        }
        List<IAConstraintVO<Integer, String>> outList = new ArrayList<>();

        for (IAConstraintVO<Integer, Integer> constraint : inList) {
            outList.add(convertIAConstraintVO(tenantId, constraint));
        }

        return outList;
    }

    @Override
    public List<IAConstraintVO<Integer, Integer>> convertListOfTypedIAConstraintVOs(Integer tenantId,
                                                                                             List<IAConstraintVO<Integer, String>> inList) {
        if (inList == null) {
            return null;
        }
        List<IAConstraintVO<Integer, Integer>> outList = new ArrayList<>();

        for (IAConstraintVO<Integer, String> typedConstraint : inList) {
            outList.add(convertTypedIAConstraintVO(tenantId, typedConstraint));
        }

        return outList;
    }

    @Override
    public List<ItemAssocVO<Integer, String>> convertListOfItemAssocVOs(
            Integer tenantId, List<ItemAssocVO<Integer,Integer>> inList) {
        if (inList == null) {
            return null;
        }
        List<ItemAssocVO<Integer, String>> outList = new ArrayList<>();

        for (ItemAssocVO<Integer,Integer> itemAssoc : inList) {
            outList.add(convertItemAssocVO(tenantId, itemAssoc));
        }
        return outList;
    }

    @Override
    public List<ItemAssocVO<Integer,Integer>> convertListOfTypedItemAssocVOs(
            Integer tenantId, List<ItemAssocVO<Integer, String>> inList) {
        if (inList == null) {
            return null;
        }
        List<ItemAssocVO<Integer,Integer>> outList = new ArrayList<>();

        for (ItemAssocVO<Integer, String> typedItemAssoc : inList) {
            outList.add(convertTypedItemAssocVO(tenantId, typedItemAssoc));
        }
        return outList;
    }

    @Override
    public List<ItemVO<Integer, String>> convertListOfItemVOs(Integer tenantId,
                                                                       List<ItemVO<Integer, Integer>> inList) {
        if (inList == null) {
            return null;
        }
        List<ItemVO<Integer, String>> outList = new ArrayList<>();

        for (ItemVO<Integer, Integer> item : inList) {
            outList.add(convertItemVO(tenantId, item));
        }
        return outList;
    }

    @Override
    public List<ItemVO<Integer, Integer>> convertListOfTypedItemVOs(Integer tenantId,
                                                                             List<ItemVO<Integer, String>> inList) {
        if (inList == null) {
            return null;
        }
        List<ItemVO<Integer, Integer>> outList = new ArrayList<>();

        for (ItemVO<Integer, String> typedItem : inList) {
            outList.add(convertTypedItemVO(tenantId, typedItem));
        }
        return outList;
    }

    @Override
    public List<RankedItemVO<Integer, String>> convertListOfRankedItemVOs(Integer tenantId,
                                                                                           List<RankedItemVO<Integer, Integer>> inList) {
        if (inList == null) {
            return null;
        }
        List<RankedItemVO<Integer, String>> outList = new ArrayList<>();

        for (RankedItemVO<Integer, Integer> rankedItem : inList) {
            outList.add(convertRankedItemVO(tenantId, rankedItem));
        }

        return outList;
    }

    @Override
    public List<RankedItemVO<Integer, Integer>> convertListOfTypedRankedItemVOs(Integer tenantId,
                                                                                                  List<RankedItemVO<Integer, String>> inList) {
        if (inList == null) {
            return null;
        }
        List<RankedItemVO<Integer, Integer>> outList = new ArrayList<>();

        for (RankedItemVO<Integer, String> typedRankedItem : inList) {
            outList.add(convertTypedRankedItemVO(tenantId, typedRankedItem));
        }

        return outList;
    }

    @Override
    public List<RatingVO<Integer, String>> convertListOfRatingVOs(Integer tenantId,
                                                                                    List<RatingVO<Integer, Integer>> inList) {
        if (inList == null) {
            return null;
        }
        List<RatingVO<Integer, String>> outList = new ArrayList<>();

        for (RatingVO<Integer, Integer> rating : inList) {
            outList.add(convertRatingVO(tenantId, rating));
        }

        return outList;
    }

    @Override
    public List<RatingVO<Integer, Integer>> convertListOfTypedRatingVOs(Integer tenantId,
                                                                                          List<RatingVO<Integer, String>> inList) {
        if (inList == null) {
            return null;
        }
        List<RatingVO<Integer, Integer>> outList = new ArrayList<>();

        for (RatingVO<Integer, String> typedRating : inList) {
            outList.add(convertTypedRatingVO(tenantId, typedRating));
        }

        return outList;
    }

    @Override
    public List<RecommendationVO<Integer, String>> convertListOfRecommendationVO(
            Integer tenantId, List<RecommendationVO<Integer, Integer>> inList) {
        if (inList == null) {
            return null;
        }
        List<RecommendationVO<Integer, String>> outList = new ArrayList<>();

        for (RecommendationVO<Integer, Integer> recommendation : inList) {
            outList.add(convertRecommendationVO(tenantId, recommendation));
        }

        return outList;
    }

    @Override
    public List<RecommendationVO<Integer, Integer>> convertListOfTypedRecommendationVO(
            Integer tenantId, List<RecommendationVO<Integer, String>> inList) {
        if (inList == null) {
            return null;
        }
        List<RecommendationVO<Integer, Integer>> outList = new ArrayList<>();

        for (RecommendationVO<Integer, String> typedRecommendation : inList) {
            outList.add(convertTypedRecommendationVO(tenantId, typedRecommendation));
        }

        return outList;
    }

    @Override
    public List<RecommendedItemVO<Integer, String>> convertListOfRecommendedItemVOs(Integer tenantId,
                                                                                             List<RecommendedItemVO<Integer, Integer>> inList) {
        if (inList == null) {
            return null;
        }
        List<RecommendedItemVO<Integer, String>> outList = new ArrayList<>();

        for (RecommendedItemVO<Integer, Integer> recommendedItem : inList) {
            outList.add(convertRecommendedItemVO(tenantId, recommendedItem));
        }

        return outList;
    }

    @Override
    public List<RecommendedItemVO<Integer, Integer>> convertListOfTypedRecommendedItemVOs(Integer tenantId,
                                                                                                   List<RecommendedItemVO<Integer, String>> inList) {
        if (inList == null) {
            return null;
        }
        List<RecommendedItemVO<Integer, Integer>> outList = new ArrayList<>();

        for (RecommendedItemVO<Integer, String> typedRecommendedItem : inList) {
            outList.add(convertTypedRecommendedItemVO(tenantId, typedRecommendedItem));
        }

        return outList;
    }

    @Override
    public List<RecommendedItemVO<Integer, String>> convertListOfRecommendedItemVOs(
            List<RecommendedItemVO<Integer, Integer>> inList) {
        if (inList == null) {
            return null;
        }
        Integer tenantId;
        List<RecommendedItemVO<Integer, String>> outList = new ArrayList<>();

        for (RecommendedItemVO<Integer, Integer> recommendedItem : inList) {
            if (recommendedItem.getItem() == null) {
                continue;
            }
            tenantId = recommendedItem.getItem().getTenant();
            if (tenantId == null) {
                continue;
            }
            outList.add(convertRecommendedItemVO(tenantId, recommendedItem));
        }

        return outList;
    }

    @Override
    public List<RecommendedItemVO<Integer, Integer>> convertListOfTypedRecommendedItemVOs(
            List<RecommendedItemVO<Integer, String>> inList) {
        if (inList == null) {
            return null;
        }
        List<RecommendedItemVO<Integer, Integer>> outList = new ArrayList<>();

        Integer tenantId;
        for (RecommendedItemVO<Integer, String> typedRecommendedItem : inList) {
            if (typedRecommendedItem.getItem() == null) {
                continue;
            }
            tenantId = typedRecommendedItem.getItem().getTenant();
            if (tenantId == null) {
                continue;
            }
            outList.add(convertTypedRecommendedItemVO(tenantId, typedRecommendedItem));
        }

        return outList;
    }


    // get integer based id of type
    @Override
    public Integer getIdOfActionType(Integer tenantId, String actionType) {
        return actionTypeDAO.getIdOfType(tenantId, actionType);
    }

    @Override
    public Integer getIdOfAggregateType(Integer tenantId, String aggregateType) {
        return aggregateTypeDAO.getIdOfType(tenantId, aggregateType);
    }

    @Override
    public Integer getIdOfAssocType(Integer tenantId, String assocType) {
        return assocTypeDAO.getIdOfType(tenantId, assocType);
    }

    @Override
    public Integer getIdOfAssocType(Integer tenantId, String assocType, Boolean visible) {
        return assocTypeDAO.getIdOfType(tenantId, assocType, visible);
    }

    @Override
    public Integer getIdOfItemType(Integer tenantId, String itemType) {
        return itemTypeDAO.getIdOfType(tenantId, itemType);
    }

    @Override
    public Integer getIdOfItemType(Integer tenantId, String itemType, Boolean visible) {
        return itemTypeDAO.getIdOfType(tenantId, itemType, visible);
    }

    @Override
    public Integer getIdOfSourceType(Integer tenantId, String sourceType) {
        return sourceTypeDAO.getIdOfType(tenantId, sourceType);
    }

    @Override
    public Integer getIdOfViewType(Integer tenantId, String viewType) {
        return viewTypeDAO.getIdOfType(tenantId, viewType);
    }

    // get string based id of type
    @Override
    public String getActionTypeById(Integer tenantId, Integer actionTypeId) {
        return actionTypeDAO.getTypeById(tenantId, actionTypeId);
    }

    @Override
    public String getAggregateTypeById(Integer tenantId, Integer aggregateTypeId) {
        return aggregateTypeDAO.getTypeById(tenantId, aggregateTypeId);
    }

    @Override
    public String getAssocTypeById(Integer tenantId, Integer assocTypeId) {
        return assocTypeDAO.getTypeById(tenantId, assocTypeId);
    }

    @Override
    public String getItemTypeById(Integer tenantId, Integer itemTypeId) {
        return itemTypeDAO.getTypeById(tenantId, itemTypeId);
    }

    @Override
    public String getSourceTypeById(Integer tenantId, Integer sourceTypeId) {
        return sourceTypeDAO.getTypeById(tenantId, sourceTypeId);
    }

    @Override
    public String getViewTypeById(Integer tenantId, Integer viewTypeId) {
        return viewTypeDAO.getTypeById(tenantId, viewTypeId);
    }

    // get mappings of types
    @Override
    public HashMap<String, Integer> getActionTypeMapping(Integer tenantId) {
        return actionTypeDAO.getMapping(tenantId);
    }

    @Override
    public HashMap<String, Integer> getAggregateTypeMapping(Integer tenantId) {
        return aggregateTypeDAO.getMapping(tenantId);
    }

    @Override
    public HashMap<String, Integer> getAssocTypeMapping(Integer tenantId) {
        return assocTypeDAO.getMapping(tenantId);
    }

    @Override
    public HashMap<String, Integer> getItemTypeMapping(Integer tenantId) {
        return itemTypeDAO.getMapping(tenantId);
    }

    @Override
    public HashMap<String, Integer> getSourceTypeMapping(Integer tenantId) {
        return sourceTypeDAO.getMapping(tenantId);
    }

    @Override
    public HashMap<String, Integer> getViewTypeMapping(Integer tenantId) {
        return viewTypeDAO.getMapping(tenantId);
    }

    // get set of types
    @Override
    public Set<String> getActionTypes(Integer tenantId) {
        return actionTypeDAO.getTypes(tenantId);
    }

    @Override
    public Set<String> getAggregateTypes(Integer tenantId) {
        return aggregateTypeDAO.getTypes(tenantId);
    }

    @Override
    public Set<String> getAssocTypes(Integer tenantId) {
        return assocTypeDAO.getTypes(tenantId);
    }

    @Override
    public Set<String> getAssocTypes(Integer tenantId, Boolean visible) {
        return assocTypeDAO.getTypes(tenantId, visible);
    }

    @Override
    public Set<String> getItemTypes(Integer tenantId) {
        return itemTypeDAO.getTypes(tenantId);
    }

    @Override
    public Set<String> getItemTypes(Integer tenantId, Boolean visible) {
        return itemTypeDAO.getTypes(tenantId, visible);
    }

    @Override
    public Set<String> getSourceTypes(Integer tenantId) {
        return sourceTypeDAO.getTypes(tenantId);
    }

    @Override
    public Set<String> getViewTypes(Integer tenantId) {
        return viewTypeDAO.getTypes(tenantId);
    }
}
