###############################################################
# $Author: szavrel $
# $Revision: 18127 $
# $Date: 2011-04-13 18:30:59 +0200 (Mi, 13 Apr 2011) $
###############################################################

# ATTENTION: do not add other sql statements than the CREATE TABLE statement

CREATE TABLE profile (
  profileId INT(11) unsigned NOT NULL AUTO_INCREMENT,
  tenantId INT(11) NOT NULL,
  itemId INT(11)NOT NULL,
  itemTypeId INT(11) NOT NULL,
  profileData text,
  changeDate TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  active TINYINT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (profileId),
  UNIQUE KEY (tenantId, itemId, itemTypeId)
) COMMENT='Table containing item profiles';
    
