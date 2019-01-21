/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - RondaPonto.as.
 */

package valueObjects
{
import com.adobe.fiber.services.IFiberManagingService;
import com.adobe.fiber.valueobjects.IValueObject;
import flash.events.EventDispatcher;
import mx.collections.ArrayCollection;
import mx.events.PropertyChangeEvent;
import valueObjects.Ponto;
import valueObjects.Ronda;

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_RondaPonto extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
        try
        {
            if (flash.net.getClassByAlias("br.com.controleronda.bean.RondaPonto") == null)
            {
                flash.net.registerClassAlias("br.com.controleronda.bean.RondaPonto", cz);
            }
        }
        catch (e:Error)
        {
            flash.net.registerClassAlias("br.com.controleronda.bean.RondaPonto", cz);
        }
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
        valueObjects.Ponto.initRemoteClassAliasSingleChild();
        valueObjects.Ronda.initRemoteClassAliasSingleChild();
    }

    model_internal var _dminternal_model : _RondaPontoEntityMetadata;
    model_internal var _changedObjects:mx.collections.ArrayCollection = new ArrayCollection();

    public function getChangedObjects() : Array
    {
        _changedObjects.addItemAt(this,0);
        return _changedObjects.source;
    }

    public function clearChangedObjects() : void
    {
        _changedObjects.removeAll();
    }

    /**
     * properties
     */
    private var _internal_id : int;
    private var _internal_ponto : valueObjects.Ponto;
    private var _internal_horaInicialPonto : String;
    private var _internal_horaFinalPonto : String;
    private var _internal_dataCadastro : Date;
    private var _internal_ronda : valueObjects.Ronda;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_RondaPonto()
    {
        _model = new _RondaPontoEntityMetadata(this);

        // Bind to own data or source properties for cache invalidation triggering

    }

    /**
     * data/source property getters
     */

    [Bindable(event="propertyChange")]
    public function get id() : int
    {
        return _internal_id;
    }

    [Bindable(event="propertyChange")]
    public function get ponto() : valueObjects.Ponto
    {
        return _internal_ponto;
    }

    [Bindable(event="propertyChange")]
    public function get horaInicialPonto() : String
    {
        return _internal_horaInicialPonto;
    }

    [Bindable(event="propertyChange")]
    public function get horaFinalPonto() : String
    {
        return _internal_horaFinalPonto;
    }

    [Bindable(event="propertyChange")]
    public function get dataCadastro() : Date
    {
        return _internal_dataCadastro;
    }

    [Bindable(event="propertyChange")]
    public function get ronda() : valueObjects.Ronda
    {
        return _internal_ronda;
    }

    public function clearAssociations() : void
    {
    }

    /**
     * data/source property setters
     */

    public function set id(value:int) : void
    {
        var oldValue:int = _internal_id;
        if (oldValue !== value)
        {
            _internal_id = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "id", oldValue, _internal_id));
        }
    }

    public function set ponto(value:valueObjects.Ponto) : void
    {
        var oldValue:valueObjects.Ponto = _internal_ponto;
        if (oldValue !== value)
        {
            _internal_ponto = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "ponto", oldValue, _internal_ponto));
        }
    }

    public function set horaInicialPonto(value:String) : void
    {
        var oldValue:String = _internal_horaInicialPonto;
        if (oldValue !== value)
        {
            _internal_horaInicialPonto = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "horaInicialPonto", oldValue, _internal_horaInicialPonto));
        }
    }

    public function set horaFinalPonto(value:String) : void
    {
        var oldValue:String = _internal_horaFinalPonto;
        if (oldValue !== value)
        {
            _internal_horaFinalPonto = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "horaFinalPonto", oldValue, _internal_horaFinalPonto));
        }
    }

    public function set dataCadastro(value:Date) : void
    {
        var oldValue:Date = _internal_dataCadastro;
        if (oldValue !== value)
        {
            _internal_dataCadastro = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "dataCadastro", oldValue, _internal_dataCadastro));
        }
    }

    public function set ronda(value:valueObjects.Ronda) : void
    {
        var oldValue:valueObjects.Ronda = _internal_ronda;
        if (oldValue !== value)
        {
            _internal_ronda = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "ronda", oldValue, _internal_ronda));
        }
    }

    /**
     * Data/source property setter listeners
     *
     * Each data property whose value affects other properties or the validity of the entity
     * needs to invalidate all previously calculated artifacts. These include:
     *  - any derived properties or constraints that reference the given data property.
     *  - any availability guards (variant expressions) that reference the given data property.
     *  - any style validations, message tokens or guards that reference the given data property.
     *  - the validity of the property (and the containing entity) if the given data property has a length restriction.
     *  - the validity of the property (and the containing entity) if the given data property is required.
     */


    /**
     * valid related derived properties
     */
    model_internal var _isValid : Boolean;
    model_internal var _invalidConstraints:Array = new Array();
    model_internal var _validationFailureMessages:Array = new Array();

    /**
     * derived property calculators
     */

    /**
     * isValid calculator
     */
    model_internal function calculateIsValid():Boolean
    {
        var violatedConsts:Array = new Array();
        var validationFailureMessages:Array = new Array();

        var propertyValidity:Boolean = true;

        model_internal::_cacheInitialized_isValid = true;
        model_internal::invalidConstraints_der = violatedConsts;
        model_internal::validationFailureMessages_der = validationFailureMessages;
        return violatedConsts.length == 0 && propertyValidity;
    }

    /**
     * derived property setters
     */

    model_internal function set isValid_der(value:Boolean) : void
    {
        var oldValue:Boolean = model_internal::_isValid;
        if (oldValue !== value)
        {
            model_internal::_isValid = value;
            _model.model_internal::fireChangeEvent("isValid", oldValue, model_internal::_isValid);
        }
    }

    /**
     * derived property getters
     */

    [Transient]
    [Bindable(event="propertyChange")]
    public function get _model() : _RondaPontoEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _RondaPontoEntityMetadata) : void
    {
        var oldValue : _RondaPontoEntityMetadata = model_internal::_dminternal_model;
        if (oldValue !== value)
        {
            model_internal::_dminternal_model = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "_model", oldValue, model_internal::_dminternal_model));
        }
    }

    /**
     * methods
     */


    /**
     *  services
     */
    private var _managingService:com.adobe.fiber.services.IFiberManagingService;

    public function set managingService(managingService:com.adobe.fiber.services.IFiberManagingService):void
    {
        _managingService = managingService;
    }

    model_internal function set invalidConstraints_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_invalidConstraints;
        // avoid firing the event when old and new value are different empty arrays
        if (oldValue !== value && (oldValue.length > 0 || value.length > 0))
        {
            model_internal::_invalidConstraints = value;
            _model.model_internal::fireChangeEvent("invalidConstraints", oldValue, model_internal::_invalidConstraints);
        }
    }

    model_internal function set validationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_validationFailureMessages;
        // avoid firing the event when old and new value are different empty arrays
        if (oldValue !== value && (oldValue.length > 0 || value.length > 0))
        {
            model_internal::_validationFailureMessages = value;
            _model.model_internal::fireChangeEvent("validationFailureMessages", oldValue, model_internal::_validationFailureMessages);
        }
    }


}

}
