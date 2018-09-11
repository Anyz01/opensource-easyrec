package org.easyrec.vocabulary;

import org.easyrec.model.core.web.ErrorMessage;
import org.easyrec.model.core.web.Message;
import org.easyrec.model.core.web.Operator;
import org.easyrec.model.core.web.SuccessMessage;

/**
 * Contains messages returned by various services.
 *
 * @author pmarschik
 * @author fsalcher
 */
public class MSG {

    // action
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";

    // operator messages
    public static final Message OPERATOR_DOES_NOT_EXISTS = new ErrorMessage(
            100, "Account does not exist!");
    public static final Message OPERATOR_EXISTS = new ErrorMessage(
            101, "User Name exists!");
    public static final Message OPERATOR_ALREADY_EXISTS = new ErrorMessage(
            102, "This User Name already exists!");
    public static final Message OPERATOR_ACTIVATED = new SuccessMessage(
            103, "Account successfully activated!");
    public static final Message OPERATOR_ACTIVATION_FAILED = new ErrorMessage(
            104, "Activation of Account failed!");
    public static final Message OPERATOR_SIGNED_IN = new ErrorMessage(
            105, "User is signed in!");
    public static final Message OPERATOR_SIGNED_IN_FAILED = new ErrorMessage(
            106, "Login failed! Please check your login combination.");
    public static final Message OPERATOR_SIGNED_OUT = new ErrorMessage(
            107, "User signed out!");
    public static final Message OPERATOR_DEACTIVATED = new ErrorMessage(
            108, "Account deactivated!");
    public static final Message OPERATOR_DEACTIVATION_FAILED = new ErrorMessage(
            109, "Deactivation of Account failed!");
    public static final Message OPERATOR_REGISTERED = new SuccessMessage(
            110, "Account Registered!");
    public static final Message OPERATOR_UPDATED = new SuccessMessage(
            111, "Account Updated!");
    public static final Message OPERATOR_NOT_ACTIVATED = new ErrorMessage(
            112, "This Account is not activated!");
    public static final Message ADMINISTRATOR_SIGNED_IN = new ErrorMessage(
            113, "Administrator is signed in!");
    public static final Message OPERATOR_EMPTY = new ErrorMessage(
            120, "No User Name specified!");
    public static final Message OPERATOR_PASSWORD_TO_SHORT = new ErrorMessage(
            121, "The Password must be at least " + Operator.MIN_PASSWORD_LENGTH + " characters long!");
    public static final Message OPERATOR_CONTAINS_SPACE = new ErrorMessage(
            122, "The User Name must not contain any spaces");
    public static final Message OPERATOR_FIRSTNAME_EMPTY = new ErrorMessage(
            125, "A First Name is required!");
    public static final Message OPERATOR_LASTNAME_EMPTY = new ErrorMessage(
            126, "A Last Name is required!");
    public static final Message OPERATOR_EMAIL_INVALID = new ErrorMessage(
            127, "A valid Email Address is required!");
    public static final Message OPERATOR_PASSWORD_MATCH = new ErrorMessage(
            130, "The Password must match with the confirmed Password!");
    public static final Message OPERATOR_TOS_NOT_ACCEPTED = new ErrorMessage(
            131, "The Terms of Service must be accepted!");
    public static final Message OPERATOR_WRONG_PASSWORD = new ErrorMessage(
            132, "The old password is wrong!");
    public static final Message OPERATOR_REMOVED = new SuccessMessage(
            150, "Operator successfully removed!");
    public static final Message OPERATOR_REMOVE_FAILED = new ErrorMessage(
            151, "Removing Operator failed!");
    public static final Message OPERATOR_PASSWORD_UPDATED = new SuccessMessage(
            152, "Password successfully updated!");
    public static final Message OPERATOR_PASSWORD_UPDATED_FAILED = new ErrorMessage(
            153, "Password update failed!");

    // tenant messages
    public static final Message TENANT_REGISTERED = new ErrorMessage(
            200, "Tenant successfully registered!");
    public static final Message TENANT_REMOVED = new ErrorMessage(
            201, "Tenant successfully removed!");
    public static final Message NO_VALID_OPERATOR = new ErrorMessage(
            202, "Please provide an existing operator id!");
    public static final Message NO_VALID_URL = new ErrorMessage(
            203, "Please provide a valid public URL!");
    public static final Message REMOTE_TENANT_EXISTS = new ErrorMessage(
            204, "A tenant with this name already exists. Please provide a different name!");
    public static final Message TENANT_EXISTS = new ErrorMessage(
            205, "A tenant with this name was removed once and can not be recreated. Please provide a different name!");
    public static final Message TENANT_NOT_EXISTS = new ErrorMessage(
            206, "Please provide an existing tenant id!");
    public static final Message INVALID_TENANTID = new ErrorMessage(
            207, "Please provide a tenant id containing only letters [a-z,A-Z], numbers [0-9] or underscore _!");
    public static final Message TENANT_UPDATED = new ErrorMessage(
            208, "Tenant URL updated!");
    public static final Message NOT_SIGNED_IN = new ErrorMessage(
            209, "Please sign in first!");
    public static final Message TENANT_AUTHENTICATION_FAILED = new ErrorMessage(
            210, "Authentication with Recommender failed!");
    public static final Message TENANT_REMOVE_FAILED = new ErrorMessage(
            211, "Tenant could not be removed!");
    public static final Message TENANT_RESET = new ErrorMessage(
            212, "Tenant successfully reset!");
    public static final Message TENANT_RESET_FAILED = new ErrorMessage(
            213, "Resetting Tenant failed!");
    public static final Message TENANT_WRONG_TENANT = new ErrorMessage(
            214, "Tenant does not exist!");
    public static final Message TENANT_WRONG_TENANT_APIKEY = new ErrorMessage(
            299, "Wrong APIKey/Tenant combination!");

    // item messages
    public static final Message ITEM_NOT_EXISTS = new ErrorMessage(
            300, "Item does not exist!");
    public static final Message ITEM_NO_ID = new ErrorMessage(
            301, "Item id required!");
    public static final Message ITEM_NO_TYPE = new ErrorMessage(
            302, "Item requires a type!");
    public static final Message ITEM_NO_DESCRIPTION = new ErrorMessage(
            303, "Item requires a description!");
    public static final Message ITEM_NO_URL = new ErrorMessage(
            304, "Item requires a URL!");
    public static final Message ITEM_INVALID_RATING_VALUE = new ErrorMessage(
            305, "Rating Value must be a valid Integer in the range from 1 to 10!");
    public static final Message ITEM_NOT_ACTIVE = new ErrorMessage(
            306, "The requested item is marked as inactive!");
    public static final Message ITEM_UPDATE_FAILED = new ErrorMessage(
            307, "Item update failed!");
    public static final Message ITEM_NO_ACTIVE = new ErrorMessage(
            308, "Missing parameter: active (true|false)");
    public static final Message ITEM_INVALID_ACTION_VALUE = new ErrorMessage(
            305, "Action Value must be a valid Integer!");

    // profile messages
    public static final Message PROFILE_SAVED = new SuccessMessage(
            310, "Profile successfully saved!");
    public static final Message PROFILE_NOT_SAVED = new ErrorMessage(
            311, "An error occurred while saving the profile!");
    public static final Message PROFILE_DELETED = new SuccessMessage(
            312, "Profile successfully deleted!");
    public static final Message PROFILE_NOT_DELETED = new ErrorMessage(
            313, "An error occurred while deleting the profile!");
    public static final Message PROFILE_NOT_LOADED = new ErrorMessage(
            314, "An error occurred while loading the profile!");
    public static final Message PROFILE_FIELD_SAVED = new SuccessMessage(
            315, "Profile field successfully saved!");
    public static final Message PROFILE_FIELD_NOT_SAVED = new ErrorMessage(
            315, "An error occurred while saving a profile field!");
    public static final Message PROFILE_FIELD_NOT_LOADED = new ErrorMessage(
            316, "An error occurred while loading the profile field!");
    public static final Message PROFILE_FIELD_DELETED = new SuccessMessage(
            317, "Profile field successfully deleted!");
    public static final Message PROFILE_FIELD_NOT_DELETED = new ErrorMessage(
            318, "An error occurred while deleting the profile field!");
    public static final Message PROFILE_NO_API_KEY = new ErrorMessage(
            330, "Necessary APIKey has not been provided!");
    public static final Message PROFILE_NO_TENANT_ID = new ErrorMessage(
            331, "Necessary tenantID has not been provided!");
    public static final Message PROFILE_NO_ITEM_ID = new ErrorMessage(
            332, "Necessary itemID has not been provided!");
    public static final Message PROFILE_NO_ITEM_TYPE = new ErrorMessage(
            333, "Necessary itemType has not been provided!");
    public static final Message PROFILE_NO_PROFILE_PROVIDED = new ErrorMessage(
            334, "Necessary profile has not been provided!");
    public static final Message PROFILE_NO_FIELD_PROVIDED = new ErrorMessage(
            335, "Necessary profile field has not been provided!");
    public static final Message PROFILE_NO_VALUE_PROVIDED = new ErrorMessage(
            336, "Necessary profile field value has not been provided!");
    public static final Message PROFILE_MULTIPLE_FIELDS_WITH_SAME_NAME = new ErrorMessage(
            337, "XPath points to multiple profile fields!");
    public static final Message PROFILE_NO_KEY_PROVIDED = new ErrorMessage(
            338, "Necessary profile field key has not been provided!");

    // user/login messages
    public static final Message USER_NO_SESSION_ID = new ErrorMessage(
            401, "A session id required!");
    public static final Message USER_NO_ID = new ErrorMessage(
            402, "No user id given!");

    // itemassoc messages
    public static final Message ITEM_FROM_ID_DOES_NOT_EXIST = new ErrorMessage(
            500, "ItemFrom Id does not exist!");
    public static final Message ITEM_TO_ID_DOES_NOT_EXIST = new ErrorMessage(
            501, "ItemTo Id does not exist!");
    public static final Message ASSOC_TYPE_DOES_NOT_EXIST = new ErrorMessage(
            502, "Association Type does not exist!");
    public static final Message INVALID_ASSOC_VALUE = new ErrorMessage(
            503, "Invalid Assoc Value (use a decimal value between 0-100)!");
    public static final Message ITEMFROM_EQUAL_ITEMTO = new ErrorMessage(
            504, "ItemFrom Id must differ from ItemTo Id!");
    public static final Message ASSOC_TYPE_NEEDED = new ErrorMessage(
            505, "Association Type (assoctype) needed!");
    public static final Message REC_TYPE_NEEDED = new ErrorMessage(
            505, "Recommendation Type (rectype) needed! Valid options are: RECS_FOR_USER, RANKING, CLUSTER, HISTORY or any valid assocType");

    // plugin messages
    public static final Message PLUGIN_INSTALLED = new SuccessMessage(
            600, "Plugin successfully installed!");
    public static final Message PLUGIN_NOT_INSTALLED = new ErrorMessage(
            601, "Plugin could not be installed!");
    public static final Message PLUGIN_PARAM_INVALID = new ErrorMessage(
            602, "Plugin parameter invalid!");
    public static final Message PLUGIN_NOT_ACTIVE = new ErrorMessage(
            603, "Plugins disabled!");
    public static final Message PLUGIN_STARTED = new SuccessMessage(
            604, "Plugins successfully started!");

    // cluster messages
    // error messages
    public static final Message CLUSTER_NO_ID = new ErrorMessage(
            700, "Cluster id required!");
    public static final Message CLUSTER_NOT_EXISTS = new ErrorMessage(
            701, "The provided cluster does not exist!");

    // success messages
    public static final Message CLUSTER_SUCCESSFULLY_CREATED = new SuccessMessage(
            750, "Cluster successfully created!");

    // miscellaneous messages
    public static final Message XSL_NOT_EXISTS = new ErrorMessage(
            900, "XSL does not exist!");
    public static final Message GENERATOR_FINISHED_SUCCESS = new SuccessMessage(
            901, "Generator finished successfully.");
    public static final Message GENERATOR_FINISHED_FAIL = new ErrorMessage(
            902, "Generator could not finish successfully!");
    public static final Message PLUGIN_CONFIG_CHANGED = new SuccessMessage(
            903, "Plugin Config changed successfully.");
    public static final Message OPERATION_SUCCESSFUL = new SuccessMessage(
            904, "Operation successful!");
    public static final Message SPECIAL_CHARACTERS = new ErrorMessage(
            905, "Please don't use special characters like: &gt; , &lt; , &#34;,' or %");
    public static final Message DATE_PARSE = new ErrorMessage(
            906, "Could not parse date!");
    public static final Message ARCHIVE_CONFIG_CHANGED = new SuccessMessage(
            907, "Archive Config changed successfully.");
    public static final Message SESSIONMAPPING_CONFIG_CHANGED = new SuccessMessage(
            908, "Session Mapping Config changed successfully.");
    public static final Message MAXIMUM_ACTIONS_EXCEEDED = new ErrorMessage(
            909, "Maximum limit of actions per month exceeded!");
    public static final Message WRONG_TOKEN = new ErrorMessage(
            910, "The provided token is not valid!");
    public static final Message GENERATOR_ALREADY_EXECUTING = new ErrorMessage(
            911, "Cannot start the generator! A generator is already running for a tenant!");
    public static final Message OPERATION_FAILED = new ErrorMessage(
            912, "Operation failed!");
    public static final Message MISSING_ACTIONTYPE = new ErrorMessage(
            913, "actionype required!");
    public static final Message INVALID_ACTIONTYPE = new ErrorMessage(
            914, "actionype not valid for this tenant!");
    public static final Message MISSING_ACTION_VALUE = new ErrorMessage(
            915, "The given actionType requires a valid actionValue!");
    public static final Message INVALID_JSON = new ErrorMessage(
            916, "The given actioninfo/profile is not a valid JSON!");
    public static final Message ACTION_INFO_TOO_LONG = new ErrorMessage(
            917, "The given actioninfo is too long (max. 500 chars)!");
    public static final Message PLUGIN_START_IN_FRONTEND_MODE = new ErrorMessage(
            920, "Could not start plugins because easyrec is in frontend mode!");
    public static final Message PLUGIN_START_ALREADY_RUNNING = new ErrorMessage(
            921, "Could not start plugins because some plugins are already running!");
    public static final Message PLUGIN_START_NO_TENANT_CONFIG = new ErrorMessage(
            922, "Could not load tenant configuration!");
    public static final Message VALID_URL = new ErrorMessage(
            999, "This is a valid URL!");

    private MSG() { assert false; }
}
