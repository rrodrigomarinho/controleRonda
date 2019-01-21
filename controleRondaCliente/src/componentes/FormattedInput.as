
package componentes { 
	
	import flash.events.Event;
	import flash.events.FocusEvent;
	
	import mx.controls.TextInput;
	import mx.utils.StringUtil;

	/**
	 * Autor: Fabio da Silva
	 *
	 * Classe para formatação de valores.  
	 * Características:
	 * 		- Preenche com zeros a esquerda.
	 *  	- Depende de NumericInput.
	 * 		- A alteração da propriedade text via código não provoca a sua formatação
	 * 		- IMPORTANTE: Por conveniência foi criada a propriedade value para ser utilizada no lugar de text.
	 * 			Setar text no MXML formata o valor passado, mas o mesmo não acontece qd setado via código, 
	 * 			por isso, usar value. 
	 */ 
	 
	[Event(name="valueChange", type="flash.events.Event")] 
	
	public class FormattedInput extends TextInput { 

		private var _inputFormat:String;
		private var inputFormatChange:Boolean;
		private var ni:NumericInput;
		private var tokens:String;
		private var _value:Object;
		private var valueChange:Boolean;
		private var zeros:String;
		
		public static const INPUT_FORMAT_CEP:String = "#####-###";
		public static const INPUT_FORMAT_CNPJ:String = "##.###.###/####-##";
		public static const INPUT_FORMAT_CPF:String = "###.###.###-##";
		public static const INPUT_FORMAT_FONE:String = "####-####";
		public static const INPUT_FORMAT_FONE_DDD:String = "(##) #####-####";
		public static const INPUT_FORMAT_FONE_INTERNATIONAL:String = "+## (##) ####-####";

		public static const VALUE_CHANGE:String = "valueChange";
		
		public function FormattedInput() {
			super();
			
			this.ni = new NumericInput();
			ni.precision = 0;
			
			this.addEventListener(Event.CHANGE, this.formatHandler, false, 0, true);
			this.addEventListener(FocusEvent.FOCUS_IN, this.setCursor, false, 0, true);

	        this.inputFormat = FormattedInput.INPUT_FORMAT_CEP;
	        this.restrict = "0-9";
	        this.setStyle("textAlign", "left");
	        this.value = 0;
		}

		override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void {
			super.updateDisplayList(unscaledWidth, unscaledHeight);
			
			if (this.inputFormatChange) {
				this.inputFormatChange = false;
				
				this.tokens = ""
				this.zeros = "";
				var pos:uint;

				for (var i:uint; i < this.inputFormat.length; i++) {
					var char:String = this.inputFormat.charAt(i);

					if (char == "#") {
						this.zeros += "0";
						this.tokens += "{" + pos++ + "}";
					} else {
						this.tokens += char;
					}
				}
				
				if (!this.valueChange) this.value = this.text;
			}
			
			if (this.valueChange) {
				this.valueChange = false;
				
				var retorno:String = this.zeros + this.ni.returnDigits(this.value);
				retorno = retorno.substring(retorno.length - this.zeros.length);

				var arr:Array = new Array();
				for (var x:uint; x < retorno.length; x++) {
					arr.push(retorno.charAt(x));
				}

				this.text = StringUtil.substitute(this.tokens, arr);
				
				this.setCursor(null);
			}
		}

///////////////////////////////////////////////// Propriedades ////////////////////////////////////////////////		

		public function get inputFormat():String {
			return this._inputFormat;
		}
		
		/**
		 * Seta o formato a ser utilizado.
		 * Default: INPUT_FORMAT_CEP
		 */
		[Inspectable(defaultValue="#####-###")]
		public function set inputFormat(value:String):void {
			if (this.inputFormat != value) {
				this._inputFormat = value;
				this.inputFormatChange = true;
				this.invalidateDisplayList();
			}
		}
	
		public function get value():Object {
			return this._value;
		}
		
		/** Versão Number de text, desconsiderando os caractes não numéricos. Aqui depende de NumericInput */
		[Bindable(event="valueChange")]
		public function set value(value:Object):void {
			this._value = this.ni.toNumber(value);
			this.valueChange = true;
			this.invalidateDisplayList();
			this.dispatchEvent(new Event(FormattedInput.VALUE_CHANGE));
		}
/////////////////////////////////////////////////// Métodos ///////////////////////////////////////////////////		
		
		private function formatHandler(event:Event):void {
			// Se não value fica com um caracter a mais do q text
			this.value = this.text.substring(this.text.length - this.inputFormat.length);
			
			this.setCursor(null);
		}
		
	    private function setCursor(event:FocusEvent):void {
	        this.setSelection(this.length, this.length);
	    }
	}
}