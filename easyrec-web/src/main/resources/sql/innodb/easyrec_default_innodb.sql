--
-- Table structure for table easyrec.action
--

DROP TABLE IF EXISTS action;
CREATE TABLE action (
  id int(11) unsigned NOT NULL AUTO_INCREMENT,
  tenantId int(11) NOT NULL,
  userId int(11) DEFAULT NULL,
  sessionId varchar(50) DEFAULT NULL,
  ip varchar(45) DEFAULT NULL,
  itemId int(11) DEFAULT NULL,
  itemTypeId int(11) NOT NULL,
  actionTypeId int(11) NOT NULL,
  ratingValue int(11) DEFAULT NULL,
  actionInfo varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  actionTime datetime NOT NULL,
  PRIMARY KEY (id),
  KEY action_reader (tenantId,userId,actionTypeId,itemTypeId),
  KEY charts (tenantId,actionTypeId,actionTime,itemId,itemTypeId)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='Table containing user actions';

--
-- Table structure for table easyrec.actionarch
--
DROP TABLE IF EXISTS actionarch;
CREATE TABLE actionarch (
  id int(11) unsigned NOT NULL,
  tenantId int(11) NOT NULL,
  userId int(11) default NULL,
  sessionId varchar(50) default NULL,
  ip varchar(45) default NULL,
  itemId int(11) default NULL,
  itemTypeId int(11) NOT NULL,
  actionTypeId int(11) NOT NULL,
  ratingValue int(11) default NULL,
  actionInfo varchar(500) CHARACTER SET utf8 default NULL,
  actionTime datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='Table containing archived actions';

--
-- Table structure for table easyrec.actiontype
--

DROP TABLE IF EXISTS actiontype;
CREATE TABLE actiontype (
  tenantId int(11) unsigned NOT NULL,
  name varchar(50) NOT NULL,
  id int(11) NOT NULL,
  hasvalue bit(1) NOT NULL DEFAULT b'0',
  weight INT(11) NOT NULL DEFAULT 1,
  UNIQUE KEY tenantId (tenantId,name),
  UNIQUE KEY tenantId_2 (tenantId,id)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='Table containing actiontypes';


--
-- Table structure for table easyrec.aggregatetype
--

DROP TABLE IF EXISTS aggregatetype;
CREATE TABLE aggregatetype (
  tenantId int(11) unsigned NOT NULL,
  name varchar(50) NOT NULL,
  id int(11) NOT NULL,
  UNIQUE KEY tenantId (tenantId,name),
  UNIQUE KEY tenantId_2 (tenantId,id)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='Table containing aggregatetypes';


--
-- Table structure for table easyrec.assoctype
--

DROP TABLE IF EXISTS assoctype;
CREATE TABLE assoctype (
  tenantId INT(11) unsigned NOT NULL,
  name VARCHAR(50) NOT NULL,
  id INT(11) NOT NULL,
  visible BIT(1) NOT NULL DEFAULT b'1',
  UNIQUE KEY (tenantId, name),
  UNIQUE KEY (tenantId, id)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='Table containing assoctypes';


--
-- Table structure for table easyrec.authentication
--

DROP TABLE IF EXISTS authentication;
CREATE TABLE authentication (
  tenantId int(11) unsigned NOT NULL,
  domainURL varchar(250) NOT NULL default '',
  UNIQUE KEY unique_authentication (tenantId,domainURL)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='Table containing valid access domains for tenants';

--
-- Table structure for table easyrec.idmapping
--

DROP TABLE IF EXISTS idmapping;
CREATE TABLE idmapping (
  intId int(11) unsigned NOT NULL auto_increment,
  stringId varchar(250) CHARACTER SET utf8 NOT NULL default '0',
  PRIMARY KEY  (intId),
  UNIQUE KEY unique_mapping (stringId)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='Table containing id mapping';

--
-- Table structure for table easyrec.item
--

DROP TABLE IF EXISTS item;
CREATE TABLE item (
  id int(11) NOT NULL AUTO_INCREMENT,
  tenantId int(11) NOT NULL,
  itemid varchar(250) NOT NULL DEFAULT '',
  itemtype varchar(20) NOT NULL DEFAULT '',
  description varchar(500) DEFAULT NULL,
  profileData text,
  url varchar(500) DEFAULT NULL,
  imageurl varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  active tinyint(1) DEFAULT '1',
  creationdate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  changedate TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (id),
  UNIQUE KEY itemTripple (tenantId,itemid,itemtype)
) ENGINE=InnoDb DEFAULT CHARSET=utf8 COMMENT='Table containing items with profiles';

--
-- Table structure for table easyrec.itemassoc
--

DROP TABLE IF EXISTS itemassoc;
CREATE TABLE itemassoc (
  id int(11) unsigned NOT NULL auto_increment,
  tenantId int(11) NOT NULL default '0',
  itemFromId int(11) NOT NULL default '0',
  itemFromTypeId int(11) unsigned NOT NULL default '0',
  assocTypeId int(11) unsigned NOT NULL default '0',
  assocValue double NOT NULL default '0',
  itemToId int(11) NOT NULL default '0',
  itemToTypeId int(11) unsigned NOT NULL default '0',
  sourceTypeId int(11) NOT NULL default '0',
  sourceInfo varchar(250) default '0',
  viewTypeId int(11) unsigned NOT NULL default '0',
  active tinyint(1) NOT NULL default '1',
  changeDate datetime NOT NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY unique_itemassoc (tenantId,itemFromId,itemFromTypeId,itemToId,itemToTypeId,assocTypeId,sourceTypeId),
  KEY idFrom_assoc (itemFromId,itemFromTypeId,assocTypeId,tenantId),
  KEY recommender (itemFromId,itemFromTypeId,itemToTypeId,assocTypeId,tenantId,active)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='Table containing item associations';

--
-- Table structure for table easyrec.itemtype
--
DROP TABLE IF EXISTS itemtype;
CREATE TABLE itemtype (
  tenantId INT(11) unsigned NOT NULL,
  name VARCHAR(50) NOT NULL,
  id INT(11) NOT NULL,
  visible BIT(1) NOT NULL DEFAULT b'1',
  UNIQUE KEY (tenantId, name),
  UNIQUE KEY (tenantId, id)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='Table containing itemtypes';

--
-- Table structure for table easyrec.operator
--

DROP TABLE IF EXISTS operator;
CREATE TABLE operator (
  operatorid varchar(50) NOT NULL DEFAULT '',
  password varchar(250) NOT NULL DEFAULT '',
  firstname varchar(250) DEFAULT NULL,
  lastname varchar(250) DEFAULT NULL,
  email varchar(250) DEFAULT NULL,
  phone varchar(50) DEFAULT NULL,
  company varchar(250) DEFAULT NULL,
  address varchar(250) DEFAULT NULL,
  apikey varchar(32) DEFAULT NULL,
  ip varchar(39) DEFAULT NULL,
  active tinyint(1) NOT NULL DEFAULT '0',
  creationdate date DEFAULT NULL,
  accesslevel smallint(6) DEFAULT '0',
  lastlogin date DEFAULT NULL,
  logincount int(10) unsigned DEFAULT '0',
  token varchar(32) DEFAULT NULL,
  PRIMARY KEY (operatorid)
) ENGINE=InnoDb DEFAULT CHARSET=latin1;

--
-- Table structure for table easyrec.recommendation
--

DROP TABLE IF EXISTS recommendation;
CREATE TABLE recommendation (
  id int(11) unsigned NOT NULL auto_increment,
  tenantId int(11) NOT NULL,
  userId int(11) default NULL,
  queriedItemId int(11) default NULL,
  queriedItemTypeId int(11) default NULL,
  queriedAssocTypeId int(11) default NULL,
  relatedActionTypeId int(11) default NULL,
  recommendationStrategy varchar(50) default NULL,
  explanation varchar(255) default NULL,
  recommendationTime datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='Table containing the history of recommendations';

--
-- Table structure for table easyrec.recommendeditem
--

DROP TABLE IF EXISTS recommendeditem;
CREATE TABLE recommendeditem (
  id int(11) unsigned NOT NULL auto_increment,
  itemId int(11) NOT NULL,
  itemTypeId int(11) NOT NULL,
  recommendationId int(11) NOT NULL,
  predictionValue double NOT NULL default '0',
  itemAssocId int(11) default NULL,
  explanation varchar(255) default NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY unique_recommended_item (itemId,itemTypeId,recommendationId)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='Table containing all recommended items (ever)';

--
-- Table structure for table easyrec.sourcetype
--

DROP TABLE IF EXISTS sourcetype;
CREATE TABLE sourcetype (
  tenantId int(11) unsigned NOT NULL,
  name varchar(250) NOT NULL,
  id int(11) NOT NULL,
  UNIQUE KEY tenantId (tenantId,name),
  UNIQUE KEY tenantId_2 (tenantId,id)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='Table containing sourcetypes';

--
-- Table structure for table easyrec.tenant
--

DROP TABLE IF EXISTS tenant;
CREATE TABLE tenant (
  id int(11) unsigned NOT NULL,
  stringId varchar(100) NOT NULL,
  description varchar(250) DEFAULT NULL,
  ratingRangeMin int(11) unsigned DEFAULT NULL,
  ratingRangeMax int(11) unsigned DEFAULT NULL,
  ratingRangeNeutral double DEFAULT NULL,
  active tinyint(1) NOT NULL DEFAULT '1',
  operatorid varchar(250) DEFAULT NULL,
  url varchar(250) DEFAULT NULL,
  creationdate datetime DEFAULT NULL,
  tenantConfig mediumblob,
  tenantStatistic mediumblob,
  PRIMARY KEY (id),
  UNIQUE KEY stringId (stringId,operatorid)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='Table containing tenants';

--
-- Table structure for table easyrec.viewtype
--

DROP TABLE IF EXISTS viewtype;
CREATE TABLE viewtype (
  tenantId int(11) unsigned NOT NULL,
  name varchar(50) NOT NULL,
  id int(11) NOT NULL,
  UNIQUE KEY tenantId (tenantId,name),
  UNIQUE KEY tenantId_2 (tenantId,id)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='Table containing viewtypes';

DROP TABLE IF EXISTS backtracking;
CREATE TABLE backtracking (
	userId INT(11) UNSIGNED NOT NULL DEFAULT '0',
	tenantId INT(11) UNSIGNED NOT NULL,
	itemFromId INT(11) UNSIGNED NOT NULL,
        itemFromTypeId INT(11) UNSIGNED NOT NULL,
	itemToId INT(11) UNSIGNED NOT NULL,
        itemToTypeId INT(11) UNSIGNED NOT NULL,
	recType INT(11) UNSIGNED NOT NULL,
	actionTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	INDEX assoc (tenantId, itemFromId, itemFromTypeId, recType, itemToId, itemToTypeId)
 ) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='Backtracking information about recommendations';

DROP TABLE IF EXISTS plugin;
CREATE TABLE plugin (
  id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  displayname VARCHAR(150) DEFAULT NULL,
  pluginid VARCHAR(500) NOT NULL,
  version VARCHAR(50) NOT NULL,
  origfilename VARCHAR(150) DEFAULT '',
  state VARCHAR(50) NOT NULL,
  file LONGBLOB,
  changeDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY pluginId (pluginid, version)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='Table containing plugins';

-- Add a table for versioning easyrec and set actual version number
DROP TABLE IF EXISTS easyrec;
CREATE TABLE easyrec (
  version float(9,3) DEFAULT NULL
) ENGINE=InnoDb DEFAULT CHARSET=latin1;

INSERT INTO easyrec (version) VALUES (1.0);

DROP TABLE IF EXISTS plugin_log;
CREATE TABLE plugin_log (
  id int(11) NOT NULL AUTO_INCREMENT,
  tenantId int(11) unsigned NOT NULL,
  pluginId varchar(500) NOT NULL,
  pluginVersion varchar(50) NOT NULL,
--   need to add default value or else a on update set to today is auto inserted by mysql
  startDate timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  endDate timestamp NULL DEFAULT NULL,
  assocTypeId int(11) NOT NULL,
  configuration text NOT NULL,
  statistics text,
  PRIMARY KEY (id),
--   need to use subset of pluginId otherwise maximum key length would be exhausted
  UNIQUE unique_plugin_log (tenantId,pluginId(255),pluginVersion,assocTypeId,startDate),
  KEY idx_tenantId (tenantId),
  KEY idx_endDate (endDate)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT = 'store runs of plugins';

DROP TABLE IF EXISTS plugin_configuration;
CREATE TABLE plugin_configuration (
  id int(11) NOT NULL AUTO_INCREMENT,
  tenantId int(11) NOT NULL,
  assocTypeId int(11) NOT NULL,
  pluginId varchar(500) NOT NULL,
  pluginVersion varchar(50) NOT NULL,
  name varchar(255) NOT NULL,
  configuration text NOT NULL,
  active bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (id),
  UNIQUE KEY unique_configuration (tenantId,assocTypeId,pluginId(250),pluginVersion,name(250)),
  KEY idx_tenantAssoc (tenantId,assocTypeId)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 COMMENT='store plugin configurations for tenants';
